package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

import domain.AdministratorController;
import domain.ContactPerson;
import domain.DomainController;
import domain.Employee;
import domain.IEmployee;
import domain.TicketStatusEnum;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;

public class EmployeeEditPanelController extends GridPane implements PropertyChangeListener{
    @FXML
    private TextField txFieldId;
    @FXML
    private DatePicker datePickerDateInService;  
    @FXML
    private TextField txFieldFirstName;
    @FXML
    private TextField txFieldLastName;
    @FXML
    private TextField txFieldAdress;
	@FXML
	private ComboBox<String> cmbFieldRole;
	@FXML
	private TextField txFieldPhoneNumber;
    @FXML
    private TextField txFieldEmail;
    @FXML
    private TextField txFieldUsername;
	@FXML
	private CheckBox chBoxStatus;
	@FXML
	private Button btnCreateEmployee;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	
	
	private AdministratorController dc;
	private IEmployee employee;
	
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
        btnCreateEmployee.setOnAction(this::createEmployeeStart);
    }
    
	private void saveEmployeeDetails(ActionEvent actionEvent) {
		this.dc.updateEmployee(Integer.valueOf(txFieldId.getText()),
				datePickerDateInService.getValue(),
				txFieldFirstName.getText(),
				txFieldLastName.getText(),
				txFieldAdress.getText(),
				cmbFieldRole.getSelectionModel().getSelectedItem(),
				txFieldPhoneNumber.getText(),
				txFieldEmail.getText(),
				txFieldUsername.getText(),
				chBoxStatus.isSelected());
	}
		
	private void cancelEmployeeDetails(ActionEvent actionEvent) {
		resetFields();
	}
	

	
	private void resetFields() {
		btnCreateEmployee.setVisible(true);
		btnSave.setText("Save");
		//Mag niet gewijzigd worden van employee

		//Mag wel gewijzigd worden van employee
		txFieldId.setText(employee.getId().toString());
		txFieldId.setDisable(true);
		datePickerDateInService.setValue(employee.getDateInService());
		txFieldFirstName.setText(employee.getFirstName());
		txFieldLastName.setText(employee.getLastName());
		txFieldAdress.setText(employee.getAdress());
		cmbFieldRole.setItems(FXCollections.observableArrayList(new String[] {"SM", "TE", "AD"}));
		cmbFieldRole.getSelectionModel().select(employee.getRole());
		txFieldPhoneNumber.setText(employee.getPhoneNumber());
		txFieldEmail.setText(employee.getEmail());
		txFieldUsername.setText(employee.getUser().getUserName());
		chBoxStatus.setSelected(employee.getStatus());

		btnSave.setOnAction(this::saveEmployeeDetails);
		btnCancel.setOnAction(this::cancelEmployeeDetails);
		btnCreateEmployee.setOnAction(this::createEmployeeStart);
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.employee = (IEmployee) evt.getNewValue();
		resetFields();
	}
	
	private void createEmployeeStart(ActionEvent event) {
		txFieldId.clear();
		datePickerDateInService.setValue(null);
		txFieldFirstName.clear();
		txFieldLastName.clear();
		txFieldAdress.clear();
		//cmbFieldRole.setDisable(true);
		//cmbFieldRole.getSelectionModel().clearSelection();
		txFieldPhoneNumber.clear();
		txFieldEmail.clear();
		txFieldUsername.clear();
		chBoxStatus.setSelected(true);
		
		btnSave.setOnAction(this::createEmployee);
		btnCancel.setOnAction(this::cancelEmployeeDetails);
		btnSave.setText("Create Employee");
		btnCreateEmployee.setVisible(false);
	}
	
	private void createEmployee(ActionEvent event) {
		try {
			dc.createUser(txFieldPhoneNumber.getText(),
					txFieldEmail.getText(),
					txFieldUsername.getText(),
					cmbFieldRole.getSelectionModel().getSelectedItem());
			
			dc.createEmployee(datePickerDateInService.getValue(),
					txFieldFirstName.getText(),
					txFieldLastName.getText(),
					txFieldAdress.getText(),
					cmbFieldRole.getSelectionModel().getSelectedItem(),
					txFieldPhoneNumber.getText(),
					txFieldEmail.getText(),
					txFieldUsername.getText(),
					chBoxStatus.isSelected());
			
		} catch(Exception ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Something went wrong creating the employee");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
	}
    
}
