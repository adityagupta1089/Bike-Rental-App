package com.csl456.bikerentalapp.db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.csl456.bikerentalapp.core.LocationTest;

import io.dropwizard.hibernate.AbstractDAO;

public class LocationDAO extends AbstractDAO<LocationTest>{
	 public LocationDAO(SessionFactory factory) {
	        super(factory);
	    }

	    public LocationTest create(LocationTest location) {
	        return persist(location);
	    }

		@SuppressWarnings("unchecked")
	    public List<LocationTest> findAll() {
	        return list(namedQuery("Location.findAll"));
	    }

	    public Optional<LocationTest> findById(Integer id) {
	        return Optional.ofNullable(get(id));
	    }

}
