package Security;
import java.math.BigInteger;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


/**
 *
 * @author Maciej
 */
public class Password {
    
    public static String generateHash (char [] password) throws NoSuchAlgorithmException, InvalidKeySpecException{
        final int iterations = 1000;
        char[] characters = null;
        byte[] salt = getSalt();
        System.arraycopy(password, 0, characters, 0, password.length);
        
            password = null;
            System.gc();
        
        PBEKeySpec keySpecification = new PBEKeySpec(characters, salt, iterations, 512); 
        SecretKeyFactory sekretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = sekretKeyFactory.generateSecret(keySpecification).getEncoded();
        return iterations + "::" + toHex(salt) + "::" + toHex(hash);
    }
    
    public static byte[] getSalt(){
        final SecureRandom Random = new SecureRandom();
        byte[] salt;
        salt = new byte[32];
        Random.nextBytes(salt);
        return salt;
    }
    
    
    
    
    public static boolean validate(String hashInput, String hashDatabase) throws NoSuchAlgorithmException, InvalidKeySpecException{
        String[] parts = hashInput.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
         
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
    
    
    private static String toHex(byte[] array) throws NoSuchAlgorithmException
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
    
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
