package domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SupportManagerController extends Controller {

	private IEmployee employee;
	private Ticket ticket;
	private PropertyChangeSupport ticketSubject;
	private ContractType cType;
	private PropertyChangeSupport cTypeSubject;
	private DomainManager dm;
	private IFaq faq;
	private List<TicketStatusEnum> selectedFilterStatusen = new ArrayList<>();
	private List<TicketTypeEnum> selectedFilterTypes = new ArrayList<>();
	private List<String> statusFilterContractType = new ArrayList<>();


	public SupportManagerController(IEmployee emp) {
		ticketSubject = new PropertyChangeSupport(this);
		cTypeSubject = new PropertyChangeSupport(this);
		this.employee = emp;
		setDomainManager(new DomainManager());
	}
	
	//Testen
	public SupportManagerController(IEmployee emp, DomainManager dm) {
		ticketSubject = new PropertyChangeSupport(this);
		cTypeSubject = new PropertyChangeSupport(this);
		this.employee = emp;
		setDomainManager(dm);
	}
	
	public SupportManagerController() {
		
	}
	public void setDomainManager(DomainManager domainManager) {
		this.dm = domainManager;
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
		if (dm.getAllContractTypes().stream().map(c -> c.getName()).anyMatch(c -> c == name)) {
			throw new IllegalArgumentException("there already exist a contract type with that name");
		}
		ContractType contractType = new ContractType(name, creationMethod, is24Hours, maxResponse, duration, price);
		dm.createContractType(contractType);
	}

	public void SaveStatusContractType(boolean selected) {
		this.cType.setActive(selected);
		dm.updateContractType(cType);
		setContractType(cType.getName());
	}

	public void saveAllContractType(String name, String response, ContractTypeCreationMethod creationMethod,
			boolean is24h, String duration, String price, boolean status) {
		cType.setActive(status);
		cType.setName(name);
		cType.setMaxResponseTime(Integer.parseInt(response));
		cType.setCreationMethod(creationMethod);
		cType.setIsOutsideBusinessHours(is24h);
		cType.setMinDuration(Integer.parseInt(duration));
		cType.setPrice(Double.parseDouble(price));
		dm.updateContractType(cType);
		setContractType(cType.getName());
	}
	
	public Company getCompanyByName(String companyName) {
		Company comp = dm.getAllCompanies().stream().filter(c -> c.getCompanyName() == companyName).findFirst().orElse(null);
		return comp;
	}

	@Override
	// nodig voor lijst van tickets voor tableview van ticketPanel
	public ObservableList<ITicket> getFilteredTickets() {
		ObservableList<Ticket> li = dm.getAllTickets();
		li = li.stream().filter(t -> this.selectedFilterTypes.contains(t.getType()))
				.filter(t -> this.selectedFilterStatusen.contains(t.getStatus()))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
		return (ObservableList<ITicket>) (Object) li;
	}

	public ObservableList<IContractType> getFilteredContractTypes() {
		boolean getAll = statusFilterContractType.contains("Active") && statusFilterContractType.contains("Inactive");
		boolean getActive = statusFilterContractType.contains("Active");
		boolean getInActive = statusFilterContractType.contains("Inactive");
		List<ContractType> li = dm.getAllContractTypes();
		if (getAll) {
			ObservableList<ContractType> obListContractTypes = FXCollections.observableList(li);
			return (ObservableList<IContractType>) (Object) obListContractTypes;
		} else if (getActive) {
			return li.stream().filter(c -> c.isActive() == true)
					.collect(Collectors.toCollection(FXCollections::observableArrayList));
		} else if (getInActive) {
			return li.stream().filter(c -> c.isActive() == false)
					.collect(Collectors.toCollection(FXCollections::observableArrayList));
		} else {
			List<IContractType> emptyList = new ArrayList<>();
			return FXCollections.observableArrayList(emptyList);
		}
	}


	public List<List<Ticket>> getAllTicketsAsListPerMonth(){
		List<List<Ticket>> ticketspermonth = new ArrayList<List<Ticket>>();
		List<Ticket> tickets = dm.getAllTicketsAsList();
		List<Ticket> ticketsToAdd = null;
		
		for (int month = 1; month < 13; month++) {
			ticketsToAdd = new ArrayList<>();
			for (int i = 0; i < tickets.size(); i++) {
				if (tickets.get(i).getDateCreation().getMonthValue() == month) {
					ticketsToAdd.add(tickets.get(i));
				}
			}
			ticketspermonth.add(ticketsToAdd);
		}
		return ticketspermonth;
	}
	
	public List<List<Ticket>> getAllTicketsAsListPerStatus(){
		List<List<Ticket>> ticketsperstatus = new ArrayList<List<Ticket>>();
		List<Ticket> tickets = dm.getAllTicketsAsList();
		List<Ticket> ticketsToAdd = null;
		
		for (int status = 0; status < 8; status++) {
			ticketsToAdd = new ArrayList<>();
			for (int i = 0; i < tickets.size(); i++) {
				if (tickets.get(i).getStatus() == TicketStatusEnum.values()[status]) {
					ticketsToAdd.add(tickets.get(i));
				}
			}
			ticketsperstatus.add(ticketsToAdd);
		}
		return ticketsperstatus;
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

}
