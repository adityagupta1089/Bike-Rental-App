package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.Ride;
import com.csl456.bikerentalapp.db.RideDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Date;

@Path("ride")
public class RideResource {
    private final RideDAO rideDAO;

    public RideResource(RideDAO rideDAO) {
        this.rideDAO = rideDAO;
    }


    @POST
    @Path("start")
    @UnitOfWork
    public Ride startRide(Ride ride) {
        ride.setStartTime(new Date());
        // might need to change this to rideDAO.update(ride)
        return rideDAO.start(ride);
    }

    @POST
    @Path("end")
    @UnitOfWork
    public Ride endRide(Ride ride) {
        ride.setEndTime(new Date());
        ride.calculateCost();
        // might need to change this to rideDAO.update(ride)
        return rideDAO.end(ride);
    }
}
