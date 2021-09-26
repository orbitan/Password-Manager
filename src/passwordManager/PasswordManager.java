package passwordManager;
        
public class PasswordManager{
    
    private  Mode mode;
    
    public static void main(String[] args){
        PasswordManager pwMan = new PasswordManager();
        pwMan.mode = Mode.get(args);
        pwMan.mode.run(); 
    
        EncryptionPassword ec = new EncryptionPassword();
        //Insert password and prepare it for encryption and decryption
        ec.createPassword();   

        SecretKeySpec key = EncryptionPassword.createSecretKey(ec.getPassword(), ec.getSalt(), ec.getIterationCount(), ec.getKeyLength());

        //Test
        String originalPassword = String.valueOf(ec1.getPassword());
        System.out.println("Original password: " + originalPassword);

        String encryptedPassword = EncryptionPassword.encrypt(originalPassword, key);
        System.out.println("Encrypted password: " + encryptedPassword);

        String decryptedPassword = EncryptionPassword.decrypt(encryptedPassword, key);
        System.out.println("Decrypted password: " + decryptedPassword);
    }
 
}
