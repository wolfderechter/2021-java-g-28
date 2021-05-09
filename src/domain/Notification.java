package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Notification")
public class Notification {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int notificationId;
	private String action;
    private  String  ticketName;
    private boolean isRead;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ContactPersonId")
    private ContactPerson contactPerson;
	
	protected Notification() {}
	
	public Notification(String action,String notificationTitle,ContactPerson person) {
		this.action=action;
		this.ticketName = notificationTitle;
		this.contactPerson = person;
		this.isRead=false;
		
	}
}
