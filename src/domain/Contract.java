package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Contract")
@Table(name = "Contract")
public class Contract {
	@Id
	public int ContractNr;

    public Date EndDate;
    public Date StartDate;
    public ContractEnumStatus Status;
    @ManyToOne()
    @JoinColumn(name="TypeName")
    public ContractType Type;
    @ManyToOne()
    @JoinColumn(name="CompanyNr")
    public Company Company;

    
}
