package domain;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "Companies")

@Access(AccessType.FIELD)
public class Company {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CompanyNr;
   // private StringProperty companyAdress;
	@Transient
	private StringProperty companyAdress;
	@Transient
    public StringProperty companyName;
    private Date CustomerInitDate;
    
    @OneToMany(mappedBy ="Company")
    public List<Contract> Contracts;
    
   
	@OneToMany(mappedBy ="Company")
    private List<ContactPerson> ContactPersons;
    
    
    public Company() {
    	
    }
    
    public StringProperty CompanyName() {
    	return companyName;
    }
    
    public StringProperty CompanyAdress() {
    	return companyAdress;
    }
    
    
    
    @Access(AccessType.PROPERTY)
    public String getCompanyName() {
    	return companyName.getValue();
    }
    
    public void setCompanyName(String companyName) {
    	this.companyName = new SimpleStringProperty(companyName);
    }
    
	public int getCompanyNr() {
		return CompanyNr;
	}
	public void setCompanyNr(int companyNr) {
		CompanyNr = companyNr;
	}
	@Access(AccessType.PROPERTY)
	public String getCompanyAdress() {
		return companyAdress.getValue();
	}
	
	public void setCompanyAdress(String companyAdress) {
		this.companyAdress = new SimpleStringProperty(companyAdress);
	}
//	public String getCompanyName() {
//		return CompanyName;
//	}
//	public void setCompanyName(String companyName) {
//		CompanyName = companyName;
//	}
	public Date getCustomerInitDate() {
		return CustomerInitDate;
	}
	public void setCustomerInitDate(Date customerInitDate) {
		CustomerInitDate = customerInitDate;
	}
	public List<ContactPerson> getContactPersons() {
		return ContactPersons;
	}
	public void setContactPersons(List<ContactPerson> contactPersons) {
		ContactPersons = contactPersons;
	}
	
	 public List<Contract> getContracts() {
		return Contracts;
	}

	public void setContracts(List<Contract> contracts) {
		Contracts = contracts;
	}
    
    
}
