package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import domain.Account;
import domain.AdministratorController;
import domain.ContactPerson;
import domain.Controller;
import domain.DomainController;
import domain.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class EmployeePanelController extends BorderPane implements PropertyChangeListener{

	@FXML
    private TableView<Employee> tvEmployees;

	@FXML
    private TableColumn<Employee, Number> idCol;

    @FXML
    private TableColumn<Employee, String> firstNameCol;

    @FXML
    private TableColumn<Employee, String> lastNameCol;

    @FXML
    private TableColumn<Employee, String> adressCol;

	@FXML
	private Label lblUsername;
	
	private AdministratorController dc;
	
	public EmployeePanelController(Controller dc2) {
		this.dc = (AdministratorController) dc2;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeePanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
      
        //Aanmaak TableView + opvullen met data
        idCol.setCellValueFactory(cellData -> cellData.getValue().Id());
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().FirstName());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().LastName());
        
        tvEmployees.setItems(dc.getAllEmployees());
        //Editpanel aanmaken + opvullen met eerste Employee
        //EmployeeEditPanelController eepc = new EmployeeEditPanelController(dc);
        //setRight(eepc);
        tvEmployees.getSelectionModel().selectedItemProperty()
        .addListener((observableValue, previousEmployee, selectedEmployee) -> 
        {
			if (selectedEmployee != null) {
				int index = tvEmployees.getSelectionModel().getSelectedIndex();
				dc.setEmployee(selectedEmployee);
				}
			}
		);
	
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub	
	}
	
	
}
