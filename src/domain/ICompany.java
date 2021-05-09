package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Id;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface ICompany {

	IntegerProperty CompanyNr();
	
	StringProperty CompanyAdress();
	
	StringProperty CompanyName();
	
	int getCompanyNr();

	String getCompanyName();

	String getCompanyAdress();

	//	public String getCompanyName() {
	//		return CompanyName;
	//	}
	//	public void setCompanyName(String companyName) {
	//		CompanyName = companyName;
	//	}
	Date getCustomerInitDate();

	List<ContactPerson> getContactPersons();

	List<Contract> getContracts();


	boolean getStatus();
}