package domain;

import java.util.List;
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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "ContactPersons")
@NamedQueries({
	@NamedQuery(name = "ContactPerson.getContactpersonByUsername" , query = "SELECT c FROM ContactPerson c WHERE c.user.userName = :username"),
	@NamedQuery(name = "ContactPerson.getAll" , query = "SELECT c FROM ContactPerson c")

})
public class ContactPerson extends Account {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	@Column(name = "FirstName")
	private String firstName;
	@Column(name = "LastName")
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	@OneToMany(mappedBy = "contactPerson")
	private List<Notification> notifications;
	@ManyToOne
	@JoinColumn(name = "CompanyNr")
	private Company company;
	
	public ContactPerson() {
		
	}
	
	public ContactPerson(int id, String firstName, String lastName, Company company, List<Notification> notifications) {
		
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setCompany(company);
		setNotifications(notifications);
		
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	private void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	//gui screen
	public StringProperty getUserNameProp() {
		return new SimpleStringProperty(this, "userNameProp", user.getUserName()); 
	}

	public StringProperty getFirstNameProp() {
		return new SimpleStringProperty(this, "firstNameProp", firstName);
	}

	public StringProperty getLastNameProp() {
		return new SimpleStringProperty(this, "lastNameProp", lastName);
	}
		
	public StringProperty getCompanyProp() {
		return new SimpleStringProperty(this, "companyProp", company.getCompanyName());
	}

//	public StringProperty getStatusProp() {
//		return new SimpleStringProperty(this, "statusProp", status.ToString());
//	}
	
}
