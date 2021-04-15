package domain;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity(name = "Tickets")
@Table(name = "Tickets")
@NamedQueries({
	@NamedQuery(name = "Ticket.alleTickets" , query = "SELECT d FROM Tickets d")
})
public class Ticket implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int ticketNr;
	public IntegerProperty ticketNrProp = new SimpleIntegerProperty();
    public StringProperty titleProp = new SimpleStringProperty();
    public StringProperty statusProp = new SimpleStringProperty();;
    private Date dateCreation;
    private String description;
    private Company Company;
    private int contactPersonId;
    private String picturePath;
    private List<String> attachments;
    private List<Reaction> reactions;
    
    
    
    
    public IntegerProperty getTicketNrProp() {
    	if (ticketNrProp==null) {
			ticketNrProp = new SimpleIntegerProperty(this, "ticketNrProp", ticketNr);
		}
		return ticketNrProp ;
	}

	public StringProperty getTitleProp() {
		if (titleProp==null) {
			titleProp = new SimpleStringProperty(this, "titleProp", title);
		}
		return titleProp;
	}

	public StringProperty getStatusProp() {
		if (statusProp==null) {
			statusProp = new SimpleStringProperty(this, "statusProp", status.toString());
		}
		return statusProp;
	}

    protected Ticket() {
    	
    }
}
