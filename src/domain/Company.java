package domain;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import gui.ContactPersonPanelController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "Companies")

@Access(AccessType.FIELD)
public class Company implements ICompany {

	@Transient
	private IntegerProperty companyNr;
  
	@Transient
	private StringProperty companyAdress;
	@Transient
    public StringProperty companyName;
	
	private LocalDate customerInitDate;
   
    @OneToMany(mappedBy ="Company")
    public List<Contract> contracts;
    
   
	@OneToMany(mappedBy ="Company")
    private List<ContactPerson> contactPersons;
    
    public Boolean status;
    
    public Company() {
    	
    }
    
    public Company(String companyName, String companyAddress, LocalDate date, Boolean status) {
    	setCompanyName(companyName);
    	setCompanyAdress(companyAddress);
    	setCustomerInitDate(date);
    	setStatus(status);
    	
    	
    }
    
    public void addContactPerson(ContactPerson contactPerson) {
    	contactPerson.setCompany(this);
    	contactPersons.add(contactPerson);
    	
    }
    
    @Override
    public IntegerProperty CompanyNr() {
    	return companyNr;
    }
    
    @Override
    public StringProperty CompanyName() {
    	return companyName;
    }
    
    @Override
    public StringProperty CompanyAdress() {
    	return companyAdress;
    }
  
    
    @Override
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
	public Integer getCompanyNr() {
    	if(companyNr != null) {
    		return companyNr.intValue();
    	}
		return null;
	}
	public void setCompanyNr(Integer companyNr) {
		this.companyNr = new SimpleIntegerProperty(companyNr);
	}
	
    @Override
	@Access(AccessType.PROPERTY)
    public String getCompanyName() {
    	return companyName.getValue();
    }
    
    public void setCompanyName(String companyName) {
    	if (companyName == null || companyName.isEmpty()) {
    		throw new IllegalArgumentException("The company name can not be empty!");
    	}
    	this.companyName = new SimpleStringProperty(companyName);
    }
      
	@Override
	@Access(AccessType.PROPERTY)
	public String getCompanyAdress() {
		return companyAdress.getValue();
	}
	
	public void setCompanyAdress(String companyAddress) {
		if (companyAddress == null || companyAddress.isEmpty()) {
    		throw new IllegalArgumentException("The company address can not be empty!");
    	}
		this.companyAdress = new SimpleStringProperty(companyAddress);
	}

	
	
	@Override
	public List<ContactPerson> getContactPersons() {
		return contactPersons;
	}
	public void setContactPersons(List<ContactPerson> contactPersons) {
		contactPersons = contactPersons;
	}
	
	 @Override
	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		contracts = contracts;
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public LocalDate getCustomerInitDate() {
		return customerInitDate;
	}

	public void setCustomerInitDate(LocalDate customerInitDate) {
		this.customerInitDate = customerInitDate;
	}
	
	


	
    
    
}
