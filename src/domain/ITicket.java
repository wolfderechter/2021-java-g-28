package domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Id;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public interface ITicket {

	IntegerProperty TicketNr();

	StringProperty Title();

	ObjectProperty<TicketStatusEnum> Status();

	Integer getTicketNr();

	String getTitle();

	TicketStatusEnum getStatus();
	
	TicketTypeEnum getType();
	
	LocalDate getDateCreation();

	String getDescription();

	IContactPerson getContactPerson();

	String getPicturePath();

	List<Reaction> getReactions();
	
	Employee getEmployee();
	
	LocalDate getResolvedDate();
	
}