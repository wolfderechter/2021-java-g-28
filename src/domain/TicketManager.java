package domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.collections.ObservableList;

public class TicketManager {

	public final String PERSISTENCE_UNIT_NAME = "project2";
    private EntityManager em;
    private EntityManagerFactory emf;

    public TicketManager() {
        initializePersistence();
    }

    private void initializePersistence() {
        openPersistence();  
    }
    
    private void openPersistence() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    public void closePersistence() {
        em.close();
        emf.close();
    }
    
    
}
