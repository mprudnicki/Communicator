package controllers;

import database.Communication;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import javafx.scene.text.Text;
import security.PasswordHasher;

public class LoginController implements Initializable {
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	@FXML
	private Label messageLabel;

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Button loginButton;

	@FXML
	private Button exitButton;

	@FXML
	private Label incorrectCredentialsLabel;

	@FXML
	private Button newAccountButton;

	@FXML
	private Text forgotPasswordLabel;

	@FXML
	private void loginButtonOnAction(ActionEvent event) {

            incorrectCredentialsLabel.setVisible(false);
            String passwordHashed = null;
            String username = null;
            String password = null;    
            try{
                username = txtUsername.getText();
		password = txtPassword.getText();
            } catch (NullPointerException nlp){
                LOGGER.log(Level.SEVERE, null, nlp);
            }       
            
            try{
            if(username.length() == 0 || username.length() > 50 || password.length() == 0 || password.length() > 50 )        
                throw new IllegalArgumentException();
            
                try {
                    passwordHashed = PasswordHasher.getInstance().hashpw(password, 
                    PasswordHasher.getInstance().gensalt());
                } catch (NullPointerException nlp){
                    LOGGER.log(Level.SEVERE, null, nlp);
                }
            
                LOGGER.log(Level.INFO, passwordHashed);
            
            } catch(IllegalArgumentException e){
                incorrectCredentialsLabel.setVisible(true);
            }        
                    
            

		if (txtUsername.getText().equals("test") && txtPassword.getText().equals("test")) {

			((Node) (event.getSource())).getScene().getWindow().hide();
			messageLabel.setText("Welcome: " + txtUsername.getText());
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("/fxml/Splash.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Stage stage = new Stage();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setResizable(true);
			stage.initStyle(StageStyle.TRANSPARENT);
			// scene.setFill(Color.TRANSPARENT);
			// stage.setTitle("Main Frame");
			// stage.setResizable(false);
			// stage.initStyle(StageStyle.UNDECORATED);
			
			stage.show();

		} else {
			messageLabel.setText("Username or Password invalid!");

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		incorrectCredentialsLabel.setVisible(false);

	}

	@FXML
	public void exitButtonOnAction() {
		Platform.exit();
	}

	@FXML
	public void newAccountButtonOnAction(ActionEvent event) {

		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/NewAccount.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Stage stage = new Stage();

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setTitle("New Account");
		stage.setResizable(false);
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.show();

	}

	public LoginController() {
		LOGGER.log(Level.FINE, "LOG IN Controller created");
	}

	@FXML
	public void forgotPasswordLabelOnMouseEntered() {

		System.out.println("to se przypomnij xD\nlogin:test\nhaslo:test");

	}

}
