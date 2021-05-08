package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

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
	//private GenericDao<Employee> employeeRepo;
	
	private GenericDao<Technician> technicianRepo;
	private GenericDao<Administrator> adminsitratorRepo;
	private GenericDao<SupportManager> supportmanagerRepo;
	
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
    	//setEmployeeRepo(new GenericDaoJpa<>(Employee.class));
    	setContactPersonRepo(new GenericDaoJpa<>(ContactPerson.class));
    	setFaqRepo(new GenericDaoJpa<>(Faq.class));
    	setContractRepo(new GenericDaoJpa<>(Contract.class));
    	setContractTypeRep(new GenericDaoJpa<>(ContractType.class));
    	
    	//account repo's
    	setTechnicianRepo(new GenericDaoJpa<>(Technician.class));
    	setAdministratorRepo(new GenericDaoJpa<>(Administrator.class));
    	setSupportManagerRepo(new GenericDaoJpa<>(SupportManager.class));
    	
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

//	private void setEmployeeRepo(GenericDao<Employee> employeeRepo) {
//		this.employeeRepo = employeeRepo;		
//	}
	
	private void setTechnicianRepo(GenericDao<Technician> technicianRepo) {
		this.technicianRepo = technicianRepo;
	}
	
	private void setAdministratorRepo(GenericDao<Administrator> adminRepo) {
		this.adminsitratorRepo = adminRepo;
	}
	
	private void setSupportManagerRepo(GenericDao<SupportManager> supRepo) {
		this.supportmanagerRepo = supRepo;
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
        	//employeeList = supportmanagerRepo.getAll();
        	//List<SupportManager> lis= supportmanagerRepo.getAll();
        	//employeeList.addAll(supportmanagerRepo.getAll());
        	employeeList.addAll(adminsitratorRepo.getAll());
        	employeeList.addAll(technicianRepo.getAll());
        }
        return employeeList;
    }
    
    
    public ContactPerson getContactPersonByUsername(String username) {
        TypedQuery<ContactPerson> query1 = em.createNamedQuery("ContactPerson.getContactpersonByUsername", ContactPerson.class).setParameter("username", username);
        ContactPerson cp = query1.getSingleResult();
        return cp;
    }
    
    public Account getAccountByUsername(String username, String kind) {
    	switch (kind.toLowerCase()) {
		case "technician": return technicianRepo.getAll().stream().filter(e -> e.getUser().getUserName().equals(username)).findFirst().get();
		case "administrator": return adminsitratorRepo.getAll().stream().filter(e -> e.getUser().getUserName().equals(username)).findFirst().get();
		case "supportmanager": return supportmanagerRepo.getAll().stream().filter(e -> e.getUser().getUserName().equals(username)).findFirst().get();
		default:
			throw new IllegalArgumentException("Unexpected value: " + kind.toLowerCase());
		}
    }
}
