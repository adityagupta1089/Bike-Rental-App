package com.csl456.bikerentalapp.core;

import com.google.common.base.Objects;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cycleId", nullable = false)
    private Cycle cycle;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "personId", nullable = false)
    private Person person;

    public Complaint() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Complaint complaint = (Complaint) o;
        return Objects.equal(details, complaint.details)
                && status == complaint.status
                && Objects.equal(cycle, complaint.cycle)
                && Objects.equal(person, complaint.person);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(details, status, cycle, person);
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

    public void setStatus(ComplaintStatus status) {
        this.status = status;
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

}
