package gui;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import domain.IContractType;
import domain.SupportManagerController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;

public class ContractTypePanelController extends BorderPane{
	@FXML
	private TableView<IContractType> tvContractTypes;
	@FXML
	private TableColumn<IContractType,String> colName;
	@FXML
	private TableColumn<IContractType,Boolean> colStatus;
	@FXML
	private TableColumn<IContractType,Number> colAmount;
	@FXML
	private ListView<String> lstStatusFilter;
	
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
        List<String> statuslist = Arrays.asList(new String[]{"Active", "Inactive"});
        lstStatusFilter.getSelectionModel().select("Active");
        lstStatusFilter.setItems(FXCollections.observableArrayList(statuslist));
        lstStatusFilter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstStatusFilter.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<String>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends String> c) {
				while (c.next()) {
					if (c.wasAdded()) {
						
						dc.addStatusFilterOnContractType(c.getAddedSubList());
						replaceTableViewData();
					}
					if (c.wasRemoved()) {
						//System.out.println(c.getRemoved());
						dc.RemoveStatusFilterOnContractType(c.getRemoved());
						replaceTableViewData();
					}
					// c.wasPermutated();
					// c.wasReplaced();
					}
				}
		});
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
		replaceTableViewData();
	}
	
	public void replaceTableViewData() {
		colName.setCellValueFactory(cellData -> cellData.getValue().Name());
        colStatus.setCellValueFactory(cellData-> cellData.getValue().Status());
        colAmount.setCellValueFactory(cellData-> cellData.getValue().Amount());
        tvContractTypes.setItems(this.dc.getFilteredContractTypes());
	}
}
