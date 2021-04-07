package domain;

import java.util.Date;
import java.util.List;

public class Company {

	public int CompanyNr;
    public String CompanyAdress;
    public String CompanyName;
    public Date CustomerInitDate;
    public List<Contract> Contracts;
    public List<ContactPerson> ContactPersons;
    
}
