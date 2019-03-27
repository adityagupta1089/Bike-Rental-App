package com.csl456.core;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "person")
@NamedQueries({
		@NamedQuery(
				name = "Person.findAll",
				query = "SELECT p FROM Person p"
		),
		@NamedQuery(
				name = "Person.findById",
				query = "SELECT p FROM Person p WHERE p.id = :id"
		)
})
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Length(max = 255)
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "contactNumber", nullable = false)
	private int contactNumber;

	@Email
	@Length(max = 255)
	@Column(name = "email", nullable = false)
	private String email;

	public Person() {

	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public int getContactNumber() { return contactNumber; }

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	@Override
	public int hashCode() {
		return Objects.hash(contactNumber, email, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Person)) return false;
		Person other = (Person) obj;
		return contactNumber == other.contactNumber && Objects.equals(
				email,
				other.email
		) && id == other.id && Objects.equals(name, other.name);
	}

}
