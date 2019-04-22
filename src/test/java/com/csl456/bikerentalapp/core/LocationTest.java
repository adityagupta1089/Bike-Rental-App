package com.csl456.bikerentalapp.core;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class LocationTest {
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    public static Location getLocation() {
        return new Location("SATLUJ_HOSTEL", 5, 7);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        assertThat(MAPPER.readValue(fixture("fixtures/location.json"), Location.class)).isEqualTo(getLocation());
    }
}
