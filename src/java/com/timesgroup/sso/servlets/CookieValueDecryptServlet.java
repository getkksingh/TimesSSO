package com.timesgroup.sso.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.utils.CryptoUtility;

public class CookieValueDecryptServlet extends HttpServlet{
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
			String encryptedStr = request.getParameter(SSOConstants.CookieValueDecryptXMLConstants.PARAM_STR);
			System.out.println("Encrypted String : "+encryptedStr);
			PrintWriter responseWriter =  null ;
	        
			try {
				responseWriter = response.getWriter();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			CryptoUtility cryptObj=new CryptoUtility();
			try{
				String decryptedValue= cryptObj.performDecrypt(encryptedStr);
	
		    	String strXML=String.format(
				    SSOConstants.CookieValueDecryptXMLConstants.DECRYPTED_VALUE_XML_FORMAT,decryptedValue);
				responseWriter.write(strXML);
				return ; 
			} catch (Exception e2) {
				responseWriter.write(SSOConstants.CookieValueDecryptXMLConstants.DECRYPT_ERROR_MESSAGE);
				return;
			}
		}
}
