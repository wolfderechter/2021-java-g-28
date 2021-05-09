package domain;

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
		GenericDaoJpa.startTransaction();
		ticketRepo.update(ticket);
		GenericDaoJpa.commitTransaction();
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
		return (ObservableList<ITicket>) (Object) li;
	}

}
