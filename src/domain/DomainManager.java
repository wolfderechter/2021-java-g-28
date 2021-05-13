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

	// observable list?

	private ObservableList<Ticket> ticketList;
	private List<ContactPerson> contactPersonList;
	private List<ContractType> contractTypeList;
	private ObservableList<Employee> employeeList;
	private ObservableList<Company> companyList;
	private ObservableList<Faq> faqList;
	
	

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

	// goede methode
//	public void closePersistentie() {
//        GenericDaoJpa.closePersistency();
//    }

    public List<ContactPerson> getAllContactPersons() {
        if(contactPersonList == null) {
        	contactPersonList = contactPersonRepo.getAll();
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
    
    public ObservableList<Company> getAllCompanies() {
    	if(companyList == null) {
    		companyList = FXCollections.observableArrayList(companyRepo.getAll());
    	}
    	return companyList;
    }
    
    public ObservableList<Faq> getAllFaqs() {
    	if(faqList == null) {
    		faqList = FXCollections.observableArrayList(faqRepo.getAll());
    	}
    	faqList.forEach(f -> f.convertSolution());
    	return faqList;
    }
    
//    public List<Contract> getAllContracts() {
//    	if (contractRepo == null) {
//    		contractList = contractRepo.getAll();
//    	}
//    	return contractList;
//    }
    
    public ObservableList<Employee> getAllEmployees() {
        if(employeeList == null) {
        	employeeList = FXCollections.observableArrayList(employeeRepo.getAll());
        }
        return employeeList;
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

	public void updateCompany(String name, String address, Company company) {
		GenericDaoJpa.startTransaction();
		companyRepo.update(company);
		GenericDaoJpa.commitTransaction();
	}
	
	public void updateEmployee(Integer id, LocalDate date, String firstname, String lastname, String adress,
			String role, String phonenumber, String email, String username, boolean status, IEmployee emp) {
		GenericDaoJpa.startTransaction();
		employeeRepo.update((Employee) emp);
		GenericDaoJpa.commitTransaction();
	}
	
	public Employee getEmployeeByFirstAndLastName(String first, String last) {
		return employeeRepo.getAll().stream().filter(e -> e.getFirstName().equals(first) && e.getLastName().equals(last)).findFirst().orElse(null);
	}
}
