package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import domain.AdministratorController;
import domain.ContactPerson;
import domain.Controller;
import domain.DomainController;
import domain.Employee;
import domain.IEmployee;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class EmployeePanelController extends BorderPane {

	@FXML
    private TableView<IEmployee> tvEmployees;

	@FXML
    private TableColumn<Employee, Number> idCol;

    @FXML
    private TableColumn<Employee, String> firstNameCol;
    
    @FXML
    private TableColumn<Employee, String> userNameCol;
    
    @FXML
    private TableColumn<Employee, String> lastNameCol;
    
    @FXML
    private TableColumn<Employee, String> roleCol;
    
    @FXML
    private TableColumn<Employee, String> adressCol;
    
    @FXML
    private TableColumn<Employee, LocalDate> dateInServiceCol;
    
    //nav bar om te zoeken
    @FXML
    private TextField txFieldSearch;
    
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
      
        //toevoegen van search
        txFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
        	tvEmployees.setItems(dc.getEmployeesByName(newValue));
        });
        
        //Aanmaak TableView + opvullen met data
        idCol.setCellValueFactory(cellData -> cellData.getValue().Id());
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().FirstName());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().LastName());
        roleCol.setCellValueFactory(cellData -> cellData.getValue().Role());
        userNameCol.setCellValueFactory(cellData -> cellData.getValue().getUser().UserName());
        dateInServiceCol.setCellValueFactory(cellData -> cellData.getValue().DateInService());

        tvEmployees.setItems(dc.getAllEmployees());

        //Editpanel aanmaken + opvullen met eerste Employee
        EmployeeEditPanelController eepc = new EmployeeEditPanelController(dc);
        setRight(eepc);
        tvEmployees.getSelectionModel().selectedItemProperty()
        .addListener((observableValue, previousEmployee, selectedEmployee) -> 
        {
			if (selectedEmployee != null) {
				dc.setEmployee(selectedEmployee.getId());
		        dc.addEmployeeListener(eepc);

				}
			}
		);
	
	}

	
	
}
