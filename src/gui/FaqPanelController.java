package gui;

import java.io.IOException;
import java.util.stream.Collectors;
import domain.Controller;
import domain.IFaq;
import domain.SupportManagerController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;


public class FaqPanelController extends BorderPane{

    @FXML
    private ListView<String> lstProblem;

    @FXML
    private ListView<String> lstSolution;
    
    private SupportManagerController dc;
    
	public FaqPanelController(Controller domainC) {
		this.dc= (SupportManagerController) domainC;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FaqPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        ObservableList<String> problems = dc.getAllFaqs().stream().map(f->f.getProblem()).collect(Collectors.toCollection(FXCollections::observableArrayList));
        lstProblem.setItems(problems);
        //lstStatussen.setItems(FXCollections.observableArrayList(TicketStatusEnum.values()));
        lstProblem.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lstProblem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String[] solutions = dc.getSolution(newValue);
                lstSolution.setItems(FXCollections.observableArrayList(solutions));
            }
        });
        
	}
    
}
