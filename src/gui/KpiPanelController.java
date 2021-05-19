package gui;

import java.io.IOException;
import java.time.Month;
import java.util.List;

import domain.Controller;
import domain.SupportManagerController;
import domain.Ticket;
import domain.TicketStatusEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class KpiPanelController extends GridPane {
	@FXML
	private BarChart<String, Number> bcChart;
	@FXML
	private PieChart pChart;
	@FXML
	private Button btnTicketsPerMaand;

	@FXML
	private Button btnTicketsPerStatus;

	@FXML
	private CategoryAxis bCategoryAxis;
	@FXML
	private NumberAxis bNumberAxis;
	
	SupportManagerController dc;
	
	public KpiPanelController(Controller dc2) {
		
		switch (dc2.getEmployee().getRole()) {
		case "SM": this.dc = (SupportManagerController) dc2; break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + dc.getEmployee().getRole());
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("KpiPanel.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		
		
		
		btnTicketsPerMaand.setOnAction(this::displayTicketsPerMonth);
		btnTicketsPerStatus.setOnAction(this::displayTicketsPerStatus);
	}
	
	private void displayTicketsPerMonth(ActionEvent event) {
		bcChart.getData().clear();
		bcChart.layout();
		
		bcChart.setTitle("Tickets per month");
		
		bCategoryAxis.setLabel("Month");
		bNumberAxis.setLabel("Number of tickets");
		
		bcChart.setTitle("Tickets per month");
		XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
		List<List<Ticket>> ticketsPerMonthList = dc.getAllTicketsAsListPerMonth();
		int index = 1;
		for (List<Ticket> list : ticketsPerMonthList) {
			series.getData().add(new XYChart.Data<String,Number>(Month.of(index).toString(), list.size()));
			index++;
		}
		bcChart.getData().add(series);
	}
	

	
	private void displayTicketsPerStatus(ActionEvent event) {
		bcChart.getData().clear();
		bcChart.layout();
		bcChart.setTitle("Tickets per status");
		
		bCategoryAxis.setLabel("Status");
		bNumberAxis.setLabel("Number of tickets");
		
		XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
		List<List<Ticket>> ticketsPerStatusList = dc.getAllTicketsAsListPerStatus();
		int index = 0;
		for (List<Ticket> list : ticketsPerStatusList) {
			series.getData().add(new XYChart.Data<String,Number>(TicketStatusEnum.values()[index].toString(), list.size()));
			index++;
		}
		bcChart.getData().add(series);
	}
	

}
