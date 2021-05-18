package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Id;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface IContractType {

	//property's
	IntegerProperty Amount();

	StringProperty Name();

	BooleanProperty Status();

	//setters and getters
	String getName();

	ContractTypeCreationMethod getCreationMethod();

	boolean isOutsideBusinessHours();

	boolean isActive();

	int getMaxResponseTime();

	double getPrice();

	List<Contract> getContracts();
	
	int getMinDuration();

}