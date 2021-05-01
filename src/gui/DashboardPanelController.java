package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import domain.Account;
import domain.DomainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
public class DashboardPanelController extends GridPane {

	@FXML
	private Label lblUsername;
    @FXML
    private Button btnCustomer;
    @FXML
    private Button btnTickets;
    @FXML
    private BorderPane bpDashboard;
    
    private DomainController dc;
    

	public DashboardPanelController(Account signedInAccount,DomainController domainC) {

		this.dc = domainC;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.dc = new DomainController();
		//displayTickets(null);
        lblUsername.setText("NathanT");
        btnCustomer.setOnAction(this::displayCustomers);
        btnTickets.setOnAction(this::displayTickets);
        
	}
	
		
	private void displayCustomers(ActionEvent event) {
		ContactPersonPanelController cppc = new ContactPersonPanelController(dc);
		bpDashboard.setCenter(cppc);
		}
	
	private void displayTickets(ActionEvent event) {
		
		TicketPanelController tpc = new TicketPanelController(dc);
		bpDashboard.setCenter(tpc);
		}
}
