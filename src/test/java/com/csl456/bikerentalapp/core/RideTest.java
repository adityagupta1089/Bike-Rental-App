package com.csl456.bikerentalapp.core;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class RideTest {
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    public static Ride getRide() {
    	Date d = new Date(0);
    	
        return new Ride(1, 2,d,d, 1,1,10.0);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        assertThat(MAPPER.readValue(fixture("fixtures/ride.json"), Ride.class).getStartLocationId()).isEqualTo(
        		getRide().getStartLocationId());
        assertThat(MAPPER.readValue(fixture("fixtures/ride.json"), Ride.class).getEndLocationId()).isEqualTo(
        		getRide().getEndLocationId());
        assertThat(MAPPER.readValue(fixture("fixtures/ride.json"), Ride.class).getPersonId()).isEqualTo(
        		getRide().getPersonId());
        assertThat(MAPPER.readValue(fixture("fixtures/ride.json"), Ride.class).getCycleId()).isEqualTo(
        		getRide().getCycleId());
        assertThat(MAPPER.readValue(fixture("fixtures/ride.json"), Ride.class).getStartTime()).isEqualTo(
        		getRide().getStartTime());
        assertThat(MAPPER.readValue(fixture("fixtures/ride.json"), Ride.class).getEndTime()).isEqualTo(
        		getRide().getEndTime());
        assertThat(MAPPER.readValue(fixture("fixtures/ride.json"), Ride.class).getCost()).isEqualTo(
        		getRide().getCost());
    }
}
