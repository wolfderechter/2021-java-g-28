package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

@Entity(name = "Contracts")
@Table(name = "Contracts")

@Access(AccessType.FIELD)
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
	
	@Transient
	public IntegerProperty contractNr;

    public Date EndDate;
    public Date StartDate;
    public ContractEnumStatus Status;
    @ManyToOne()
    @JoinColumn(name="TypeName")
    public ContractType Type;
    @ManyToOne()
    @JoinColumn(name="CompanyNr")
    public Company Company;
    @Id
    @Access(AccessType.PROPERTY)
    public int getContractNr() {
		return contractNr.intValue();
	}
    public Contract() {
    	
    }
    
    public IntegerProperty ContractNr() {
    	return contractNr;
    }
    

	public void setContractNr(int contractNr) {
		this.contractNr = new SimpleIntegerProperty(contractNr);
	}
    
    
}
