/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationLayer;

import Database.DBCommunication;
import Security.Password;
import Security.PasswordHasher;
import Security.UserValidator;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
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
public class LogInController implements Initializable {
    private PasswordHasher PasswordHasher;
    private UserValidator  UserValidator;
    private DBCommunication DBCommunication;
    private static final Logger LOGGER = Logger.getLogger(CommunicatorWindow.class.getName() );
    private Task<Void> backgroundThread;
    
    @FXML
    private TextField UsernameText; 
    
    @FXML
    private TextField PasswordText;
    
    @FXML
    private Button LogInButton;
    
    @FXML
    private Button SignUpButton;
    
    @FXML
    private Label IncorrectCredentialsLabel;
    
    @FXML
    public void LogInClicked(ActionEvent event){
        PasswordHasher = PasswordHasher.getInstance();
        UserValidator  = UserValidator.getInstance();
        try{
            String Username      = UsernameText.getText();
            char[] PasswordChars = PasswordText.getText().toCharArray();
            
            if(Username.length() == 0 || PasswordChars.length == 0) throw new IllegalArgumentException();
            
            UsernameText.deleteText(0, UsernameText.getLength());
            PasswordText.deleteText(0, PasswordText.getLength());
            IncorrectCredentialsLabel.setVisible(false);

            String PasswordHashed = null;
            try {
                PasswordHashed = PasswordHasher.generateHash(PasswordChars);
                DBCommunication.sendCredentials(Username, PasswordHashed);
                
                
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                LOGGER.log(Level.WARNING, null, e);
            }

            System.out.println(PasswordHashed);

        }catch(IllegalArgumentException e){
            IncorrectCredentialsLabel.setVisible(true);
        }
        
        
        
    }
   
    @FXML
    public void SignUpClicked(ActionEvent event) throws IOException{
        
        
        Parent SignUpPage  = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene  SignUpScene = new Scene(SignUpPage);
        
        Stage LogInStage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        LogInStage.setScene(SignUpScene);
        LogInStage.show();
        
    }
    
    
    
    public LogInController(){
       LOGGER.log(Level.FINE, "LOG IN Controller created");
    }
    
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        IncorrectCredentialsLabel.setVisible(false);
    }    
    
}
