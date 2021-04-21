package gui;

import java.io.IOException;


import domain.TicketController;
import javafx.event.ActionEvent;

import domain.Account;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import gui.TicketPanelController;
public class DashboardPanelController extends BorderPane {

	@FXML
	private Label lblUsername;
    @FXML
    private Button btnCustomer;
    @FXML
    private Button btnTickets;


	public DashboardPanelController(Account signedInAccount) {

		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

		//displayTickets(null);
		//lblUsername.setText(signedInAccount.getUsername());
        lblUsername.setText("bert");
        btnCustomer.setOnAction(this::displayCustomers);
        btnTickets.setOnAction(this::displayTickets);

	}
	
	
	
	private void displayCustomers(ActionEvent event) {
		ContactPersonPanelController cppc = new ContactPersonPanelController();
		setCenter(cppc);
		
		}
	
	private void displayTickets(ActionEvent event) {
		TicketPanelController tpc = new TicketPanelController(new TicketController());
		setCenter(tpc);
		}
}
