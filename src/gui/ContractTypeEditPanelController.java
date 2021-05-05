package gui;

import java.io.IOException;

import domain.ContractType;
import domain.ContractTypeCreationMethod;
import domain.DomainController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ContractTypeEditPanelController extends GridPane {
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
	
	private DomainController dc;
	
	private ContractType cType;
	
	public ContractTypeEditPanelController(DomainController dc) {
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ContractTypeEditPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
	}
	
	public void changeContractType(ContractType ct) {
		this.cType = ct;
		txtName.setText(cType.getName());
		txtMaxResponse.setText(Integer.toString(cType.getMaxResponseTime()));
		cmbCreationMethod.setItems(FXCollections.observableArrayList(ContractTypeCreationMethod.values()));
		cmbCreationMethod.getSelectionModel().select(cType.getCreationMethod());
		chk24HSupport.setSelected(cType.isOutsideBusinessHours());
		chkActive.setSelected(cType.isActive());
		txtDuration.setText(Integer.toString(cType.getMinDuration()));
		txtPrice.setText(Double.toString(cType.getPrice()));
		lblAmount.setText(cType.Amount().getValue().toString());
	}

}
