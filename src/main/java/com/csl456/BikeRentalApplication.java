package com.csl456;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csl456.core.Cycle;
import com.csl456.db.CycleDAO;
import com.csl456.resources.CycleResource;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BikeRentalApplication extends Application<BikeRentalAppConfiguration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BikeRentalAppConfiguration.class);

	public static void main(String[] args) throws Exception {
		new BikeRentalApplication().run(args);
	}

	private final HibernateBundle<BikeRentalAppConfiguration> hibernateBundle = new HibernateBundle<BikeRentalAppConfiguration>(
			Cycle.class) {
		@Override
		public DataSourceFactory getDataSourceFactory(BikeRentalAppConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};

	@Override
	public void initialize(Bootstrap<BikeRentalAppConfiguration> bootstrap) {
		LOGGER.info("Initializing configuration");
		bootstrap.addBundle(new MigrationsBundle<BikeRentalAppConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(BikeRentalAppConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
		bootstrap.addBundle(hibernateBundle);
	}

	@Override
	public void run(BikeRentalAppConfiguration configuration, Environment environment) {
		LOGGER.info("Starting the Bike Rental App");
		final CycleDAO dao = new CycleDAO(hibernateBundle.getSessionFactory());
		environment.jersey().register(new CycleResource(dao));
	}

	@Override
	public String getName() {
		return "bike-rental-app";
	}
}
