package gui;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.stream.Collectors;

import domain.AdministratorController;
import domain.Company;
import domain.ContactPerson;
import domain.Contract;
import domain.ContractEnumStatus;
import domain.Controller;
import domain.DomainController;
import domain.ICompany;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ContactPersonEditPanelController extends VBox implements PropertyChangeListener{
	  @FXML
	    private TextField TxFieldCustomerNr;

	    @FXML
	    private TextField TxFieldFirstName;

	    @FXML
	    private TextField TxFieldLastName;

	    @FXML
	    private TextField TxFieldEMail;

	    @FXML
	    private TextField TxFieldName;

	    @FXML
	    private TextField TxFieldAddress;
	    
	    @FXML
	    private Button btnSave;
	    
	    @FXML
	    private Button btnCancel;

	    @FXML
	    private TableView<Contract> tvContracts;

	    @FXML
	    private TableColumn<Contract, Number> nrCol;

	    @FXML
	    private TableColumn<Contract, String> nameCol;
	 
	    @FXML
	    private TableColumn<Contract, ContractEnumStatus> statusCol;
	    
	    @FXML
	    private TextField TxFieldUsername;

	    @FXML
	    private CheckBox checkBoxStatus;
	    
	    @FXML
	    private Button btnCreateCompany;

	    @FXML
	    private DatePicker datePickerDateInService;
	    
	    @FXML
	    private TableColumn<ContactPerson, String> firstNameCol;

	    @FXML
	    private TableColumn<ContactPerson, String> lastNameCol;

	    @FXML
	    private TableColumn<ContactPerson, String> emailCol;
	    
	    @FXML
	    private TableView<ContactPerson> tvContactPersons;
	    
	    @FXML
	    private Button btnCreateContactPerson;

	    @FXML
	    private TextField txFieldAddFirstName;

	    @FXML
	    private TextField txFieldAddLastName;

	    @FXML
	    private TextField txFieldAddEmail;
	    @FXML
	    private Button btnCreateContactPerson2;
	    
	    @FXML
	    private TextField txFieldContactPersonUsername;
    
    private ICompany company;
    
    private Controller dc;
	
    public ContactPersonEditPanelController(Controller dc2) {
    	this.dc = (AdministratorController) dc2;
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactPersonEditPanel.fxml"));
        loader.setController(this);
       loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        setPadding(new Insets(35, 0, 0, 0));
        fields();
        btnCreateCompany.setOnAction(this::createCompanyStart);
        btnCreateContactPerson2.setOnAction(this::createContactPersonStart);
        btnCreateContactPerson2.setVisible(true);
    }
    
    private void saveCompany(ActionEvent actionEvent) {
    	this.dc.updateCompany(TxFieldName.getText(), TxFieldAddress.getText());
    
    }
    
    private void saveContactPerson(ActionEvent actionEvent) {
    		this.dc.updateContactPerson(TxFieldFirstName.getText(), TxFieldLastName.getText(), TxFieldEMail.getText());

    }
    

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		this.company = (ICompany) evt.getNewValue();
		fields();
	}
	
	
	public void cancelDetails(ActionEvent actionEvent) {
		fields();
	}
	
	private void fields() {
		
		
		if(company != null) {
			TxFieldCustomerNr.setText(company.CompanyNr().getValue().toString());
			TxFieldCustomerNr.setDisable(true);
			TxFieldName.setText(company.getCompanyName());
			TxFieldAddress.setText(company.getCompanyAdress());
			firstNameCol.setCellValueFactory(cellData -> cellData.getValue().FirstName());
			lastNameCol.setCellValueFactory(cellData -> cellData.getValue().LastName());
			tvContactPersons.setItems(FXCollections.observableArrayList(this.company.getContactPersons()));
			
		btnSave.setOnAction(e-> {
				this.saveCompany(e);
				this.saveContactPerson(e);
			});
		nrCol.setCellValueFactory(cellData -> cellData.getValue().ContractNr());
		nameCol.setCellValueFactory(cellData -> cellData.getValue().getContractType().Name());
		statusCol.setCellValueFactory(cellData -> cellData.getValue().Status());
		tvContracts.setItems(FXCollections.observableArrayList(this.company.getContracts()));
		datePickerDateInService.setValue(company.getCustomerInitDate());
		checkBoxStatus.setSelected(company.getStatus());
		btnCancel.setOnAction(this::cancelDetails);
		
			}
		
		
	}
	
	private void createCompanyStart(ActionEvent event) {
		TxFieldName.clear();
		TxFieldAddress.clear();
		btnCancel.setOnAction(this::cancelDetails);
		btnSave.setText("Create company");
		btnCreateCompany.setOnAction(this::createCompany);
		
	}
	
	private void createCompany(ActionEvent event) {
		try {
			dc.createCompany(TxFieldName.getText(), TxFieldAddress.getText(), datePickerDateInService.getValue(), checkBoxStatus.isSelected());
		}catch(Exception ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Test");
			alert.setHeaderText("test");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
		
	}
	
	private void createContactPersonStart(ActionEvent event) {
		try {
			dc.createUser("0484107905", txFieldAddEmail.getText(),txFieldContactPersonUsername.getText() , "Customer");
			dc.createContactPerson(txFieldAddFirstName.getText(), txFieldAddLastName.getText(),txFieldContactPersonUsername.getText() );
			txFieldAddFirstName.clear();
			txFieldAddLastName.clear();
			txFieldContactPersonUsername.clear();
		}catch(Exception ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Test");
			alert.setHeaderText("test");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
	}

    
}
