package com.csl456.bikerentalapp.resources;

import com.csl456.bikerentalapp.core.*;
import com.csl456.bikerentalapp.db.*;
import com.csl456.bikerentalapp.filter.*;
import io.dropwizard.hibernate.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Path("complaint")
@Produces(MediaType.APPLICATION_JSON)
public class ComplaintResource {

    private final ComplaintDAO complaintDAO;

    public ComplaintResource(ComplaintDAO complaintDAO) {
        this.complaintDAO = complaintDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @LoggedIn
    public Complaint addComplaint(Complaint complaint) {
        complaint.setStartTime(new Date());
        complaint.setStatus(ComplaintStatus.UNRESOLVED);
        return complaintDAO.create(complaint);
    }

    @GET
    @UnitOfWork
    @RolesAllowed(UserRole.ADMIN)
    public List<Complaint> listComplaints() { return complaintDAO.findAll();}

    @DELETE
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @RolesAllowed(UserRole.ADMIN)
    public Complaint resolveComplaint(
            @FormParam("complaintId") int complaintId) {
        Complaint complaint = complaintDAO.getById(complaintId);
        complaint.setStatus(ComplaintStatus.RESOLVED);
        complaint.setEndTime(new Date());
        return complaintDAO.create(complaint);
    }

}
