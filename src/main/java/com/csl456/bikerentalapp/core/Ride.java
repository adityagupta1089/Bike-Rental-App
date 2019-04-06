package com.csl456.bikerentalapp.core;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    private Location startLocation;

    @Column
    private Location endLocation;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cycleId", nullable = false)
    private Cycle cycle;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "personId", nullable = false)
    private Person person;

    private double cost;

    public Ride() {

    }

    public double getCost() {

        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public Date getStartTime() {
        return startTime;
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

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void calculateCost() {
        this.cost = 0.05 * (this.endTime.getTime() - this.startTime.getTime());
    }
}
