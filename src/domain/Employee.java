package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "Employees")
@NamedQueries({
	//@NamedQuery(name = "Employee.getEmployeeByUsername" , query = "SELECT s FROM Employee s WHERE s.user.userName = :username")
})
@Access(AccessType.FIELD)
public abstract class Employee extends Account {

	private IntegerProperty id;
	@Transient
	private StringProperty adress;
	@Transient
	private ObjectProperty<Date> dateInService;
	@Transient
	private StringProperty firstName;
	@Transient
	private StringProperty lastName;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	
	public Employee() {
		
	}
	
	public IntegerProperty Id() {
		return id;
	}
	@Id
	@Access(AccessType.PROPERTY)
	public int getId() {
		return id.intValue();
	}
	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}
	
	
	public StringProperty FirstName() {
		return firstName;
	}
	public String getFirstName() {
		return firstName.getValue();
	}
	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}
	
	
	public StringProperty LastName() {
		return lastName;
	}
	public String getLastName() {
		return lastName.getValue();
	}
	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}
	
	
	public StringProperty Adress() {
		return adress;
	}
	public String getAdress() {
		return adress.getValue();
	}
	public void setAdress(String adress) {
		this.adress = new SimpleStringProperty(adress);
	}
	
	
	public ObjectProperty<Date> DateInService() {
		return dateInService;
	}
	public Date getDateInService() {
		return dateInService.getValue();
	}
	public void setDateInService(Date dateInService) {
		this.dateInService.set(dateInService);
	}
}
