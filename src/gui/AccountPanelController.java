package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import domain.Account;
import domain.ContactPerson;
import domain.DomainController;
import domain.SupportManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
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

	DomainController dc;
	
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
		try {
			String[] get = get(String.format("https://localhost:44350/Account/IsValidUserJava/%s/%s",
					txtUsername.getText(), pwfPassword.getText())).split("-");
			String isValid = get[0];
			String role = get[1];
			if (isValid.equals("true")) {
				Account signedInUser = getSignedInUser(role);
				showDashboard(signedInUser);
			} else {
				lblLoginError.setText("Username or password incorrect");
				txtUsername.requestFocus();
			}
		} catch (IOException e) {
			createAndShowPopupConnection();
		}
	}

	/**Shows dashboard**/
	private void showDashboard(Account signedInAccount) {
		DashboardPanelController dpc = new DashboardPanelController(signedInAccount,new DomainController());
		Scene scene = new Scene(dpc);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.setResizable(false);
		stage.setTitle("Actemium | Dashboard");
		stage.show();
	}

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
	
	/**Returns signed in account**/
	private Account getSignedInUser(String role) {
		if(role.equals("contactperson")) {
			dc = new DomainController();
			ContactPerson cp = dc.getContactPersonByUsername(txtUsername.getText());
			dc.close();
			return cp;
		} else {
			dc = new DomainController();
			SupportManager sm = dc.getSupportManagerByUsername(txtUsername.getText());
			dc.close();
			return sm;
		}
	}
	
	private void createAndShowPopupConnection() {
		Label lblError1 = new Label("Connection refused");
		Label lblError2 = new Label("Please check your internet connection or try again later");
		
		lblError1.setFont(Font.font(15));
		
		Button btnClose = new Button("Ok");
		
		btnClose.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent arg0) {
				Stage stage = (Stage) btnClose.getScene().getWindow();
				stage.close();	
			}
		});
		
		VBox root = new VBox(2);
		
		root.getChildren().add(lblError1);
		
		root.getChildren().add(lblError2);
		
		root.getChildren().add(btnClose);
		
		root.setAlignment(Pos.CENTER);
		
		VBox.setMargin(btnClose,new Insets(20));
		
		Scene scene = new Scene(root);
		
		Stage stage = new Stage();
		
		stage.setScene(scene);

		stage.setTitle("Error");

		stage.setHeight(200);
		
		stage.setWidth(400);
		
		stage.setResizable(false);
		
		stage.initModality(Modality.APPLICATION_MODAL);
		
		stage.show();
	}
}