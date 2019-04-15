package com.csl456.bikerentalapp.core;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "cycle")
@NamedQueries({
                      @NamedQuery(name = "Cycle.findAll", query = "SELECT C FROM Cycle C"),
                      @NamedQuery(name = "Cycle.findById", query = "SELECT C FROM Cycle C WHERE C.id = :id"),
                      @NamedQuery(name = "Cycle.findByPersonId", query = "SELECT C FROM Cycle C WHERE C.ownerId = :ownerId")
              })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Cycle.class)
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private int locationId;

    @Column(nullable = false)
	private int ownerId;

    public Cycle() {

    }


    public Cycle(int id, String brand, int locationId, int ownerId) {
		this.id = id;
		this.brand = brand;
		this.locationId = locationId;
		this.ownerId = ownerId;
	}



    

	


	public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

	public int getLocationId() {
		return locationId;
	}


	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}


	@Override
	public int hashCode() {
		return Objects.hash(brand, id, locationId, ownerId);
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
		Cycle other = (Cycle) obj;
		return Objects.equals(brand, other.brand) && id == other.id && locationId == other.locationId
				&& ownerId == other.ownerId;
	}


	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}


	public int getOwnerId() {
		return ownerId;
	}


	public void setPersonId(int ownerId) {
		this.ownerId = ownerId;
	}

}
