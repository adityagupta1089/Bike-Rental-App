package com.csl456.bikerentalapp.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
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

import com.csl456.bikerentalapp.core.Cycle;
import com.csl456.bikerentalapp.core.Location;
import com.csl456.bikerentalapp.core.Person;
import com.csl456.bikerentalapp.db.CycleDAO;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;

@ExtendWith(DropwizardExtensionsSupport.class)
class CycleResourceTest {

	private static final CycleDAO CYCLE_DAO = mock(CycleDAO.class);

	public static final ResourceExtension RESOURCES = ResourceExtension
		.builder()
		.addResource(new CycleResource(CYCLE_DAO))
		.build();

	private ArgumentCaptor<Cycle> cycleCaptor = ArgumentCaptor.forClass(
		Cycle.class
	);

	private Cycle cycle;

	@BeforeEach
	void setUp() throws Exception {
		Person owner = new Person();
		owner.setId(456);
		owner.setName("OwnerX");
		owner.setContactNumber(1234567890);
		owner.setEmail("owner@example.com");

		cycle = new Cycle();
		cycle.setId(123);
		cycle.setBrand("BrandX");
		cycle.setLocation(Location.LOCATION_1);
		cycle.setOwner(owner);
	}

	@AfterEach
	void tearDown() throws Exception {
		reset(CYCLE_DAO);
	}

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

}
