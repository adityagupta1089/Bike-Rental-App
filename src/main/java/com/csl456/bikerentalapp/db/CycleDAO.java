package com.csl456.bikerentalapp.db;

import java.util.List;

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

	public Cycle findById(Integer id) {
		return get(id);
	}

	@SuppressWarnings("unchecked")
	public List<Cycle> getCyclesByOwnerId(int ownerId) {
		return list(namedQuery("Cycle.findByPersonId").setParameter("ownerId", ownerId));
	}

	public Cycle remove(Integer id) {
		Cycle cycle = findById(id);
		cycle.setStatus(0);
		return persist(cycle);
	}

}
