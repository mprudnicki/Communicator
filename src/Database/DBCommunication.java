/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Security.Password;
import Security.PasswordHasher;
import Security.UserValidator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javafx.concurrent.Task;

/**
 *
 * @author Maciej
 */
public class DBCommunication {
    
    UserValidator userValidator;
    PasswordHasher PasswordHasher;
    public DBCommunication(){
    
        
    
    }
    
    
    public void sendCredentials(String Username, String PasswordInput) throws IllegalArgumentException, NoSuchAlgorithmException, InvalidKeySpecException{
        userValidator = userValidator.getInstance();
        PasswordHasher = PasswordHasher.getInstance();
        
        char[] UsernameChars = Username.toCharArray();
        char[] PasswordChars = PasswordInput.toCharArray();
        
        if(UsernameChars.length == 0 || PasswordChars.length == 0) throw new IllegalArgumentException();
        
        String PasswordHashed;
        
        PasswordHashed = PasswordHasher.generateHash(PasswordChars);
        
        
        Task<String> sendCredentials = new Task<String>() {
            @Override
            protected String call() throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        
        //TODO
        
        
    }
    
}
