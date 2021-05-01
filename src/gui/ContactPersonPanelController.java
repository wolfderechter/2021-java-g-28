package gui;

import java.io.IOException;

import domain.ContactPerson;
import domain.DomainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ContactPersonPanelController extends BorderPane {

	@FXML
    private TableView<ContactPerson> tvContactPersons;

	@FXML
    private TableColumn<ContactPerson, String> userNameCol;

    @FXML
    private TableColumn<ContactPerson, String> firstNameCol;

    @FXML
    private TableColumn<ContactPerson, String> lastNameCol;

    @FXML
    private TableColumn<ContactPerson, String> companyCol;

	@FXML
	private Label lblUsername;
	
	@FXML 
	private TextArea TextAreaTest;
	
	private DomainController dc;
	
	public ContactPersonPanelController(DomainController dc) {
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactPersonPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
      
        ContactPersonEditPanelController cpepc = new ContactPersonEditPanelController(dc.getAllContactPersons().get(0));
        cpepc.setDisable(true);
        setRight(cpepc);
      //  System.out.println(dc.getAllContactPersons());
        userNameCol.setCellValueFactory(cellData -> cellData.getValue().getUserNameProp());
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProp());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().getLastNameProp());
        companyCol.setCellValueFactory(cellData -> cellData.getValue().getCompanyProp());
        tvContactPersons.setItems(this.dc.getAllContactPersons());
        tvContactPersons.getSelectionModel().selectedItemProperty()
        .addListener((observableValue, previousContactPerson, selectedContactPerson) -> 
        {
		
			if (selectedContactPerson!= null) {
				int index = tvContactPersons.getSelectionModel().getSelectedIndex();
				displaySelectedContactPersonDetails(selectedContactPerson);
				}
			}
		);
	
	}
        private void displaySelectedContactPersonDetails(ContactPerson selectedContactPerson) {
        	ContactPersonEditPanelController cpepc = new ContactPersonEditPanelController(selectedContactPerson);
        	setRight(cpepc);
        }   
	
	
}
