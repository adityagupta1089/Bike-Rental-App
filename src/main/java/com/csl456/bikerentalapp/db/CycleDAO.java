package com.csl456.bikerentalapp.db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.csl456.bikerentalapp.core.Cycle;

import io.dropwizard.hibernate.AbstractDAO;

public class CycleDAO extends AbstractDAO<Cycle> {

	public CycleDAO(SessionFactory factory) {
		super(factory);
	}

	public Cycle create(Cycle cycle) {
		return persist(cycle);
	}

	@SuppressWarnings("unchecked")
	public List<Cycle> findAll() {
		return list(namedQuery("Cycle.findAll"));
	}

	public Optional<Cycle> findById(Long id) {
		return Optional.ofNullable(get(id));
	}

}
