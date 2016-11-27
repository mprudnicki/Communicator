/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author Maciej
 */
public class Communication {
    
    private Communication(){}
    
    public static void sendCredentials(String Username, String Password) throws IllegalArgumentException{
        char[] UsernameChars = Username.toCharArray();
        char[] PasswordChars = Password.toCharArray();
        
        if(UsernameChars.length == 0 || PasswordChars.length == 0) throw new IllegalArgumentException();
        
        //TODO
        
        
    }
    
}
