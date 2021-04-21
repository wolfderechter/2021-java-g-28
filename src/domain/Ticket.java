package domain;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
public class Ticket {

	@Id
	private int ticketNr;
	//private SimpleIntegerProperty ticketNrProp = new SimpleIntegerProperty();
    //private SimpleStringProperty titleProp = new SimpleStringProperty();
    //private SimpleIntegerProperty statusProp = new SimpleIntegerProperty();
	private String title;
	private int status;
    private Date dateCreation;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "CompanyNr")
    private Company Company;
    @ManyToOne
    @JoinColumn(name = "contactPersonId")
    private ContactPerson contactPerson;
    private String picturePath;
    //private List<String> attachments;
    @OneToMany(mappedBy = "ticket")
    private List<Reaction> reactions;
    
    
    
    
    /*public IntegerProperty getTicketNrProp() {
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
	}*/
    
    //public IntegerProperty getTicketNr() {
    //	return ticketNrProp;
    //}
    
   // public IntegerProperty getStatus() {
    //	return statusProp;
    //}
    
   // public StringProperty getTitel() {
   // titleProp;
    //}

    protected Ticket() {
    	
    }
}
