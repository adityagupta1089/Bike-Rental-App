package com.csl456.complaint;

import javax.ws.rs.core.Response;

public class ComplaintHandler {
	// API endpoint
	public Response registerComplaint(Complaint complaint) {
		return Response.ok().build();
	}

	// API endpoint
	public Response resolveComplaint(Complaint complaint) {
		return Response.ok().build();
	}

}
