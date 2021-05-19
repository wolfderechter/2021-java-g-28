package domain;

import java.util.ArrayList;
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
@Table(name = "ContactTypes")
@Access(AccessType.FIELD)
public class ContractType implements IContractType {
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
	private List<Contract> contracts = new ArrayList<>();

	// constructors
	protected ContractType() {
	}

	public ContractType(String name, ContractTypeCreationMethod creationMethod, boolean outsideBusinessHours,
			int maxResponseTime, int minDuration, double price) {
		setName(name);
		setCreationMethod(creationMethod);
		setIsOutsideBusinessHours(outsideBusinessHours);
		setMaxResponseTime(maxResponseTime);
		setMinDuration(minDuration);
		setPrice(price);
		setActive(true);
	}

	// property's
	@Override
	public IntegerProperty Amount() {
		return new SimpleIntegerProperty((int) contracts.stream().count());
	}

	@Override
	public StringProperty Name() {
		return name;
	}

	@Override
	public BooleanProperty Status() {
		return new SimpleBooleanProperty(Active);
	}

	// setters and getters
	@Override
	@Id
	@Access(AccessType.PROPERTY)
	public String getName() {
		return name.getValue();
	}

	@Override
	public ContractTypeCreationMethod getCreationMethod() {
		return CreationMethod;
	}

	public void setCreationMethod(ContractTypeCreationMethod creationMethod) {
		CreationMethod = creationMethod;
	}

	@Override
	public boolean isOutsideBusinessHours() {
		return OutsideBusinessHours;
	}

	public void setIsOutsideBusinessHours(boolean isOutsideBusinessHours) {
		OutsideBusinessHours = isOutsideBusinessHours;
	}

	@Override
	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean isActive) {
		this.Active = isActive;
	}

	@Override
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

	@Override
	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	@Override
	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public void setName(String name) {
		if (name.isEmpty() || name == null)
			throw new IllegalArgumentException("Contract Type Name can't be empty");
		this.name = new SimpleStringProperty(name);
	}

}