package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.Person;
import com.csl456.bikerentalapp.db.PersonDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    public List<Person> listPerson() {
        return personDAO.findAll();
    }
}
