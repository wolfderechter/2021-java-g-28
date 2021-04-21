package domain;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DomainController {

private DomainManager dm = new DomainManager();
	
	public ContactPerson getContactPersonByUsername(String username) {
        ContactPerson cp = dm.getContactPersonByUsername(username);
    	return cp;
    }
	
	public SupportManager getSupportManagerByUsername(String username) {
		SupportManager sm = dm.getSupportManagerByUsername(username);
    	return sm;
	}
	
	public ObservableList<ContactPerson>  getAllContactPersons() {
        List<ContactPerson> li = dm.getAllContactPersons();
        ObservableList<ContactPerson> obListContactPersons = FXCollections.observableList(li);
        return obListContactPersons;
    }
	
	public void close() {
        dm.closePersistentie();
    }
}
