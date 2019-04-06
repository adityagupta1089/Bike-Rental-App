package com.csl456.bikerentalapp.core;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "cycle")
@NamedQueries({
                      @NamedQuery(name = "Cycle.findAll", query = "SELECT C FROM Cycle C"),
                      @NamedQuery(name = "Cycle.findById", query = "SELECT C FROM Cycle C WHERE C.id = :id")
              })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Cycle.class)
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ownerId", nullable = false)
    private Person owner;

    public Cycle() {

    }

    public Cycle(String brand, Location location, Person owner) {
        this.brand = brand;
        this.location = location;
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Cycle cycle = (Cycle) o;
        return Objects.equal(brand, cycle.brand) && location == cycle.location && Objects.equal(owner, cycle.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(brand, location, owner);
    }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Location getLocation() { return location; }

    public void setLocation(Location location) { this.location = location; }

    public Person getOwner() { return owner; }

    public void setOwner(Person owner) { this.owner = owner; }

}
