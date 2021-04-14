package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DashboardPanelController extends BorderPane {

	@FXML
	private Label lblUsername;
	
    @FXML
    private Button btnCustomer;

//    @FXML
//    public void test(ActionEvent event) {
//    	ContactPersonPanelController cppc = new ContactPersonPanelController();
//		setCenter(cppc);
//    }
    
	public DashboardPanelController(String username) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
		
        btnCustomer.setOnAction(this::display);
	}
	
	
	
	private void display(ActionEvent event) {
		
		ContactPersonPanelController cppc = new ContactPersonPanelController();
		setCenter(cppc);
		
		}
}
