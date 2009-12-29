package com.timesgroup.sso.constants;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
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


public class DESExample1 
{
 
   
    private PaddedBufferedBlockCipher cipher = null;
    private ByteArrayInputStream in = null;
    private ByteArrayOutputStream out = null;
    private static byte[] key = Hex.decode("0e6ecdae2c452fb61fd5bc253225081c436d8357b67c64b0");
    
    public static void main(String[] args)
    {
    	
         DESExample1 de = new DESExample1();
     //  de.performEncrypt(key,"Krishna Krishna Krishna KrishnaKrishna KrishnaKrishna KrishnaKrishna Krishna");
        de.performDecrypt(Hex.decode("0e6ecdae2c452fb61fd5bc253225081c436d8357b67c64b0".getBytes()), "1884576968dfaea7760f9ced810dd91f5546ae87b882d3595680d0d7953dbd29878bf53034c9e834:4683d120a15d86fed96b5c507458d24ee1305d20f2814389cb3bf13b41cc40697c56f5d8745f1a186d97db658a0a7972:024a6deb9b65ecb3:502ee0d3c4aef098:");
      
      
    
    }


   
        
   
    private final void performEncrypt(byte[] key,String data)
    {	
    	in = new ByteArrayInputStream(data.getBytes());
    	out = new ByteArrayOutputStream();       
    	cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()));
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
		        out.write(':');
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
		        out.write(':');
		    }
		}
		catch (CryptoException ce)
		{

		}
		
		System.out.println(new String(out.toByteArray()));
    }

   
    private final void performDecrypt(byte[] key,String encryptedStr)
    {    
    	in = new ByteArrayInputStream("krishna".getBytes());
        out = new ByteArrayOutputStream();
        cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()));

        cipher.init(false, new KeyParameter(key));
        
        BufferedReader br = new BufferedReader(new StringReader(encryptedStr.replaceAll(":", "\n")));

      
        try
        {
            int outL;
            byte[] inblock = null;
            byte[] outblock = null;
            String rv = null;
            while ((rv = br.readLine()) != null)
            {
                inblock = Hex.decode(rv);
                outblock = new byte[cipher.getOutputSize(inblock.length)];

                outL = cipher.processBytes(inblock, 0, inblock.length, 
                                            outblock, 0);
                /*
                 * Before we write anything out, we need to make sure
                 * that we've got something to write out. 
                 */
                if (outL > 0)
                {
                    out.write(outblock, 0, outL);
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
                    out.write(outblock, 0, outL);
                }
            }
            catch (CryptoException ce)
            {

            }
        }
        catch (IOException ioeread)
        {
            ioeread.printStackTrace();
        }
        
        System.out.println(new String(out.toByteArray()));
    }
    
}

