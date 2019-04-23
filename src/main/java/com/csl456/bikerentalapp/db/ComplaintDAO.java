package com.csl456.bikerentalapp.db;

import com.csl456.bikerentalapp.core.*;
import io.dropwizard.hibernate.*;
import org.hibernate.*;

import java.util.*;

public class ComplaintDAO extends AbstractDAO<Complaint> {

    public ComplaintDAO(SessionFactory factory) {
        super(factory);
    }

    public Complaint create(Complaint complaint) {
        return persist(complaint);
    }

    @SuppressWarnings("unchecked")
    public List<Complaint> findAll() {
        return list(namedQuery("Complaint.findAll"));
    }

    public Complaint getById(int complaintId) {
        return get(complaintId);
    }

}
