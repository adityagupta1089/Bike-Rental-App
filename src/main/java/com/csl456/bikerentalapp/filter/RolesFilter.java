package com.csl456.bikerentalapp.filter;

import com.csl456.bikerentalapp.core.*;
import com.csl456.bikerentalapp.db.*;
import io.dropwizard.hibernate.*;

import javax.ws.rs.*;
import javax.ws.rs.container.*;
import javax.ws.rs.core.*;
import java.util.*;

public class RolesFilter implements ContainerRequestFilter {

    private UserDAO        userDAO;
    private SessionDAO     sessionDAO;
    private List<UserRole> allowedRoles;

    public RolesFilter(SessionDAO sessionDAO, UserDAO userDAO,
            UserRole[] allowedRoles) {
        this.sessionDAO   = sessionDAO;
        this.userDAO      = userDAO;
        this.allowedRoles = Arrays.asList(allowedRoles);
    }

    @Override
    @UnitOfWork
    public void filter(ContainerRequestContext requestContext) {
        final String accessToken
                = requestContext.getHeaderString("Access_Token");
        if (sessionDAO.notLoggedIn(accessToken)) {
            throw new WebApplicationException("You are not logged in.",
                    Response.Status.UNAUTHORIZED
            );
        }
        String username = sessionDAO.getUserName(accessToken);
        User   user     = userDAO.findByUserName(username);
        if (!allowedRoles.contains(user.getRole())) {
            throw new WebApplicationException(
                    "You do not have sufficient allowed Roles.",
                    Response.Status.UNAUTHORIZED
            );
        }
    }

}
