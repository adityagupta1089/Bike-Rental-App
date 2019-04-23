package com.csl456.bikerentalapp.db;

import com.csl456.bikerentalapp.core.*;
import io.dropwizard.hibernate.*;
import org.hibernate.*;

import java.util.*;

public class CycleDAO extends AbstractDAO<Cycle> {

    public CycleDAO(SessionFactory factory) {
        super(factory);
    }

    public Cycle create(Cycle cycle) {
        cycle.setStatus(1);
        return persist(cycle);
    }

    @SuppressWarnings("unchecked")
    public List<Cycle> findAll() {
        return list(namedQuery("Cycle.findAll"));
    }

    @SuppressWarnings("unchecked")
    public List<Cycle> getCyclesByOwnerId(int ownerId) {
        return list(namedQuery("Cycle.findByPersonId").setParameter("ownerId",
                ownerId
        ));
    }

    public Cycle remove(Integer id) {
        Cycle cycle = findById(id);
        cycle.setStatus(0);
        return persist(cycle);
    }

    public Cycle findById(Integer id) {
        return get(id);
    }

}
