package com.csl456.ride;

import java.util.Date;

import javax.ws.rs.core.Response;

import com.csl456.cycle.Cycle;
import com.csl456.location.Location;
import com.csl456.person.Person;

public class RideHandler {

	// API Endpoint
	public Response startRide(Date time, Location position, Cycle cycle, Person person) {
		return Response.ok().build();
	}

	// API Endpoint
	public Response endRide(Date time, Location position) {
		return Response.ok().build();
	}
}
