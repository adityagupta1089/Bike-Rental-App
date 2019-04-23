package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.*;
import com.csl456.bikerentalapp.db.*;
import com.csl456.bikerentalapp.filter.*;
import io.dropwizard.hibernate.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

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
    @RolesAllowed(UserRole.ADMIN)
    public Location createLocation(
            Location location) { return locationDAO.create(location);}

    @GET
    @UnitOfWork
    @RolesAllowed(UserRole.ADMIN)
    public List<Location> listLocations() { return locationDAO.findAll();}

}
