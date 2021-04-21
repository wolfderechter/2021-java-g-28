package main;
import domain.SupportManager;
import gui.AccountPanelController;
import gui.DashboardPanelController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {
	//TicketController tc = new TicketController();
	
	 @Override
	    public void start(Stage stage) {
	        
		 	DashboardPanelController root = new DashboardPanelController(new SupportManager());
		 	
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
