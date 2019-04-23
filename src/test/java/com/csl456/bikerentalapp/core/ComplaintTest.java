package com.csl456.bikerentalapp.core;

import com.fasterxml.jackson.databind.*;
import io.dropwizard.jackson.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class ComplaintTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    public static Complaint getComplaint() {
        return new Complaint(
                "punctured",
                ComplaintStatus.UNRESOLVED,
                1,
                new Date(0),
                null,
                1
        );
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        Complaint complaint = MAPPER.readValue(
                fixture("fixtures/complaint.json"),
                Complaint.class
        );
        assertThat(complaint).isEqualTo(getComplaint());
    }

}
