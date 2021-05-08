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
@Table(name = "SupportManagers")
@NamedQueries({
	@NamedQuery(name = "Technician.getTechnicianByUsername" , query = "SELECT s FROM Technician s WHERE s.user.userName = :username")
})
public class SupportManager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String adress;
	private Date dateInService;
	private String firstName;
	private String lastName;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	
	public User getUser() {
		return this.user;
	}
	
//	public int Id() {
//		return id;
//	}
//
//	public int getId() {
//		return this.id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getFirstName() {
//		return this.firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	
//	public String getLastName() {
//		return this.lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
}
