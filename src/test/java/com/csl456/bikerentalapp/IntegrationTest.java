package com.csl456.bikerentalapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.csl456.bikerentalapp.core.Person;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
public class IntegrationTest {
	private static final String TMP_FILE = createTempFile();
	private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test.yml");

	@BeforeAll
	public static void migrateDb() throws Exception {
		RULE.getApplication().run("db", "migrate", CONFIG_PATH);
	}

	public static final DropwizardAppExtension<BikeRentalAppConfiguration> RULE
		= new DropwizardAppExtension<>(
			BikeRentalApplication.class,
			CONFIG_PATH,
			ConfigOverride.config("database.url", "jdbc:h2:" + TMP_FILE)
		);

	private static String createTempFile() {
		try {
			return File.createTempFile("test", null).getAbsolutePath();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private Person postPerson(Person person) {
		return RULE.client()
			.target("http://localhost:" + RULE.getLocalPort() + "/person")
			.request()
			.post(Entity.entity(person, MediaType.APPLICATION_JSON_TYPE))
			.readEntity(Person.class);
	}

	@Test
	public void testPostPerson() throws Exception {
		final Person person = new Person("Aditya Gupta", 1234567890L, "aditya@example.com");
		final Person newPerson = postPerson(person);
		assertThat(newPerson.getId()).isNotNull();
		assertThat(newPerson.getName()).isEqualTo(person.getName());
		assertThat(newPerson.getContactNumber()).isEqualTo(person.getContactNumber());
		assertThat(newPerson.getEmail()).isEqualTo(person.getEmail());

	}
}