/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationLayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Maciej
 */
public class SignUpController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(CommunicatorWindow.class.getName() );
    
    @FXML
    private TextField UsernameText; 
    
    @FXML
    private TextField EmailText; 
    
    @FXML
    private TextField PasswordText;
    
    @FXML
    public void CreateAccount(ActionEvent event){
        String Username      = UsernameText.getText();
        char[] PasswordChars = PasswordText.getText().toCharArray();
        String EmailAddress  = EmailText.getText();
        
        if(Username.length() == 0 || PasswordChars.length == 0 || EmailAddress.contains("@") == false) throw new IllegalArgumentException();
        
        
        
    }
    @FXML
    public void GoBackToLogIn(ActionEvent event) throws IOException{
        Parent LogInPage   = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene  LogInScene  = new Scene(LogInPage);
        
        Stage SignUpStage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        SignUpStage.setScene(LogInScene);
        SignUpStage.show();
        
    }
    
    
    public SignUpController(){
       LOGGER.log(Level.FINE, "SIGN UP Controller created");
    }
    
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    
}
