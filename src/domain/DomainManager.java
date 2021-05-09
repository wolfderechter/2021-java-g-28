package domain;

import java.util.List;
<<<<<<< Upstream, based on branch 'main' of https://github.com/HoGentProjectenII/2021-java-g-28
import java.util.stream.Collector;
import java.util.stream.Collectors;

=======
>>>>>>> 5fe1771 create ticket implemented
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
<<<<<<< Upstream, based on branch 'main' of https://github.com/HoGentProjectenII/2021-java-g-28
	

	//observable list?
=======
	private GenericDao<Reaction> reactionRepo;
	private GenericDao<Notification> notifRepo;
	// observable list?
>>>>>>> 5fe1771 create ticket implemented
	private ObservableList<Ticket> ticketList;
	private List<ContactPerson> contactPersonList;
	private List<ContractType> contractTypeList;
	private ObservableList<Employee> employeeList;
<<<<<<< Upstream, based on branch 'main' of https://github.com/HoGentProjectenII/2021-java-g-28
	private ObservableList<Company> companyList;
=======
>>>>>>> 5fe1771 create ticket implemented
	private List<Faq> faqList;
	private List<Contract> contractList;
	private List<Company> companyList;

<<<<<<< Upstream, based on branch 'main' of https://github.com/HoGentProjectenII/2021-java-g-28
    public DomainManager() {
    	setTicketRepo(new GenericDaoJpa<>(Ticket.class));
    	setEmployeeRepo(new GenericDaoJpa<>(Employee.class));
    	setContactPersonRepo(new GenericDaoJpa<>(ContactPerson.class));
    	setFaqRepo(new GenericDaoJpa<>(Faq.class));
    	setContractRepo(new GenericDaoJpa<>(Contract.class));
    	setContractTypeRep(new GenericDaoJpa<>(ContractType.class));
    	setCompanyRepo(new GenericDaoJpa<>(Company.class));
    	openPersistentie();
=======
	// TIJDELIJK -> login
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
		setReactionrepo(new GenericDaoJpa<>(Reaction.class));
		setNotifRepo(new GenericDaoJpa<>(Notification.class));
		openPersistentie();
>>>>>>> 5fe1771 create ticket implemented
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
	private void setNotifRepo(GenericDao<Notification> notifRepo) {
		this.notifRepo = notifRepo;
		
	}

	private void setReactionrepo(GenericDao<Reaction> reactionRepo) {
		this.reactionRepo = reactionRepo;
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
	
	private void setCompanyRepo(GenericDao<Company> companyRepo) {
		this.companyRepo = companyRepo;
	}

	private void setEmployeeRepo(GenericDao<Employee> employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	// goede methode
//	public void closePersistentie() {
//        GenericDaoJpa.closePersistency();
//    }
<<<<<<< Upstream, based on branch 'main' of https://github.com/HoGentProjectenII/2021-java-g-28
    
    
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
=======
>>>>>>> 5fe1771 create ticket implemented

<<<<<<< Upstream, based on branch 'main' of https://github.com/HoGentProjectenII/2021-java-g-28
    }

	public ObservableList<Employee> getEmployeesByName(String name) {
		if(employeeList == null) {
	    	employeeList = FXCollections.observableArrayList(employeeRepo.getAll());
		}
    	return employeeList.stream()
    			.filter(e -> e.getFirstName().toLowerCase().contains(name.toLowerCase()) || e.getLastName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
=======
	public List<ContactPerson> getAllContactPersons() {
		if (contactPersonList == null) {
			contactPersonList = contactPersonRepo.getAll();
		}
		return contactPersonList;
	}

	public List<ContractType> getAllContractTypes() {
		if (contractTypeList == null) {
			contractTypeList = contractTypeRepo.getAll();
		}
		return contractTypeList;
	}

	public ObservableList<Ticket> getAllTickets() {
		if (ticketList == null) {
			ticketList = FXCollections.observableArrayList(ticketRepo.getAll());
		}
		return ticketList;
	}

	public List<Faq> getAllFaqs() {
		if (faqList == null) {
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

	public ObservableList<Employee> getAllEmployees() {
		if (employeeList == null) {
			employeeList = FXCollections.observableArrayList(employeeRepo.getAll());
		}
		return employeeList;
	}

	public ContactPerson getContactPersonByUsername(String username) {
		return contactPersonRepo.getAll().stream().filter(c -> c.getUser().getUserName() == username).findFirst()
				.orElse(null);
	}

	public Employee getEmployeeByUsername(String username, String role) {
		return employeeRepo.getAll().stream()
				.filter(e -> e.getUser().getUserName().equals(username) && e.getRole().equals(role.toUpperCase()))
				.findFirst().get();
	}

	public List<Company> getAllCompanies() {
		if (companyList == null) {
			companyList = companyRepo.getAll();
		}
		return companyList;
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

	public void createReaction(Reaction reaction) {
		GenericDaoJpa.startTransaction();
		reactionRepo.insert(reaction);
		GenericDaoJpa.commitTransaction();
>>>>>>> 5fe1771 create ticket implemented
	}
}
