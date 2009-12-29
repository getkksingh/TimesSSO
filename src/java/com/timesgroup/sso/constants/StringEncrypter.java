package com.timesgroup.sso.constants;

// CIPHER / GENERATORS
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;
 
public class StringEncrypter {
    public static final byte[] DATA = "This is a test".getBytes();

    public static final File DATA_FILE = new File( "encrypted.data" );
    public static final File KEY_FILE = new File( "key.data" );
    public static void main(String[] args) throws Exception {
    	
 
    		byte[] key = "Secret Key kksin".getBytes() ;//... secret sequence of bytes
    		byte[] dataToSend = "kksingh_iitk".getBytes() ;

    		//Encrypt
    		Cipher c = Cipher.getInstance("AES");
    		SecretKeySpec k =
    		  new SecretKeySpec(key, "AES");
    		c.init(Cipher.ENCRYPT_MODE, k);
    		byte[] encryptedData = c.doFinal(dataToSend);
    		byte[] encodedBytes = Base64.encode(encryptedData);
    		System.out.println(new String(encodedBytes));
    			
    		Cipher c1 = Cipher.getInstance("AES");	
    		c1.init(Cipher.DECRYPT_MODE, k);
    		byte[] data = c1.doFinal(Base64.decode(encodedBytes));
    	    System.out.println(new String(data));
    	
    }
    
	public static void encrypt() throws Exception{
		    KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
	        SecretKey skey = kgen.generateKey();
	        byte[] raw = skey.getEncoded();
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");

	        // Create the cipher for encrypting
	        Cipher cipher = Cipher.getInstance("Blowfish");
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

	        // Encrypt the data
	        byte[] encrypted = cipher.doFinal( DATA );

	        // Save the encrypted data
	        FileOutputStream fos = new FileOutputStream( DATA_FILE );
	        fos.write( encrypted );
	        fos.close();

	        // Save the cipher settings
	        byte[] encodedKeySpec = skeySpec.getEncoded();
	        FileOutputStream eksos = new FileOutputStream( KEY_FILE );
	        eksos.write( encodedKeySpec );
	        eksos.close();

	}
	
	public static void decrypt() throws Exception{
		 // Read the encrypted data
        FileInputStream fis = new FileInputStream(DATA_FILE);
        byte[] temp = new byte[ (int) DATA_FILE.length()];
        int bytesRead = fis.read(temp);
        byte[] data = new byte[bytesRead];
        System.arraycopy(temp, 0, data, 0, bytesRead);

        
        // Read the cipher settings
        FileInputStream eksis = new FileInputStream( KEY_FILE );
        bytesRead = eksis.read(temp);
        byte[] encodedKeySpec = new byte[bytesRead];
        System.arraycopy(temp, 0, encodedKeySpec, 0, bytesRead);

        // Recreate the secret/symmetric key
        SecretKeySpec skeySpec = new SecretKeySpec( encodedKeySpec, "Blowfish");

        // Create the cipher for encrypting
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        // Decrypt the data
        byte[] decrypted = cipher.doFinal(data);

        // Validate successful decryption
        for (int i = 0; i < decrypted.length; i++) {
            if ( decrypted[ i ] != DATA[ i ] ) {
                System.err.println( "Decrypted data wrong at byte " + i + "!" );
                System.exit( 1 );
            }
        }
        System.err.println( "Success!" );

	}
}
 
