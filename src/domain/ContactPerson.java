package domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;




@Entity
@Table(name = "ContactPersons")
@NamedQueries({
	@NamedQuery(name = "ContactPerson.allContactPersons" , query = "SELECT * FROM ContactPerson")
})
public class ContactPerson extends Account {
	
	@Id
	public int id;
	public String FirstName;
	public String LastName;
	public Company Company;
	public List<Notification> Notifications;
	
	protected ContactPerson() {
		
	}
	
}
