package domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import repository.GenericDao;
import repository.GenericDaoJpa;



public class DomainManager {

	private GenericDao<Ticket> ticketRepo;
	private GenericDao<ContactPerson> contactPersonRepo;
	//private GenericDao<Employee> employeeRepo;
	
	//observable list?
	private List<Ticket> ticketList;
	private List<ContactPerson> contactPersonList;
	
	//TIJDELIJK -> login
	public final String PERSISTENCE_UNIT_NAME = "project2";
    private EntityManager em;
    private EntityManagerFactory emf;

    public DomainManager() {
    	setTicketRepo(new GenericDaoJpa<>(Ticket.class));
    	//setEmployeeRepo(new GenericDaoJpa<>(Employee.class));
    	setContactPersonRepo(new GenericDaoJpa<>(ContactPerson.class));
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
    
    private void setContactPersonRepo(GenericDao<ContactPerson> contactPersonRepo) {
		this.contactPersonRepo = contactPersonRepo;	
	}

	private void setTicketRepo(GenericDao<Ticket> ticketRepo) {
		this.ticketRepo = ticketRepo;		
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
    
    public List<Ticket> getAllTickets() {
        if(ticketList == null) {
        	ticketList = ticketRepo.getAll();
        }
        return ticketList;
    }
    
    public ContactPerson getContactPersonByUsername(String username) {
        TypedQuery<ContactPerson> query1 = em.createNamedQuery("ContactPerson.getContactpersonByUsername", ContactPerson.class).setParameter("username", username);
        ContactPerson cp = query1.getSingleResult();
        return cp;
    }
    
    public SupportManager getSupportManagerByUsername(String username) {
    	TypedQuery<SupportManager> query1 = em.createNamedQuery("SupportManager.getSupportManagerByUsername", SupportManager.class).setParameter("username", username);
        SupportManager sm = query1.getSingleResult();
        return sm;
    }

}
