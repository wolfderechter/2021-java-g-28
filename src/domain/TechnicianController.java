package domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;

public class TechnicianController extends Controller {

	private Ticket ticket;
	private List<TicketStatusEnum> selectedFilterStatusen = new ArrayList<TicketStatusEnum>();
	private List<TicketTypeEnum> selectedFilterTypes = new ArrayList<TicketTypeEnum>();
	private PropertyChangeSupport ticketSubject;
	
	private DomainManager dm = new DomainManager();

	public TechnicianController() {
		ticketSubject = new PropertyChangeSupport(this);
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
		
	}

	public void addReaction(String text) {
		// nog te vervangen met ingelogde usernaam
		ticket.addReaction(text, false, "Nathan Supp Test");
		dm.updateTicket(ticket);
		
		
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
	public ObservableList<ITicket> getFilteredTickets() {
		ObservableList<Ticket> li = dm.getAllTickets();
		li = li.stream().filter(t->this.selectedFilterTypes.contains(t.getType()))
				.filter(t->this.selectedFilterStatusen.contains(t.getStatus()))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		return (ObservableList<ITicket>) (Object) li;
	}
	//deze methode gaat de lijst filteren die in table van tickets wordt gestoken
	public void addStatusFilterOnTickets(List<? extends TicketStatusEnum> added) {
		this.selectedFilterStatusen.addAll(added);
		
	}

	public void removeStatusFilterOnTickets(List<? extends TicketStatusEnum> removed) {
		this.selectedFilterStatusen.removeAll(removed);
	}

	public void addTypeFilterOnTickets(List<? extends TicketTypeEnum> added) {
		this.selectedFilterTypes.addAll(added);
	}
	
	public void removeTypeFilterOnTickets(List<? extends TicketTypeEnum> removed) {
		this.selectedFilterTypes.removeAll(removed);
	}
	
	public IEmployee getTechnicianByUsername(String username) {
		return dm.getEmployeeByUsername(username, "TE");
	}

	public void createTicket(LocalDate creaDate, String title, String description,
			TicketTypeEnum type,String contactpersonName) {
			ContactPerson contactperson = dm.getContactPersonByUsername(contactpersonName);
			Ticket ticket = new Ticket(creaDate,title,description,type,contactperson);
			dm.createTicket(ticket);
			setTicket(ticket.getTicketNr());
	}
	
	public List<String> getAllCompanyNames() {
		List<String> li = dm.getAllCompanies().stream().map(Company::getCompanyName).collect(Collectors.toList());
		return li;
	}

	public List<String> getContactPersonFromCompanyName(String companyName) {
		List<ContactPerson> li = dm.getAllCompanies().stream().filter(c->c.getCompanyName() == companyName).map(c->c.getContactPersons()).findFirst().orElse(null);
		List<String> liString = li.stream().map(c->c.getUser().getUserName()).collect(Collectors.toList());
		return liString;
	}
	
	
	

	// voor het aantal behandelde tickets per contractType
//	public int getProcessedTicketPerContractType(ContractType type) {
//
//	}

}
