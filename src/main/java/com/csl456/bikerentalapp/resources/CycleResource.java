package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.Cycle;
import com.csl456.bikerentalapp.db.CycleDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    public Cycle createCycle(Cycle cycle) {
        return cycleDAO.create(cycle);
    }

    @GET
    @UnitOfWork
    public List<Cycle> listCycle() {
        return cycleDAO.findAll();
    }

}
