package gui;

import java.io.IOException;

import domain.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class DashboardPanelController extends GridPane {

	@FXML
	private Label lblUsername;
	
	public DashboardPanelController(Account signedInAccount) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
		
		this.lblUsername.setText(signedInAccount.getUsername());
	}
}
