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
	private DomainManager dm = new DomainManager();

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
	public void setTicket(int ticketNr) {
		Ticket ticket = dm.getAllTickets().stream().filter(t->t.getTicketNr() == ticketNr).findFirst().orElse(null);
		ticketSubject.firePropertyChange("ticket", this.ticket, ticket);
		this.ticket = ticket;
		
	}

	public void updateTicket(TicketStatusEnum status,String descrip) {
		//changes in de edit panel toepassen
		ticket.setStatus(status);
		ticket.setDescription(descrip);
		//ticket changen in de panel
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
	public ObservableList<ITicket> getAllTickets() {
		// WEGGGG
		// dm.getAllContactPersons();
		ObservableList<Ticket> li = dm.getAllTickets();
		
		return (ObservableList<ITicket>) (Object) li;
	}

	// voor het aantal behandelde tickets per contractType
//	public int getProcessedTicketPerContractType(ContractType type) {
//
//	}

}
