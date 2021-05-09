package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.event.ActionEvent;

public class LoginController {


	
	/**Sends GET request to specific URL and returns result from server as a string
	 * @throws IOException **/
	private String get(String urlToRead) throws IOException {
		StringBuilder result = new StringBuilder();

		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "text/plain");
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(5000);
		try (var reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			result.append(reader.readLine());
		}

		return result.toString();
	}
	
	public String[] getValidationAndRole(String username, String password) throws IOException {
		return get(String.format("https://localhost:44350/Account/IsValidUserJava/%s/%s",
				username, password)).split("-");
	}
	
	/**Returns signed in account**/
	public IEmployee getSignedInUser(String role, String username) {
		DomainManager d = new DomainManager();
		IEmployee e = d.getEmployeeByUsername(username);
		//d.closePersistentie();
		return e;
	}
	
	public Controller getController(IEmployee emp) {
		switch (emp.getRole()) {
		case "AD": return new AdministratorController(emp);
		case "SM": return new SupportManagerController(emp);
		case "TE": return new TechnicianController(emp);
		default:
			throw new IllegalArgumentException("Unexpected value: " + emp.getRole());
		}
	}
}
