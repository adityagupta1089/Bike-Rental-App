package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.Person;
import com.csl456.bikerentalapp.core.User;
import com.csl456.bikerentalapp.core.UserRole;
import com.csl456.bikerentalapp.db.PersonDAO;
import com.csl456.bikerentalapp.filter.RolesAllowed;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Path("/person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
	private final PersonDAO personDAO;

	public PersonResource(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@POST
	@UnitOfWork
	public Person createPerson(Person person) {
		return personDAO.create(person);
	}

	@GET
	@UnitOfWork
	@RolesAllowed(UserRole.ADMIN)
	public List<Person> listPeople() {
		return personDAO.findAll();
	}
}
