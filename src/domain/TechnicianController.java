package domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class TechnicianController extends Controller {

	private Ticket ticket;
	private PropertyChangeSupport ticketSubject;
	private GenericDao<Ticket> ticketRepo;

	public TechnicianController() {
		ticketSubject = new PropertyChangeSupport(this);
		setTicketRepo(new GenericDaoJpa<>(Ticket.class));
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

	}

	public void updateTicket(Ticket ticket) {
		ticketSubject.firePropertyChange("ticket", this.ticket, ticket);
		GenericDaoJpa.startTransaction();
		ticketRepo.update(ticket);
		GenericDaoJpa.commitTransaction();
	}

	public void addReaction(String text) {
		// nog te vervangen met ingelogde usernaam
		ticket.addReaction(text, false, "Nathan Supp Test");
		GenericDaoJpa.startTransaction();
		ticketRepo.update(ticket);
		GenericDaoJpa.commitTransaction();
	}

	// als ticket gewijzigd wordt gaat de ticket in editticketpanel ook veranderd
	// worden
	public void addTicketListener(PropertyChangeListener pcl) {
		ticketSubject.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		ticketSubject.removePropertyChangeListener(pcl);
	}

	// nodig voor lijst van tickets voor tableview van ticketPanel
	public ObservableList<Ticket> getAllTickets() {
		// WEGGGG
		// dm.getAllContactPersons();
		List<Ticket> li = dm.getAllTickets();
		ObservableList<Ticket> obListTickets = FXCollections.observableList(li);
		return obListTickets;
	}

	// voor het aantal behandelde tickets per contractType
	public int getProcessedTicketPerContractType(ContractType type) {

	}

}
