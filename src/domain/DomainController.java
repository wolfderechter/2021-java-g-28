package domain;

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
	
	public void close() {
        dm.closePersistentie();
    }
}
