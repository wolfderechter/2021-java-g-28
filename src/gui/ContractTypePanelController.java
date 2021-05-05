package gui;

import java.io.IOException;

import domain.ContractType;
import domain.DomainController;
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
	
	private DomainController dc;
	
	public ContractTypePanelController(DomainController dc) {
		this.dc = dc;
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
		setRight(cepc);
		tvContractTypes.getSelectionModel().selectedItemProperty()
		.addListener((observableValue, vorigContractType, selectedContractType) -> 
		{
		//Controleer of er een ContractType is geselecteerd
		if (selectedContractType!= null) {
			cepc.changeContractType(selectedContractType);
			}
		});
	}
}
