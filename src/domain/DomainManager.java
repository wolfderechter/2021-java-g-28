package domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;



public class DomainManager {

	public final String PERSISTENCE_UNIT_NAME = "project2";
    private EntityManager em;
    private EntityManagerFactory emf;

    public DomainManager() {
    	initializePersistentie();
	}

    private void initializePersistentie() {
        openPersistentie();  
    }
    
    private void openPersistentie() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    public void closePersistentie() {
        em.close();
        emf.close();
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
    
    public List<ContactPerson> getAllContactPersons() {
        return em.createNamedQuery("ContactPerson.getAllContactPersons", ContactPerson.class).getResultList();
    }
}
