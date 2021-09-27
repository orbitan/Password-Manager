package passwordManager;
        
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.spec.SecretKeySpec;

public class PasswordManager{
    
    private  Mode mode;
    
    public static void main(String[] args){
        PasswordManager pwMan = new PasswordManager();
        pwMan.mode = Mode.get(args);
        pwMan.mode.run(); 
    
        EncryptionPassword ec = new EncryptionPassword();
        
        //Insert password and prepare it for encryption and decryption
        ec.createPassword();   

        SecretKeySpec key;
        try {
            key = EncryptionPassword.createSecretKey(ec.getPassword(), ec.getSalt(), ec.getIterationCount(), ec.getKeyLength());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NullPointerException ex) {
            
        }
    }
 
}
