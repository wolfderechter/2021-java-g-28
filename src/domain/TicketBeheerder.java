package domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TicketBeheerder {

	public final String PERSISTENCE_UNIT_NAME = "project2";
    private EntityManager em;
    private EntityManagerFactory emf;

    public TicketBeheerder() {
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
    
    public List<Ticket> geefAlleTickets() {
        return em.createNamedQuery("Ticket.alleTickets", Ticket.class).getResultList();
    }
    
}
