package com.timesgroup.sso.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class SSOUtils {
	
    private static Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
    
	public static boolean containsSpecialCharacter(String inputString, String specialCharacters) {
		if(specialCharacters == null)
			return false;
		
		int specialCharLength = specialCharacters.length();
		int inputStringLength = inputString.length();
		for(int i = 0; i < specialCharLength;i++){
		  	for(int j = 0 ; j < inputStringLength; j++)
		  		if(inputString.charAt(j)==specialCharacters.charAt(i))
		  			return true;
		}
		return false;
	}
	
	public static boolean isValidEmailAddress (String emailAddress) {
	      Matcher emailMatcher = emailPattern.matcher(emailAddress);
	      boolean matchFound = emailMatcher.matches();
	      return matchFound;
	}
	
	public static String formatDate(Date date,String dateformat){
		SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
		formatter.format(date);
		return formatter.format(date);
		
	}
	
	public static void main(String[] args) {
	//	System.out.println(SSOUtils.formatDate(new Date(),"MM/dd/yyyy"));
		//System.out.println(SSOUtils.isValidEmailAddress("@get.kksingh@gmail.com.oo"));
	}

	public static Map createRequestMapWithLowerCaseParams(
			HttpServletRequest request) {
		Map outPutMap = new HashMap () ;
		Map inputMap = request.getParameterMap();
		Set s = inputMap.entrySet();
		Iterator it=s.iterator();
        while(it.hasNext())
        {

            Map.Entry m =(Map.Entry)it.next();
            String key =((String)m.getKey()).toLowerCase();
            String value = ((String[])m.getValue())[0];
            outPutMap.put(key, value);
        }

		
		return outPutMap ;
	}
}
