package com.timesgroup.sso.constants;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.security.SecureRandom;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.generators.DESedeKeyGenerator;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.DESedeParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

public class EnCryptDeCrypt {
public static void main(String[] args) throws Exception{
	performEncrypt();
	//decrypt(Hex.decode("1315adbcd33df22fb58345293d25b5b957a2401abcc1646e"),"55ee8b9ef8b4b246");
}

private static void decrypt(byte[] key,String data) throws Exception {
	 PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()));  
	 cipher.init(false, new KeyParameter(key));
	  byte[] inblock = null;
      byte[] outblock = null;
      int outL;
      
      inblock = Hex.decode(data);
      outblock = new byte[cipher.getOutputSize(inblock.length)];

      outL = cipher.processBytes(inblock, 0, inblock.length, outblock, 0);
      outL = cipher.doFinal(outblock, 0);
      System.out.println(new String(outblock));
      
	
}

private static void performEncrypt()
{	String data = "1234567890" ;	
    SecureRandom sr = null;
    KeyGenerationParameters kgp = new KeyGenerationParameters(
            sr, 
            DESedeParameters.DES_EDE_KEY_LENGTH*8);

/*
* Second, initialise the key generator with the parameters
*/
  DESedeKeyGenerator kg = new DESedeKeyGenerator();
   kg.init(kgp);

/*
* Third, and finally, generate the key
*/
   System.out.println(kg);
    byte[] key = kg.generateKey();
	 ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
	 ByteArrayOutputStream out =new ByteArrayOutputStream();

	   PaddedBufferedBlockCipher cipher = null;
	   cipher = new PaddedBufferedBlockCipher(
               new CBCBlockCipher(new DESedeEngine()));

    // initialise the cipher with the key bytes, for encryption
    cipher.init(true, new KeyParameter(key));

    /*
     * Create some temporary byte arrays for use in
     * encryption, make them a reasonable size so that
     * we don't spend forever reading small chunks from
     * a file.
     *
     * There is no particular reason for using getBlockSize()
     * to determine the size of the input chunk.  It just
     * was a convenient number for the example.  
     */
    // int inBlockSize = cipher.getBlockSize() * 5;
    int inBlockSize = 47;
    int outBlockSize = cipher.getOutputSize(inBlockSize);

    byte[] inblock = new byte[inBlockSize];
    byte[] outblock = new byte[outBlockSize];

    int inL;
	int outL;
	byte[] rv = null;
	while ((inL=in.read(inblock, 0, inBlockSize)) > 0)
	{
	    outL = cipher.processBytes(inblock, 0, inL, outblock, 0);
	    /*
	     * Before we write anything out, we need to make sure
	     * that we've got something to write out. 
	     */
	    if (outL > 0)
	    {
	        rv = Hex.encode(outblock, 0, outL);
	        out.write(rv, 0, rv.length);
	        out.write('\n');
	    }
	}

	try
	{
	    /*
	     * Now, process the bytes that are still buffered
	     * within the cipher.
	     */
	    outL = cipher.doFinal(outblock, 0);
	    if (outL > 0)
	    {
	        rv = Hex.encode(outblock, 0, outL);
	        out.write(rv, 0, rv.length);
	        out.write('\n');
	    }
	}
	catch (CryptoException ce)
	{

	}
}
private static void encrypt() throws Exception{
	
	
	 String  data = "12345678910" ;
	 SecureRandom sr =    new SecureRandom();
	 KeyGenerationParameters kgp = new KeyGenerationParameters( sr, DESedeParameters.DES_EDE_KEY_LENGTH*8);
	 DESedeKeyGenerator kg = new DESedeKeyGenerator();
	 kg.init(kgp);
	 byte[] key = kg.generateKey();
	  
	 
	 PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()));
	 ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
	 ByteArrayOutputStream out =new ByteArrayOutputStream();
	   cipher.init(true, new KeyParameter(key));
	 
	   int inBlockSize = 47;
       int outBlockSize = cipher.getOutputSize(inBlockSize);

       byte[] inblock = new byte[inBlockSize];
       byte[] outblock = new byte[outBlockSize];

       int inL;
	   int outL;
	   byte[] rv = null;
	   while ((inL=in.read(inblock, 0, inBlockSize)) > 0)
	   {
	       outL = cipher.processBytes(inblock, 0, inL, outblock, 0);
	       /*
	        * Before we write anything out, we need to make sure
	        * that we've got something to write out. 
	        */
	       if (outL > 0)
	       {
	           rv = Hex.encode(outblock, 0, outL);
	           out.write(rv, 0, rv.length);
	           out.write('\n');
	       }
	   }

	   try
	   {
	       /*
	        * Now, process the bytes that are still buffered
	        * within the cipher.
	        */
	       outL = cipher.doFinal(outblock, 0);
	       if (outL > 0)
	       {
	           rv = Hex.encode(outblock, 0, outL);
	           out.write(rv, 0, rv.length);
	           out.write('\n');
	       }
	   }
	   catch (CryptoException ce)
	   {

	   }
	 
     //System.out.println(outL);
     System.out.println("Key: " + new String(Hex.encode(key)));
     System.out.println("Encrypt: " + new String(Hex.encode(outblock)));
             
	
}
}
