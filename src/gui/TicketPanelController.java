package gui;

import java.io.IOException;

import domain.Ticket;
import domain.TicketController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class TicketPanelController extends BorderPane {

	@FXML
	private TableView<Ticket> tvTickets;
	@FXML
	private TableColumn<Ticket, Number> ticketNrCol;
	@FXML
	private TableColumn<Ticket, String> statusCol;
	@FXML
	private TableColumn<Ticket, String>  titleCol;
	private TicketController tc;
	
	public TicketPanelController(TicketController ticketsC) {
		tc= ticketsC;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        ticketNrCol.setCellValueFactory(cellData -> cellData.getValue().getTicketNrProp());
        statusCol.setCellValueFactory(cellData-> cellData.getValue().getStatusProp());
        titleCol.setCellValueFactory(cellData-> cellData.getValue().getTitleProp());
		tvTickets.setItems(tc.getAllTickets());
	}
}
