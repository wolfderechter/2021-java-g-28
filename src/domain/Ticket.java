package domain;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
import javax.persistence.Transient;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity(name = "Tickets")
@Table(name = "Tickets")
@NamedQueries({
	@NamedQuery(name = "Ticket.getAll" , query = "SELECT d FROM Tickets d")
})
@Access(AccessType.FIELD)
public class Ticket {
	
	@Transient
	private IntegerProperty ticketNr;
	@Transient
    private StringProperty title;
	@Transient
    private ObjectProperty<TicketStatusEnum> status;
    private Date dateCreation;
    private String description;
	@ManyToOne()
	@JoinColumn(name="contactPersonId")
    private ContactPerson contactPersonId;
    private String picturePath;
    //@Column(name = "FirstName")
    //@ManyToOne(mappedBy = "Attachments")
    //private List<String> attachments;
    @OneToMany(mappedBy = "ticket")
    private List<Reaction> reactions;
    
    public Ticket() {
    	
    }
    
   
    
    public void  addReaction(String text,boolean isSolution,String nameUser) {
    	reactions.add(new Reaction(text, isSolution, nameUser, this));
    	
    }
   
   public IntegerProperty TicketNr() {
		return ticketNr; 
	}

	public StringProperty Title() {
		return title;
	}

	public ObjectProperty<TicketStatusEnum> Status() {
		return status;
	}
	@Id
	@Access(AccessType.PROPERTY)
	public int getTicketNr() {
		return ticketNr.intValue();
	}
	public void setTicketNr(int ticketNr) {
		
		this.ticketNr = new SimpleIntegerProperty(ticketNr);
	}
	
	@Access(AccessType.PROPERTY)
	public String getTitle() {
		return title.getValue();
	}

	public void setTitle(String title) {
		this.title = new SimpleStringProperty(title);
	}
	@Access(AccessType.PROPERTY)
	public TicketStatusEnum getStatus() {
		return status.getValue();
	}

	public void setStatus(TicketStatusEnum status) {
		
		this.status = new SimpleObjectProperty<TicketStatusEnum>(status);
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
}
