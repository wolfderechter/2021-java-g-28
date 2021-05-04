package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.stream.Collectors;

import domain.DomainController;
import domain.Reaction;
import domain.Ticket;
import domain.TicketStatusEnum;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
	@FXML
	private ListView<String> lstReactions;
	@FXML
	private Button btnAddReaction;
	@FXML
	private TextArea txtReactionText;

	private Ticket ticket;

	private DomainController dc;

	public TicketEditPanelController(DomainController dc) {
		this.dc = dc;
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
		this.dc.updateTicket(ticket);
	}

	private void cancelTicketDetails(ActionEvent actionEvent) {
		resetFields();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.ticket = (Ticket) evt.getNewValue();
		resetFields();
	}

	private void resetFields() {
		//ticket info
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
		//button acties
		btnSave.setOnAction(this::saveTicketDetails);
		btnCancel.setOnAction(this::cancelTicketDetails);
		//listview reacties
		//lstReactions.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		btnAddReaction.setOnAction(this::addReaction);
		ObservableList<String> reactions = FXCollections.observableArrayList(ticket.getReactions().stream().map(r->r.getNameUserReaction()).collect(Collectors.toList()));
		lstReactions.setItems(reactions);
		lstReactions.getSelectionModel().selectedItemProperty()
		.addListener((observableValue, vorigReactie, selectedReactie) -> 
		{
		//Controleer of er een ticket is geselecteerd
		if (selectedReactie!= null) {
			int index = lstReactions.getSelectionModel().getSelectedIndex();
			txtReactionText.setText(ticket.getReactions().get(index).getText());
			}
		}
	);
		System.out.println(ticket.TicketNr());
		System.out.println(ticket.getReactions());
	}
	
	@FXML
	private void addReaction(ActionEvent event) {
		// naam van filosoof aan de gebruiker vragen
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Enter details");
		dialog.setHeaderText("Add Reaction");
		dialog.setContentText("Enter Reaction:");
		dialog.showAndWait().ifPresent(response -> {
			if (!response.isBlank()) {
				// voeg nieuwe filosoof toe in model
				dc.addReaction(response);
				// zie volgende slide
				lstReactions.getSelectionModel().selectLast(); 
			}
		});
	}

}
