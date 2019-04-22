package com.csl456.bikerentalapp.resources;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csl456.bikerentalapp.core.Cycle;
import com.csl456.bikerentalapp.core.UserRole;
import com.csl456.bikerentalapp.db.CycleDAO;
import com.csl456.bikerentalapp.filter.RolesAllowed;

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
	@RolesAllowed(UserRole.ADMIN)
	public Cycle createCycle(Cycle cycle) {
		return cycleDAO.create(cycle);
	}

	@GET
	@UnitOfWork
	@RolesAllowed(UserRole.ADMIN)
	public List<Cycle> listCycle(@QueryParam("ownerId") Optional<Integer> ownerId) {
		if (ownerId.isPresent()) {
			return cycleDAO.getCyclesByOwnerId(ownerId.get());
		} else {
			return cycleDAO.findAll();
		}
	}

	@GET
	@UnitOfWork
	@Path("{id}")
	@RolesAllowed(UserRole.ADMIN)
	public Cycle getCycleById(@PathParam("id") int id) {
		return cycleDAO.findById(id);
	}

	@DELETE
	@UnitOfWork
	@RolesAllowed(UserRole.ADMIN)
	public void removeCycle(@PathParam("id") int id) {
		cycleDAO.remove(id);
	}

}
