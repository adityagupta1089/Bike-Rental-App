package com.csl456.bikerentalapp.db;

import com.csl456.bikerentalapp.core.*;
import io.dropwizard.hibernate.*;
import org.hibernate.*;

import java.util.*;

public class RideDAO extends AbstractDAO<Ride> {

    public RideDAO(SessionFactory factory) {
        super(factory);
    }

    public Ride start(Ride ride) {
        return persist(ride);
    }

    public Ride getById(int rideId) {
        return get(rideId);
    }

    public Ride end(Ride ride) {
        return persist(ride);
    }

    @SuppressWarnings("unchecked")
    public List<Ride> findByPersonId(int id) {
        return list(namedQuery("Ride.findByPersonId").setParameter("personId",
                id
        ));
    }

}
