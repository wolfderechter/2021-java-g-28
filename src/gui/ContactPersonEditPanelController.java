package gui;

import java.io.IOException;

import domain.ContactPerson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ContactPersonEditPanelController extends GridPane{
    @FXML
    private TextField TxFieldName;

    @FXML
    private TextField TxFieldStreet;

    @FXML
    private TextField TxFieldTown;

    @FXML
    private TextField TxFieldHouse;

    @FXML
    private TextField TxFieldPostalCode;

    @FXML
    private TextField TxFieldCountry;

    @FXML
    private TextField TxFieldTelephone;

    @FXML
    private TextField TxFieldCustomerNr;

    @FXML
    private TextField TxField;
	
    public ContactPersonEditPanelController(ContactPerson contactperson) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactPersonEditPanel.fxml"));
        loader.setController(this);
       loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
      // TxFieldCustomerNr.setText(contactPerson.getId());
       TxFieldName.setText(contactperson.getLastNameProp().getValue().toString());
        
    }
    
}
