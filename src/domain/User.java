package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AspNetUsers")
public class User {

	@Id
	@Column(name = "Id")
	private String id;
	@Column(name = "UserName")
	private String userName;
	
	public User() {
		
	}
}
