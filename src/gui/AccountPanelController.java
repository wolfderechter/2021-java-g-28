package gui;

import java.io.IOException;

import domain.Controller;
import domain.DomainController;
import domain.IEmployee;
import domain.LoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class is responsible for signing in.
 * @author Stef
 */
public class AccountPanelController extends GridPane {
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField pwfPassword;
	@FXML
	private Button btnSignIn;
	@FXML
	private Label lblLoginError;

	DomainController dc;
	LoginController lc;
	
	public AccountPanelController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountPanel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        lc = new LoginController();
        
        txtUsername.setOnAction(this::signIn);
        pwfPassword.setOnAction(this::signIn);
        btnSignIn.setOnAction(this::signIn);
	}
	
	/**Checks if credentials are valid and signs user in and shows dashboard, if not valid, shows error message on screen**/
	private void signIn(ActionEvent event) {
		try {
			String isValid = lc.getValidation(txtUsername.getText(), pwfPassword.getText());
			
			if (isValid.equals("true")) {
				IEmployee signedInUser = null;
				try {
					signedInUser = getSignedInUser(txtUsername.getText());
				} catch (IllegalArgumentException e) {
					lblLoginError.setText(e.getMessage());
				}
				showDashboard(lc.getController(signedInUser));
			} else {
				lblLoginError.setText("Username or password incorrect");
				txtUsername.requestFocus();
			}
		} catch (IOException e) {
			createAndShowPopupConnection();
		}
	}

	/**Shows dashboard**/
	private void showDashboard(Controller controller) {
		DashboardPanelController dpc = new DashboardPanelController(controller);
		Scene scene = new Scene(dpc);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setTitle("Actemium | Dashboard");
		stage.show();
	}
	
	/**Returns signed in account**/
	private IEmployee getSignedInUser(String username) {
		return lc.getSignedInUser(username);
	}
	
	private void createAndShowPopupConnection() {
		Label lblError1 = new Label("Connection refused");
		Label lblError2 = new Label("Please check your internet connection or try again later");
		
		lblError1.setFont(Font.font(15));
		
		Button btnClose = new Button("Ok");
		
		btnClose.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent arg0) {
				Stage stage = (Stage) btnClose.getScene().getWindow();
				stage.close();	
			}
		});
		
		VBox root = new VBox(2);
		
		root.getChildren().add(lblError1);
		
		root.getChildren().add(lblError2);
		
		root.getChildren().add(btnClose);
		
		root.setAlignment(Pos.CENTER);
		
		VBox.setMargin(btnClose,new Insets(20));
		
		Scene scene = new Scene(root);
		
		Stage stage = new Stage();
		
		stage.setScene(scene);

		stage.setTitle("Error");

		stage.setHeight(200);
		
		stage.setWidth(400);
		
		stage.setResizable(false);
		
		stage.initModality(Modality.APPLICATION_MODAL);
		
		stage.show();
	}
}