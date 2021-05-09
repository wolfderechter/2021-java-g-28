package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.stream.Collectors;

import domain.Company;
import domain.ContactPerson;
import domain.ITicket;
import domain.TechnicianController;
import domain.TicketStatusEnum;
import domain.TicketTypeEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;

public class TicketEditPanelController extends GridPane implements PropertyChangeListener {
	@FXML
	private TextField txFieldTicketNr;
	@FXML
	private TextField txFieldTitle;
	@FXML
	private ComboBox<TicketStatusEnum> cmbFieldStatus;
	@FXML
	private ComboBox<TicketTypeEnum> cmbType;
	@FXML
	private ComboBox<String> cmbCompany;
	@FXML
	private ComboBox<String> cmbContactPerson;
	@FXML
	private TextArea txAreaDescription;
	@FXML
	private DatePicker dpDateCreate;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	@FXML
	private ListView<String> lstReactions;
	@FXML
	private Button btnAddReaction;
	@FXML 
	private Button btnCreateTicket;
	@FXML
	private TextArea txtReactionText;

	private ITicket ticket;

	private TechnicianController dc;

	public TicketEditPanelController(TechnicianController dc2) {
		this.dc = dc2;
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
		this.dc.updateTicket(cmbFieldStatus.getSelectionModel().getSelectedItem(),txAreaDescription.getText());
	}

	private void cancelTicketDetails(ActionEvent actionEvent) {
		resetFields();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.ticket = (ITicket) evt.getNewValue();
		resetFields();
	}

	private void resetFields() {
		//ticket info
		btnCreateTicket.setVisible(true);
		btnSave.setText("Save");
		txFieldTitle.setText(ticket.Title().getValue());
		cmbFieldStatus.setItems(FXCollections.observableArrayList(TicketStatusEnum.values()));
		cmbFieldStatus.getSelectionModel().select(ticket.getStatus());
		cmbType.setItems(FXCollections.observableArrayList(TicketTypeEnum.values()));
		cmbType.getSelectionModel().select(ticket.getType());
		txAreaDescription.setText(ticket.getDescription());
		cmbCompany.setItems(FXCollections.observableArrayList(dc.getAllCompanyNames()));
		cmbCompany.getSelectionModel().select(ticket.getContactPerson().getCompany().getCompanyName());
		cmbCompany.setDisable(true);
		cmbContactPerson.setItems(FXCollections.observableArrayList(dc.getContactPersonFromCompanyName(cmbCompany.getSelectionModel().getSelectedItem())));
		cmbContactPerson.getSelectionModel().select(ticket.getContactPerson().getUser().getUserName());
		cmbContactPerson.setDisable(true);
		dpDateCreate.setValue(ticket.getDateCreation());
		dpDateCreate.setDisable(true);
		//button acties
		btnSave.setOnAction(this::saveTicketDetails);
		btnCancel.setOnAction(this::cancelTicketDetails);
		btnCreateTicket.setOnAction(this::createTicketStart);
		lstReactions.setVisible(true);
		txtReactionText.setVisible(true);
		
		//listview reacties
		//lstReactions.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		btnAddReaction.setOnAction(this::addReaction);
		btnAddReaction.setVisible(true);
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
	}
	
	private void createTicketStart(ActionEvent event) {
		txFieldTitle.clear();
		txAreaDescription.clear();
		dpDateCreate.setValue(null);
		dpDateCreate.setDisable(false);
		cmbFieldStatus.setDisable(true);
		cmbFieldStatus.getSelectionModel().select(TicketStatusEnum.Created);
		cmbCompany.setDisable(false);
		cmbContactPerson.setDisable(false);
		cmbType.getSelectionModel().select(null);;
		//button acties
		btnSave.setOnAction(this::createTicket);
		btnCancel.setOnAction(this::cancelTicketDetails);
		btnSave.setText("Create ticket");
		btnCreateTicket.setVisible(false);
		btnAddReaction.setVisible(false);
		lstReactions.setVisible(false);
		txtReactionText.setVisible(false);
	}
	
	private void createTicket(ActionEvent event) {
		dc.createTicket(dpDateCreate.getValue(),txFieldTitle.getText(),txAreaDescription.getText(),cmbType.getSelectionModel().selectedItemProperty().getValue(),
				cmbContactPerson.getSelectionModel().selectedItemProperty().getValue());
		
	}
	
	//veranderen door een knop methode
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
