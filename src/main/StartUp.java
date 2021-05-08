package main;


import domain.Account;
import domain.Administrator;
import domain.AdministratorController;
import domain.ContactPerson;
import domain.Controller;
import domain.DomainController;
import domain.Technician;
import domain.TechnicianController;
import gui.AccountPanelController;
import gui.ContactPersonPanelController;
import gui.DashboardPanelController;
import gui.FaqPanelController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {
	@Override
	public void start(Stage stage) {
		// TESTING PURPOSE
		boolean runWithLogin = false;

		Parent root;
		Controller dc = new AdministratorController();
		Account g = new Administrator();
		
		if (runWithLogin) {
			root = new AccountPanelController();
		} else {
			root = new DashboardPanelController(g,dc);
		}
		
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
