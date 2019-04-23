package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.Session;
import com.csl456.bikerentalapp.db.SessionDAO;
import com.csl456.bikerentalapp.db.UserDAO;
import com.csl456.bikerentalapp.filter.LoggedIn;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

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
    public Session login(@FormParam("username") String username, @FormParam(
    		"password") String password) {

        if (userDAO.findUsersByUsernameAndPassword(username, password) == null) {
            throw new WebApplicationException("Username or Password Wrong",
					Status.UNAUTHORIZED);
        }

        Session session = new Session(username);
        sessionDAO.insert(session);

        return session;
    }

    @DELETE
    @UnitOfWork
    @LoggedIn
    public int logout(@HeaderParam("Access_Token") String accessToken) {
        return sessionDAO.remove(accessToken);
    }
}
