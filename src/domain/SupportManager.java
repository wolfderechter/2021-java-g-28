package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SupportManagers")
@NamedQueries({
	@NamedQuery(name = "SupportManager.getSupportManagerByUsername" , query = "SELECT s FROM SupportManager s WHERE s.user.userName = :username")
})
public class SupportManager extends Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String adress;
	private Date dateInService;
	private String firstName;
	private String lastName;
	private User user;
	
	public SupportManager() {
		
	}
	
	public SupportManager(String username) {
		super(username);
		setId(id);
		setAdress(adress);
		setDateInService(dateInService);
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public String getAdress() {
		return adress;
	}
	private void setAdress(String adress) {
		this.adress = adress;
	}
	public Date getDateInService() {
		return dateInService;
	}
	private void setDateInService(Date dateInService) {
		this.dateInService = dateInService;
	}
	public String getFirstName() {
		return firstName;
	}
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	private void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
