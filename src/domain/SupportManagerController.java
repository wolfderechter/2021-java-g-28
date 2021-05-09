package domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class SupportManagerController extends Controller {

	private IEmployee employee;
	private Ticket ticket;
	private PropertyChangeSupport ticketSubject;
	private GenericDao<Ticket> ticketRepo;
	private DomainManager dm = new DomainManager();
	private Faq faq;
	private GenericDao<Faq> faqRepo;
	private GenericDao<Contract> contractRepo;
	private List<TicketStatusEnum> selectedFilterStatusen = new ArrayList<TicketStatusEnum>();
	private List<TicketTypeEnum> selectedFilterTypes = new ArrayList<TicketTypeEnum>();

	public SupportManagerController(IEmployee emp) {
		ticketSubject = new PropertyChangeSupport(this);
		setTicketRepo(new GenericDaoJpa<>(Ticket.class));
		setFaqRepo(new GenericDaoJpa<>(Faq.class));
		setContractRepo(new GenericDaoJpa<>(Contract.class));
		this.employee = emp;
	}
	
	private void setFaqRepo(GenericDao<Faq> faqRepo) {
		this.faqRepo = faqRepo;
	}
	
	@Override
	public IEmployee getEmployee() {
		return this.employee;
	}
	
	public ObservableList<Faq> getAllFaqs() {
		List<Faq> li = dm.getAllFaqs();
		ObservableList<Faq> obListFaqs = FXCollections.observableList(li);
		return obListFaqs;
	}
	
	private void setContractRepo(GenericDao<Contract> contractRepo) {
		this.contractRepo = contractRepo;
	}

	private void setTicketRepo(GenericDao<Ticket> ticketRepo) {
		this.ticketRepo = ticketRepo;
	}

	public void close() {
		GenericDaoJpa.closePersistency();
	}

	public void addReaction(String text) {
		// nog te vervangen met ingelogde usernaam
		ticket.addReaction(text, false, "Nathan Supp Test");
		dm.updateTicket(ticket);
		
		
	}

	// nodig voor lijst van contractTypes voor tableview van ContractTypePanel
	public ObservableList<ContractType> getAllContractTypes() {
		List<ContractType> li = dm.getAllContractTypes();
		ObservableList<ContractType> obListContractTypes = FXCollections.observableList(li);
		return obListContractTypes;
	}

	public ObservableList<Contract> getAllContracts() {
		List<Contract> li = dm.getAllContracts();
		ObservableList<Contract> obListContracts = FXCollections.observableList(li);
		return obListContracts;
	}
	
	public void createTicket(LocalDate creaDate, String title, String description,
			TicketTypeEnum type,String contactpersonName) {
			ContactPerson contactperson = dm.getContactPersonByUsername(contactpersonName);
			Ticket ticket = new Ticket(creaDate,title,description,type,contactperson);
			dm.createTicket(ticket);
			setTicket(ticket.getTicketNr());
	}
	
	@Override
	// nodig voor lijst van tickets voor tableview van ticketPanel
	public ObservableList<ITicket> getFilteredTickets() {
		ObservableList<Ticket> li = dm.getAllTickets();
		li = li.stream().filter(t->this.selectedFilterTypes.contains(t.getType()))
				.filter(t->this.selectedFilterStatusen.contains(t.getStatus()))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println(li);
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
	// bij het zetten van de ticket wordt de ticket in de editticketpanel geset
	public void setTicket(int ticketNr) {
		Ticket ticket = dm.getAllTickets().stream().filter(t->t.getTicketNr() == ticketNr).findFirst().orElse(null);
		ticketSubject.firePropertyChange("ticket", this.ticket, ticket);
		this.ticket = ticket;
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

}
