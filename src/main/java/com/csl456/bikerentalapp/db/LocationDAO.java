package com.csl456.bikerentalapp.db;

import com.csl456.bikerentalapp.core.*;
import io.dropwizard.hibernate.*;
import org.hibernate.*;

import java.util.*;

public class LocationDAO extends AbstractDAO<Location> {

    public LocationDAO(SessionFactory factory) {
        super(factory);
    }

    public Location create(Location location) {
        return persist(location);
    }

    @SuppressWarnings("unchecked")
    public List<Location> findAll() {
        return list(namedQuery("Location.findAll"));
    }

    public Optional<Location> findById(Integer id) {
        return Optional.ofNullable(get(id));
    }

}
