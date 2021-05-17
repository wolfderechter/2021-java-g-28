package domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Id;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public interface ICompany {

	IntegerProperty CompanyNr();
	
	StringProperty CompanyAdress();
	
	StringProperty CompanyName();
	
	Integer getCompanyNr();
		
	String getCompanyName();

	String getCompanyAdress();

	LocalDate getCustomerInitDate();

	List<ContactPerson> getContactPersons();

	List<Contract> getContracts();


	boolean getStatus();


	void setCustomerInitDate(LocalDate customerInitDate);

}