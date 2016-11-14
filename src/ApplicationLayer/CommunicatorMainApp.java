/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationLayer;

import java.io.IOException;
import java.util.logging.*;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Maciej
 */
public class CommunicatorMainApp extends Application implements EventHandler{
    private static final Logger LOGGER = Logger.getLogger(CommunicatorMainApp.class.getName() );
    
    
    
    @Override
    public void start(Stage Window) {
        try{
            AnchorPane LogIn = (AnchorPane) FXMLLoader.load(CommunicatorMainApp.class.getResource("LogIn.fxml"));
            Scene Logging = new Scene(LogIn);
            
            Window.setScene(Logging);
            Window.setTitle("Welcome");
            Window.setResizable(false);
            Window.show();
            LOGGER.log(Level.FINE, "Main App window created and shown");
            
        }catch(IOException | NullPointerException e){
            LOGGER.log(Level.SEVERE, e.toString(), e);
            System.exit(1);
        }
        
        
    
    }

    @Override
    public void handle(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param args the command line arguments
     */
   
    
}
