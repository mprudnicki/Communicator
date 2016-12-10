/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author ADMIN
 */
public class UserValidator {
    Password password;
    private UserValidator UserValidator;
    private UserValidator(){}
    public boolean validate(String hashInput, String hashDatabase) throws NoSuchAlgorithmException, InvalidKeySpecException{
        String[] parts = hashInput.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = password.fromHex(parts[1]);
        byte[] hash = password.fromHex(parts[2]);
         
        PBEKeySpec spec = new PBEKeySpec(hashDatabase.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();
         
        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
    
    public UserValidator getInstance(){
        if (UserValidator == null){
        //    synchronized(UserValidator.class){
                UserValidator = new UserValidator();
            }
        //}
        return UserValidator;
    }
        
        
}
