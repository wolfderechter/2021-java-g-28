package gui;

import java.io.IOException;

import domain.Account;
import domain.ContactPerson;
import domain.DomainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class EmployeePanelController extends BorderPane {

	@FXML
    private TableView<Account> tvEmployees;

	@FXML
    private TableColumn<Account, Integer> idCol;

    @FXML
    private TableColumn<Account, String> firstNameCol;

    @FXML
    private TableColumn<Account, String> lastNameCol;

    @FXML
    private TableColumn<Account, String> adressCol;

	@FXML
	private Label lblUsername;
	
	private DomainController dc;
	
	public EmployeePanelController(DomainController dc) {
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeePanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
      
        EmployeeEditPanelController eepc = new EmployeeEditPanelController(this.dc.getAllEmployees().get(0));
        eepc.setDisable(true);
        setRight(eepc);
      //  System.out.println(dc.getAllContactPersons());
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProp());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().getLastNameProp());
        idCol.setCellValueFactory(cellData -> cellData.getValue().getId());
        
        tvEmployees.setItems(this.dc.getAllEmployees());
        tvEmployees.getSelectionModel().selectedItemProperty()
        .addListener((observableValue, previousEmployee, selectedEmployee) -> 
        {
		
			if (selectedEmployee != null) {
				int index = tvEmployees.getSelectionModel().getSelectedIndex();
				displaySelectedContactPersonDetails(selectedEmployee);
				}
			}
		);
	
	}
        private void displaySelectedEmployeeDetails(Account selectedEmployee) {
        	EmployeeEditPanelController eepc = new EmployeeEditPanelController(selectedEmployee);
        	setRight(eepc);
        }   
	
	
}
