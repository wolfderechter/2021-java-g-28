package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class DashboardPanelController extends GridPane {

	@FXML
	private Label lblUsername;
	
	public DashboardPanelController(String username) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
		
		this.lblUsername.setText(username);
	}
}
