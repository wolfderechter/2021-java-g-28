package domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
	private IEmployee loggedInEmployee;
	private Employee employee;
	
	public AdministratorController(IEmployee emp) {
		companySubject = new PropertyChangeSupport(this);
		contactPersonSubject = new PropertyChangeSupport(this);
		employeeSubject = new PropertyChangeSupport(this);
		this.loggedInEmployee = emp;
	}
	
	@Override
	public IEmployee getEmployee() {
		return this.loggedInEmployee;
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

	public void createEmployee(LocalDate creationDate, String firstName, String lastName, String adress, String role, String phoneNumber, String email, String username, boolean isActive) {
		User user = dm.getUserByUsername(username);
		Employee employee = new Employee(creationDate, firstName, lastName, adress, role, phoneNumber, email, username, isActive, user);
		
		dm.createEmployee(employee);
		//dm.createUser??
		setEmployee(employee.getId());
	}

	public void createUser(String phoneNumber, String email, String username, String role) {
		
		try {
			get(String.format("https://localhost:44350/Account/CreateUserJava/%s/%s/%s/%s",
					username, email, phoneNumber, role));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	
	//Call naar Dotnet
	private String get(String urlToRead) throws IOException {
		StringBuilder result = new StringBuilder();

		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "text/plain");
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(5000);
		try (var reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			result.append(reader.readLine());
		}

		return result.toString();
	}

}
