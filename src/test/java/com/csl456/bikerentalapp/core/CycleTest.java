package com.csl456.bikerentalapp.core;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class CycleTest {
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	public static Cycle getCycle() {
		Person owner = new Person();
		owner.setId(456);
		owner.setName("OwnerX");
		owner.setContactNumber(1234567890);
		owner.setEmail("owner@example.com");

		Cycle cycle = new Cycle();
		cycle.setId(123);
		cycle.setBrand("BrandX");
		cycle.setLocation(Location.LOCATION_1);
		cycle.setOwner(owner);

		return cycle;
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		assertEquals(
			MAPPER.readValue(fixture("fixtures/cycle.json"), Cycle.class),
			getCycle()
		);
	}

	@Test
	public void serializesToJSON() throws Exception {
		assertEquals(
			MAPPER.readValue(fixture("fixtures/cycle.json"), Cycle.class),
			getCycle()
		);
	}

}
