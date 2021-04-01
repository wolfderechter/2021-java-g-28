package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
	@NamedQuery(name = "Ticket.alleTickets" , query = "SELECT * FROM Tickets")
})
public class Ticket {

	public int ticketNr;
    public String Status;
    public Date DateCreation;
    public String Title;
    public String Description;
    public String Type;
    public int CompanyNr;
    public int ContactPersonId;
    public String PicturePath;
    public List<String> Reactions;
    public List<String> Attachments;
    public Date Now;
    public String Created;
    
    protected Ticket() {
    	
    }
}
