package com.csl456.bikerentalapp.db;

import com.csl456.bikerentalapp.core.Ride;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class RideDAO extends AbstractDAO<Ride> {
    public RideDAO(SessionFactory factory) {
        super(factory);
    }

    public Ride start(Ride ride) {
        return persist(ride);
    }

    public Ride end(Ride ride) {
        return persist(ride);
    }
}
