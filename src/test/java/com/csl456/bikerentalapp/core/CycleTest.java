package com.csl456.bikerentalapp.core;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class CycleTest {
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	public static Cycle getCycle() {
		return new Cycle(
			"Atlas",
			Location.SATLUJ_HOSTEL,
			new Person("Aditya Gupta", 1234567890L, "aditya@example.com")
		);
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		assertEquals(MAPPER.readValue(fixture("fixtures/cycle.json"), Cycle.class), getCycle());
	}

	@Test
	public void serializesToJSON() throws Exception {
		assertEquals(MAPPER.readValue(fixture("fixtures/cycle.json"), Cycle.class), getCycle());
	}

}
