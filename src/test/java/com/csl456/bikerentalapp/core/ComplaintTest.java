package com.csl456.bikerentalapp.core;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class ComplaintTest {
	 private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	 public static Complaint getComplaint() {
			Calendar calendar = Calendar.getInstance();
			calendar.set(2019, 3, 15, 17, 9, 57);
			Date happyNewYearDate = calendar.getTime();
	        return new Complaint("punctured", ComplaintStatus.UNRESOLVED, 1 ,happyNewYearDate , null, 1 );
	    }
	 
	 @Test
	    public void deserializesFromJSON() throws Exception {
	        assertThat(MAPPER.readValue(fixture("fixtures/complaint.json"), Complaint.class)).isEqualTo(getComplaint());
	    }

}
