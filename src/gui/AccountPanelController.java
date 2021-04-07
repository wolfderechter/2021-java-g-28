package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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
		
	}
	
	private void signIn(ActionEvent event) {
		
	}
}
