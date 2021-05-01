package gui;

import java.io.IOException;

import domain.ContactPerson;
import domain.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EmployeeEditPanelController extends GridPane{
    @FXML
    private TextField TxFieldFirstName;

    @FXML
    private TextField TxFieldLastName;
    
    @FXML
    private TextField TxFieldAdress;

    @FXML
    private TextField TxFieldTelephone;

    @FXML
    private TextField TxFieldEmail;

	
    public EmployeeEditPanelController(Employee employee) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeEditPanel.fxml"));
        loader.setController(this);
       loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
      // TxFieldCustomerNr.setText(contactPerson.getId());
       TxFieldFirstName.setText(employee.getFirstNameProp().getValue().toString());
        
    }
    
}
