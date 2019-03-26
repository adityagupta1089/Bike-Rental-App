package com.csl456.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {

	public TemplateHealthCheck() {
	}

	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}
}
