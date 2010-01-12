package com.timesgroup.sso.constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Test {

	public static void main(String[] args){
		
		/*Properties properties = new Properties(); 
		try { 
			properties.load(new FileInputStream("SSO.properties")); 
			String p = properties.getProperty("SMTPIP");
	         System.out.println( "IP : " + p);
	       
			} catch (IOException e){
				e.printStackTrace();
			} */
		//email ^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$ 
		//System.out.println("Test.main()"+"asas".matches("^[a-z]+[0-9]*(\\.[a-z]+)?$"));
		System.out.println("Test.main()"+"as234567899".matches("^[0-9]*$"));
		
		
	}
	
	
}
