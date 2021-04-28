package gui;

import java.io.IOException;

import domain.Ticket;
import domain.TicketController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TicketEditPanelController extends GridPane {
	@FXML
	private TextField TxFieldTicketNr;
	@FXML
	private TextField TxFieldTitle;
	@FXML
	private TextField TxFieldStatus;
	@FXML
	private TextArea TxAreaDescription;
	@FXML
	private TextField TxFieldCompName;
	@FXML
	private TextField TxFieldContPersName;
	@FXML
	private TextField TxFieldCreDate;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	
	public TicketEditPanelController(Ticket ticket) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketEditPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        TxFieldTicketNr.setText(ticket.TicketNr().getValue().toString());
        TxFieldTicketNr.setDisable(true);
        TxFieldTitle.setText(ticket.Title().getValue());
        TxFieldTitle.setDisable(true);
        TxFieldStatus.setText(ticket.status.toString());
        TxAreaDescription.setText(ticket.getDescription());
        TxFieldContPersName.setText(String.format("%s %s", ticket.getContactPersonId().getFirstName(),ticket.getContactPersonId().getLastName()));
        TxFieldCompName.setText(ticket.getContactPersonId().getCompany().getCompanyName());
        TxFieldCreDate.setText(ticket.getDateCreation().toString());	
        btnSave.setOnAction(this::saveTicketDetails);
        
        
	}
	
	public void saveTicketDetails(ActionEvent actionEvent) {
    	
    }

}
