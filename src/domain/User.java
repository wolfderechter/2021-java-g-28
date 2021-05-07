package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "AspNetUsers")

@Access(AccessType.FIELD)
public class User {

	@Id
	@Column(name = "Id")
	private String id;
//	@Column(name = "UserName")
	@Transient
	private StringProperty userName;
	
	public User() {
		
	}
	
	public StringProperty UserName() {
		return userName;
	} 
	
	@Access(AccessType.PROPERTY)
	public String getUserName() {
		return userName.getValue();
	}
	
	public void setUserName(String userName) {
		this.userName = new SimpleStringProperty(userName);
	}
}
