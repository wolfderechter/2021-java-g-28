package domain;

public abstract class Account {

	protected String username;

	public Account() {
		
	}
	
	public Account(String username) {
		setUsername(username);
	}
	
	public String getUsername() {
		return this.username;
	}

	private void setUsername(String username) {
		this.username = username;
	}
}
