package gui;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.mockito.internal.junit.UniversalTestListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class AccountPanelController extends GridPane {
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;
	@FXML
	private Button btnSignIn;
	@FXML
	private Label lblUsername;
	@FXML
	private Label lblPassword;

	public AccountPanelController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
		
        btnSignIn.setOnAction(this::signIn);
	}
	
	private void signIn(ActionEvent event) {
		
		DashboardPanelController dpc = new DashboardPanelController(txtUsername.getText());
		Scene scene = new Scene(dpc, 1900, 900);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
	}
}
