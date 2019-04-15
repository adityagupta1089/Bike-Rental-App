package com.csl456.bikerentalapp.core;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String details;

    @Column
    private ComplaintStatus status;

    @Column(nullable = false)
	private int cycleId;

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

    public Complaint(int id, String details, ComplaintStatus status, int cycleId, int personId) {
		this.id = id;
		this.details = details;
		this.status = status;
		this.cycleId = cycleId;
		this.personId = personId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cycleId, details, personId, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complaint other = (Complaint) obj;
		return cycleId == other.cycleId && Objects.equals(details, other.details) && personId == other.personId
				&& status == other.status;
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
