package com.csl456.bikerentalapp.filter;

import com.csl456.bikerentalapp.db.*;
import io.dropwizard.hibernate.*;

import javax.ws.rs.*;
import javax.ws.rs.container.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

@Provider
public class LoggedInFilter implements ContainerRequestFilter {

    private SessionDAO sessionDAO;

    public LoggedInFilter(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
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
    }

}
