package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import domain.Controller;
import domain.DomainController;
import domain.SupportManagerController;
import domain.TechnicianController;
import domain.Ticket;
import domain.TicketStatusEnum;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class TicketPanelController extends BorderPane implements PropertyChangeListener{

	@FXML
	private TableView<Ticket> tvTickets;
	@FXML
	private TableColumn<Ticket, Number> ticketNrCol;
	@FXML
	private TableColumn<Ticket, TicketStatusEnum> statusCol;
	@FXML
	private TableColumn<Ticket, String>  titleCol;
	
	private TechnicianController dc;
	
	public TicketPanelController(Controller dc2) {
		this.dc= (TechnicianController) dc2;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        //aanmaak tableview + data
        ticketNrCol.setCellValueFactory(cellData -> cellData.getValue().TicketNr());
        statusCol.setCellValueFactory(cellData-> cellData.getValue().Status());
        titleCol.setCellValueFactory(cellData-> cellData.getValue().Title());
		tvTickets.setItems(dc.getAllTickets());
		//toevoegen edit panel
		TicketEditPanelController tepc = new TicketEditPanelController(dc);
		dc.addTicketListener(tepc);
		setRight(tepc);
		tvTickets.getSelectionModel().selectedItemProperty()
		.addListener((observableValue, vorigTicket, selectedTicket) -> 
			{
			//Controleer of er een ticket is geselecteerd
			if (selectedTicket!= null) {
				int index = tvTickets.getSelectionModel().getSelectedIndex();
				dc.setTicket(selectedTicket);
				}
			}
		);
		}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
		
	}
}
	
