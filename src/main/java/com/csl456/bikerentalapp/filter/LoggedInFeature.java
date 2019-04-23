package com.csl456.bikerentalapp.filter;

import com.csl456.bikerentalapp.*;
import com.csl456.bikerentalapp.db.*;
import io.dropwizard.hibernate.*;

import javax.ws.rs.container.*;
import javax.ws.rs.core.*;

public class LoggedInFeature implements DynamicFeature {

    private HibernateBundle<BikeRentalAppConfiguration> hibernateBundle;

    private SessionDAO sessionDAO;

    public LoggedInFeature(
            HibernateBundle<BikeRentalAppConfiguration> hibernateBundle,
            SessionDAO sessionDAO) {
        this.hibernateBundle = hibernateBundle;
        this.sessionDAO      = sessionDAO;
    }

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        if (resourceInfo.getResourceMethod().getAnnotation(LoggedIn.class)
                != null) {
            LoggedInFilter loggedInFilter = new UnitOfWorkAwareProxyFactory(
                    hibernateBundle).create(LoggedInFilter.class,
                    SessionDAO.class,
                    sessionDAO
            );
            context.register(loggedInFilter);
        }
    }

}
