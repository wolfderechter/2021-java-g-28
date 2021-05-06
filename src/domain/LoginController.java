package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import gui.tc;
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
	public Account getSignedInUser(String role, String username) {
		if(role.equals("administrator")) {
			AdministratorController ac = new AdministratorController();
			//vervangen door lambda
			//Administrator cp = ac.getAdministratorByUsername(username);
			//ap.close();
			//return cp;
		} 
		
		if(role.equals("supportmanager")) {
			SupportManagerController spc = new SupportManagerController();
			SupportManager sm = spc.getSupportManagerByUsername(username);
			spc.close();
			return sm;
		}
		
		if(role.equals("technician")) {
			//TechnicianController tc = new TechnicianController();
			//Technician tn = new tc.getTechnicianByUsername(username);
			//tc.close();
			//return tn;
		}
	}
	
	public Controller getController(String role) {
		switch (role) {
		case "administrator": return new AdministratorController();
		case "supportmanager": return new SupportManagerController();
		case "technician": return new TechnicianController();
		default:
			throw new IllegalArgumentException("Unexpected value: " + role);
		}
	}
}
