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
	private ContactPerson contactPerson;
	private Faq faq;
	private PropertyChangeSupport ticketSubject;
	private PropertyChangeSupport contactPersonSubject;

	private GenericDao<Ticket> ticketRepo;
	private GenericDao<ContactPerson> contactPersonRepo;
	private GenericDao<Faq> faqRepo;
	private GenericDao<Contract> contractRepo;
	// private GenericDao<Employee> employeeRepo;

	// observable list?
	//private List<Ticket> ticketList;
	//private List<ContactPerson> contactPersonList;

	public DomainController() {
		ticketSubject = new PropertyChangeSupport(this);
		contactPersonSubject = new PropertyChangeSupport(this);
		setTicketRepo(new GenericDaoJpa<>(Ticket.class));
		setFaqRepo(new GenericDaoJpa<>(Faq.class));
		setContractRepo(new GenericDaoJpa<>(Contract.class));
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
	
	private void setFaqRepo(GenericDao<Faq> faqRepo) {
		this.faqRepo = faqRepo;
	}
	
	private void setContractRepo(GenericDao<Contract> contractRepo) {
		this.contractRepo = contractRepo;
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
		GenericDaoJpa.startTransaction();
		ticketRepo.update(ticket);
        GenericDaoJpa.commitTransaction();
		
	}
	
	public void setContactPerson(ContactPerson contactPerson) {
		contactPersonSubject.firePropertyChange("contactPerson", this.contactPerson, contactPerson);
		this.contactPerson = contactPerson;
	}
	
	public void updateContactPerson(ContactPerson contactPerson) {
		GenericDaoJpa.startTransaction();
		contactPersonRepo.update(contactPerson);
        GenericDaoJpa.commitTransaction();	
	}
	
	
	

	// als ticket gewijzigd wordt gaat de ticket in editticketpanel ook veranderd
	// worden
	public void addTicketListener(PropertyChangeListener pcl) {
		ticketSubject.addPropertyChangeListener(pcl);
	}
	
	public void addContactPersonListener(PropertyChangeListener pcl) {
		contactPersonSubject.addPropertyChangeListener(pcl);
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
	
	public ObservableList<Faq> getAllFaqs() {
		List<Faq> li = dm.getAllFaqs();
		ObservableList<Faq> obListFaqs = FXCollections.observableList(li);
		return obListFaqs;
	}
	
	public ObservableList<Contract> getAllContracts() {
		List<Contract> li = dm.getAllContracts();
		ObservableList<Contract> obListContracts = FXCollections.observableList(li);
		return obListContracts;
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
