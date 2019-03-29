package com.csl456.bikerentalapp.core;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class PersonTest {
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	@Test
	public void serializesToJSON() throws Exception {
		assertEquals(
			MAPPER.readValue(fixture("fixtures/person.json"), Person.class),
			getPerson()
		);
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		assertEquals(
			MAPPER.readValue(fixture("fixtures/person.json"), Person.class),
			getPerson()
		);
	}

	public static Person getPerson() {
		return new Person(123, "Abcd Efgh", 1234567890, "email@example.com");
	}
}
