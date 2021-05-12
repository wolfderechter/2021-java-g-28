package gui;

import java.io.IOException;

import domain.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SignedInAccountPanelController extends GridPane {
	@FXML
	private Label lblName;
	@FXML
	private Label lblRole;
	@FXML
	private Label lblDateInService;
	@FXML
	private Button btnSignOut;

	private Controller dc;

	public SignedInAccountPanelController(Controller controller) {

		this.dc = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("SignedInAccountPanel.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		
		
		lblName.setText(String.format("%s %s", dc.getEmployee().getFirstName(), dc.getEmployee().getLastName()));
		
		switch (dc.getEmployee().getRole()) {
		case "SM": lblRole.setText("Support Manager"); break;
		case "TE": lblRole.setText("Technician"); break;
		case "AD":lblRole.setText("Administrator"); break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + dc.getEmployee().getRole());
		}
		
		lblDateInService.setText(dc.getEmployee().getDateInService().toString());
		btnSignOut.setOnAction(this::signOut);
	}
	
	private void signOut(ActionEvent event) {
		//this.dc.close();
		
		Stage stage = new Stage();
		
		Scene scene = new Scene(new AccountPanelController());

		stage.setScene(scene);

		stage.setTitle("Actemium | Sign In");

		stage.setFullScreen(true);

		Stage main = (Stage) btnSignOut.getScene().getWindow();
		main.close();	
		
		stage.show();
	}
}
