package gui;


import java.io.IOException;
import domain.ContractType;
import domain.SupportManagerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableColumn;

public class ContractTypePanelController extends BorderPane{
	@FXML
	private TableView<ContractType> tvContractTypes;
	@FXML
	private TableColumn<ContractType,String> colName;
	@FXML
	private TableColumn<ContractType,Boolean> colStatus;
	@FXML
	private TableColumn<ContractType,Number> colAmount;
	
	private SupportManagerController dc;
	
	public ContractTypePanelController(SupportManagerController dc2) { 
		this.dc =  dc2;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ContractTypePanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }		
        colName.setCellValueFactory(cellData -> cellData.getValue().Name());
        colStatus.setCellValueFactory(cellData-> cellData.getValue().Status());
        colAmount.setCellValueFactory(cellData-> cellData.getValue().Amount());
        tvContractTypes.setItems(this.dc.getAllContractTypes());
		//toevoegen edit panel
		ContractTypeEditPanelController cepc = new ContractTypeEditPanelController(this.dc);
		dc.addContractTypeListener(cepc);
		setRight(cepc);
		tvContractTypes.getSelectionModel().selectedItemProperty()
		.addListener((observableValue, vorigContractType, selectedContractType) -> 
		{
		//Controleer of er een ContractType is geselecteerd
		if (selectedContractType!= null) {
			dc.setContractType(selectedContractType.getName());
			}
		});
	}
}
