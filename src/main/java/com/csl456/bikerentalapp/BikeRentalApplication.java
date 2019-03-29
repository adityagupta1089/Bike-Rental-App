package com.csl456.bikerentalapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csl456.bikerentalapp.core.Cycle;
import com.csl456.bikerentalapp.core.Person;
import com.csl456.bikerentalapp.db.CycleDAO;
import com.csl456.bikerentalapp.db.PersonDAO;
import com.csl456.bikerentalapp.resources.CycleResource;
import com.csl456.bikerentalapp.resources.PersonResource;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BikeRentalApplication
	extends Application<BikeRentalAppConfiguration> {
	private static final Logger LOGGER
		= LoggerFactory.getLogger(BikeRentalAppConfiguration.class);

	public static void main(String[] args) throws Exception {
		new BikeRentalApplication().run(args);
	}

	private final HibernateBundle<BikeRentalAppConfiguration> hibernateBundle
		= new HibernateBundle<BikeRentalAppConfiguration>(
			Cycle.class,
			Person.class
		) {
			@Override
			public DataSourceFactory getDataSourceFactory(
				BikeRentalAppConfiguration configuration
			) {
				return configuration.getDataSourceFactory();
			}
		};

	@Override
	public String getName() { return "bike-rental-app"; }

	@Override
	public void initialize(Bootstrap<BikeRentalAppConfiguration> bootstrap) {
		LOGGER.info("Initializing configuration");
		bootstrap.setConfigurationSourceProvider(
			new SubstitutingSourceProvider(
				bootstrap.getConfigurationSourceProvider(),
				new EnvironmentVariableSubstitutor(true)
			)
		);
		bootstrap.addBundle(new MigrationsBundle<BikeRentalAppConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(
				BikeRentalAppConfiguration configuration
			) {
				return configuration.getDataSourceFactory();
			}
		});
		bootstrap.addBundle(hibernateBundle);
	}

	@Override
	public void run(
		BikeRentalAppConfiguration configuration, Environment environment
	) {
		LOGGER.info("Starting the Bike Rental App");
		final CycleDAO cycleDao
			= new CycleDAO(hibernateBundle.getSessionFactory());
		final PersonDAO personDao
			= new PersonDAO(hibernateBundle.getSessionFactory());
		environment.jersey().register(new CycleResource(cycleDao));
		environment.jersey().register(new PersonResource(personDao));
	}
}
