package domain;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class DomainManager {

	private GenericDao<Ticket> ticketRepo;
	private GenericDao<ContactPerson> contactPersonRepo;
	private GenericDao<Faq> faqRepo;
	private GenericDao<Contract> contractRepo;
	private GenericDao<ContractType> contractTypeRepo;
	private GenericDao<Employee> employeeRepo;
	private GenericDao<Company> companyRepo;
	private GenericDao<User> userRepo;


	// observable list?

	private ObservableList<Ticket> ticketList;
	private ObservableList<ContactPerson> contactPersonList;
	private List<ContractType> contractTypeList;
	private ObservableList<Employee> employeeList;
	private ObservableList<Company> companyList;
	private ObservableList<Faq> faqList;
	private List<User> userList;

	
	

	private List<Contract> contractList;

	public final String PERSISTENCE_UNIT_NAME = "project2";
	private EntityManager em;
	private EntityManagerFactory emf;

	public DomainManager() {
		setTicketRepo(new GenericDaoJpa<>(Ticket.class));
		setEmployeeRepo(new GenericDaoJpa<>(Employee.class));
		setContactPersonRepo(new GenericDaoJpa<>(ContactPerson.class));
		setFaqRepo(new GenericDaoJpa<>(Faq.class));
		setContractRepo(new GenericDaoJpa<>(Contract.class));
		setContractTypeRep(new GenericDaoJpa<>(ContractType.class));
		setCompanyRepo(new GenericDaoJpa<>(Company.class));
		setUserRepo(new GenericDaoJpa<>(User.class));
		openPersistentie();
	}



	private void openPersistentie() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}

	public void closePersistentie() {
		em.close();
		emf.close();
		GenericDaoJpa.closePersistency();
	}


	private void setCompanyRepo(GenericDao<Company> companyRepo) {
		this.companyRepo = companyRepo;
	}

	private void setContractTypeRep(GenericDao<ContractType> contractTypeRepo) {
		this.contractTypeRepo = contractTypeRepo;
	}

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


	private void setEmployeeRepo(GenericDao<Employee> employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	private void setUserRepo(GenericDao<User> userRepo) {
		this.userRepo = userRepo;
	}


    public ObservableList<ContactPerson> getAllContactPersons() {
        if(contactPersonList == null) {
        	contactPersonList = FXCollections.observableArrayList(contactPersonRepo.getAll());
        }
        return contactPersonList;
    }
    
    public List<ContractType> getAllContractTypes() {
        if(contractTypeList == null) {
        	contractTypeList = contractTypeRepo.getAll();
        }
        return contractTypeList;
    }
    
    public ObservableList<Ticket> getAllTickets() {
        ticketList = FXCollections.observableArrayList(ticketRepo.getAll());
        return ticketList;
    }	

    //kpi
    public List<Ticket> getAllTicketsAsList(){
    	return ticketRepo.getAll();
    }
    
    public ObservableList<Company> getAllCompanies() {

    		companyList = FXCollections.observableArrayList(companyRepo.getAll());
    	return companyList;
    }
    
    public ObservableList<Faq> getAllFaqs() {
    	if(faqList == null) {
    		faqList = FXCollections.observableArrayList(faqRepo.getAll());
    	}
    	faqList.forEach(f -> f.convertSolution());
    	return faqList;
    }
   
    
    public ObservableList<Employee> getAllEmployees() {
        return employeeList =  FXCollections.observableArrayList(employeeRepo.getAll());
    }
    
    public ContactPerson getContactPersonByUsername(String username) {
    	ContactPerson cp = contactPersonRepo.getAll().stream().filter(c->c.getUser().getUserName() == username).findFirst().orElse(null);
        return cp;
    }
    

    public IEmployee getEmployeeByUsername(String username) {
    	return employeeRepo.getAll().stream().filter(e -> e.getUser().getUserName().equals(username)).findFirst().orElse(null);
    }

	public ObservableList<Company> getCompaniesByName(String name) {
		if(companyList == null) {
			companyList = FXCollections.observableArrayList(companyRepo.getAll());
		} 
	return companyList.stream().filter(c->c.getCompanyName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
	}
	
	public ObservableList<Employee> getEmployeesByName(String name) {
		if(employeeList == null) {
	    	employeeList = FXCollections.observableArrayList(employeeRepo.getAll());
		}
    	return employeeList.stream()
    			.filter(e -> e.getFirstName().toLowerCase().contains(name.toLowerCase()) || e.getLastName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
	}

	public List<Contract> getAllContracts() {
		if (contractRepo == null) {
			contractList = contractRepo.getAll();
		}
		return contractList;
	}

	public void createTicket(Ticket ticket) {
		GenericDaoJpa.startTransaction();
		ticketRepo.insert(ticket);
        GenericDaoJpa.commitTransaction();
        ticketList.add(ticket);
	}
	
	public void createContactPerson(ContactPerson contactPerson) {
		GenericDaoJpa.startTransaction();
		contactPersonRepo.insert(contactPerson);
		GenericDaoJpa.commitTransaction();
		contactPersonList.add(contactPerson);
	}
	
	public void createCompany(Company company) {
		
		GenericDaoJpa.startTransaction();
		companyRepo.insert(company);
		GenericDaoJpa.commitTransaction();
		companyList.add(company);
	}
	
	
	public void createContractType(ContractType contractType) {
		GenericDaoJpa.startTransaction();
		contractTypeRepo.insert(contractType);
        GenericDaoJpa.commitTransaction();
        contractTypeList.add(contractType);
	}
	
	public void createEmployee(Employee employee) {
		GenericDaoJpa.startTransaction();
		employeeRepo.insert(employee);
        GenericDaoJpa.commitTransaction();
        employeeList.add(employee);
	}
	
	public void updateContractType(ContractType cType) {
		GenericDaoJpa.startTransaction();
		contractTypeRepo.update(cType);
		GenericDaoJpa.commitTransaction();
	}
	
	public void updateTicket(Ticket ticket) 
	{
		GenericDaoJpa.startTransaction();
		ticketRepo.update(ticket);
		GenericDaoJpa.commitTransaction();
	}

	public void updateContactPerson(ContactPerson cp) {
		GenericDaoJpa.startTransaction();
		contactPersonRepo.update(cp);
		GenericDaoJpa.commitTransaction();
	}

	public void updateCompany(Company company) {
		GenericDaoJpa.startTransaction();
		companyRepo.update(company);
		GenericDaoJpa.commitTransaction();
		
	}
	
	public void updateEmployee(IEmployee emp) {
		GenericDaoJpa.startTransaction();
		employeeRepo.update((Employee) emp);
		GenericDaoJpa.commitTransaction();
	}
	
	public Employee getEmployeeByFirstAndLastName(String first, String last) {
		return employeeRepo.getAll().stream().filter(e -> e.getFirstName().equals(first) && e.getLastName().equals(last)).findFirst().orElse(null);
	}
	
	public Company getCompanyByCompanyName(String companyName) {
		
		return companyList.stream().filter(c -> c.getCompanyName().equals(companyName)).findFirst().get();
	}


	
	
	public void editFirstName(int index, String newFirstName) {
		GenericDaoJpa.startTransaction();
		getAllContactPersons().get(index).setFirstName(newFirstName);
		GenericDaoJpa.commitTransaction();
	}


	public User getUserByUsername(String username) {
		//if(userList == null) {
	    	userList = userRepo.getAll();
		//}
    	return userList.stream()
    			.filter(u -> u.getUserName().equals(username)).findFirst().orElse(null);
	}
	
	public Company getCompanyByName(String name) {
		return companyList.stream()
    			.filter(c -> c.getCompanyName().equals(name)).findFirst().get();
	}
	



	public ObservableList<Employee> getEmployeesByUsername(String username) {
		if(employeeList == null) {
	    	employeeList = FXCollections.observableArrayList(employeeRepo.getAll());
		}
    	return employeeList.stream()
    			.filter(e -> e.getUsername().toLowerCase().contains(username.toLowerCase()))
    			.collect(Collectors.toCollection(FXCollections::observableArrayList));
	}
	
}
