package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import domain.ContractTypeCreationMethod;
import domain.DomainController;
import domain.IContractType;
import domain.SupportManagerController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class ContractTypeEditPanelController extends GridPane implements PropertyChangeListener{
	@FXML
	private ComboBox<ContractTypeCreationMethod> cmbCreationMethod;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtMaxResponse;
	@FXML
	private CheckBox chk24HSupport;
	@FXML
	private CheckBox chkActive;
	@FXML
	private TextField txtDuration;
	@FXML
	private TextField txtPrice;
	@FXML
	private Label lblAmount;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnCreate;
	
	
	private SupportManagerController dc;
	
	private IContractType cType;
	
	public ContractTypeEditPanelController(SupportManagerController dc2) {
		this.dc = dc2;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ContractTypeEditPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        btnCancel.setOnAction(this::cancelChanges);
        btnSave.setText("Make Changes");
		btnSave.setOnAction(this::saveChangesStart);
		btnCreate.setOnAction(this::createContractTypeStart);
	}
	
	public void createContractTypeStart(ActionEvent event) {
		makeEverythingEditable();
		txtName.clear();
		txtMaxResponse.clear();
		cmbCreationMethod.getSelectionModel().select(1);
		chk24HSupport.setSelected(false);
		txtDuration.clear();
		txtPrice.clear();;
		btnCreate.setOnAction(this::createContractType);
		btnCreate.setText("Create");
		
	}
	
	public void createContractType(ActionEvent event) {
		try {
			dc.createContractType(txtName.getText(), Integer.parseInt(txtMaxResponse.getText()),cmbCreationMethod.getSelectionModel().getSelectedItem(),
					chk24HSupport.isSelected(),Integer.parseInt(txtDuration.getText()),Double.parseDouble(txtPrice.getText()));
			resetFields();
		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Something went wrong creating the contracttype");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
	}
	
	public void saveChangesStart(ActionEvent event) {
		if(cType.Amount().getValue() == 0){
			makeEverythingEditable();
		}
		chkActive.setDisable(false);
		btnSave.setText("Save Changes");
		btnSave.setOnAction(this::saveChanges);
	}
	
	public void saveChanges(ActionEvent event) {
		if(cType.Amount().getValue() != 0){
			dc.SaveStatusContractType(chkActive.isSelected());
		}else {
			dc.saveAllContractType(txtName.getText(),txtMaxResponse.getText(),cmbCreationMethod.getSelectionModel().getSelectedItem(),
					chk24HSupport.isSelected(),txtDuration.getText(),txtPrice.getText(),chkActive.isSelected());
		}
	}
	
	public void cancelChanges(ActionEvent event) {
		resetFields();
	}
	
	//gaat alle textboxen opvullen met data van contractType
	public void resetFields() {
		txtName.setText(cType.getName());
		txtMaxResponse.setText(Integer.toString(cType.getMaxResponseTime()));
		cmbCreationMethod.setItems(FXCollections.observableArrayList(ContractTypeCreationMethod.values()));
		cmbCreationMethod.getSelectionModel().select(cType.getCreationMethod());
		chk24HSupport.setSelected(cType.isOutsideBusinessHours());
		chkActive.setSelected(cType.isActive());
		txtDuration.setText(Integer.toString(cType.getMinDuration()));
		txtPrice.setText(Double.toString(cType.getPrice()));
		lblAmount.setText(cType.Amount().getValue().toString());
		chkActive.setDisable(true);
		//alles op disabled buiten active
		txtName.setDisable(true);
		txtMaxResponse.setDisable(true);
		cmbCreationMethod.setDisable(true);
		chk24HSupport.setDisable(true);
		txtDuration.setDisable(true);
		txtPrice.setDisable(true);
		lblAmount.setDisable(true);
		btnCancel.setOnAction(this::cancelChanges);
        btnSave.setText("Make Changes");
		btnSave.setOnAction(this::saveChangesStart);
		btnCreate.setOnAction(this::createContractTypeStart);
	}
	
	private void makeEverythingEditable() {
		txtName.setDisable(false);
		txtMaxResponse.setDisable(false);
		cmbCreationMethod.setDisable(false);
		chk24HSupport.setDisable(false);
		txtDuration.setDisable(false);
		txtPrice.setDisable(false);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.cType = (IContractType) evt.getNewValue();
		resetFields();
	}

}
