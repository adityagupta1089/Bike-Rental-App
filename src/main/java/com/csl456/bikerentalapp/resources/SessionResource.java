package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.Session;
import com.csl456.bikerentalapp.db.SessionDAO;
import com.csl456.bikerentalapp.db.UserDAO;
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
    public Session login(@FormParam("username") String username, @FormParam("password") String password)
            throws Exception {

        if (userDAO.findUsersByUsernameAndPassword(username, password).isEmpty()) {
            throw new Exception("Username or Password Wrong");
        }

        Session session = new Session(username);
        sessionDAO.insert(session);

        return session;
    }

    @DELETE
    @UnitOfWork
    public void logout(Session session) {
        sessionDAO.remove(session);
    }
}
