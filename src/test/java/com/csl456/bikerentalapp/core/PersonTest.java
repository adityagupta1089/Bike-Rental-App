package com.csl456.bikerentalapp.core;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class PersonTest {
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	public static Person getPerson() {
		Person person = new Person();
		person.setId(123);
		person.setName("Abcd Efgh");
		person.setContactNumber(1234567890);
		person.setEmail("email@example.com");
		return person;
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		assertEquals(
			MAPPER.readValue(fixture("fixtures/person.json"), Person.class),
			getPerson()
		);
	}

	@Test
	public void serializesToJSON() throws Exception {
		assertEquals(
			MAPPER.readValue(fixture("fixtures/person.json"), Person.class),
			getPerson()
		);
	}
}
