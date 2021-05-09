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

	int getId();

	void setId(int id);

	StringProperty FirstName();

	String getFirstName();

	void setFirstName(String firstName);

	StringProperty LastName();

	String getLastName();

	void setLastName(String lastName);

	StringProperty Adress();

	String getAdress();

	void setAdress(String adress);

	ObjectProperty<LocalDate> DateInService();

	LocalDate getDateInService();

	void setDateInService(LocalDate dateInService);

	StringProperty Role();

	String getRole();

	void setRole(String role);

	void setStatus(Boolean status);

	Boolean getStatus();

}