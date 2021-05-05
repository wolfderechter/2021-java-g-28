package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Companies")
@NamedQueries({
	//@NamedQuery(name = "Company.getCompanyById" , query = "SELECT * FROM Companies WHERE CompanyNr = :id")
})
public class Company {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CompanyNr;
    private String CompanyAdress;
    private String CompanyName;
    private Date CustomerInitDate;
    //private List<Contract> Contracts;
    @OneToMany(mappedBy ="company")
    private List<ContactPerson> ContactPersons;
	public int getCompanyNr() {
		return CompanyNr;
	}
	public void setCompanyNr(int companyNr) {
		CompanyNr = companyNr;
	}
	public String getCompanyAdress() {
		return CompanyAdress;
	}
	public void setCompanyAdress(String companyAdress) {
		CompanyAdress = companyAdress;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
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
    
    
    
}
