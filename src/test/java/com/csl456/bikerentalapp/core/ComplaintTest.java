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
			Date d= new Date(0);
	        return new Complaint("punctured", ComplaintStatus.UNRESOLVED, 1 ,d , null, 1 );
	    }
	 
	 @Test
	    public void deserializesFromJSON() throws Exception {
	        assertThat(MAPPER.readValue(fixture("fixtures/complaint.json"), Complaint.class).getCycleId()).isEqualTo(
	        		getComplaint().getCycleId());
	        assertThat(MAPPER.readValue(fixture("fixtures/complaint.json"), Complaint.class).getDetails()).isEqualTo(
	        		getComplaint().getDetails());
	        assertThat(MAPPER.readValue(fixture("fixtures/complaint.json"), Complaint.class).getEndTime()).isEqualTo(
	        		getComplaint().getEndTime());
	        assertThat(MAPPER.readValue(fixture("fixtures/complaint.json"), Complaint.class).getPersonId()).isEqualTo(
	        		getComplaint().getPersonId());
	        assertThat(MAPPER.readValue(fixture("fixtures/complaint.json"), Complaint.class).getStartTime()).isEqualTo(
	        		getComplaint().getStartTime());
	        assertThat(MAPPER.readValue(fixture("fixtures/complaint.json"), Complaint.class).getStatus()).isEqualTo(
	        		getComplaint().getStatus());
	    }

}
