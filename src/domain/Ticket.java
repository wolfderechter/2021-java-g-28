package domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Access(AccessType.FIELD)
public class Ticket implements ITicket {

	@Transient
	private IntegerProperty ticketNr;
	@Transient
	private StringProperty title;
	@Transient
	private ObjectProperty<TicketStatusEnum> status;
	private Date dateCreation;
	private String description;
	@ManyToOne()
	@JoinColumn(name = "contactPersonId")
	private ContactPerson contactPerson;
	private String picturePath;
	// @Column(name = "FirstName")
	// @ManyToOne(mappedBy = "Attachments")
	// private List<String> attachments;
	@OneToMany(mappedBy = "ticket", cascade = CascadeType.PERSIST)
	private List<Reaction> reactions;

	protected Ticket() {

	}

	public void addReaction(String text, boolean isSolution, String nameUser) {
		reactions.add(new Reaction(text, isSolution, nameUser, this));
		contactPerson.addNotification(title.getValue());
	}

	@Override
	public IntegerProperty TicketNr() {
		return ticketNr;
	}

	@Override
	public StringProperty Title() {
		return title;
	}

	@Override
	public ObjectProperty<TicketStatusEnum> Status() {
		return status;
	}

	@Override
	@Id
	@Access(AccessType.PROPERTY)
	public int getTicketNr() {
		return ticketNr.intValue();
	}

	public void setTicketNr(int ticketNr) {

		this.ticketNr = new SimpleIntegerProperty(ticketNr);
	}

	@Override
	@Access(AccessType.PROPERTY)
	public String getTitle() {
		return title.getValue();
	}

	public void setTitle(String title) {
		this.title = new SimpleStringProperty(title);
	}

	@Override
	@Access(AccessType.PROPERTY)
	public TicketStatusEnum getStatus() {
		return status.getValue();
	}

	public void setStatus(TicketStatusEnum status) {

		this.status = new SimpleObjectProperty<TicketStatusEnum>(status);
	}

	@Override
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public ContactPerson getContactPersonId() {
		return contactPerson;
	}

	public void setContactPersonId(ContactPerson contactPersonId) {

		this.contactPerson = contactPersonId;
	}

	@Override
	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	@Override
	public List<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}
}
