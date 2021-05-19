package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity
@Table(name = "Employees")
@Access(AccessType.FIELD)
public class Employee implements IEmployee {
	@Transient
	private IntegerProperty id;

	private StringProperty adress;
	
	private ObjectProperty<LocalDate> dateInService;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty role;
	private Boolean status;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;

	
	public Employee() {
		
	}
	
	public Employee(LocalDate creationDate, String firstName, String lastName, String adress, String role, String phoneNumber, String email, String username, boolean isActive, User user) {
		setDateInService(creationDate);
		setFirstName(firstName);
		setLastName(lastName);
		setAdress(adress);
		setRole(role);
		//setPhoneNumber(phoneNumber);
		//setEmail(email);
		//setUsername(username);
		setStatus(isActive);
		setUser(user);
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
	public String getPhoneNumber() {
		return user.getPhoneNumber();
	}
	
	public void setEmail(String email) {
		user.setEmail(email);
	}
	public void setPhoneNumber(String phonenumber) {
		user.setPhoneNumber(phonenumber);
	}
	public void setUsername(String username) {
		user.setUserName(username);
	}
	public String getUsername() {
		return user.getUserName();
	}
	
	public IntegerProperty Id() {
		return id;
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
	public StringProperty FirstName() {
		return firstName;
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
	public StringProperty LastName() {
		return lastName;
	}
	@Override
	@Access(AccessType.PROPERTY)
	public String getLastName() {
		return lastName.getValue();
	}
	
	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}
	
	
	@Override
	public StringProperty Adress() {
		return adress;
	}
	@Override
	@Access(AccessType.PROPERTY)
	public String getAdress() {
		return adress.getValue();
	}
	
	public void setAdress(String adress) {
		this.adress = new SimpleStringProperty(adress);
	}
	
	
	@Override
	public ObjectProperty<LocalDate> DateInService() {
		return dateInService;
	}
	@Override
	@Access(AccessType.PROPERTY)
	public LocalDate getDateInService() {
		return dateInService.getValue();
	}
	
	public void setDateInService(LocalDate dateInService) {
		this.dateInService = new SimpleObjectProperty<LocalDate>(dateInService);
	}
	
	
	
	@Override
	public StringProperty Role() {
		return role;
	}
	@Override
	@Access(AccessType.PROPERTY)
	public String getRole() {
		return role.getValue();
	}
	
	public void setRole(String role) {
		this.role = new SimpleStringProperty(role);
	}
	

	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Boolean getStatus() {
		return this.status;
	}
}
