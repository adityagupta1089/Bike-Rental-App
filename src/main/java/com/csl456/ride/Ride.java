package com.csl456.ride;

import java.util.Date;

import com.csl456.cycle.Cycle;
import com.csl456.location.Location;
import com.csl456.person.Person;

public class Ride {
	Location startLocation;
	Location endLocation;
	Date startTime;
	Date endTime;
	Cycle cycle;
	Person person;

	public void startRide(Date time, Location position, Cycle cycle, Person person) {

	}

	public void endRide(Date time, Location position) {

	}

	public int getCost() {
		return 0;
	}
}
