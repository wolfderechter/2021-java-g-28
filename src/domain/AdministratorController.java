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

	private GenericDao<Employee> employeeRepo;
	private Employee employee;
	private PropertyChangeSupport employeeSubject;
	private DomainManager dm = new DomainManager();
	private ContactPerson contactPerson;
	private PropertyChangeSupport contactPersonSubject;
	private GenericDao<ContactPerson> contactPersonRepo;
	private Company company;
	private GenericDao<Company> companyRepo;
	private PropertyChangeSupport companySubject;
	
	
	
	
	public AdministratorController() {
		setEmployeeRepo(new GenericDaoJpa<>(Employee.class));
		setCompanyRepo(new GenericDaoJpa<>(Company.class));
		companySubject = new PropertyChangeSupport(this);
		contactPersonSubject = new PropertyChangeSupport(this);
		setEmployeeRepo(new GenericDaoJpa<>(Employee.class));
		setContactPersonRepo(new GenericDaoJpa<>(ContactPerson.class));
		employeeSubject = new PropertyChangeSupport(this);
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
			
			GenericDaoJpa.startTransaction();
			contactPersonRepo.update(contactPerson);
			GenericDaoJpa.commitTransaction();
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
		
		
		GenericDaoJpa.startTransaction();
		companyRepo.update(company);
		GenericDaoJpa.commitTransaction();
		
		
	}
	
	public ObservableList<ICompany> getCompaniesByName(String name) {
		ObservableList<Company> li = dm.getCompaniesByName(name);
		return (ObservableList<ICompany>) (Object) li;
	}
	
	
	private void setCompanyRepo(GenericDao<Company> companyRepo) {
		this.companyRepo = companyRepo;
	}
	
	public void addCompanyListener(PropertyChangeListener pcl) {
		companySubject.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		companySubject.removePropertyChangeListener(pcl);
	}

	private void setContactPersonRepo(GenericDao<ContactPerson> contactPersonRepo) {
		this.contactPersonRepo = contactPersonRepo;
	}


	public void close() {
		GenericDaoJpa.closePersistency();
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
	

	public ObservableList<Employee> getAllEmployees() {
		ObservableList<Employee> li = dm.getAllEmployees();
		return li;
	}

	public IEmployee getAdministratorByUsername(String username) {
		IEmployee a = dm.getEmployeeByUsername(username, "AD");
		return a;
	}

	public void setEmployeeRepo(GenericDao<Employee> employeeRepo) {
		this.employeeRepo = employeeRepo;
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
		GenericDaoJpa.startTransaction();
		employeeRepo.update(employee);
		GenericDaoJpa.commitTransaction();
		
	}
}
