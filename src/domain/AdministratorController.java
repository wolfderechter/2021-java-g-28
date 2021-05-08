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

	public AdministratorController() {
		employeeSubject = new PropertyChangeSupport(this);
		contactPersonSubject = new PropertyChangeSupport(this);
		setEmployeeRepo(new GenericDaoJpa<>(Employee.class));
		setContactPersonRepo(new GenericDaoJpa<>(ContactPerson.class));
	}

	public ContactPerson getContactPersonByUsername(String username) {
		ContactPerson cp = dm.getContactPersonByUsername(username);
		return cp;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		contactPersonSubject.firePropertyChange("contactPerson", this.contactPerson, contactPerson);
		this.contactPerson = contactPerson;
	}

	public void updateContactPerson(ContactPerson contactPerson) {
		GenericDaoJpa.startTransaction();
		contactPersonRepo.update(contactPerson);
		GenericDaoJpa.commitTransaction();
	}

	public void addContactPersonListener(PropertyChangeListener pcl) {
		contactPersonSubject.addPropertyChangeListener(pcl);
	}

	public ObservableList<ContactPerson> getAllContactPersons() {

		List<ContactPerson> li = dm.getAllContactPersons();
		ObservableList<ContactPerson> obListContactPersons = FXCollections.observableList(li);
		return obListContactPersons;
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

	// als ticket gewijzigd wordt gaat de ticket in editticketpanel ook veranderd
	// worden
	public void addEmployeeListener(PropertyChangeListener pcl) {
		employeeSubject.addPropertyChangeListener(pcl);
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
