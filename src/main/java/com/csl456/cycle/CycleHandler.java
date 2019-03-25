package com.csl456.cycle;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

public class CycleHandler {
	private static Map<Integer, Cycle> activeCycles = new HashMap<>();
	private static Map<Integer, Cycle> inactiveCycles = new HashMap<>();

	// API endpoint
	public Response transferCycle(Cycle cycle) {
		return Response.ok().build();
	}

	// API endpoint
	public Response takeCycle(Cycle cycle) {
		return Response.ok().build();
	}

	// API endpoint
	public Response givecycle(Cycle cycle) {
		return Response.ok().build();
	}
}
