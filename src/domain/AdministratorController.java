package domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class AdministratorController extends Controller {

	private Employee employee;
	private PropertyChangeSupport employeeSubject;
	private DomainManager dm = new DomainManager();
	private GenericDao<Employee> employeeRepo;
	private ContactPerson contactPerson;
	private PropertyChangeSupport contactPersonSubject;
	private GenericDao<ContactPerson> contactPersonRepo;

	public AdministratorController() {
		setEmployeeRepo(new GenericDaoJpa<>(Employee.class));
		contactPersonSubject = new PropertyChangeSupport(this);
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

	private void setEmployeeRepo(GenericDao<Employee> employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	public void close() {
		GenericDaoJpa.closePersistency();
	}

//	public Employee getEmployeeByUsername(String username) {
//		Employee sm = dm.getEmployeeByUsername(username);
//		return sm;
//	}

	public void updateEmployee(Employee employee) {
		employeeSubject.firePropertyChange("employee", this.employee, employee);
		GenericDaoJpa.startTransaction();
		// employeeRepo.update(employee);
		GenericDaoJpa.commitTransaction();
	}

	public void setEmployee(Employee employee) {
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

	public Employee getAdministratorByUsername(String username) {
		Employee a = dm.getEmployeeByUsername(username, "AD");
		return a;
	}
}
