package com.timesgroup.sso.hibernate.apis;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.engines.RijndaelEngine;
import org.bouncycastle.crypto.generators.PKCS5S1ParametersGenerator;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.ISO10126d2Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class Cryptographer {
	public static String decrypt5(String encryptedText, String saltStr, String passwordStr) {
	    String plainText = null;
	    try {
	 
	        byte[] saltBytes = saltStr.getBytes("UTF8");
	        byte[] passwordBytes = passwordStr.getBytes("UTF8");
	 
	        ISO10126d2Padding padding = new ISO10126d2Padding();
	        SecureRandom random = new SecureRandom();
	        padding.init(random);
	 
	        byte[] ivBytes = new byte[16];
	        System.arraycopy(saltBytes, 0, ivBytes, 0, saltBytes.length < 16 ? saltBytes.length : 16);
	        if (saltBytes.length < 16)
	            padding.addPadding(ivBytes, saltBytes.length);
	 
	        byte[] keyBytes = new byte[16];
	        System.arraycopy(passwordBytes, 0, keyBytes, 0, passwordBytes.length < 16 ? passwordBytes.length : 16);
	        if (passwordBytes.length < 16)
	            padding.addPadding(keyBytes, passwordBytes.length);
	 
	        Cipher cipher = Cipher.getInstance("Rijndael/CBC/ISO10126Padding");
	        System.out.println("Using provider: " + cipher.getProvider().getName() + " - "
	                + cipher.getProvider().getInfo());
	 
	        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "Rijndael");
	        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
	 
	        BASE64Decoder decoder = null;// new BASE64Decoder();
	        byte[] encryptedBytes = decoder.decodeBuffer(encryptedText);
	        byte[] results = cipher.doFinal(encryptedBytes);
	 
	        plainText = new String(results, "UTF-8");
	        System.out.println(plainText);
	 
	    } catch (Exception e) {
	        // log.error("Error in decryption method", e);
	        e.printStackTrace();
	    }
	    return plainText;
	}
	private static byte[] decryptData(byte[] data, String password, String paddingMode, String salt) throws Exception {
        if (data == null || data.length == 0)
            throw new IllegalArgumentException("data is empty");
        if (password == null || password == "")
            throw new IllegalArgumentException("password is empty");
        if (salt == null || salt == "")
            salt = ".";
 
        byte[] saltBytes = salt.getBytes("UTF8");
        byte[] passBytes = password.getBytes("UTF8");// new byte[16];
 
        PKCS5S1ParametersGenerator generator = new PasswordDeriveBytes(new SHA1Digest());
        generator.init(passBytes, saltBytes, 100);
 
        byte[] key = ((KeyParameter) generator.generateDerivedParameters(256)).getKey();
        passBytes = new byte[16];
        saltBytes = new byte[16];
        System.arraycopy(key, 0, passBytes, 0, 16);
        System.arraycopy(key, 16, saltBytes, 0, 16);
 
        Cipher cipher = Cipher.getInstance("Rijndael/CBC/" + paddingMode);
        SecretKeySpec keySpec = new SecretKeySpec(passBytes, "Rijndael");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(saltBytes));
 
        byte[] original = cipher.doFinal(data);
        return original;
    }
	
	private static BufferedBlockCipher getCipher(String password, boolean encrypt)
    throws Exception
{
    byte[] pwd = password.getBytes("UTF8");
    
    // equivalent to .NET class 
    // Rfc2898DeriveBytes when initialising with
    // Rfc2898DeriveBytes pwdb = new Rfc2898DeriveBytes(password, GENERAL_SALT)
    PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator();        
    generator.init(pwd, "key@123".getBytes(), 0x122);
    
    // compared to .NET pwdb.GetBytes(32), pwdb.GetBytes(16) we use bit sizes here
    ParametersWithIV iv = ((ParametersWithIV)generator.generateDerivedParameters(256,128));                
    
    // encrypt the stuff using rijndael
    // .NET would be this ...
    // Rijndael alg = Rijndael.Create();
    // alg.Key = pwdb.GetBytes(32);
    // alg.IV = pwdb.GetBytes(16);
    RijndaelEngine engine = new RijndaelEngine();
    BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));        
    
    // .NET
    // CryptoStream cs = new CryptoStream(ms, (encrypt ? alg.CreateEncryptor() : alg.CreateDecryptor()),
    // CryptoStreamMode.Write);                 
    cipher.init(encrypt, iv);
    
    return cipher;
}

 public static void main(String[] args) throws Exception {
	 String encryptedtuser = "3C79F0C7EA3162B275E73B07A66925F4071B09367B52424A1012206FC76C5CC1D2CCAC7375732BF11CCA8157EC915BB8D2DC8B86472E5CEE4463D5FA6CD6E327B279982D7C080DC31038D49227D498E5148E6A69FBE7AE49";
	Cryptographer.decryptData(encryptedtuser.getBytes(), "key@123", "ISO10126Padding", "key@123");
}
}
