package domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Id;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public interface IEmployee {

	User getUser();

	String getEmail();

	String getPhoneNumber();

	IntegerProperty Id();

	Integer getId();

	StringProperty FirstName();

	String getFirstName();

	StringProperty LastName();

	String getLastName();

	StringProperty Adress();

	String getAdress();
	
	String getUsername();

	ObjectProperty<LocalDate> DateInService();

	LocalDate getDateInService();

	StringProperty Role();

	String getRole();

	Boolean getStatus();

}