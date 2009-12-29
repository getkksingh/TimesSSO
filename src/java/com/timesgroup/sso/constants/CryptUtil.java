package com.timesgroup.sso.constants;

import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptUtil {
 /**
  * Turns array of bytes into string
  *
  * @param buf
  *            Array of bytes to convert to hex string
  * @return Generated hex string
  */
 public static String asHexStr(byte buf[]) {
  StringBuffer strbuf = new StringBuffer(buf.length * 2);
  int i;

  for (i = 0; i < buf.length; i++) {
   if (((int) buf[i] & 0xff) < 0x10)
    strbuf.append("0");
   strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
  }
  return strbuf.toString();
 }
 
 public static SecretKeySpec getSecretKeySpec(String passphrase,
   String algorithm, int kgenbit)
 throws Exception
 {  
  // 8-byte Salt - SHOULD NOT BE DISCLOSED
  // alternative approach is to have the salt passed from the
  // calling class (pass-the-salt)?
       byte[] salt = {
           (byte)0xA9, (byte)0x87, (byte)0xC8, (byte)0x32,
           (byte)0x56, (byte)0xA5, (byte)0xE3, (byte)0xB2,
       };
      
       // Iteration count
       int iterationCount = 1024;
         
  KeySpec keySpec = new PBEKeySpec(passphrase.toCharArray(),
                             salt,
                             iterationCount);
  
       SecretKey secretKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES").
                             generateSecret(keySpec);
      
       MessageDigest md = MessageDigest.getInstance("MD5");
       md.update(secretKey.getEncoded());
       md.update(salt);
       for(int i = 1; i < iterationCount; i++)
       md.update(md.digest());

       byte[] keyBytes = md.digest();
       SecretKeySpec skeyspec = new SecretKeySpec(keyBytes, algorithm);
      
       return skeyspec;
      
 }
 
 /**
  * Encrypt a byte array given the secret key spec
  * @param message
  * @param skeyspec
  * @return
  * @throws Exception
  */
 public static byte[] encrypt(byte[] message, SecretKeySpec skeyspec)
 throws Exception
 {
  Cipher cipher = Cipher.getInstance(skeyspec.getAlgorithm());
  cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
  byte[] encrypted = cipher.doFinal(message);
  return encrypted;
 }
 
 /**
  * Decrypt a byte array given the same secret key spec used to encrypt the message
  *
  * @param message
  * @param skeyspec
  * @return
  * @throws Exception
  */
 public static byte[] decrypt(byte[] message, SecretKeySpec skeyspec)
 throws Exception
 {
  Cipher cipher = Cipher.getInstance(skeyspec.getAlgorithm());
  cipher.init(Cipher.DECRYPT_MODE, skeyspec);
  byte[] decrypted = cipher.doFinal(message);
  return decrypted;
 }
 
 /**
  * Main method, shows how to use the CryptUtil class
  * @param args
  * @throws Exception
  */
 public static void main(String[] args) throws Exception
 {
  String message = "This is just an example";

  System.out.println("(HEX) Original  : " + asHexStr(message.getBytes()));
  SecretKeySpec skeySpec = getSecretKeySpec("mypassword","AES",128);
     
     byte[] encrypted = encrypt(message.getBytes(), skeySpec);
     System.out.println("(HEX) Encrypted : " + asHexStr(encrypted));
    
     skeySpec = getSecretKeySpec("mypassword","AES",128);
    
     byte[] decrypted = decrypt(encrypted, skeySpec);
     System.out.println("(HEX) Decrypted : " + asHexStr(decrypted));
    
     if ( Arrays.equals(decrypted, message.getBytes()))
      System.out.println("THE ONE AND THE SAME");
 }
}