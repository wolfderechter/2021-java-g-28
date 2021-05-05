package gui;

import java.io.IOException;

import domain.DomainController;
import domain.Faq;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class FaqPanelController extends GridPane{

	
	@FXML
	private TableView<Faq> tvFaq;
    @FXML
    private TableColumn<Faq, String> nameCol;
    
private DomainController dc;
	
	public FaqPanelController(DomainController domainC) {
		this.dc= domainC;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FaqPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        nameCol.setCellValueFactory(cellDate -> cellDate.getValue().Problem());
        tvFaq.setItems(dc.getAllFaqs());
	}
    
}
