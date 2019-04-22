package com.csl456.bikerentalapp.core;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class UserTest {
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private static User getUser() { return new User("Vinit", "abc", UserRole.ADMIN, 1); }

    @Test
    public void deserializesFromJSON() throws Exception {
        assertEquals(MAPPER.readValue(fixture("fixtures/user.json"), User.class), getUser());
    }
}
