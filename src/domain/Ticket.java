package domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
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
	private LocalDate dateCreation;
	private String description;
	private TicketTypeEnum type;
	@ManyToOne()
	@JoinColumn(name = "contactPersonId")
	private ContactPerson contactPerson;
	private String picturePath;
	// @Column(name = "FirstName")
	// @ManyToOne(mappedBy = "Attachments")
	// private List<String> attachments;
	@OneToMany(mappedBy = "ticket", cascade = CascadeType.PERSIST)
	private List<Reaction> reactions;
	@ManyToOne()
	@JoinColumn(name = "companyNr")
	private Company company;


	protected Ticket() {

	}
	
	public Ticket(LocalDate creaDate, String title, String description,
			TicketTypeEnum type,ContactPerson contactperson) {
		setDateCreation(creaDate);
		setTitle(title);
		setDescription(description);
		setType(type);
		setContactPerson(contactperson);
		setStatus(TicketStatusEnum.Created);
		
	}

	public Reaction addReaction(String text, boolean isSolution, String nameUser) {
		Reaction reaction = new Reaction(text, isSolution, nameUser, this);
		reactions.add(reaction);
		contactPerson.addNotification(title.getValue());
		return reaction;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Access(AccessType.PROPERTY)
	public Integer getTicketNr() {
		if(ticketNr != null) {
			return ticketNr.intValue();
		}
		return null;
	}

	public void setTicketNr(Integer ticketNr) {
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
	public LocalDate getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDate dateCreation) {
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
	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactperson) {
		this.contactPerson = contactperson;
		this.company = contactperson.getCompany();
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

	public TicketTypeEnum getType() {
		return type;
	}

	public void setType(TicketTypeEnum type) {
		this.type = type;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
