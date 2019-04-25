package com.csl456.bikerentalapp;

import com.csl456.bikerentalapp.core.Session;
import com.csl456.bikerentalapp.core.*;
import com.csl456.bikerentalapp.db.*;
import com.csl456.bikerentalapp.filter.*;
import com.csl456.bikerentalapp.resources.*;
import io.dropwizard.*;
import io.dropwizard.configuration.*;
import io.dropwizard.db.*;
import io.dropwizard.hibernate.*;
import io.dropwizard.jersey.jackson.*;
import io.dropwizard.migrations.*;
import io.dropwizard.setup.*;
import org.hibernate.*;
import org.slf4j.*;

public class BikeRentalApplication
        extends Application<BikeRentalAppConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            BikeRentalAppConfiguration.class);

    private final HibernateBundle<BikeRentalAppConfiguration> hibernateBundle
            = new HibernateBundle<BikeRentalAppConfiguration>(Cycle.class,
            Person.class,
            Session.class,
            User.class,
            Complaint.class,
            Ride.class,
            Location.class
    ) {
        @Override
        public DataSourceFactory getDataSourceFactory(
                BikeRentalAppConfiguration configuration) {
            return configuration.getDatabase();
        }
    };

    public static void main(String[] args) throws
            Exception { new BikeRentalApplication().run(args);}

    @Override
    public String getName() { return "bike-rental-app";}

    @Override
    public void initialize(Bootstrap<BikeRentalAppConfiguration> bootstrap) {
        LOGGER.info("Initializing configuration");
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(true)
        ));
        bootstrap.addBundle(new MigrationsBundle<BikeRentalAppConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(
                    BikeRentalAppConfiguration configuration) {
                return configuration.getDatabase();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(BikeRentalAppConfiguration configuration,
            Environment environment) {
        LOGGER.info("Starting the Bike Rental App");
        SessionFactory factory = hibernateBundle.getSessionFactory();

        /* DAOs */
        final CycleDAO     cycleDAO     = new CycleDAO(factory);
        final PersonDAO    personDAO    = new PersonDAO(factory);
        final SessionDAO   sessionDAO   = new SessionDAO(factory);
        final UserDAO      userDAO      = new UserDAO(factory);
        final RideDAO      rideDAO      = new RideDAO(factory);
        final ComplaintDAO complaintDAO = new ComplaintDAO(factory);
        final LocationDAO  locationDAO  = new LocationDAO(factory);

        /* Resources */
        environment
                .jersey()
                .register(new CycleResource(cycleDAO, userDAO, sessionDAO));
        environment.jersey().register(new PersonResource(personDAO));
        environment
                .jersey()
                .register(new SessionResource(userDAO, sessionDAO, personDAO));
        environment.jersey().register(new UserResource(userDAO,
                sessionDAO,
                personDAO,
                configuration.getSmtpServerDetails()
        ));
        environment.jersey().register(new RideResource(rideDAO));
        environment.jersey().register(new ComplaintResource(complaintDAO));
        environment.jersey().register(new LocationResource(locationDAO));

        /* Exception Parsers */
        environment.jersey().register(new JsonProcessingExceptionMapper(true));

        /* Filters */
        environment
                .jersey()
                .register(new LoggedInFeature(hibernateBundle, sessionDAO));
        environment
                .jersey()
                .register(new RolesAllowedFeature(hibernateBundle,
                        sessionDAO,
                        userDAO
                ));
    }

}
