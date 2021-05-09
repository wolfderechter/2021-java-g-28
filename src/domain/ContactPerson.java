package domain;

import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
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
@Access(AccessType.FIELD)
public class ContactPerson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private StringProperty firstName;
	private StringProperty lastName;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	@OneToMany(mappedBy = "contactPerson",cascade = CascadeType.PERSIST)
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
		checkContracts();
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setCompany(company);
		setNotifications(notifications);
	}
	
	public void checkContracts() {
		if(contracts.stream().map(c->c.getStatus()).filter(c->c == ContractEnumStatus.Running).count() == 0)
			throw new IllegalArgumentException("Customer doesn't have an active contract");
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
	
	public String getEmail() {
		return user.getEmail();
	}
	
	public void setEmail(String email) {
		user.setEmail(email);
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
