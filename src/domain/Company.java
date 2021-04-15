package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Company implements Serializable {

	public int CompanyNr;
    public String CompanyAdress;
    public String CompanyName;
    public Date CustomerInitDate;
    public List<Contract> Contracts;
    public List<ContactPerson> ContactPersons;
    
}
