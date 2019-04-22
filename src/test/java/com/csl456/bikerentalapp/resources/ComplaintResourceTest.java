package com.csl456.bikerentalapp.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;

import com.csl456.bikerentalapp.core.Complaint;
import com.csl456.bikerentalapp.core.ComplaintStatus;
import com.csl456.bikerentalapp.db.ComplaintDAO;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ComplaintResourceTest {
	private static final ComplaintDAO COMPLAINT_DAO = mock(ComplaintDAO.class);

	private static final ResourceExtension RESOURCES = 
			ResourceExtension.builder().addResource(new ComplaintResource(COMPLAINT_DAO)).build();

	private final ArgumentCaptor<Complaint> complaintCaptor = ArgumentCaptor.forClass(Complaint.class);

	private Complaint complaint;

	@Test
	void createComplaint() {
		when(COMPLAINT_DAO.add(any(Complaint.class))).thenReturn(complaint);
		final Response response = RESOURCES.target("/complaint")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(complaint, MediaType.APPLICATION_JSON_TYPE));
		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		verify(COMPLAINT_DAO).add(complaintCaptor.capture());
		assertThat(complaintCaptor.getValue()).isEqualTo(complaint);
	}

	@Test
	void listComplaints() {
		final List<Complaint> complaints = Collections.singletonList(complaint);
		when(COMPLAINT_DAO.findAll()).thenReturn(complaints);

		final List<Complaint> response = RESOURCES.target("/complaint")
			.request()
			.get(new GenericType<List<Complaint>>() {});

		verify(COMPLAINT_DAO).findAll();
		assertThat(response).containsAll(complaints);
	}

	@BeforeEach
	void setUp() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, 3, 15, 17, 9, 57);
		Date happyNewYearDate = calendar.getTime();
		complaint = new Complaint(
				"punctured", ComplaintStatus.UNRESOLVED, 1 ,happyNewYearDate , null, 1 );
	}

	@AfterEach
	void tearDown() {
		reset(COMPLAINT_DAO);
	}


}
