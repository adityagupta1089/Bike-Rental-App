package com.csl456.bikerentalapp.core;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "cycle")
@NamedQueries({
	@NamedQuery(name = "Cycle.findAll", query = "SELECT c FROM Cycle c"),
	@NamedQuery(
		name = "Cycle.findById",
		query = "SELECT c FROM Cycle c WHERE c.id = :id"
	)
})
public class Cycle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Length(max = 255)
	@Column(name = "brand", nullable = false)
	private String brand;

	@Column(name = "location", nullable = false)
	private Location location;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ownerid", referencedColumnName = "id")
	private Person owner;

	public Cycle() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Cycle)) return false;
		Cycle other = (Cycle) obj;
		return Objects.equals(brand, other.brand)
				&& id == other.id
				&& location == other.location
				&& owner == other.owner;
	}

	public String getBrand() { return brand; }

	public int getId() { return id; }

	public Location getLocation() { return location; }

	public Person getOwner() { return owner; }

	@Override
	public int hashCode() {
		return Objects.hash(brand, id, location, owner);
	}

	public void setBrand(String brand) { this.brand = brand; }

	public void setId(int id) { this.id = id; }

	public void setLocation(Location location) { this.location = location; }

	public void setOwner(Person owner) { this.owner = owner; }

}
