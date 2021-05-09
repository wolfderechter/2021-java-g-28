package gui;

import java.io.IOException;
import java.util.stream.Collectors;

import domain.Controller;
import domain.DomainController;
import domain.Faq;
import domain.IFaq;
import domain.SupportManagerController;
import domain.TechnicianController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class FaqPanelController extends BorderPane{

    @FXML
    private ListView<String> lstProblem;

    @FXML
    private ListView<Faq> lstSolution;
    
    private SupportManagerController dc;
    
    private IFaq faq;
	
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
        
	}
    
}
