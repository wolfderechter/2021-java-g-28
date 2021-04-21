package domain;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    public String title;
    public TicketStatusEnum status;
    private Date dateCreation;
    private String description;
	@ManyToOne()
	@JoinColumn(name="CompanyNr")
    private Company company;
	@ManyToOne()
	@JoinColumn(name="contactPersonId")
    private ContactPerson contactPersonId;
    private String picturePath;
    //@Column(name = "FirstName")
    //@ManyToOne(mappedBy = "Attachments")
    //private List<String> attachments;
    @OneToMany(mappedBy = "ticket")
    private List<Reaction> reactions;
    
    
    
    
    
   public IntegerProperty getTicketNrProp() {
		return new SimpleIntegerProperty(this, "ticketNrProp", ticketNr); 
	}

	public StringProperty getTitleProp() {
		return new SimpleStringProperty(this, "titleProp", title);
	}

	public StringProperty getStatusProp() {
		return new SimpleStringProperty(this, "statusProp", status.toString());
	}

    public Ticket() {
    	
    }




	public int getTicketNr() {
		return ticketNr;
	}




	public void setTicketNr(int ticketNr) {
		this.ticketNr = ticketNr;
	}






	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}

	public TicketStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TicketStatusEnum status) {
		this.status = status;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public ContactPerson getContactPersonId() {
		return contactPersonId;
	}

	public void setContactPersonId(ContactPerson contactPersonId) {
		this.contactPersonId = contactPersonId;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public List<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}




//	public String getStatus() {
//		return status;
//	}
//
//
//
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
}
