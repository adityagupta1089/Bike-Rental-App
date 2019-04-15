package com.csl456.bikerentalapp.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csl456.bikerentalapp.core.Complaint;
import com.csl456.bikerentalapp.core.ComplaintStatus;
import com.csl456.bikerentalapp.db.ComplaintDAO;

import io.dropwizard.hibernate.UnitOfWork;

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
	public Complaint addComplaint(Complaint complaint) {
		complaint.setStatus(ComplaintStatus.UNRESOLVED);
		return complaintDAO.add(complaint);
	}

	@DELETE
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Complaint resolveComplaint(@FormParam("complaintId") int complaintId) {
		Complaint complaint = complaintDAO.getById(complaintId);
		complaint.setStatus(ComplaintStatus.RESOLVED);
		return complaintDAO.resolve(complaint);
	}

}
