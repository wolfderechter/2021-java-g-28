package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import domain.DomainController;
import domain.Ticket;
import domain.TicketStatusEnum;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TicketEditPanelController extends GridPane implements PropertyChangeListener {
	@FXML
	private TextField TxFieldTicketNr;
	@FXML
	private TextField TxFieldTitle;
	@FXML
	private ComboBox<TicketStatusEnum> CmbFieldStatus;
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

	private Ticket ticket;
	
	private DomainController dc;

	public TicketEditPanelController(DomainController dc) {
		this.dc = dc;
		this.ticket = new Ticket();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketEditPanel.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		
	}

	private void saveTicketDetails(ActionEvent actionEvent) {
		ticket.setStatus(CmbFieldStatus.getSelectionModel().getSelectedItem());
		ticket.setDescription(TxAreaDescription.getText());
		this.dc.setTicket(ticket);
	}
	
	private void cancelTicketDetails(ActionEvent actionEvent) {
		propertyChange(new PropertyChangeEvent(actionEvent, "ticket", this.ticket, this.ticket));
	}
	
	

	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.ticket = (Ticket) evt.getNewValue();
		TxFieldTicketNr.setText(ticket.TicketNr().getValue().toString());
		TxFieldTicketNr.setDisable(true);
		TxFieldTitle.setText(ticket.Title().getValue());
		TxFieldTitle.setDisable(true);
		CmbFieldStatus.setItems(FXCollections.observableArrayList(TicketStatusEnum.values()));
		CmbFieldStatus.getSelectionModel().select(ticket.getStatus());
		TxAreaDescription.setText(ticket.getDescription());
		TxFieldContPersName.setText(String.format("%s %s", ticket.getContactPersonId().getFirstName(),
				ticket.getContactPersonId().getLastName()));
		TxFieldContPersName.setDisable(true);
		TxFieldCompName.setText(ticket.getContactPersonId().getCompany().getCompanyName());
		TxFieldCompName.setDisable(true);
		TxFieldCreDate.setText(ticket.getDateCreation().toString());
		TxFieldCreDate.setDisable(true);
		btnSave.setOnAction(this::saveTicketDetails);
		btnCancel.setOnAction(this::cancelTicketDetails);
	}

}
