package com.csl456.bikerentalapp.db;

import com.csl456.bikerentalapp.core.Complaint;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class ComplaintDAO extends AbstractDAO<Complaint> {
    public ComplaintDAO(SessionFactory factory) {
        super(factory);
    }

    public Complaint add(Complaint complaint) {
        return persist(complaint);
    }
    
    public Complaint resolve(Complaint complaint) {
        return persist(complaint);
    }

    public Complaint getById(int complaintId) {
		return get(complaintId);
	}
}
