package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public interface IContactPerson {

	int getId();

	User getUser();

	String getEmail();

	String getFirstName();

	String getLastName();

	Company getCompany();

	List<Notification> getNotifications();

	List<Contract> getContracts();

}