/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import static Security.Password.getSalt;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author ADMIN
 */
public class PasswordHasher {
    private PasswordHasher PasswordHasher;
    private Password password;
    
    
    private static final Logger LOGGER = Logger.getLogger(Password.class.getName() );
    public PasswordHasher(){}
    public String generateHash (char [] password) throws NoSuchAlgorithmException, InvalidKeySpecException{
        final int iterations = 1023;
        byte[] salt;
        salt = password.getSalt();
        PBEKeySpec keySpecification = new PBEKeySpec(password, salt, iterations, 512); 
        SecretKeyFactory sekretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = sekretKeyFactory.generateSecret(keySpecification).getEncoded();
        return iterations + ":" + password.toHex(salt) + ":" + password.toHex(hash);
    }
    
    public PasswordHasher getInstance(){
        if (PasswordHasher == null){
            synchronized(UserValidator.class){
                PasswordHasher = new PasswordHasher();
            }
        }
        return PasswordHasher;
    }
}
