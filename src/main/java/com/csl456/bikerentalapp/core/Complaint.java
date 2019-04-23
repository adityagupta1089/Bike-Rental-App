package com.csl456.bikerentalapp.core;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "complaint")
@NamedQueries({
        @NamedQuery(name = "Complaint.findAll",
                query = "SELECT C FROM Complaint C")
})
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String details;

    @Column(nullable = false)
    private ComplaintStatus status;

    @Column(nullable = false)
    private int cycleId;

    @Column(nullable = false)
    private Date startTime;

    @Column
    private Date endTime;

    @Column(nullable = false)
    private int personId;

    public Complaint() {}

    public Complaint(String details, ComplaintStatus status, int cycleId,
            Date startTime, Date endTime, int personId) {
        this.details   = details;
        this.status    = status;
        this.cycleId   = cycleId;
        this.startTime = startTime;
        this.endTime   = endTime;
        this.personId  = personId;
    }

    public int getId() { return id;}

    public void setId(int id) { this.id = id;}

    public String getDetails() { return details;}

    public void setDetails(String details) { this.details = details;}

    public ComplaintStatus getStatus() { return status;}

    public void setStatus(ComplaintStatus status) { this.status = status;}

    private Date getStartTime() { return startTime;}

    public void setStartTime(Date startTime) { this.startTime = startTime;}

    private Date getEndTime() { return endTime;}

    public void setEndTime(Date endTime) { this.endTime = endTime;}

    public int getCycleId() { return cycleId;}

    public void setCycleId(int cycleId) { this.cycleId = cycleId;}

    public int getPersonId() { return personId;}

    public void setPersonId(int personId) { this.personId = personId;}

}
