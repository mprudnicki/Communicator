/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Security.Password;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javafx.concurrent.Task;

/**
 *
 * @author Maciej
 */
public class DBCommunication {
    
    private DBCommunication(){}
    
    public static void sendCredentials(String Username, String PasswordInput) throws IllegalArgumentException, NoSuchAlgorithmException, InvalidKeySpecException{
        char[] UsernameChars = Username.toCharArray();
        char[] PasswordChars = PasswordInput.toCharArray();
        
        if(UsernameChars.length == 0 || PasswordChars.length == 0) throw new IllegalArgumentException();
        
        String PasswordHashed;
        PasswordHashed = Password.generateHash(PasswordChars);
        
        
        Task<String> sendCredentials = new Task<String>() {
            @Override
            protected String call() throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        
        //TODO
        
        
    }
    
}
