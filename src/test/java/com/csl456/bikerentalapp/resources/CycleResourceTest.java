package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.Cycle;
import com.csl456.bikerentalapp.core.Location;
import com.csl456.bikerentalapp.core.Person;
import com.csl456.bikerentalapp.db.CycleDAO;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
class CycleResourceTest {

	/*private static final CycleDAO CYCLE_DAO = mock(CycleDAO.class);

	private static final ResourceExtension RESOURCES = ResourceExtension.builder()
																		.addResource(new CycleResource(CYCLE_DAO))
																		.build();

	private final ArgumentCaptor<Cycle> cycleCaptor = ArgumentCaptor.forClass(Cycle.class);

	private Cycle cycle;

	@Test
	void createCycle() {
		when(CYCLE_DAO.create(any(Cycle.class))).thenReturn(cycle);
		final Response response = RESOURCES.target("/cycle")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(cycle, MediaType.APPLICATION_JSON_TYPE));
		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		verify(CYCLE_DAO).create(cycleCaptor.capture());
		assertThat(cycleCaptor.getValue()).isEqualTo(cycle);
	}

	@Test
	void listCycles() {
		final List<Cycle> cycles = Collections.singletonList(cycle);
		when(CYCLE_DAO.findAll()).thenReturn(cycles);

		final List<Cycle> response = RESOURCES.target("/cycle")
			.request()
			.get(new GenericType<List<Cycle>>() {});

		verify(CYCLE_DAO).findAll();
		assertThat(response).containsAll(cycles);
	}

	@BeforeEach
	void setUp() {
		cycle = new Cycle(
			"Atlas",
			Location.SATLUJ_HOSTEL,
			new Person("Aditya Gupta", 1234567890L, "aditya@example.com")
		);
	}

	@AfterEach
	void tearDown() {
		reset(CYCLE_DAO);
	}*/

}
