package com.timesgroup.sso.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.mapping.UserActivity;

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
	
	public static PrintWriter getPrintWriter(HttpServletResponse response) {

		PrintWriter responseWriter = null;

		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return responseWriter;
	}
	
	public static Date getDate(String date){
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date parsedDate=null;
		try{
		     parsedDate = df.parse("23/09/1987");           
		     
		} catch (ParseException e)
		{
		     e.printStackTrace();
		}
		
		return parsedDate;
	}
	
	public static String getUUID(){
		
		return new BigInteger(UUID.randomUUID().toString().replaceAll("-", ""), 16).toString(36);
	}
	
	public static String getPropertyValue(String property){
		
		String propertyValue = null;
		try{
			
			InputStream inputStream = TimesMail.class.getResourceAsStream("/com/timesgroup/sso/conf/SSO.properties");  
			Properties properties = new Properties();  
			
			// load the inputStream using the Properties  
			properties.load(inputStream);  
			// get the value of the property  
			propertyValue = properties.getProperty(property);  
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return propertyValue;
	}
	
	public static void logUserActivity(HttpServletRequest request, String activity, String aliasId, String siteId){
		
		UserActivityLogger userActivityLogger = UserActivityLogger.getInstance();
		UserActivity userActivity=new UserActivity();
		
		userActivity.setActivity(activity);
		userActivity.setCreated_date(new Date());
		userActivity.setIp_address(request.getRemoteAddr());
		
		String file = request.getRequestURI();
		if (request.getQueryString() != null) {
		   file += "?" + request.getQueryString();
		}
		URL reconstructedURL;
		try {
			reconstructedURL = new URL(request.getScheme(),request.getServerName(),
			                               request.getServerPort(), file);
			userActivity.setPage_url(reconstructedURL.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userActivity.setSite_id(siteId);
		userActivity.setUser_id(aliasId);
		
		userActivityLogger.enQueueUserActivity(userActivity);
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("SSOUtils.main()"+SSOUtils.getPropertyValue("SUCCESS_RU"));
	}

}
