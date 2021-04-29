package domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DomainController {

private DomainManager dm = new DomainManager();
	
	private Ticket ticket;
	private PropertyChangeSupport ticketSubject;
	
	public DomainController() {
		ticketSubject= new PropertyChangeSupport(this);
	}
	//bij het zetten van de ticket wordt de ticket in de editticketpanel geset
	public void setTicket(Ticket ticket) {
		ticketSubject.firePropertyChange("ticket", this.ticket, ticket);
		this.ticket = ticket;
	}
	// als ticket gewijzigd wordt gaat de ticket in editticketpanel ook veranderd worden
	public void addTicketListener(PropertyChangeListener pcl) {
	    ticketSubject.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
    	ticketSubject.removePropertyChangeListener(pcl); 
    }
	
	public ObservableList<ContactPerson>  getAllContactPersons() {
        List<ContactPerson> li = dm.getAllContactPersons();
        ObservableList<ContactPerson> obListContactPersons = FXCollections.observableList(li);
        return obListContactPersons;
    }
	//nodig voor lijst van tickets voor tableview
	public ObservableList<Ticket>  getAllTickets() {
        List<Ticket> li = dm.getAllTickets();
        ObservableList<Ticket> obListTickets = FXCollections.observableList(li);
        return obListTickets;
    }
	//nodig voor login
	public ContactPerson getContactPersonByUsername(String username) {
        ContactPerson cp = dm.getContactPersonByUsername(username);
        return cp;
    }

    public SupportManager getSupportManagerByUsername(String username) {
        SupportManager sm = dm.getSupportManagerByUsername(username);
        return sm;
    }
	
	public void close() {
        dm.closePersistentie();
    }
	
	
	

	
}
