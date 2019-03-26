package com.csl456;

import com.csl456.health.TemplateHealthCheck;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BikeRentalApplication extends Application<BikeRentalAppConfiguration> {

	public static void main(String[] args) throws Exception {
		new BikeRentalApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<BikeRentalAppConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(BikeRentalAppConfiguration configuration, Environment environment) {
		environment.healthChecks().register("templateHealthCheck", new TemplateHealthCheck());
	}
}
