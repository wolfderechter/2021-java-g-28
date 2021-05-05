package domain;

import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity(name = "ContractTypes")
@Table(name = "ContractTypes")
@Access(AccessType.FIELD)
public class ContractType {
	@Transient
	private StringProperty name;
    private ContractTypeCreationMethod CreationMethod;
    @Column(name = "IsOutsideBusinessHours")
    private boolean OutsideBusinessHours;
    @Column(name = "IsActive")
    private boolean Active;
    private int MaxResponseTime;
    private int MinDuration;
    private double Price;
    @OneToMany(mappedBy = "Type", cascade = CascadeType.PERSIST)
    private List<Contract> contracts;
    
    //constructors
    protected ContractType() {}
    
    
    
    
    
    
    
    
    public ContractType(String name, ContractTypeCreationMethod creationMethod, boolean outsideBusinessHours,
			int maxResponseTime, int minDuration, double price) {
    	setName(name);
		CreationMethod = creationMethod;
		OutsideBusinessHours = outsideBusinessHours;
		MaxResponseTime = maxResponseTime;
		MinDuration = minDuration;
		Price = price;
		Active = true;
	}








	//property's
    public IntegerProperty Amount() {
		return new SimpleIntegerProperty((int) contracts.stream().count());
	}

	public StringProperty Name() {
		return name;
	}

	public BooleanProperty Status() {
			return new SimpleBooleanProperty(Active);
	}
	
	//setters and getters
	@Id
	@Access(AccessType.PROPERTY)
	public String getName() {
		return name.getValue();
	}
	
	public ContractTypeCreationMethod getCreationMethod() {
		return CreationMethod;
	}

	public void setCreationMethod(ContractTypeCreationMethod creationMethod) {
		CreationMethod = creationMethod;
	}

	public boolean isOutsideBusinessHours() {
		return OutsideBusinessHours;
	}

	public void setIsOutsideBusinessHours(boolean isOutsideBusinessHours) {
		OutsideBusinessHours = isOutsideBusinessHours;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean isActive) {
		this.Active = isActive;
	}

	public int getMaxResponseTime() {
		return MaxResponseTime;
	}

	public void setMaxResponseTime(int maxResponseTime) {
		MaxResponseTime = maxResponseTime;
	}

	public int getMinDuration() {
		return MinDuration;
	}

	public void setMinDuration(int minDuration) {
		MinDuration = minDuration;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public void setName(String name) {
		if(name.isEmpty() || name == null)
			throw new IllegalArgumentException("Contract Type Name mag niet leeg zijn");
			
			this.name = new SimpleStringProperty(name);
	}
	
    
}