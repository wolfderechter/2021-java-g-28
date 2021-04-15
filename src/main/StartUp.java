package main;
import gui.AccountPanelController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {
	//TicketController tc = new TicketController();
	
	 @Override
	    public void start(Stage stage) {
	        
		 	AccountPanelController root = new AccountPanelController();
		 	
		 	Scene scene = new Scene(root);
		 	
		 	stage.setScene(scene);
		 	
		 	stage.setTitle("Actemium | Sign In");
		 	
		 	stage.setMaximized(true);
		 	
		 	stage.setResizable(false);
		 	
		 	stage.show();
	    }

	    public static void main(String... args) {
	        launch(StartUp.class, args);
	    }
    
}
