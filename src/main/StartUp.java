package main;

import domain.AdministratorController;
import domain.Controller;
import domain.Employee;
import domain.IEmployee;

import domain.TechnicianController;
import gui.AccountPanelController;
import gui.DashboardPanelController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {
	@Override
	public void start(Stage stage) {
		// TESTING PURPOSE
		boolean runWithLogin = true;

		Parent root;
		
//		Controller dc = new AdministratorController();
//		IEmployee g = new Employee();

		Controller dc = new TechnicianController();
		IEmployee g = new Employee();

		
//		Controller dc = new TechnicianController();
//		IEmployee g = new Employee();
		
//		Controller dc = new AdministratorController();
//		IEmployee d = new Employee();
		root = new AccountPanelController();
		
		if (runWithLogin) {
			
		} else {
			//root = new DashboardPanelController(g,dc);
		}
		
		Scene scene = new Scene(root);

		stage.setScene(scene);

		stage.setTitle("Actemium | Sign In");

		stage.setFullScreen(true);

		stage.show();
	}

	public static void main(String... args) {
		launch(StartUp.class, args);
	}

}
