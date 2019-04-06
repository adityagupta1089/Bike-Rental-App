package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.Complaint;
import com.csl456.bikerentalapp.db.ComplaintDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("complaint")
public class ComplaintResource {
    private final ComplaintDAO complaintDAO;

    public ComplaintResource(ComplaintDAO complaintDAO) {
        this.complaintDAO = complaintDAO;
    }

    @POST
    @UnitOfWork
    public Complaint addComplaint(Complaint complaint) {
        return complaintDAO.create(complaint);
    }

    @DELETE
    @UnitOfWork
    public void removeComplaint(Complaint complaint) {
        complaintDAO.remove(complaint);
    }

}
