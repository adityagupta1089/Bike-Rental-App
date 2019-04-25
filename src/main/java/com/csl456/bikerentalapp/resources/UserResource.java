package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.*;
import com.csl456.bikerentalapp.db.*;
import com.csl456.bikerentalapp.filter.*;
import com.google.common.cache.*;
import io.dropwizard.hibernate.*;
import org.simplejavamail.email.*;
import org.simplejavamail.mailer.*;

import javax.annotation.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;
import java.util.concurrent.*;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final Random random = new Random();

    private final UserDAO           userDAO;
    private final SessionDAO        sessionDAO;
    private final PersonDAO         personDAO;
    private final SMTPServerDetails smtpServerDetails;

    private static final LoadingCache<String, String> otpCache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(
                        @Nonnull String username) { return generateOTP();}
            });

    private static final Cache<String, User> userCache = CacheBuilder
            .newBuilder()
            .build();

    public UserResource(UserDAO userDAO, SessionDAO sessionDAO,
            PersonDAO personDAO, SMTPServerDetails smtpServerDetails) {
        this.userDAO           = userDAO;
        this.sessionDAO        = sessionDAO;
        this.personDAO         = personDAO;
        this.smtpServerDetails = smtpServerDetails;
    }

    private static String generateOTP() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 8; i++) otp.append(random.nextInt(10));
        return otp.toString();
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(User user) throws ExecutionException {
        Person person = personDAO.findById(user.getPersonId()).get();
        String otp    = otpCache.get(user.getUsername());
        Email email = EmailBuilder
                .startingBlank()
                .from("Bike Rental Admin", "noreply@bikerentalapp.com")
                .to(person.getName(), person.getEmail())
                .withSubject("Your Registration OTP")
                .withPlainText("Your OTP is " + otp)
                .buildEmail();
        MailerBuilder.withSMTPServer(
                smtpServerDetails.getHost(),
                smtpServerDetails.getPort(),
                smtpServerDetails.getUsername(),
                smtpServerDetails.getPassword()
        ).buildMailer().sendMail(email);
        userCache.put(user.getUsername(), user);
    }

    @GET
    @UnitOfWork
    @RolesAllowed(UserRole.ADMIN)
    public List<User> listUsers() { return userDAO.findAll();}

    @POST
    @Path("changePass")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @LoggedIn
    public void changePassword(@FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("newPassword") String newPassword) throws Exception {
        if (userDAO.findUsersByUsernameAndPassword(username, password)
                == null) { throw new Exception("Username or Password Wrong"); }
        sessionDAO.removeAll(username);
        userDAO.changePassword(username, newPassword);
    }

    @POST
    @Path("forgotPass/{username}")
    @UnitOfWork
    public void forgotPass(@PathParam("username") String username) throws
            Exception {
        User   user   = userDAO.findByUserName(username);
        Person person = personDAO.findById(user.getPersonId()).get();
        String otp    = otpCache.get(username);
        Email email = EmailBuilder
                .startingBlank()
                .from("Bike Rental Admin", "noreply@bikerentalapp.com")
                .to(person.getName(), person.getEmail())
                .withSubject("Your Forgot Password OTP")
                .withPlainText("Your OTP is " + otp)
                .buildEmail();
        MailerBuilder.withSMTPServer(
                smtpServerDetails.getHost(),
                smtpServerDetails.getPort(),
                smtpServerDetails.getUsername(),
                smtpServerDetails.getPassword()
        ).buildMailer().sendMail(email);
    }

    @POST
    @Path("validateRegistrationOTP/{username}")
    @UnitOfWork
    public User validateRegistrationOTP(@PathParam("username") String username,
            @FormParam("otp") String otp) throws ExecutionException {
        if (otpCache.get(username).equals(otp)) {
            return userDAO.create(userCache.getIfPresent(username));
        } else { throw new WebApplicationException("OTP does not match"); }
    }

    @POST
    @Path("validateForgotPassOTP/{username}")
    @UnitOfWork
    public User validateForgotPassOTP(@PathParam("username") String username,
            @FormParam("otp") String otp) throws ExecutionException {
        if (otpCache.get(username).equals(otp)) {
            return userDAO.findByUserName(username);
        } else { throw new WebApplicationException("OTP does not match"); }
    }

}
