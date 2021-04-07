package main;
import domain.Account;
import domain.TicketController;
import gui.AccountPanelController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartUp extends Application {
	//TicketController tc = new TicketController();
	
	 @Override
	    public void start(Stage stage) {
	        
		 	AccountPanelController root = new AccountPanelController();
		 	
		 	Scene scene = new Scene(root, 1900,900);
		 	
		 	stage.setScene(scene);
		 	
		 	stage.setTitle("Actemium | Sign In");
		 	
		 	stage.show();
	    }

	    public static void main(String... args) {
	        launch(StartUp.class, args);
	    }
    
}
