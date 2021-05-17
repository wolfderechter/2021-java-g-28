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
	    private ListView<String> lstNamen;

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
			ObservableList<String> namen = company.getContactPersons().stream().map(c->c.getFirstName() + c.getLastName()).collect(Collectors.toCollection(FXCollections::observableArrayList));
			lstNamen.setItems(namen);
			lstNamen.getSelectionModel().selectedItemProperty()
			.addListener((observableValue, previousContactPerson, selectedContactPerson) -> 
			{
			if (selectedContactPerson!= null) {
				int index = lstNamen.getSelectionModel().getSelectedIndex();
				TxFieldFirstName.setText(company.getContactPersons().get(index).getFirstName());
				TxFieldLastName.setText(company.getContactPersons().get(index).getLastName());
				TxFieldEMail.setText(company.getContactPersons().get(index).getEmail());
				dc.setContactPerson(index);
				}
			}
		);
			
		btnSave.setOnAction(e-> {
				this.saveCompany(e);
				this.saveContactPerson(e);
			});
		nrCol.setCellValueFactory(cellData -> cellData.getValue().ContractNr());
		nameCol.setCellValueFactory(cellData -> cellData.getValue().getContractType().Name());
		statusCol.setCellValueFactory(cellData -> cellData.getValue().Status());
		tvContracts.setItems(FXCollections.observableArrayList(this.company.getContracts()));
		datePickerDateInService.setValue(company.getCustomerInitDate());
		TxFieldUsername.setText(company.getCompanyName());
		checkBoxStatus.setSelected(company.getStatus());
		btnCancel.setOnAction(this::cancelDetails);
		
			}
		
		
	}
	
	private void createCompanyStart(ActionEvent event) {
		TxFieldName.clear();
		TxFieldAddress.clear();
		TxFieldFirstName.clear();
		TxFieldLastName.clear();
		TxFieldEMail.clear();
		//btnSave.setOnAction(this::createCompany);
		//btnSave.setOnAction(this::createCompany);
		btnCancel.setOnAction(this::cancelDetails);
		btnSave.setText("Create company");
		btnCreateCompany.setOnAction(this::createCompany);
		
	}
	
	private void createCompany(ActionEvent event) {
		try {
			System.out.println(datePickerDateInService.getValue().toString());
			dc.createCompany(TxFieldName.getText(), TxFieldAddress.getText(), datePickerDateInService.getValue(), true);
			dc.createContactPerson(TxFieldFirstName.getText(), TxFieldLastName.getText(), TxFieldEMail.getText(), TxFieldName.getText());
			System.out.println("tesdst");
		}catch(Exception ex) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Test");
			alert.setHeaderText("test");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
		
	}

    
}
