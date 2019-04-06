package com.csl456.bikerentalapp.core;


import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NamedQueries({
                      @NamedQuery(name = "User.findAll", query = "SELECT U FROM User U"),
                      @NamedQuery(name = "User.findByUserNameAndPassword",
                                  query = "SELECT U FROM User U WHERE U.name = :name AND U.password = :password")
              })
public class User {
    @Id
    @Column
    private String name;

    @Column
    private String password;

    @Column
    private UserRole role;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "personId", nullable = false)
    private Person person;

    public User() {

    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        User user = (User) o;
        return Objects.equal(name, user.name)
                && Objects.equal(password, user.password)
                && role == user.role
                && Objects.equal(person, user.person);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, password, role, person);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
