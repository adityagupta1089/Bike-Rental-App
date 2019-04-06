package com.csl456.bikerentalapp.core;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
@NamedQueries({
                      @NamedQuery(name = "Person.findAll", query = "SELECT P FROM Person P"),
                      @NamedQuery(name = "Person.findById", query = "SELECT P FROM Person P WHERE P.id = :id")
              })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Person.class)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long contactNumber;

    @Email
    @Column(nullable = false)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<Cycle> cycles = new HashSet<>();

    public Person() {

    }

    public Person(String name, Long contactNumber, String email) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Person person = (Person) o;
        return contactNumber == person.contactNumber
                && Objects.equal(name, person.name)
                && Objects.equal(email, person.email)
                && Objects.equal(cycles, person.cycles);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, contactNumber, email, cycles);
    }

    public long getContactNumber() { return contactNumber; }

    public void setContactNumber(long contactNumber) { this.contactNumber = contactNumber; }

    public Set<Cycle> getCycles() { return cycles; }

    public void setCycles(Set<Cycle> cycles) { this.cycles = cycles; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

}
