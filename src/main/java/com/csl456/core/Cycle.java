package com.csl456.core;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "cycle")
@NamedQueries({ @NamedQuery(name = "com.csl456.core.Cycle.findAll", query = "SELECT * FROM cycle") })
public class Cycle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "brand", nullable = false)
	private String brand;

	@Column(name = "location", nullable = false)
	private Location location;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "ownerid"), nullable = false)
	private int ownerId;

	public Cycle() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, id, location, ownerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cycle))
			return false;
		Cycle other = (Cycle) obj;
		return Objects.equals(brand, other.brand) && id == other.id && location == other.location
				&& ownerId == other.ownerId;
	}

}
