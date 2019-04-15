package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.Person;
import com.csl456.bikerentalapp.core.User;
import com.csl456.bikerentalapp.db.SessionDAO;
import com.csl456.bikerentalapp.db.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	private final UserDAO userDAO;

	private final SessionDAO sessionDAO;

	public UserResource(UserDAO userDAO, SessionDAO sessionDAO) {
		this.userDAO = userDAO;
		this.sessionDAO = sessionDAO;
	}

	@POST
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	public User register(User user) {
		return userDAO.create(user);
	}

	@GET
	@UnitOfWork
	public List<User> listPerson() {
		return userDAO.findAll();
	}

	@POST
	@Path("changePass")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public User changePassword(User user, @FormParam("newPassword") String newPassword) throws Exception {
		if (userDAO.findUsersByUsernameAndPassword(user.getUsername(), user.getPassword()) == null) {
			throw new Exception("Username or Password Wrong");
		}
		sessionDAO.removeAll(user.getUsername());
		user.setPassword(newPassword);
		return userDAO.create(user);
	}

	@POST
	@Path("forgotPass")
	@UnitOfWork
	public void forgotPass() {
		// TODO
	}
}
