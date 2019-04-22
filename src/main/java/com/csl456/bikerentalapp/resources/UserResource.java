package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.Person;
import com.csl456.bikerentalapp.core.User;
import com.csl456.bikerentalapp.core.UserRole;
import com.csl456.bikerentalapp.db.PersonDAO;
import com.csl456.bikerentalapp.db.SessionDAO;
import com.csl456.bikerentalapp.db.UserDAO;
import com.csl456.bikerentalapp.filter.LoggedIn;
import com.csl456.bikerentalapp.filter.RolesAllowed;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.dropwizard.hibernate.UnitOfWork;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	private final UserDAO userDAO;
	private final SessionDAO sessionDAO;
	private final PersonDAO personDAO;

	private static final Random random = new Random();

	private LoadingCache<String, String> otpCache = CacheBuilder
			.newBuilder()
			.expireAfterWrite(10, TimeUnit.SECONDS)
			.build(new CacheLoader<String, String>() {
				@Override
				public String load(String username) {
					return generateOTP();
				}
			});

	public UserResource(UserDAO userDAO, SessionDAO sessionDAO, PersonDAO personDAO) {
		this.userDAO = userDAO;
		this.sessionDAO = sessionDAO;
		this.personDAO = personDAO;
	}

	@POST
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public User register(User user) {
		return userDAO.create(user);
	}

	@GET
	@UnitOfWork
	@RolesAllowed(UserRole.ADMIN)
	public List<User> listUsers() {
		return userDAO.findAll();
	}

	@POST
	@Path("changePass")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@LoggedIn
	public void changePassword(@FormParam("username") String username, @FormParam("password") String password, @FormParam("newPassword") String newPassword) throws Exception {
		if (userDAO.findUsersByUsernameAndPassword(username, password) == null) {
			throw new Exception("Username or Password Wrong");
		}
		sessionDAO.removeAll(username);
		userDAO.changePassword(username, newPassword);
	}

	@POST
	@Path("forgotPass/{username}")
	@UnitOfWork
	public void forgotPass(@PathParam("username") String username) throws Exception {
		User user = userDAO.findByUserName(username);
		Person person = personDAO.findById(user.getPersonId()).get();
		String otp = otpCache.get(username);
		Email email = EmailBuilder.startingBlank()
				.from("Bike Rental Admin", "bikerentaladmin@iitrpr.ac.in")
				.to(person.getName(), person.getEmail())
				.withSubject("Your Forgot Password OTP")
				.withPlainText("Your OTP is " + otp)
				.buildEmail();
		MailerBuilder
				.buildMailer()
				.sendMail(email);
	}

	private String generateOTP() {
		StringBuilder otp = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			otp.append(random.nextInt(10));
		}
		return otp.toString();
	}

	@POST
	@Path("validateOTP/{username}")
	@UnitOfWork
	public User validateOTP(@PathParam("username") String username, @FormParam("otp") String otp) throws ExecutionException {
		if (otpCache.get(username).equals(otp)) {
			return userDAO.findByUserName(username);
		} else throw new WebApplicationException("OTP does not match");
	}
}
