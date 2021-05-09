package gui;

import java.io.IOException;
import domain.Controller;
import domain.IEmployee;
import domain.ITicket;
import domain.SupportManagerController;
import domain.TechnicianController;
import domain.TicketStatusEnum;
import domain.TicketTypeEnum;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class TicketPanelController extends BorderPane {

	@FXML
	private TableView<ITicket> tvTickets;
	@FXML
	private TableColumn<ITicket, Number> ticketNrCol;
	@FXML
	private TableColumn<ITicket, TicketStatusEnum> statusCol;
	@FXML
	private TableColumn<ITicket, String> titleCol;
	@FXML
	private ListView<TicketStatusEnum> lstStatussen;
	@FXML
	private ListView<TicketTypeEnum> lstTypes;


	private TechnicianController dc;
	private SupportManagerController dc2;

	public TicketPanelController(Controller dc2, IEmployee emp) {
		switch (emp.getRole()) {
		case "TE": this.dc = (TechnicianController) dc2; break;
		case "SM": this.dc2 = (SupportManagerController) dc2;
		default:
			throw new IllegalArgumentException("Unexpected value: " + emp.getRole());
		}
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketPanel.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		// toevoegen filters
		tvTickets.setPlaceholder(new Label("No Tickets matching your filters"));
		//filter lijsten opmaken
		lstStatussen.setItems(FXCollections.observableArrayList(TicketStatusEnum.values()));
		lstStatussen.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lstStatussen.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TicketStatusEnum>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends TicketStatusEnum> c) {
				while (c.next()) {
					if (c.wasAdded()) {

						dc.addStatusFilterOnTickets(c.getAddedSubList());
						vervangTableViewData();
					}
					if (c.wasRemoved()) {
						//System.out.println(c.getRemoved());
						dc.removeStatusFilterOnTickets(c.getRemoved());
						vervangTableViewData();
					}
					// c.wasPermutated();
					// c.wasReplaced();
					}
				}
		});
		lstStatussen.getSelectionModel().selectIndices(0, 1);
		lstTypes.setItems(FXCollections.observableArrayList(TicketTypeEnum.values()));
		lstTypes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lstTypes.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TicketTypeEnum>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends TicketTypeEnum> c) {
				while (c.next()) {
					if (c.wasAdded()) {

						dc.addTypeFilterOnTickets(c.getAddedSubList());
						vervangTableViewData();
					}
					if (c.wasRemoved()) {
						
						dc.removeTypeFilterOnTickets(c.getRemoved());
						vervangTableViewData();
					}
					// c.wasPermutated();
					// c.wasReplaced();
					}
				}
		});
		lstTypes.getSelectionModel().selectAll();
		vervangTableViewData();
		// toevoegen edit panel
		TicketEditPanelController tepc = new TicketEditPanelController(dc);
		dc.addTicketListener(tepc);
		setRight(tepc);
		tvTickets.getSelectionModel().selectedItemProperty()
				.addListener((observableValue, vorigTicket, selectedTicket) -> {
					// Controleer of er een ticket is geselecteerd
					if (selectedTicket != null) {
						dc.setTicket(selectedTicket.getTicketNr());
					}
				});		
	}

	private void vervangTableViewData() {
		// aanmaak tableview + data
		ticketNrCol.setCellValueFactory(cellData -> cellData.getValue().TicketNr());
		statusCol.setCellValueFactory(cellData -> cellData.getValue().Status());
		titleCol.setCellValueFactory(cellData -> cellData.getValue().Title());
		tvTickets.setItems(dc.getFilteredTickets());
	}
}
