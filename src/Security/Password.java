package Security;
import ApplicationLayer.CommunicatorWindow;
import java.math.BigInteger;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


/**
 *
 * @author Maciej
 */
public class Password {
    private static final Logger LOGGER = Logger.getLogger(Password.class.getName() );
    public Password(){}
    
    public String generateHash (char [] password) throws NoSuchAlgorithmException, InvalidKeySpecException{
        final int iterations = 1023;
        byte[] salt = getSalt();
        PBEKeySpec keySpecification = new PBEKeySpec(password, salt, iterations, 512); 
        SecretKeyFactory sekretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = sekretKeyFactory.generateSecret(keySpecification).getEncoded();
        return iterations + ":" + this.toHex(salt) + ":" + this.toHex(hash);
    }
    
    public byte[] getSalt(){
        final SecureRandom Random = new SecureRandom();
        byte[] salt;
        salt = new byte[32];
        Random.nextBytes(salt);
        return salt;
    }
    
    
    
    
    
    
    
    public String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
    
    public byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
