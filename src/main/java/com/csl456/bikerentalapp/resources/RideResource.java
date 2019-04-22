package com.csl456.bikerentalapp.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csl456.bikerentalapp.core.Ride;
import com.csl456.bikerentalapp.db.RideDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("ride")
@Produces(MediaType.APPLICATION_JSON)
public class RideResource {
	private final RideDAO rideDAO;

	public RideResource(RideDAO rideDAO) {
		this.rideDAO = rideDAO;
	}

	@POST
	@Path("start")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Ride startRide(@FormParam("startLocationId") int startLocationId, @FormParam("personId") int personId,
			@FormParam("cycleId") int cycleId) {
		Ride ride = new Ride();
		ride.setStartLocationId(startLocationId);
		ride.setPersonId(personId);
		ride.setCycleId(cycleId);
		ride.setStartTime(new Date());
		return rideDAO.start(ride);
	}

	@POST
	@Path("end")
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Ride endRide(@FormParam("endLocationId") int endLocationId, @FormParam("rideId") int rideId) {
		Ride ride = rideDAO.getById(rideId);
		ride.setEndLocationId(endLocationId);
		ride.setEndTime(new Date());
		ride.calculateCost();
		return rideDAO.end(ride);
	}
	
	@GET
	@UnitOfWork
	public List<Ride> listRidesByPersonId(@QueryParam("personId") int id ) {
		return rideDAO.findByPersonId(id);
	}

}
