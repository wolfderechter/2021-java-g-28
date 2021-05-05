package domain;

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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "Employees")
@NamedQueries({
	@NamedQuery(name = "Employee.getEmployeeByUsername" , query = "SELECT s FROM Employee s WHERE s.user.userName = :username")
})
@Access(AccessType.FIELD)
public class Employee extends Account {

	
	@Transient
	private IntegerProperty id;
	//@Transient
	//private StringProperty adress;
	//private Date dateInService;
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
	@Id
	@Access(AccessType.PROPERTY)
	public String getFirstName() {
		return firstName.getValue();
	}
	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}
	
	
	public StringProperty LastName() {
		return lastName;
	}
	@Id
	@Access(AccessType.PROPERTY)
	public String getLastName() {
		return lastName.getValue();
	}
	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}
}
