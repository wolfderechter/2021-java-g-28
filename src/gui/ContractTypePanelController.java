package gui;

import java.io.IOException;

import domain.ContractType;
import domain.DomainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ContractTypePanelController {
	@FXML
	private TableView<ContractType> tvContractenTypes;
	@FXML
	private TableColumn<ContractType,String> colName;
	@FXML
	private TableColumn<ContractType,String> colStatus;
	@FXML
	private TableColumn<ContractType,Integer> colAmount;
	
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
        colName.setCellValueFactory(cellData -> cellData.getValue().getName());
        colStatus.setCellValueFactory(cellData-> cellData.getValue().Status());
        colAmount.setCellValueFactory(cellData-> cellData.getValue().Title());
        tvContractenTypes.setItems(dc.getAllContractTypes());
		//toevoegen edit panel
		TicketEditPanelController tepc = new TicketEditPanelController(dc);
	}

}
