package domain;

import java.beans.Transient;
import java.util.ArrayList;
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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity(name = "ContactPerson")
@Table(name = "ContactPersons")
@Access(AccessType.FIELD)
public class ContactPerson implements IContactPerson {

	
	private IntegerProperty id;
	private StringProperty firstName;
	private StringProperty lastName;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	@OneToMany(mappedBy = "contactPerson",cascade = CascadeType.PERSIST)
	private List<Notification> notifications;
	@ManyToOne
	@JoinColumn(name = "CompanyNr")
	private Company company;
	@OneToMany
	private List<Contract> contracts = new ArrayList<>();
	
	public ContactPerson() {
		
	}
	
	public ContactPerson(String firstName, String lastName, User user, Company company) {
		setFirstName(firstName);
		setLastName(lastName);
		setCompany(company);
		setUser(user);
	}
	

	public IntegerProperty id() {
		return id;
	}
	public StringProperty FirstName() {
		return firstName;
	}
	
	public StringProperty LastName() {
		return lastName;
	}
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Access(AccessType.PROPERTY)
	public Integer getId() {
		if(id != null) {
			return id.intValue();
		}
		return null;
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}
	
	@Override
	public User getUser() {
		return user;
	}
	

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String getEmail() {
		return user.getEmail();
	}
	
	public void setEmail(String email) {
		user.setEmail(email);
	}
	
	@Override
	@Access(AccessType.PROPERTY)
	public String getFirstName() {
		return firstName.getValue();
	}

	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}

	@Override
	@Access(AccessType.PROPERTY)
	public String getLastName() {
		return lastName.getValue();
	}

	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);;
	}

	@Override
	public Company getCompany() {
		return company;
	}

	private void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public List<Notification> getNotifications() {
		return notifications;
	}
	
	private void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public void addNotification(String reaction) {
		notifications.add(new Notification("Reaction",reaction,this));
	}

	@Override
	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	
	public Integer getCompanyNr() {
		return company.getCompanyNr();
	}
	
	
	
	
	
}
