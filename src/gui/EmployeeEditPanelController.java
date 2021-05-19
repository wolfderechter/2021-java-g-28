package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDate;
import domain.AdministratorController;
import domain.IEmployee;
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
		emptyFields();
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
		txFieldId.setDisable(true);
		txFieldFirstName.clear();
		txFieldLastName.clear();
		txFieldAdress.clear();
		//cmbFieldRole.setDisable(true);
		//cmbFieldRole.getSelectionModel().clearSelection();
		datePickerDateInService.setValue(LocalDate.now());
		datePickerDateInService.setDisable(true);
		cmbFieldRole.setItems(FXCollections.observableArrayList(new String[] {"SM", "TE", "AD"}));
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
			//Check if obligated values are present else throw exception
			if(txFieldPhoneNumber.getText().isBlank() || 
				txFieldEmail.getText().isBlank() || 
				cmbFieldRole.getSelectionModel().getSelectedItem().isBlank()) {
				throw new Exception("Phonenumber, Email and role can not be empty!");
			}
			
			if(txFieldFirstName.getText().isBlank() || 
				txFieldLastName.getText().isBlank() ||
				txFieldAdress.getText().isBlank()) {
				throw new Exception("Firstname, Lastname and Adress can not be empty!");
			}
			
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
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Creating Employee");
			alert.setHeaderText("Employee succesfully created");
			alert.showAndWait();
			emptyFields();
			
		} catch(Exception ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Something went wrong creating the employee");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
	}
	
	private void emptyFields() {
		txFieldId.clear();
		txFieldId.setDisable(true);
		datePickerDateInService.setValue(null);
		txFieldFirstName.clear();
		txFieldLastName.clear();
		txFieldAdress.clear();
		cmbFieldRole.setItems(FXCollections.observableArrayList(new String[] {"SM", "TE", "AD"}));
		txFieldPhoneNumber.clear();
		txFieldEmail.clear();
		txFieldUsername.clear();
		chBoxStatus.setSelected(true);
		btnSave.setOnAction(this::saveEmployeeDetails);
		btnCancel.setOnAction(this::cancelEmployeeDetails);
		btnCreateEmployee.setOnAction(this::createEmployeeStart);
		btnSave.setText("Save");
		btnCreateEmployee.setVisible(true);

	}
    
}
