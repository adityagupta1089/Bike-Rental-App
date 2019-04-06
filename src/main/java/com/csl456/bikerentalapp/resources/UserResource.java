package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.User;
import com.csl456.bikerentalapp.db.SessionDAO;
import com.csl456.bikerentalapp.db.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.FormParam;
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
    public User register(User user) {
        return userDAO.create(user);
    }

    @POST
    @Path("changePass")
    public User changePassword(User user, @FormParam("newPassword") String newPassword) throws Exception {
        if (userDAO.findUsersByUsernameAndPassword(user.getName(), user.getPassword()).isEmpty()) {
            throw new Exception("Username or Password Wrong");
        }
        sessionDAO.removeAll(user.getName());
        user.setPassword(newPassword);
        // might need to change this to userDAO.update(user)
        return userDAO.create(user);
    }

    @POST
    @Path("forgotPass")
    public void forgotPass() {
        //TODO
    }
}
