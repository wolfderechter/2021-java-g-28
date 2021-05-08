package domain;

import java.beans.PropertyChangeSupport;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class SupportManagerController extends Controller {

	private Ticket ticket;
	private PropertyChangeSupport ticketSubject;
	private GenericDao<Ticket> ticketRepo;
	private DomainManager dm = new DomainManager();
	private Faq faq;
	private GenericDao<Faq> faqRepo;
	private GenericDao<Contract> contractRepo;

	public SupportManagerController() {
		ticketSubject = new PropertyChangeSupport(this);
		setTicketRepo(new GenericDaoJpa<>(Ticket.class));
		setFaqRepo(new GenericDaoJpa<>(Faq.class));
		setContractRepo(new GenericDaoJpa<>(Contract.class));
	}
	
	private void setFaqRepo(GenericDao<Faq> faqRepo) {
		this.faqRepo = faqRepo;
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

	public SupportManager getSupportManagerByUsername(String username) {
		SupportManager sm = (SupportManager) dm.getAccountByUsername(username, "SupportManager");
		return sm;
	}

}
