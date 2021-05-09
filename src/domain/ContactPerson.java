package domain;

import java.beans.Transient;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity(name = "ContactPerson")
@Table(name = "ContactPersons")
@NamedQueries({
	@NamedQuery(name = "ContactPerson.getContactpersonByUsername" , query = "SELECT c FROM ContactPerson c WHERE c.user.userName = :username"),
	@NamedQuery(name = "ContactPerson.getAll" , query = "SELECT d FROM ContactPerson d ")

})
@Access(AccessType.FIELD)
public class ContactPerson {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	private StringProperty firstName;
	private StringProperty lastName;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	@OneToMany(mappedBy = "contactPerson")
	private List<Notification> notifications;
	@ManyToOne
	@JoinColumn(name = "CompanyNr")
//	private ObjectProperty<Company> company;
	private Company company;
	@OneToMany
	private List<Contract> contracts;
	
	
	public ContactPerson() {
		
	}
	


	public ContactPerson(int id, String firstName, String lastName, Company company, List<Notification> notifications) {
		
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setCompany(company);
		setNotifications(notifications);
		
	}
	
	public void addNotification(String reaction) {
		notifications.add(new Notification("Reaction",reaction,this));
	}

	
	public StringProperty FirstName() {
		return firstName;
	}
	
	public StringProperty LastName() {
		return lastName;
	}
	
	
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	

	public void setUser(User user) {
		this.user = user;
	}
	
	@Access(AccessType.PROPERTY)
	public String getFirstName() {
		return firstName.getValue();
	}

	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}

	@Access(AccessType.PROPERTY)
	public String getLastName() {
		return lastName.getValue();
	}

	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);;
	}
	


	public Company getCompany() {
		return company;
	}

	private void setCompany(Company company) {
		this.company = company;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}
	
	private void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}



	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	
	
	
}
