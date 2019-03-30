package com.csl456.bikerentalapp.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "person")
@NamedQueries({
	@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
	@NamedQuery(
		name = "Person.findById",
		query = "SELECT p FROM Person p WHERE p.id = :id"
	)
})
@JsonIdentityInfo(
	generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "id"
)
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Length(max = 255)
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "contactNumber", nullable = false)
	private long contactNumber;

	@Email
	@Length(max = 255)
	@Column(name = "email", nullable = false)
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<Cycle> cycles = new HashSet<>();

	public Person() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Person)) return false;
		Person other = (Person) obj;
		return contactNumber == other.contactNumber
				&& Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(name, other.name);
	}

	public long getContactNumber() { return contactNumber; }

	public Set<Cycle> getCycles() { return cycles; }

	public String getEmail() { return email; }

	public int getId() { return id; }

	public String getName() { return name; }

	@Override
	public int hashCode() {
		return Objects.hash(contactNumber, email, id, name);
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setCycles(Set<Cycle> cycles) { this.cycles = cycles; }

	public void setEmail(String email) { this.email = email; }

	public void setId(int id) { this.id = id; }

	public void setName(String name) { this.name = name; }

	@Override
	public String toString() {
		return "Person [id="
				+ id + ", name=" + name + ", contactNumber=" + contactNumber
				+ ", email=" + email + "]";
	}

}
