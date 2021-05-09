package domain;


import java.io.Serializable;
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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "Companies")

@Access(AccessType.FIELD)
public class Company implements ICompany {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private IntegerProperty companyNr;
   // private StringProperty companyAdress;
	@Transient
	private StringProperty companyAdress;
	@Transient
    public StringProperty companyName;
    private Date CustomerInitDate;
    
    @OneToMany(mappedBy ="Company")
    public List<Contract> contracts;
    
   
	@OneToMany(mappedBy ="Company", cascade = CascadeType.PERSIST)
    private List<ContactPerson> contactPersons;
    
    public Boolean status;
    
    public Company() {
    	
    }
    
    public IntegerProperty CompanyNr() {
    	return companyNr;
    }
    
    public StringProperty CompanyName() {
    	return companyName;
    }
    
    public StringProperty CompanyAdress() {
    	return companyAdress;
    }
    
    
    @Override
	@Id
    @Access(AccessType.PROPERTY)
	public int getCompanyNr() {
		return companyNr.intValue();
	}
	public void setCompanyNr(int companyNr) {
		this.companyNr = new SimpleIntegerProperty(companyNr);
	}
	
    @Override
	@Access(AccessType.PROPERTY)
    public String getCompanyName() {
    	return companyName.getValue();
    }
    
    public void setCompanyName(String companyName) {
    	this.companyName = new SimpleStringProperty(companyName);
    }
      
	@Override
	@Access(AccessType.PROPERTY)
	public String getCompanyAdress() {
		return companyAdress.getValue();
	}
	
	public void setCompanyAdress(String companyAdress) {
		this.companyAdress = new SimpleStringProperty(companyAdress);
	}

	@Override
	public Date getCustomerInitDate() {
		return CustomerInitDate;
	}
	public void setCustomerInitDate(Date customerInitDate) {
		CustomerInitDate = customerInitDate;
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
	
	public void setStatus(Boolean role) {
		this.status = status;
	}
	
    
    
}
