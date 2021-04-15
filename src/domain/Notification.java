package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Notification")
public class Notification {
	 
	@Id
	@Column(name = "NotificationId")
	private int notificationId;
	@Column(name = "Action")
	private String action;
	@Column(name = "TicketName")
    private  String  ticketName;
	@Column(name = "IsRead")
    private boolean isRead;
	@ManyToOne
	@JoinColumn(name = "ContactPersonId")
    private ContactPerson contactPerson;
}
