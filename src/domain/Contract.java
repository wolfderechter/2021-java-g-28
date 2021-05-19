package domain;

import java.time.LocalDate;
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
@Access(AccessType.FIELD)
public class Contract {
	
	@Transient
	public IntegerProperty contractNr;
	@Transient
	public ObjectProperty<ContractEnumStatus> status;
	
    public ObjectProperty<LocalDate> endDate;
    public ObjectProperty<LocalDate> startDate;
    
    @ManyToOne()
    @JoinColumn(name="TypeName")
    public ContractType type;
    @ManyToOne()
    @JoinColumn(name="CompanyNr")
    public Company company;
    
    
    public Contract() {
    	
    }
    
    public IntegerProperty ContractNr() {
    	return contractNr;
    }
    
    public ObjectProperty<ContractEnumStatus> Status() {
    	return status;
    }
    
    public ObjectProperty<LocalDate> StartDate() {
    	return startDate;
    }
    
    public ObjectProperty<LocalDate> EndDate() {
    	return endDate;
    }
    
    @Access(AccessType.PROPERTY)
    public ContractEnumStatus getStatus() {
    	return status.getValue();
    }
    
    public void setStatus(ContractEnumStatus status) {
    	this.status = new SimpleObjectProperty<ContractEnumStatus>(status);
    }

    
    @Id
    @Access(AccessType.PROPERTY)
    public int getContractNr() {
		return contractNr.intValue();
	}

	public void setContractNr(int contractNr) {
		this.contractNr = new SimpleIntegerProperty(contractNr);
	}
	
	public ContractType getContractType() {
		return type;
	}

	@Access(AccessType.PROPERTY)
	public LocalDate getEndDate() {
		return endDate.getValue();
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = new SimpleObjectProperty(endDate);
	}

	@Access(AccessType.PROPERTY)
	public LocalDate getStartDate() {
		return startDate.getValue();
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = new SimpleObjectProperty(startDate);
	}
	
	
    
    
}
