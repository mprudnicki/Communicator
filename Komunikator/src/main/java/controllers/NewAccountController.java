package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;

public class NewAccountController implements Initializable{
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName() );
    
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    
    
    
	StringProperty nameProperty = new SimpleStringProperty();

	// widocznosc/ niewidocznosc label OK imie
	BooleanProperty nameOkProperty = new SimpleBooleanProperty(false);

	public BooleanProperty getNameOkProperty() {
		return nameOkProperty;
	}
	
	public BooleanProperty getPasswordOkProperty() {
		return passwordOkProperty;
	}


	// textfield naziwsko
	StringProperty passwordProperty = new SimpleStringProperty();

	// wylaczenie/wlaczenie pola nazwisko
	BooleanProperty disablePasswordProperty = new SimpleBooleanProperty(true);

	// widocznosc/ niewidocznosc label OK naziwsko
	BooleanProperty passwordOkProperty = new SimpleBooleanProperty(false);

	// textfield - rok urodzenia
	IntegerProperty yearProperty = new SimpleIntegerProperty();

	// przyjmuje wartosc checkBox
	BooleanProperty confirmProperty = new SimpleBooleanProperty(false);

	// obliczony wiek
	StringProperty ageProperty = new SimpleStringProperty();

	// wlacz/wylacz przycisk
	BooleanProperty buttonProperty = new SimpleBooleanProperty(false);

	// aktualny rok
	IntegerProperty actualYearProperty = new SimpleIntegerProperty(LocalDate.now().getYear());
    
	@FXML 
	private Button cancelButton;

	@FXML Text welldefinedUsername;

	@FXML Button createAccountButton;

	@FXML Text welldefinedPassword;

	@FXML public void cancelButtonOnAction(ActionEvent event) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Stage stage = new Stage();
		
		
        Stage stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();

		Scene scene = new Scene(parent);
		
		
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.setResizable(false);
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}
	
	
	   public NewAccountController(){
	       LOGGER.log(Level.FINE, "SIGN UP Controller created");
	       nameOkProperty.bind(nameProperty.isNotEmpty());
	       passwordOkProperty.bind(passwordProperty.isNotEmpty());
	    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		usernameTextField.textProperty().bindBidirectional(nameProperty);
		passwordTextField.textProperty().bindBidirectional(passwordProperty);

		
		createAccountButton.disableProperty().bind(disablePasswordProperty);

		welldefinedUsername.visibleProperty().bind(getNameOkProperty());
		welldefinedPassword.visibleProperty().bind(getPasswordOkProperty());

	}


	@FXML public void createAccountButtonOnAction() {}


	


}



