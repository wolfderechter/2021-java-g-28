package domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import javax.persistence.Persistence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class DomainController {

	private DomainManager dm = new DomainManager();

	private Ticket ticket;
	private PropertyChangeSupport ticketSubject;

	private GenericDao<Ticket> ticketRepo;
	private GenericDao<ContactPerson> contactPersonRepo;

	// private GenericDao<Employee> employeeRepo;

	// observable list?
	//private List<Ticket> ticketList;
	//private List<ContactPerson> contactPersonList;

	public DomainController() {
		ticketSubject = new PropertyChangeSupport(this);
		setTicketRepo(new GenericDaoJpa<>(Ticket.class));
		// setEmployeeRepo(new GenericDaoJpa<>(Employee.class));
		setContactPersonRepo(new GenericDaoJpa<>(ContactPerson.class));
		
	}

	// alles voor repo's
	private void setContactPersonRepo(GenericDao<ContactPerson> contactPersonRepo) {
		this.contactPersonRepo = contactPersonRepo;
	}

	private void setTicketRepo(GenericDao<Ticket> ticketRepo) {
		this.ticketRepo = ticketRepo;
	}


	public void close() {
		GenericDaoJpa.closePersistency();
	}

	// bij het zetten van de ticket wordt de ticket in de editticketpanel geset
	public void setTicket(Ticket ticket) {
		ticketSubject.firePropertyChange("ticket", this.ticket, ticket);
		this.ticket = ticket;
		System.out.println(ticket.getTicketNr());
	}

	public void updateTicket(Ticket ticket) {
		GenericDaoJpa.startTransaction();
		ticketRepo.update(ticket);
        GenericDaoJpa.commitTransaction();
	}
	
	public void addReaction(String text) {
		//nog te vervangen met ingelogde usernaam
		ticket.addReaction(text,false,"Nathan Supp Test");
		GenericDaoJpa.startTransaction();
		ticketRepo.update(ticket);
        GenericDaoJpa.commitTransaction();
        ticketSubject.firePropertyChange("ticket", this.ticket, ticket);
	}


	// als ticket gewijzigd wordt gaat de ticket in editticketpanel ook veranderd
	// worden
	public void addTicketListener(PropertyChangeListener pcl) {
		ticketSubject.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		ticketSubject.removePropertyChangeListener(pcl);
	}

	public ObservableList<ContactPerson> getAllContactPersons() {
		List<ContactPerson> li = dm.getAllContactPersons();
		ObservableList<ContactPerson> obListContactPersons = FXCollections.observableList(li);
		return obListContactPersons;
	}

	// nodig voor lijst van tickets voor tableview
	public ObservableList<Ticket> getAllTickets() {
		// WEGGGG
		dm.getAllContactPersons();
		List<Ticket> li = dm.getAllTickets();
		ObservableList<Ticket> obListTickets = FXCollections.observableList(li);
		return obListTickets;
	}

	// nodig voor login
	public ContactPerson getContactPersonByUsername(String username) {
		ContactPerson cp = dm.getContactPersonByUsername(username);
		return cp;
	}

	public SupportManager getSupportManagerByUsername(String username) {
		SupportManager sm = dm.getSupportManagerByUsername(username);
		return sm;
	}

}
