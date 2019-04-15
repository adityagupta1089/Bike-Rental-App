package com.csl456.bikerentalapp.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csl456.bikerentalapp.core.Location;
import com.csl456.bikerentalapp.db.LocationDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/location")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LocationResource {
	private final LocationDAO locationDAO;

    public LocationResource(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    @POST
    @UnitOfWork
    public Location createLocation(Location location) {
        return locationDAO.create(location);
    }

    @GET
    @UnitOfWork
    public List<Location> listLocation() {
        return locationDAO.findAll();
    }

}
