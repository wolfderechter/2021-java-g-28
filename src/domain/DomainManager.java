package domain;

import java.util.Collections;
import java.util.List;
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
	

	//observable list?
	private ObservableList<Ticket> ticketList;
	private List<ContactPerson> contactPersonList;
	private List<ContractType> contractTypeList;
	private ObservableList<Employee> employeeList;
	private ObservableList<Company> companyList;
	private List<Faq> faqList;
	private List<Contract> contractList;
	
	//TIJDELIJK -> login
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
	
	private void setCompanyRepo(GenericDao<Company> companyRepo) {
		this.companyRepo = companyRepo;
	}

	private void setEmployeeRepo(GenericDao<Employee> employeeRepo) {
		this.employeeRepo = employeeRepo;		
	}
	

	//goede methode
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
        if(ticketList == null) {
        	ticketList = FXCollections.observableArrayList(ticketRepo.getAll());
        }
        return ticketList;
    }
    
    public ObservableList<Company> getAllCompanies() {
    	if(companyList == null) {
    		companyList = FXCollections.observableArrayList(companyRepo.getAll());
    	}
    	return companyList;
    }
    
    public List<Faq> getAllFaqs() {
    	if(faqList == null) {
    		faqList = faqRepo.getAll();
    	}
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
        TypedQuery<ContactPerson> query1 = em.createNamedQuery("ContactPerson.getContactpersonByUsername", ContactPerson.class).setParameter("username", username);
        ContactPerson cp = query1.getSingleResult();
        return cp;
    }
    

    public IEmployee getEmployeeByUsername(String username, String role) {
    	return employeeRepo.getAll().stream().filter(e -> e.getUser().getUserName().equals(username) && e.getRole().equals(role.toUpperCase())).findFirst().get();

    }

	public ObservableList<Employee> getEmployeesByName(String name) {
		if(employeeList == null) {
	    	employeeList = FXCollections.observableArrayList(employeeRepo.getAll());
		}
    	return employeeList.stream()
    			.filter(e -> e.getFirstName().toLowerCase().contains(name.toLowerCase()) || e.getLastName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
	}
}
