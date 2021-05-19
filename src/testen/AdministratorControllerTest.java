package testen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import domain.DomainManager;
import domain.Employee;
import domain.ICompany;
import domain.IEmployee;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import domain.AdministratorController;
import domain.Company;

@ExtendWith(MockitoExtension.class)
class AdministratorControllerTest {

	
	@Mock
	private DomainManager domainManagerDummy;
	
	@InjectMocks
	private AdministratorController administratorController;
	
	@Test
	public void getAllCompaniesRetursCompanies() {
		
		Company company = new Company();
		Company company2 = new Company();
		List<Company> companies = new ArrayList<Company>();
		companies.add(company);
		companies.add(company2);
		Mockito.when(domainManagerDummy.getAllCompanies()).thenReturn(FXCollections.observableArrayList(companies));
		ObservableList<ICompany> result = administratorController.getAllCompanies();
		Assertions.assertEquals(companies, result);
		Mockito.verify(domainManagerDummy).getAllCompanies();
		
	}
	
	@Test
	public void getAllEmployeesReturnsEployees() {
		
		Employee employee = new Employee();
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		
		Mockito.when(domainManagerDummy.getAllEmployees()).thenReturn(FXCollections.observableArrayList(employees));
		ObservableList<IEmployee> result = administratorController.getAllEmployees();
		Assertions.assertEquals(employees, result);
		Mockito.verify(domainManagerDummy).getAllEmployees();
		
	}
	
	@Test
	public void getCompaniesByNameRetursListOfCompanies() {
		String name = "HansAnders";
		LocalDate date = LocalDate.now();
		Company hansAnders = new Company(name, "lamstraat 15, 9100", date, true);

		List<Company> companies = new ArrayList<Company>();
		companies.add(hansAnders);
		Mockito.when(domainManagerDummy.getCompaniesByName(name)).thenReturn(FXCollections.observableArrayList(companies));
		ObservableList<ICompany> result = administratorController.getCompaniesByName(name);
		Assertions.assertEquals(companies, result);
		Mockito.verify(domainManagerDummy).getCompaniesByName(name);
		
	}
	
	@Test
	public void getEmployeesByNameRetursListOfEmployees() {
		String firstName = "Zowie";
		LocalDate date = LocalDate.now();
		User user = new User();
		Employee employee = new Employee(date, firstName, "lamstraat 15","9100", "AD", true, user);

		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		Mockito.when(domainManagerDummy.getEmployeesByName(firstName)).thenReturn(FXCollections.observableArrayList(employees));
		ObservableList<IEmployee> result = administratorController.getEmployeesByName(firstName);
		Assertions.assertEquals(employees, result);
		Mockito.verify(domainManagerDummy).getEmployeesByName(firstName);
		
	}
	
	@Test
	public void getEmployeesByUserNameRetursListOfEmployees() {
		String firstName = "Nathan";
		LocalDate date = LocalDate.now();
		User user = new User();
		user.setUserName("Hans");
		String username = user.getUserName();
		Employee employee = new Employee(date, firstName, "lamstraat 15","9100", "AD", true, user);

		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		Mockito.when(domainManagerDummy.getEmployeesByUsername(username)).thenReturn(FXCollections.observableArrayList(employees));
		ObservableList<IEmployee> result = administratorController.getEmployeesByUsername(username);
		Assertions.assertEquals(employees, result);
		Mockito.verify(domainManagerDummy).getEmployeesByUsername(username);
		
	}
	
}
