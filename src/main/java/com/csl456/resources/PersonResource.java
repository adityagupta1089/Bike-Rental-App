package com.csl456.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csl456.core.Person;
import com.csl456.db.PersonDAO;

import io.dropwizard.hibernate.UnitOfWork;

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
	public List<Person> listPeople() {
		return personDAO.findAll();
	}
}
