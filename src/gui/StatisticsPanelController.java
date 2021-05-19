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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StatisticsPanelController extends AnchorPane{
	@FXML
	private LineChart<Number,Number> lnChart;
	@FXML
	private ChoiceBox<String> chbCompany;
	@FXML
	private Label lblTicketsAmount;
	
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
			  public void changed(ObservableValue<? extends String> observableValue, String nameSelected, String oldvalue) {
				  selectedCompany =(ICompany) dc.getCompanyByName(nameSelected);
				  setDataChart();
			  }
		});
		chbCompany.getSelectionModel().selectFirst();
		setDataChart();
	}
	
	public void setDataChart(){
		lblTicketsAmount.setText(Long.toString(selectedCompany.getTickets().stream().count()));
		//setting the chart
		final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("ticket");
        //creating the chart
        lnChart =new LineChart<Number,Number>(xAxis,yAxis);
        lnChart.setTitle("ticket resolve time");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Data company");
        //populating the series with data
        selectedCompany.getTickets().forEach(t->series.getData().add(new XYChart.Data(1, "gsegrs")));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        lnChart.getData().add(series);
	}
	
	

}
