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
import javax.persistence.Table;

@Entity
@Table(name = "Companies")
@NamedQueries({
	//@NamedQuery(name = "Company.getCompanyById" , query = "SELECT * FROM Companies WHERE CompanyNr = :id")
})
public class Company {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int CompanyNr;
    public String CompanyAdress;
    public String CompanyName;
    public Date CustomerInitDate;
//    public List<Contract> Contracts;
//    public List<ContactPerson> ContactPersons;
    
}
