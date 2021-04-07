package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "Tickets")
@NamedQueries({
	@NamedQuery(name = "Ticket.alleTickets" , query = "SELECT * FROM Tickets")
})
public class Ticket {

	@Id
	public int ticketNr;
    public TicketStatusEnum status;
    public Date dateCreation;
    public String title;
    public String description;
    public TicketTypeEnum type;
    public int companyNr;
    public int contactPersonId;
    public String picturePath;
    public List<String> attachments;
    public List<Reaction> reactions;
    
    protected Ticket() {
    	
    }
}
