package main;

<<<<<<< Upstream, based on branch 'main' of https://github.com/HoGentProjectenII/2021-java-g-28
=======


>>>>>>> 5fe1771 create ticket implemented
import domain.AdministratorController;
import domain.Controller;
import domain.Employee;
<<<<<<< Upstream, based on branch 'main' of https://github.com/HoGentProjectenII/2021-java-g-28
import domain.IEmployee;
=======

>>>>>>> 5fe1771 create ticket implemented
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
		boolean runWithLogin = false;

		Parent root;
<<<<<<< Upstream, based on branch 'main' of https://github.com/HoGentProjectenII/2021-java-g-28

		Controller dc = new AdministratorController();
		IEmployee g = new Employee();
		
//		Controller dc = new TechnicianController();
//		IEmployee g = new Employee();
		
//		Controller dc = new AdministratorController();
//		IEmployee d = new Employee();

=======
		Controller dc = new TechnicianController();
		Employee g = new Employee();
>>>>>>> 5fe1771 create ticket implemented
		
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
