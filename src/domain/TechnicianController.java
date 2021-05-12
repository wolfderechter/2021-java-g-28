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
	private IEmployee employee;
	
	public TechnicianController(IEmployee emp) {
		ticketSubject = new PropertyChangeSupport(this);
		this.employee = emp;
	}
	
	@Override
	public IEmployee getEmployee() {
		return this.employee;
	}

	@Override
	public void close() {
		dm.closePersistentie();
	}

	@Override
	// bij het zetten van de ticket wordt de ticket in de editticketpanel geset
	public void setTicket(int ticketNr) {
		Ticket ticket = dm.getAllTickets().stream().filter(t->t.getTicketNr() == ticketNr).findFirst().orElse(null);
		ticketSubject.firePropertyChange("ticket", this.ticket, ticket);
		this.ticket = ticket;
	}

	@Override
	public void updateTicket(TicketStatusEnum status,String descrip, String first, String last) {
		//changes in de edit panel toepassen
		ticket.setStatus(status);
		ticket.setDescription(descrip);
		Employee employee = dm.getEmployeeByFirstAndLastName(first, last);
		System.out.println(employee.getFirstName());
		ticket.setEmployee(employee);
		//ticket changen in de panel
		dm.updateTicket(ticket);
	}

	public void addReaction(String text) {
		// nog te vervangen met ingelogde usernaam
		ticket.addReaction(text, false, employee.getUser().getUserName());
		dm.updateTicket(ticket);
	}

	@Override
	// als ticket gewijzigd wordt gaat de ticket in editticketpanel ook veranderd
	// worden
	public void addTicketListener(PropertyChangeListener pcl) {
		ticketSubject.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		ticketSubject.removePropertyChangeListener(pcl);
	}
	
	@Override
	// nodig voor lijst van tickets voor tableview van ticketPanel
	public ObservableList<ITicket> getFilteredTickets() {
		ObservableList<Ticket> li = dm.getAllTickets();
		li = li.stream().filter(t->this.selectedFilterTypes.contains(t.getType()))
				.filter(t->this.selectedFilterStatusen.contains(t.getStatus()))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		return (ObservableList<ITicket>) (Object) li;
	}
	
	@Override
	//deze methode gaat de lijst filteren die in table van tickets wordt gestoken
	public void addStatusFilterOnTickets(List<? extends TicketStatusEnum> added) {
		this.selectedFilterStatusen.addAll(added);
	}

	@Override
	public void removeStatusFilterOnTickets(List<? extends TicketStatusEnum> removed) {
		this.selectedFilterStatusen.removeAll(removed);
	}

	@Override
	public void addTypeFilterOnTickets(List<? extends TicketTypeEnum> added) {
		this.selectedFilterTypes.addAll(added);
	}
	
	@Override
	public void removeTypeFilterOnTickets(List<? extends TicketTypeEnum> removed) {
		this.selectedFilterTypes.removeAll(removed);
	}
	
	@Override
	public List<String> getAllCompanyNames() {
		List<String> li = dm.getAllCompanies().stream().map(Company::getCompanyName).collect(Collectors.toList());
		return li;
	}

	@Override
	public List<String> getContactPersonFromCompanyName(String companyName) {
		List<ContactPerson> li = dm.getAllCompanies().stream().filter(c->c.getCompanyName() == companyName).map(c->c.getContactPersons()).findFirst().orElse(null);
		List<String> liString = li.stream().map(c->c.getUser().getUserName()).collect(Collectors.toList());
		return liString;
	}
	
	@Override
	public List<String> getAllEmployeesCombo(){
		return dm.getAllEmployees().stream().map(employee -> String.format("%s %s, %s", employee.getFirstName(), employee.getLastName(), employee.getRole())).collect(Collectors.toList());
	}
	
	// voor het aantal behandelde tickets per contractType
//	public int getProcessedTicketPerContractType(ContractType type) {
//
//	}
}
