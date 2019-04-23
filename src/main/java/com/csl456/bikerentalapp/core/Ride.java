package com.csl456.bikerentalapp.core;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@NamedQueries({ @NamedQuery(name = "Ride.findByPersonId", query = "SELECT R FROM Ride R WHERE R.personId = :personId") })

public class Ride {

	/** 
	 * */
	private static final double MIN_COST = 10;
	/**
	 * We want to have a cost of 10 Rupees per hour, this is measured in
	 * Rupees/millisecond so we have 10Rs/(60*60*1000)millisecond
	 */
	private static final double COST_MULTIPLIER = 10.0 / (60.0 * 60.0 * 1000.0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private int startLocationId;

	@Column
	private int endLocationId;

	@Column(nullable = false)
	private Date startTime;

	@Column
	private Date endTime;

	@Column(nullable = false)
	private int cycleId;

	@Column(nullable = false)
	private int personId;

	@Column(nullable = false)
	private double cost;

	public Ride() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCost() {

		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Ride(int startLocationId, int endLocationId, Date startTime, Date endTime, int cycleId, int personId,
			double cost) {
		this.startLocationId = startLocationId;
		this.endLocationId = endLocationId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.cycleId = cycleId;
		this.personId = personId;
		this.cost = cost;
	}

	public Date getStartTime() {
		return startTime;
	}

	@Override
	public String toString() {
		return "Ride [id=" + id + ", startLocationId=" + startLocationId + ", endLocationId=" + endLocationId
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", cycleId=" + cycleId + ", personId="
				+ personId + ", cost=" + cost + "]";
	}

	public int getStartLocationId() {
		return startLocationId;
	}

	public void setStartLocationId(int startLocationId) {
		this.startLocationId = startLocationId;
	}

	public int getEndLocationId() {
		return endLocationId;
	}

	public void setEndLocationId(int endLocationId) {
		this.endLocationId = endLocationId;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getCycleId() {
		return cycleId;
	}

	public void setCycleId(int cycleId) {
		this.cycleId = cycleId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public void calculateCost() {
		this.cost = Math.min(MIN_COST, COST_MULTIPLIER * (this.endTime.getTime() - this.startTime.getTime()));
	}
}
