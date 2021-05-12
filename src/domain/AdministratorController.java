package domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class AdministratorController extends Controller {
	
	//private Employee employee;
	private PropertyChangeSupport employeeSubject;
	private DomainManager dm = new DomainManager();
	private ContactPerson contactPerson;
	private PropertyChangeSupport contactPersonSubject;
	private Company company;
	private PropertyChangeSupport companySubject;
	private IEmployee employee;
	
	public AdministratorController(IEmployee emp) {
		companySubject = new PropertyChangeSupport(this);
		contactPersonSubject = new PropertyChangeSupport(this);
		employeeSubject = new PropertyChangeSupport(this);
		this.employee = emp;
	}
	
	@Override
	public IEmployee getEmployee() {
		return this.employee;
	}

	public ContactPerson getContactPersonByUsername(String username) {
		ContactPerson cp = dm.getContactPersonByUsername(username);
		return cp;
	}
	public void setContactPerson(int contactPersonIndex) {
		this.contactPerson = this.company.getContactPersons().get(contactPersonIndex);
		contactPersonSubject.firePropertyChange("contactPerson", this.contactPerson, contactPerson);	
	}
	

	public void updateContactPerson(String firstName, String lastName, String email) {
		if(contactPerson != null) {
			contactPerson.setFirstName(firstName);
			contactPerson.setLastName(lastName);
			contactPerson.setEmail(email);
			
			dm.updateContactPerson(contactPerson);
		}
		}
	

//	public ObservableList<ContactPerson> getAllContactPersons() {
//		
//		List<ContactPerson> li = dm.getAllContactPersons();
//		ObservableList<ContactPerson> obListContactPersons = FXCollections.observableList(li);
//		return obListContactPersons;
//	}
//	
	public ObservableList<ICompany> getAllCompanies() {
		ObservableList<Company> li = dm.getAllCompanies();
		return (ObservableList<ICompany>) (Object) li;
	}
	
	public void updateCompany(String name, String address) {
		company.setCompanyAdress(address);
		company.setCompanyName(name);

		dm.updateCompany(name, address, company);
		
	}
	
	public ObservableList<ICompany> getCompaniesByName(String name) {
		ObservableList<Company> li = dm.getCompaniesByName(name);
		return (ObservableList<ICompany>) (Object) li;

	}
	
	
	public void addCompanyListener(PropertyChangeListener pcl) {
		companySubject.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		companySubject.removePropertyChangeListener(pcl);
	}

	@Override
	public void close() {
		dm.closePersistentie();
	}

//	public Employee getEmployeeByUsername(String username) {
//		Employee sm = dm.getEmployeeByUsername(username);
//		return sm;
//	}

	public void setEmployee(int employeeId) {
		Employee employee = dm.getAllEmployees().stream().filter(e -> e.getId() == employeeId).findFirst().orElse(null);
		employeeSubject.firePropertyChange("employee", this.employee, employee);
		this.employee = employee;
	}

	
	public void addEmployeeListener(PropertyChangeListener pcl) {
		employeeSubject.addPropertyChangeListener(pcl);
	}
	
	public void setCompany(int companyNr) {
		Company company = dm.getAllCompanies().stream().filter(c->c.getCompanyNr() == companyNr).findFirst().orElse(null);
		companySubject.firePropertyChange("company", this.company, company);
		this.company = company;
	}
	

	public ObservableList<IEmployee> getAllEmployees() {
		ObservableList<Employee> li = dm.getAllEmployees();
		return (ObservableList<IEmployee>) (Object) li;
	}

	public void updateEmployee(Integer id, LocalDate date, String firstname, String lastname, String adress,
			String role, String phonenumber, String email, String username, boolean status) {
		employee.setId(id);
		employee.setDateInService(date);
		employee.setFirstName(firstname);
		employee.setLastName(lastname);
		employee.setAdress(adress);
		employee.setRole(role);
		employee.setPhoneNumber(phonenumber);
		employee.setEmail(email);
		employee.setUsername(username);
		employee.setStatus(status);
		
		dm.updateEmployee(id, date, firstname, lastname, adress,
				role, phonenumber, email, username, status, employee);
		
	}

	public ObservableList<IEmployee> getEmployeesByName(String name) {
		ObservableList<Employee> li = dm.getEmployeesByName(name);
		return (ObservableList<IEmployee>) (Object) li;
	}
}
