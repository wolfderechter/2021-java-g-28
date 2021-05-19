package testen;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import domain.ContactPerson;
import domain.User;

class ContactPersonTest {
	
	private ContactPerson contactPerson;
	
	@BeforeEach
	public void before() {
		contactPerson = new ContactPerson();
	}

	@ParameterizedTest
	@NullAndEmptySource
	public void firstNameCanNotBeNullOrEmpty(String firstName) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactPerson.setFirstName(firstName);
		});
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void lastNameCanNotBeNullOrEmpty(String lastName) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactPerson.setLastName(lastName);
		});
	}
	
	private static Stream<Arguments> parameters() {
		return Stream.of(
				Arguments.of("Zowie", "Verschuere", new User()),
				Arguments.of("Wolf", "DeRechter", new User()),
				Arguments.of("Nathan", "Tersago", new User())
				);
	}
	
	@ParameterizedTest
	@MethodSource("parameters")
	public void createCorrectContactPerson(String firstName, String lastName, User user) {
		ContactPerson contactPerson1 = new ContactPerson(firstName, lastName, user);
		Assertions.assertEquals(firstName, contactPerson1.getFirstName());
		Assertions.assertEquals(lastName, contactPerson1.getLastName());
		Assertions.assertEquals(user, contactPerson1.getUser());
	}

}
