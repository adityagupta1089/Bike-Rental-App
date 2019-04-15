package com.csl456.bikerentalapp.db;

import com.csl456.bikerentalapp.core.Cycle;
import com.csl456.bikerentalapp.core.Location;
import com.csl456.bikerentalapp.core.Person;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CycleDAOTest {
	/*private final DAOTestExtension daoTestRule = DAOTestExtension.newBuilder()
																 .addEntityClass(Person.class)
																 .addEntityClass(Cycle.class)
																 .build();

	private CycleDAO cycleDAO;

	@Test
	public void createCycle() {
		final Cycle cycle = daoTestRule.inTransaction(
			() -> cycleDAO.create(
				new Cycle(
					"Atlas",
					Location.SATLUJ_HOSTEL,
					new Person("Aditya", 1234567890L, "aditya@example.com")
				)
			)
		);
		assertThat(cycle.getId()).isGreaterThan(0);
		assertThat(cycle.getBrand()).isEqualTo("Atlas");
		assertThat(cycle.getLocation()).isEqualTo(Location.SATLUJ_HOSTEL);
		assertThat(cycle.getOwner().getId()).isGreaterThan(0);
		assertThat(cycle.getOwner().getName()).isEqualTo("Aditya");
		assertThat(cycle.getOwner().getContactNumber()).isEqualTo(1234567890L);
		assertThat(cycle.getOwner().getEmail()).isEqualTo("aditya@example.com");
		assertThat(cycleDAO.findById(cycle.getId())).isEqualTo(Optional.of(cycle));
	}

	@Test
	public void findAll() {
		daoTestRule.inTransaction(() -> {
			cycleDAO.create(
				new Cycle(
					"Atlas",
					Location.SATLUJ_HOSTEL,
					new Person("Aditya", 1234567890L, "aditya@example.com")
				)
			);
			cycleDAO.create(
				new Cycle("Avon", Location.CHEM_DEPT, new Person("Vinit", 2345678901L, "vinit@example.com"))
			);
		});

		final List<Cycle> cycles = cycleDAO.findAll();
		assertThat(cycles).extracting("brand").containsOnly("Atlas", "Avon");
		assertThat(cycles).extracting("location").containsOnly(Location.SATLUJ_HOSTEL, Location.CHEM_DEPT);
		assertThat(cycles).extracting("owner").extracting("name").containsOnly("Aditya", "Vinit");
	}

	@Test
	public void handlesNullOwner() {
		assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(
			() -> daoTestRule.inTransaction(
				() -> cycleDAO.create(new Cycle("Atlas", Location.SATLUJ_HOSTEL, null))
			)
		);
	}

	@BeforeEach
	public void setUp() {
		cycleDAO = new CycleDAO(daoTestRule.getSessionFactory());
	}*/
}
