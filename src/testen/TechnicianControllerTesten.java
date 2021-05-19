package testen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.Company;
import domain.ContactPerson;
import domain.DomainManager;
import domain.Employee;
import domain.Reaction;
import domain.TechnicianController;
import domain.Ticket;
import domain.User;
import javafx.collections.FXCollections;
@ExtendWith(MockitoExtension.class)
class TechnicianControllerTesten {

	@Mock
	private DomainManager domainManagerDummy;
	
	@InjectMocks
	private TechnicianController technicianController;
	
	@Test
    public void getAllEmployeeNamesByCompanyNameReturnListOfnames() {
		Company company  = new Company();
		company.setCompanyName("test");
		ContactPerson cp = new ContactPerson();
		User user = new User();
		user.setUserName("FrankV");
		cp.setUser(user);
		List<ContactPerson> cplist = new ArrayList<>();
		company.setContactPersons(cplist);
		cplist.add(cp);
		List<Company> companylist = new ArrayList<>();
		companylist.add(company);
		Mockito.when(domainManagerDummy.getAllCompanies()).thenReturn(FXCollections.observableArrayList(companylist));
		List<String> result= technicianController.getContactPersonFromCompanyName("test");
		List<String> listnames = new ArrayList<>();
		listnames.add("FrankV");
		Assertions.assertEquals(listnames,result);
		Mockito.verify(domainManagerDummy).getAllCompanies();
	}
	
	
	@Test
    public void getAllEmployeesComboReturnsListOfAllFullNames() {
		Employee emp1  = new Employee();
		emp1.setFirstName("Fred");
		emp1.setLastName("Test");
		emp1.setRole("TE");
		List<Employee> emplist = new ArrayList<>();
		emplist.add(emp1);
		Mockito.when(domainManagerDummy.getAllEmployees()).thenReturn(FXCollections.observableArrayList(emplist));
		List<String> result= technicianController.getAllEmployeesCombo();
		List<String> listnames = new ArrayList<>();
		listnames.add("Fred Test, TE");
		Assertions.assertEquals(listnames,result);
		Mockito.verify(domainManagerDummy).getAllEmployees();
	}
	
	@Test
    public void getAllCompanyNamesReturnsListOfNames() {
		Company company  = new Company();
		company.setCompanyName("test");
		List<Company> companylist = new ArrayList<>();
		companylist.add(company);
		Mockito.when(domainManagerDummy.getAllCompanies()).thenReturn(FXCollections.observableArrayList(companylist));
		List<String> result= technicianController.getAllCompanyNames();
		List<String> listnames = new ArrayList<>();
		listnames.add("test");
		Assertions.assertEquals(listnames,result);
		Mockito.verify(domainManagerDummy).getAllCompanies();
	}
	
	
	
	
	
	
	

}
