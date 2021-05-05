package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import domain.Company;
import domain.ContactPerson;
import domain.Contract;
import domain.DomainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ContactPersonEditPanelController extends GridPane implements PropertyChangeListener{
    @FXML
    private TextField TxFieldName;

    @FXML
    private TextField TxFieldStreet;

    @FXML
    private TextField TxFieldTelephone;

    @FXML
    private TextField TxFieldCustomerNr;

    @FXML
    private TextField TxField;
    
    @FXML
    private TableColumn<ContactPerson, Number> nrCol;

//    @FXML
//    private TableColumn<?, ?> typeCol;
//
//    @FXML
//    private TableColumn<?, ?> statusCol;
//
//    @FXML
//    private TableColumn<?, ?> startdateCol;
//
//    @FXML
//    private TableColumn<?, ?> enddateCol;

    @FXML
    private TextField TxFieldUsername;

    @FXML
    private TableView<Contract> tvContracts;

    
    private ContactPerson contactPerson;
    
    private DomainController dc;
	
    public ContactPersonEditPanelController(DomainController dc) {
    	this.dc = dc;
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactPersonEditPanel.fxml"));
        loader.setController(this);
       loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        //nrCol.setCellValueFactory(cellData -> cellData.getValue().getCompany().getContracts());
		//tvContracts.setItems(dc.getAllContracts());
     
        fields();
    }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		this.contactPerson = (ContactPerson) evt.getNewValue();
		fields();
	}
	
	private void fields() {
		if(contactPerson != null) {
			TxFieldStreet.setText(contactPerson.getCompany().getCompanyAdress());
			TxFieldName.setText(contactPerson.getCompany().getCompanyName());
			TxFieldCustomerNr.setText("" + contactPerson.getId());
			TxFieldCustomerNr.setDisable(true);
			TxFieldUsername.setText(contactPerson.getUser().getUserName());
			
			}
	
		
		
		//TxFieldStreet.setText(contactPerson.FirstName().getValue().toString());
		
	}
	

    
}
