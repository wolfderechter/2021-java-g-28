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
	private ContractType cType;
	private PropertyChangeSupport cTypeSubject;
	private DomainManager dm = new DomainManager();
	private IFaq faq;
	private List<TicketStatusEnum> selectedFilterStatusen = new ArrayList<>();
	private List<TicketTypeEnum> selectedFilterTypes = new ArrayList<>();
	private List<String> statusFilterContractType = new ArrayList<>();

	public SupportManagerController(IEmployee emp) {
		ticketSubject = new PropertyChangeSupport(this);
		cTypeSubject = new PropertyChangeSupport(this);
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

	public void addReaction(String text) {
		// nog te vervangen met ingelogde usernaam
		ticket.addReaction(text, false, "Nathan Supp Test");
		dm.updateTicket(ticket);
	}

	// nodig voor lijst van contractTypes voor tableview van ContractTypePanel
	

	public ObservableList<Contract> getAllContracts() {
		List<Contract> li = dm.getAllContracts();
		ObservableList<Contract> obListContracts = FXCollections.observableList(li);
		return obListContracts;
	}

	public ObservableList<IFaq> getAllFaqs() {
		ObservableList<Faq> li = dm.getAllFaqs();
		return (ObservableList<IFaq>) (Object) li;
	}

	public void createTicket(LocalDate creaDate, String title, String description, TicketTypeEnum type,
			String contactpersonName, String employeeFristName, String employeeLastName) {
		ContactPerson contactperson = dm.getContactPersonByUsername(contactpersonName);
		Employee employee = dm.getEmployeeByFirstAndLastName(employeeFristName, employeeLastName);
		Ticket ticket = new Ticket(creaDate, title, description, type, contactperson, employee);
		dm.createTicket(ticket);
		setTicket(ticket.getTicketNr());
	}

	public void createContractType(String name, int maxResponse, ContractTypeCreationMethod creationMethod,
			boolean is24Hours, int duration, double price) {
		if(dm.getAllContractTypes().stream().map(c->c.getName()).anyMatch(c->c==name)) {
			throw new IllegalArgumentException("there already exist a contract type with that name");
		}
		ContractType contractType = new ContractType(name, creationMethod, is24Hours, maxResponse, duration, price);
		dm.createContractType(contractType);

	}

	@Override
	// nodig voor lijst van tickets voor tableview van ticketPanel
	public ObservableList<ITicket> getFilteredTickets() {
		ObservableList<Ticket> li = dm.getAllTickets();
		li = li.stream().filter(t -> this.selectedFilterTypes.contains(t.getType()))
				.filter(t -> this.selectedFilterStatusen.contains(t.getStatus()))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println(li);
		return (ObservableList<ITicket>) (Object) li;
	}
	
	public ObservableList<IContractType> getFilteredContractTypes() {
		boolean getAll = statusFilterContractType.contains("Active") && statusFilterContractType.contains("Inactive");
		boolean getActive = statusFilterContractType.contains("Active");
		boolean getInActive = statusFilterContractType.contains("Inactive");
		List<ContractType> li = dm.getAllContractTypes();
		if(getAll) {
				ObservableList<ContractType> obListContractTypes = FXCollections.observableList(li);
				return (ObservableList<IContractType>) (Object) obListContractTypes;
			}else if(getActive){
			return li.stream().filter(c->c.isActive() == true).collect(Collectors.toCollection(FXCollections::observableArrayList));
			}else if(getInActive) {
				return li.stream().filter(c->c.isActive() == false).collect(Collectors.toCollection(FXCollections::observableArrayList));
			}else {
				List<IContractType> emptyList = new ArrayList<>();
				return FXCollections.observableArrayList(emptyList);
			}
		}
	public ObservableList<ITicket> getFiltererdTickets(){
		ObservableList<Ticket> li = dm.getAllTickets();
		li = li.stream().filter(t -> this.selectedFilterTypes.contains(t.getType()))
				.filter(t -> this.selectedFilterStatusen.contains(t.getStatus()))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		System.out.println(li);
		return (ObservableList<ITicket>) (Object) li;
	}

	@Override
	// deze methode gaat de lijst filteren die in table van tickets wordt gestoken
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

	public void addStatusFilterOnContractType(List<? extends String> addList) {
		statusFilterContractType.addAll(addList);
	}

	public void RemoveStatusFilterOnContractType(List<? extends String> removeList) {
		statusFilterContractType.removeAll(removeList);
	}

	@Override
	// bij het zetten van de ticket wordt de ticket in de editticketpanel geset
	public void setTicket(int ticketNr) {
		Ticket ticket = dm.getAllTickets().stream().filter(t -> t.getTicketNr() == ticketNr).findFirst().orElse(null);
		ticketSubject.firePropertyChange("ticket", this.ticket, ticket);
		this.ticket = ticket;
	}

	public void setContractType(String contractTypeName) {
		ContractType contractType = dm.getAllContractTypes().stream().filter(t -> t.getName() == contractTypeName)
				.findFirst().orElse(null);
		cTypeSubject.firePropertyChange("contactType", this.cType, contractType);
		this.cType = contractType;
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

	public void addContractTypeListener(PropertyChangeListener pcl) {
		cTypeSubject.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeContractTypeListener(PropertyChangeListener pcl) {
		cTypeSubject.removePropertyChangeListener(pcl);
	}

	@Override
	public List<String> getAllCompanyNames() {
		List<String> li = dm.getAllCompanies().stream().map(Company::getCompanyName).collect(Collectors.toList());
		return li;
	}

	@Override
	public List<String> getContactPersonFromCompanyName(String companyName) {
		List<ContactPerson> li = dm.getAllCompanies().stream().filter(c -> c.getCompanyName() == companyName)
				.map(c -> c.getContactPersons()).findFirst().orElse(null);
		List<String> liString = li.stream().map(c -> c.getUser().getUserName()).collect(Collectors.toList());
		return liString;
	}

	@Override
	public List<String> getAllEmployeesCombo() {
		return dm.getAllEmployees().stream().map(employee -> String.format("%s %s, %s", employee.getFirstName(),
				employee.getLastName(), employee.getRole())).collect(Collectors.toList());
	}

	public String[] getSolution(String problem) {
		faq = getAllFaqs().stream().filter(f -> f.getProblem().equals(problem)).findFirst().get();
		return faq.getSolutionArray();
	}

}
