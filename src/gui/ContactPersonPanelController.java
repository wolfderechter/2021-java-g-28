package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Observable;

import domain.Company;
import domain.ContactPerson;
import domain.DomainController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ContactPersonPanelController extends BorderPane  implements PropertyChangeListener{

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
	  
       
      //  System.out.println(dc.getAllContactPersons());
        userNameCol.setCellValueFactory(cellData -> cellData.getValue().getUser().UserName());
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().FirstName());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().LastName());
        companyCol.setCellValueFactory(cellData -> cellData.getValue().getCompany().CompanyName());
        tvContactPersons.setItems(dc.getAllContactPersons());
        ContactPersonEditPanelController cpepc = new ContactPersonEditPanelController(dc);
        dc.addContactPersonListener(cpepc);
        setRight(cpepc);
        tvContactPersons.getSelectionModel().selectedItemProperty()
        .addListener((observableValue, previousContactPerson, selectedContactPerson) -> 
        {
		
			if (selectedContactPerson!= null) {
				int index = tvContactPersons.getSelectionModel().getSelectedIndex();
				dc.setContactPerson(selectedContactPerson);
				}
			}
		);
	
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
       
}
	

