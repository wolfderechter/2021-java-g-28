package gui;

import java.io.IOException;

import domain.Controller;
import domain.ICompany;
import domain.SupportManagerController;
import domain.TechnicianController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StatisticsPanelController extends AnchorPane {
	@FXML
	private LineChart<String,Number> lnChart;
	@FXML
	private ChoiceBox<String> chbCompany;
	@FXML
	private Label lblTicketsAmount;
	@FXML
	private NumberAxis Axis;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private Label lblAverageTime;
	

	private SupportManagerController dc;
	private ICompany selectedCompany;

	public StatisticsPanelController(Controller dc2) {
		this.dc = (SupportManagerController) dc2;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StatisticsPanel.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		chbCompany.setItems(FXCollections.observableArrayList(dc.getAllCompanyNames()));
		
		chbCompany.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String nameSelected,
					String oldvalue) {
				
				selectedCompany = (ICompany) dc.getCompanyByName(oldvalue);
				setDataChart();
			}
		});
	}

	public void setDataChart() {
		long amount = selectedCompany.getTickets().stream().count();
		lblTicketsAmount.setText(Long.toString(amount));
		int total = selectedCompany.getTickets().stream().map(t->t.getResolvedDate().compareTo(t.getDateCreation())).reduce(0, Integer::sum);
		double avg = total / amount;
		lblAverageTime.setText(Double.toString( avg) + " Days");
		// setting the chart
		// creating the chart
		lnChart.setTitle("ticket resolve time");
		lnChart.getXAxis().setLabel("Ticket Tijdsduur");
		lnChart.getYAxis().setLabel("Duration in days");
		// defining a series
		XYChart.Series<String, Number> series = new Series<>();
		
		// populating the series with data
		selectedCompany.getTickets()
				.forEach(t -> series.getData().add(new XYChart.Data<String, Number>(t.getTitle(),
						t.getResolvedDate().compareTo(t.getDateCreation()))));
		lnChart.setData(FXCollections.observableArrayList(series));
	}

}
