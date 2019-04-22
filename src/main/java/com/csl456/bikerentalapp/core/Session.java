package com.csl456.bikerentalapp.core;

import javax.inject.Named;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "session")
@NamedQueries({
        @NamedQuery(name = "Session.removeAll", query = "DELETE FROM Session S WHERE S.identity = :username"),
        @NamedQuery(name = "Session.loggedIn", query = "SELECT S FROM Session S WHERE S.accessToken = :accessToken"),
        @NamedQuery(name ="Session.remove", query = "DELETE FROM Session S WHERE S.accessToken = :accessToken")
})
public class Session {
    @Id
    private String accessToken;

    @Column(name = "username")
    private String identity;

    @Column
    private Date created;

    public Session() {

    }

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
