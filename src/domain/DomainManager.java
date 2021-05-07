package domain;

import java.util.List;

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
	//private GenericDao<Employee> employeeRepo;
	private GenericDao<ContractType> contractTypeRepo;
	private GenericDao<Employee> employeeRepo;
	
	//observable list?
	private ObservableList<Ticket> ticketList;
	private List<ContactPerson> contactPersonList;
	private List<ContractType> contractTypeList;
	private List<Employee> employeeList;

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
    
    public List<Faq> getAllFaqs() {
    	if(faqList == null) {
    		faqList = faqRepo.getAll();
    	}
    	return faqList;
    }
    
    public List<Contract> getAllContracts() {
    	if (contractRepo == null) {
    		contractList = contractRepo.getAll();
    	}
    	return contractList;
    }
   
    
    public List<Employee> getAllEmployees() {
        if(employeeList == null) {
        	employeeList = employeeRepo.getAll();
        }
        return employeeList;
    }
    
    public ContactPerson getContactPersonByUsername(String username) {
        TypedQuery<ContactPerson> query1 = em.createNamedQuery("ContactPerson.getContactpersonByUsername", ContactPerson.class).setParameter("username", username);
        ContactPerson cp = query1.getSingleResult();
        return cp;
    }
    
    public Employee getEmployeeByUsername(String username) {
    	TypedQuery<Employee> query1 = em.createNamedQuery("Employee.getEmployeeByUsername", Employee.class).setParameter("username", username);
        Employee sm = query1.getSingleResult();
        return sm;
    }

}
