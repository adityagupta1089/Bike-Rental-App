package com.csl456.bikerentalapp.core;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class PersonTest {
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	private static final String NULL_ERROR_MESSAGE = "may not be null";
	
	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

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

	// Should be replaced with individual class field validator tests
	@Test
	public void validate_not_null() throws Exception {
		Person person = new Person();

		Set<ConstraintViolation<Person>> constraintViolations
			= validator.validate(person);

		assertEquals(2, constraintViolations.size());
		assertEquals(
			NULL_ERROR_MESSAGE,
			constraintViolations.iterator().next().getMessage()
		);
	}

	public static Person getPerson() {
		return new Person(123, "Abcd Efgh", 1234567890, "email@example.com");
	}
}
