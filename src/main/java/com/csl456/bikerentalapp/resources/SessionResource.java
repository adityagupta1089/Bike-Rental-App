package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.Session;
import com.csl456.bikerentalapp.db.SessionDAO;
import com.csl456.bikerentalapp.db.UserDAO;
import com.csl456.bikerentalapp.filter.LoggedIn;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("session")
@Produces(MediaType.APPLICATION_JSON)
public class SessionResource {
	private final UserDAO userDAO;

	private final SessionDAO sessionDAO;

	public SessionResource(UserDAO userDAO, SessionDAO sessionDAO) {
		this.userDAO = userDAO;
		this.sessionDAO = sessionDAO;
	}

	@POST
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Session login(@FormParam("username") String username, @FormParam("password") String password)
			throws Exception {

		if (userDAO.findUsersByUsernameAndPassword(username, password) == null) {
			throw new Exception("Username or Password Wrong");
		}

		Session session = new Session(username);
		sessionDAO.insert(session);

		return session;
	}

	@DELETE
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_JSON)
	@LoggedIn
	public void logout(@HeaderParam("Acccess_Token") String accessToken) {
		sessionDAO.remove(accessToken);
	}
}
