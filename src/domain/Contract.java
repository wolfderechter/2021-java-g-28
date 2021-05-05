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
public class Contract {
	
	@Transient
	public IntegerProperty contractNr;

//    public Date EndDate;
//    public ContractEnumStatus Status;
//
//    public ContractType Type;
	 @ManyToOne
	 @JoinColumn(name = "CompanyNr")
	 public Company Company;

    public Contract() {
    	
    }
    
    public IntegerProperty ContractNr() {
    	return contractNr;
    }
    
    @Id
    @Access(AccessType.PROPERTY)
    public int getContractNr() {
		return contractNr.intValue();
	}

	public void setContractNr(int contractNr) {
		this.contractNr = new SimpleIntegerProperty(contractNr);
	}
    
    
}
