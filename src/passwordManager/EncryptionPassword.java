package passwordManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;



public class EncryptionPassword {
	
	private char[] password;
	private final byte[] salt = new String("123456789").getBytes();;
	private final int iterationCount = 40000;
	private final int keyLength = 128;
	
	public void setPassword(char[] password) {
		this.password = password;
	}
	
	public char[] getPassword() {
		return this.password;
	}
	
	public byte[] getSalt() {
		return this.salt;
	}

	public int getIterationCount() {
		return this.iterationCount;
	}

	public int getKeyLength() {
		return this.keyLength;
	}
	
	public char[] createPassword() {
	Scanner scan = new Scanner(System.in);
	System.out.println("Bitte Passwort eingeben: ");
	String s = scan.nextLine();
    this.password = new String(s).toCharArray();
    scan.close();
    return this.password;
	}
	
	
	//method constructs a SecretKeySpec object from a byte array 
	public static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException, NullPointerException {
             
		//getInstance() method returns a "SecretKeyFactory" object that implements the specified encryption algorithm
		//Parameters: standard name of algorithm
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		
		//Constructor takes password, salt, iterationCount and keyLength for generating PBEKey of variable-key-size PBE ciphers
		//Note: PBE stands for PasswordBasedEncription. Password and salt are cloned before stored in new PBEKeySpec object
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        
        //generateSecret() method generates the shared secret "keySpec" and returns it in a new buffer
        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        
        //method getEncoded() returns the key in its primary encoding format, or null if the key does not support endoding
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }

	//method encrypt() uses data to be encrypted and key as parameters
    public static String encrypt(String dataToEncrypt, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException, InvalidKeyException {
        
    	//getInstance() method  instantiate the Cipher object as an AES cipher with CBC mode of operation and PKCS5 padding scheme
    	Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    	
    	//init() method to initialize the Cipher object with the Key in encryption mode
        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
        
        //getParameters() method returns the algorithm parameters of the Cipher object
        //
        AlgorithmParameters parameters = pbeCipher.getParameters();
        
        //getParameterSpec() returns a specification of this parameter object 
        //IvParameterSpec class specifies an initialization vector (IV)
        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
        
        //doFinal() method performs the encryption and returns a byte array containing the encrypted data
        //getIV() method returns the initialization vector
        byte[] cryptoText = pbeCipher.doFinal(dataToEncrypt.getBytes("UTF-8"));
        byte[] iv = ivParameterSpec.getIV();
        return base64Encode(iv) + ":" + base64Encode(cryptoText);
    }

    //base64Encode() method encodes binary-to-text. 
    //Input bytes array
    //Output contains characters from the set A-Z, a-z, 0-9, +, /
    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    //method decrypt() uses string and key as parameters
    //split() method to split the encrypted string in iv and property
    //getInstance() method  instantiate the Cipher object as an AES cipher with CBC mode of operation and PKCS5 padding scheme
    public static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        //init() method to initialize the Cipher object with the Key and IvParameterSpec instance (with decoded IV) in decryption mode
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
        
        //doFinal() method performs the decryption and returns a string containing the decrypted data in UTF-8
        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    }
    
    //base64Decode() method decodes text-to-binary and returns a byte array
    private static byte[] base64Decode(String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }
    
    
}
