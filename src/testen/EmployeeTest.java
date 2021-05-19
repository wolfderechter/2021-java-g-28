package testen;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import domain.ContractType;
import domain.Employee;
import domain.User;

public class EmployeeTest {

	private Employee employee;
	private User user;
	
	@BeforeEach
	public void before() {
		user = new User();
		employee = new Employee(LocalDate.now(), "firstName", "lastName", "adress", "AD", true, user);	}
	
	
	//paramterizedtest foute namen ingeven
	@ParameterizedTest
	@NullAndEmptySource
	public void firstNameCanNotBeNullOrEmpty(String firstname) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
					employee.setFirstName(firstname);
		});
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void lastNameCanNotBeNullOrEmpty(String lastname) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
					employee.setLastName(lastname);
		});
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void adressCanNotBeNullOrEmpty(String adress) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
					employee.setAdress(adress);
		});
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void phonenumberCanNotBeNullOrEmpty(String phonenumber) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
					employee.setPhoneNumber(phonenumber);
		});
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void emailCanNotBeNullOrEmpty(String email) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
					employee.setEmail(email);
		});
	}
	
	private static Stream<Arguments> parametersEmployee() {
	    return Stream.of(
	      Arguments.of(LocalDate.now(), "firstName", "lastName", "adress", "AD", true, new User()),
	      Arguments.of(LocalDate.now(), "Wolf", "De Rechter", "peperstraat 5, 9100", "AD", true, new User()),
	      Arguments.of(LocalDate.now(), "Jari", "Van der Coelden", "degraeve straat 3, 9100", "SM", true, new User()),
	      Arguments.of(LocalDate.now(), "Zowie", "Verschuere", "driegaaienstraat 67, 9100", "TE", false, new User())
	    );
	}
	
	@ParameterizedTest
	@MethodSource("parametersEmployee")
	public void newEmployeeCreatesValidEmployee(LocalDate dateInService, String firstname, String lastname, String adress, String role, Boolean status, User user) {
		Employee employee1 = new Employee(dateInService, firstname, lastname, adress, role, status, user);
		Assertions.assertEquals(firstname, employee1.getFirstName());	
		Assertions.assertEquals(lastname, employee1.getLastName());	
		Assertions.assertEquals(role, employee1.getRole());	
		Assertions.assertEquals(status, employee1.getStatus());	
		Assertions.assertEquals(user, employee1.getUser());	
	}
	
	@ParameterizedTest
	@MethodSource("parametersEmployee")
	public void editEmployeeEditsSuccesfully(LocalDate dateInService, String firstname, String lastname, String adress, String role, Boolean status, User user) {
		employee.setFirstName(firstname);
		employee.setLastName(lastname);
		employee.setAdress(adress);
		employee.setRole(role);
		employee.setStatus(status);
		
		Assertions.assertEquals(firstname, employee.getFirstName());	
		Assertions.assertEquals(lastname, employee.getLastName());	
		Assertions.assertEquals(role, employee.getRole());	
		Assertions.assertEquals(status, employee.getStatus());	
	}
}
