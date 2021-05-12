package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;


import domain.AdministratorController;
import domain.Company;
import domain.ContactPerson;
import domain.Controller;
import domain.DomainController;
import domain.ICompany;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ContactPersonPanelController extends BorderPane {

	@FXML
    private TableView<ICompany> tvCompany;

	@FXML
    private TableColumn<ICompany, String> nameCol;

    @FXML
    private TableColumn<ICompany, String> addressCol;

	@FXML
	private Label lblUsername;
	
	@FXML 
	private TextArea TextAreaTest;
	
	 @FXML
	  private TextField txFieldSearch;
	
	private AdministratorController dc;
	
	public ContactPersonPanelController(Controller dc2) {
		this.dc = (AdministratorController) dc2;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactPersonPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
     
        txFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
        	tvCompany.setItems(dc.getCompaniesByName(newValue));
        	});
        
        nameCol.setCellValueFactory(cellData -> cellData.getValue().CompanyName());
        addressCol.setCellValueFactory(cellData -> cellData.getValue().CompanyAdress());
        tvCompany.setItems(dc.getAllCompanies());
        ContactPersonEditPanelController cpepc = new ContactPersonEditPanelController(dc);
        dc.addCompanyListener(cpepc);
        setRight(cpepc);
        tvCompany.getSelectionModel().selectedItemProperty()
        .addListener((observableValue, previousCompany, selectedCompany) -> 
        {
		
			if (selectedCompany!= null) {
				dc.setCompany(selectedCompany.getCompanyNr());
				}
			}
		);
        
	}

       
}
	

