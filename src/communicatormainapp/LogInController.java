/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicatormainapp;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.*;


/**
 * FXML Controller class
 *
 * @author Maciej
 */
public class LogInController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(CommunicatorMainApp.class.getName() );
    
    @FXML
    private Label UsernameText; 
    
    @FXML
    private Label PasswordText;
    
    @FXML
    private Button LogInButton;
    
    @FXML
    private Button SignUpButton;
    
    
    
    
    private LogInController(){
       LOGGER.log(Level.FINE, "Controller created");
    }
    
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        LogInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Accepted");
            }
        });
    }    
    
}
