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

import com.csl456.bikerentalapp.core.Person;
import com.csl456.bikerentalapp.db.PersonDAO;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;

@ExtendWith(DropwizardExtensionsSupport.class)
class PersonResourceTest {

	private static final PersonDAO PERSON_DAO = mock(PersonDAO.class);
	
	public static final ResourceExtension RESOURCES = ResourceExtension
		.builder()
		.addResource(new PersonResource(PERSON_DAO))
		.build();
	
	private ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(
		Person.class
	);
	
	private Person person;

	@BeforeEach
	void setUp() throws Exception {
		person = new Person(123, "Abcd Efgh", 1234567890, "email@example.com");
	}

	@AfterEach
	void tearDown() throws Exception {
		reset(PERSON_DAO);
	}

	@Test
	void createPerson() {
		when(PERSON_DAO.create(any(Person.class))).thenReturn(person);
		final Response response = RESOURCES.target("/person")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(person, MediaType.APPLICATION_JSON_TYPE));
		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		verify(PERSON_DAO).create(personCaptor.capture());
		assertThat(personCaptor.getValue()).isEqualTo(person);
	}

	@Test
	void listPeople() {
		final List<Person> people = Collections.singletonList(person);
		when(PERSON_DAO.findAll()).thenReturn(people);

		final List<Person> response = RESOURCES.target("/person")
			.request()
			.get(new GenericType<List<Person>>() {});

		verify(PERSON_DAO).findAll();
		assertThat(response).containsAll(people);
	}

}
