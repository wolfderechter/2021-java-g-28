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
}
