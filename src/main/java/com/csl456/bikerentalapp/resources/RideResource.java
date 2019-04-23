package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.*;
import com.csl456.bikerentalapp.db.*;
import com.csl456.bikerentalapp.filter.*;
import io.dropwizard.hibernate.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Path("ride")
@Produces(MediaType.APPLICATION_JSON)
public class RideResource {

    private final RideDAO rideDAO;

    public RideResource(RideDAO rideDAO) {this.rideDAO = rideDAO;}

    @POST
    @Path("start")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @LoggedIn
    public Ride startRide(@FormParam("startLocationId") int startLocationId,
            @FormParam("personId") int personId,
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
    @LoggedIn
    public Ride endRide(@FormParam("endLocationId") int endLocationId,
            @FormParam("rideId") int rideId) {
        Ride ride = rideDAO.getById(rideId);
        ride.setEndLocationId(endLocationId);
        ride.setEndTime(new Date());
        ride.calculateCost();
        return rideDAO.end(ride);
    }

    @GET
    @UnitOfWork
    @RolesAllowed(UserRole.ADMIN)
    public List<Ride> listRidesByPersonId(@QueryParam(
            "personId") int id) { return rideDAO.findByPersonId(id);}

}
