package main;


import domain.ContactPerson;
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

		if (runWithLogin) {
			root = new AccountPanelController();
		} else {
			root = new DashboardPanelController(new ContactPerson());
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
