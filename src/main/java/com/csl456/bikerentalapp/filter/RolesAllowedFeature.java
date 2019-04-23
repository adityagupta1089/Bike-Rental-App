package com.csl456.bikerentalapp.filter;

import com.csl456.bikerentalapp.*;
import com.csl456.bikerentalapp.core.*;
import com.csl456.bikerentalapp.db.*;
import io.dropwizard.hibernate.*;

import javax.ws.rs.container.*;
import javax.ws.rs.core.*;

public class RolesAllowedFeature implements DynamicFeature {

    private final SessionDAO                                  sessionDAO;
    private final UserDAO                                     userDAO;
    private final HibernateBundle<BikeRentalAppConfiguration> hibernateBundle;

    public RolesAllowedFeature(
            HibernateBundle<BikeRentalAppConfiguration> hibernateBundle,
            SessionDAO sessionDAO, UserDAO userDAO) {
        this.hibernateBundle = hibernateBundle;
        this.sessionDAO      = sessionDAO;
        this.userDAO         = userDAO;
    }

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        RolesAllowed roles = resourceInfo
                .getResourceMethod()
                .getAnnotation(RolesAllowed.class);
        if (roles != null) {
            RolesFilter rolesFilter = new UnitOfWorkAwareProxyFactory(
                    hibernateBundle).create(RolesFilter.class, new Class<?>[]{
                    SessionDAO.class, UserDAO.class, UserRole[].class
            }, new Object[]{sessionDAO, userDAO, roles.value()});
            context.register(rolesFilter);
        }
    }

}