package com.csl456.bikerentalapp.core;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "complaint")
@NamedQueries({ @NamedQuery(name = "Complaint.findAll", query = "SELECT C FROM Complaint C")
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

    public Complaint() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

	public Complaint(String details, ComplaintStatus status, int cycleId, Date startTime, Date endTime, int personId) {
		this.details = details;
		this.status = status;
		this.cycleId = cycleId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.personId = personId;
	}

	@Override
	public String toString() {
		return "Complaint [id=" + id + ", details=" + details + ", status=" + status + ", cycleId=" + cycleId
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", personId=" + personId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cycleId, details, endTime, personId, startTime, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Complaint other = (Complaint) obj;
		return cycleId == other.cycleId && Objects.equals(details, other.details)
				&& Objects.equals(endTime, other.endTime) && personId == other.personId
				&& Objects.equals(startTime, other.startTime) && status == other.status;
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

	public void setStatus(ComplaintStatus status) {
        this.status = status;
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

}
