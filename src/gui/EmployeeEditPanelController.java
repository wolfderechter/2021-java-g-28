package gui;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

import domain.AdministratorController;
import domain.ContactPerson;
import domain.DomainController;
import domain.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
    private TextField TxFieldDateInService;
    @FXML
    private TextField TxFieldEmail;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	
	private AdministratorController dc;
	private Employee employee;
	
    public EmployeeEditPanelController(AdministratorController domainC) {
    	this.dc = domainC;
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeEditPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }  
    }
    
	private void saveEmployeeDetails(ActionEvent actionEvent) {
		this.dc.updateEmployee(employee);
	}
		
	private void cancelEmployeeDetails(ActionEvent actionEvent) {
		resetFields();
	}
	

	
	private void resetFields() {
		//Mag niet gewijzigd worden van employee

		//Mag wel gewijzigd worden van employee
		TxFieldFirstName.setText(employee.getFirstName());
		TxFieldFirstName.setDisable(true);
		TxFieldLastName.setText(employee.getLastName());
		TxFieldLastName.setDisable(true);
		
		TxFieldAdress.setText(employee.getAdress());
		TxFieldAdress.setDisable(true);
		TxFieldDateInService.setText(employee.getDateInService().toString());
		TxFieldDateInService.setDisable(true);
		
		btnSave.setOnAction(this::saveEmployeeDetails);
		btnCancel.setOnAction(this::cancelEmployeeDetails);
	}
    
}
