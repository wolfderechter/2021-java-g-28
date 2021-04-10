package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This class is responsible for signing in.
 * @author Stef
 */
public class AccountPanelController extends GridPane {
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField pwfPassword;
	@FXML
	private Button btnSignIn;
	@FXML
	private Label lblLoginError;

	public AccountPanelController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }		
        txtUsername.setOnAction(this::signIn);
        pwfPassword.setOnAction(this::signIn);
        btnSignIn.setOnAction(this::signIn);
	}
	
	/**Checks if credentials are valid and signs user in and shows dashboard, if not valid, shows error message on screen**/
	private void signIn(ActionEvent event) {
		String get = get(String.format("https://localhost:44350/Account/IsValidUserJava/%s/%s", txtUsername.getText(), pwfPassword.getText()));
		
		//FOR TESTING PURPOSE PUT FALSE
		boolean runWithLogin = true;
		
		if(runWithLogin) {
			if (get.equals("true")) {
				showDashboard();
			} else {
				lblLoginError.setText("Username or password incorrect");
				txtUsername.requestFocus();
			}
		} else {
			showDashboard();
		}
	}
	
	/**Shows dashboard**/
	private void showDashboard() {
		DashboardPanelController dpc = new DashboardPanelController(txtUsername.getText());
		Scene scene = new Scene(dpc);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.setTitle("Actemium | Dashboard");
		stage.show();
	}

	/**Sends GET request to specific URL and returns result from server as a string**/
	private String get(String urlToRead) {
		StringBuilder result = new StringBuilder();
		try {
			URL url = new URL(urlToRead);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "text/plain");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			try (var reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				result.append(reader.readLine());
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}