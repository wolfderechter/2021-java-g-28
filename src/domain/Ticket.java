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
    public String Status;
    public Date DateCreation;
    public String Title;
    public String Description;
    public String Type;
    public int CompanyNr;
    public int ContactPersonId;
    public String PicturePath;
    public List<String> Attachments;
    
    protected Ticket() {
    	
    }
}
