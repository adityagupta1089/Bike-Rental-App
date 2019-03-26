package com.csl456.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csl456.core.Cycle;
import com.csl456.db.CycleDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/cycle")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CycleResource {
	private final CycleDAO cycleDAO;

	public CycleResource(CycleDAO cycleDAO) {
		this.cycleDAO = cycleDAO;
	}

	@POST
	@UnitOfWork
	public Cycle createPerson(Cycle cycle) {
		return cycleDAO.create(cycle);
	}

	@GET
	@UnitOfWork
	public List<Cycle> listPeople() {
		return cycleDAO.findAll();
	}

}
