package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import domain.Controller;
import domain.DomainController;
import domain.IEmployee;
import domain.SupportManagerController;
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
	@FXML
	private Button btnEmployee;
	@FXML
	private Button btnFaq;
	@FXML
	private Button btnContractType;
	

	private Controller dc;

	public DashboardPanelController(Controller controller) {

		this.dc = controller;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardPanel.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		// displayTickets(null);
		System.out.println(dc.getEmployee().getRole());
		if(dc.getEmployee().getRole().equals("SM")){
			btnEmployee.setDisable(true);
			btnCustomer.setDisable(true);
		}
		
		if(dc.getEmployee().getRole().equals("TE")) {
			btnCustomer.setDisable(true);
			btnEmployee.setDisable(true);
			btnContractType.setDisable(true);
		}
		
		if(dc.getEmployee().getRole().equals("AD")) {
			btnTickets.setDisable(true);
			btnContractType.setDisable(true);
		}
		
		lblUsername.setText(dc.getEmployee().getUser().getUserName());
		btnCustomer.setOnAction(this::displayCustomers);
		btnTickets.setOnAction(this::displayTickets);
		btnFaq.setOnAction(this::displayFaq);
		btnEmployee.setOnAction(this::displayEmployees);
		btnContractType.setOnAction(this::displayContractType);
	}

	private void displayCustomers(ActionEvent event) {
		setActiveButtonColor(btnCustomer);
		ContactPersonPanelController cppc = new ContactPersonPanelController(dc);
		bpDashboard.setCenter(cppc);
	}

	private void displayTickets(ActionEvent event) {
		setActiveButtonColor(btnTickets);
		TicketPanelController tpc = new TicketPanelController(dc);
		bpDashboard.setCenter(tpc);
	}
	private void displayContractType(ActionEvent event) {
		setActiveButtonColor(btnContractType);
		ContractTypePanelController ctpc = new ContractTypePanelController((SupportManagerController) dc);
		bpDashboard.setCenter(ctpc);
	}
	
	private void displayFaq(ActionEvent event) {
		setActiveButtonColor(btnFaq);
		FaqPanelController fpc = new FaqPanelController(dc);
		bpDashboard.setCenter(fpc);
	}

	private void displayEmployees(ActionEvent event) {
		setActiveButtonColor(btnEmployee);
		EmployeePanelController epc = new EmployeePanelController(dc);
		bpDashboard.setCenter(epc);
	}

	private void setActiveButtonColor(Button button) {
		btnCustomer.setStyle("-fx-text-fill: #7c7c7c;");
		btnTickets.setStyle("-fx-text-fill: #7c7c7c;");
		btnEmployee.setStyle("-fx-text-fill: #7c7c7c;");
		btnFaq.setStyle("-fx-text-fill: #7c7c7c;");

		if (button.equals(btnCustomer)) {
			btnCustomer.setStyle("-fx-text-fill: #000000;");
		}

		if (button.equals(btnTickets)) {
			btnTickets.setStyle("-fx-text-fill: #000000;");
		}

		if (button.equals(btnEmployee)) {
			btnEmployee.setStyle("-fx-text-fill: #000000;");
		}

		if (button.equals(btnFaq)) {
			btnFaq.setStyle("-fx-text-fill: #000000;");
		}
		
		if (button.equals(btnContractType)) {
			btnContractType.setStyle("-fx-text-fill: #000000;");
		}
	}
}
