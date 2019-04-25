package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.*;
import com.csl456.bikerentalapp.db.*;
import com.csl456.bikerentalapp.filter.*;
import io.dropwizard.hibernate.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

@Path("session")
@Produces(MediaType.APPLICATION_JSON)
public class SessionResource {

    private final UserDAO    userDAO;
    private final SessionDAO sessionDAO;
    private final PersonDAO  personDAO;

    public SessionResource(UserDAO userDAO, SessionDAO sessionDAO,
            PersonDAO personDAO) {
        this.userDAO    = userDAO;
        this.sessionDAO = sessionDAO;
        this.personDAO  = personDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public UserInformation login(@FormParam("username") String username,
            @FormParam("password") String password) {
        User user = userDAO.findUsersByUsernameAndPassword(username, password);
        if (user == null) {
            throw new WebApplicationException("Username or Password Wrong",
                    Status.UNAUTHORIZED
            );
        }
        Person person = personDAO
                .findById(user.getPersonId())
                .orElseThrow(() -> new WebApplicationException(
                        "No Person Found for this User",
                        Status.NOT_FOUND
                ));
        Session session = new Session(username);
        sessionDAO.insert(session);
        return new UserInformation(session, user, person);
    }

    @DELETE
    @UnitOfWork
    @LoggedIn
    public int logout(@HeaderParam("Access_Token") String accessToken) {
        return sessionDAO.remove(accessToken);
    }

}
