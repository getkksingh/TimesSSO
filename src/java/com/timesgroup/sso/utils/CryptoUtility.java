package com.timesgroup.sso.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

public class CryptoUtility {
    private PaddedBufferedBlockCipher cipher = null;
    private ByteArrayInputStream in = null;
    private ByteArrayOutputStream out = null;
    private static byte[] key = Hex.decode("0e6ecdae2c452fb61fd5bc253225081c436d8357b67c64b0");

	 public final String performEncrypt(String data)
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
			
			return (new String(out.toByteArray()));
	    }

	   
	    public final String performDecrypt(String encryptedStr)
	    {    
	    	in = new ByteArrayInputStream(encryptedStr.replaceAll(":", "\n").getBytes());
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
	        
	        return (new String(out.toByteArray()));
	    }
}
