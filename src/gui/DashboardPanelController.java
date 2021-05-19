package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import domain.Controller;
import domain.SupportManagerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class DashboardPanelController extends GridPane {

	@FXML
	private Button btnUsername;
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
	@FXML
	private Button btnKpi;
	@FXML
	private Button btnStatistics;

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
			btnKpi.setDisable(true);
			btnStatistics.setDisable(true);
			btnFaq.setDisable(true);
		}
		
		if(dc.getEmployee().getRole().equals("AD")) {
			btnTickets.setDisable(true);
			btnContractType.setDisable(true);
			btnFaq.setDisable(true);
			btnKpi.setDisable(true);
			btnStatistics.setDisable(true);
			btnFaq.setDisable(true);
		}
		
		btnUsername.setText(dc.getEmployee().getUser().getUserName());		
		btnUsername.setOnAction(this::displayAccount);
		btnCustomer.setOnAction(this::displayCustomers);
		btnTickets.setOnAction(this::displayTickets);
		btnFaq.setOnAction(this::displayFaq);
		btnEmployee.setOnAction(this::displayEmployees);
		btnContractType.setOnAction(this::displayContractType);

		btnKpi.setOnAction(this::displayKpi);

		btnStatistics.setOnAction(this::displayStatictics);

	}

	private void displayStatictics(ActionEvent event) {
		setActiveButtonColor(btnStatistics);
		StatisticsPanelController cppc = new StatisticsPanelController(dc);
		bpDashboard.setCenter(cppc);
		bpDashboard.setPadding(new Insets(150, 0, 0, 300));
	}
	
	private void displayCustomers(ActionEvent event) {
		setActiveButtonColor(btnCustomer);
		ContactPersonPanelController cppc = new ContactPersonPanelController(dc);
		bpDashboard.setCenter(cppc);
		bpDashboard.setPadding(new Insets(150, 0, 0, 300));
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
		bpDashboard.setPadding(new Insets(100, 0, 0, 250));
	}
	
	private void displayAccount(ActionEvent event) {
		setActiveButtonColor(btnUsername);
		SignedInAccountPanelController sap = new SignedInAccountPanelController(dc);
		bpDashboard.setCenter(sap);
		bpDashboard.setPadding(new Insets(0, 0, 0, 500));
	}
	
	private void displayKpi(ActionEvent event) {
		setActiveButtonColor(btnKpi);
		KpiPanelController kpi = new KpiPanelController(dc);
		bpDashboard.setCenter(kpi);
		
	}

	private void setActiveButtonColor(Button button) {
		btnCustomer.setStyle("-fx-text-fill: #7c7c7c;");
		btnTickets.setStyle("-fx-text-fill: #7c7c7c;");
		btnEmployee.setStyle("-fx-text-fill: #7c7c7c;");
		btnFaq.setStyle("-fx-text-fill: #7c7c7c;");
		btnUsername.setStyle("-fx-text-fill: #7c7c7c;");
		btnStatistics.setStyle("-fx-text-fill: #7c7c7c;");
		btnKpi.setStyle("-fx-text-fill: #7c7c7c;");
		//set active button
		button.setStyle("-fx-text-fill: #000000;");
	}
}
