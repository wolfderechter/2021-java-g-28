package domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Administrator extends Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String adress;
	private Date dateInService;
	private String firstName;
	private String lastName;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	
	public User getUser() {
		return this.user;
	}
}
