package com.csl456.bikerentalapp.db;


import com.csl456.bikerentalapp.core.Session;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class SessionDAO extends AbstractDAO<Session> {

    public SessionDAO(SessionFactory factory) {
        super(factory);
    }

    public void insert(Session session) {
        persist(session);
    }

    public void remove(Session session) {
        currentSession().remove(session);
    }

    public void removeAll(String username) {
        namedQuery("Session.removeAll").setParameter("username", username).executeUpdate();
    }
}
