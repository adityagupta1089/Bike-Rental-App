package com.csl456.bikerentalapp.core;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "session")
@NamedQuery(name = "Session.findAll", query = "DELETE FROM Session S WHERE S.identity = :username")
public class Session {
    @Id
    private final String accessToken;

    @Column(name = "username")
    private final String identity;

    @Column
    private final Date created;

    public Session(String username) {
        this.identity = username;
        this.accessToken = UUID.randomUUID().toString().substring(0, 23);
        this.created = new Date();
    }

    public String getIdentity() {
        return identity;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Date getCreated() {
        return created;
    }
}
