package com.csl456.bikerentalapp.db;

import com.csl456.bikerentalapp.core.Complaint;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class ComplaintDAO extends AbstractDAO<Complaint> {
    public ComplaintDAO(SessionFactory factory) {
        super(factory);
    }

    public Complaint create(Complaint complaint) {
        return persist(complaint);
    }

    public void remove(Complaint complaint) {
        currentSession().remove(complaint);
    }
}
