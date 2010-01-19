package com.timesgroup.sso.servlets.itimes;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.ITimesDataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserActivity;
import com.timesgroup.sso.hibernate.mapping.UserRegistrationItimes;
import com.timesgroup.sso.utils.CryptoUtility;
import com.timesgroup.sso.utils.SSOUtils;
import com.timesgroup.sso.utils.UserActivityLogger;

public class LoginServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(LoginServlet.class);
		String userId = request.getParameter(SSOConstants.Login.PARAM_USERID);
		String password = request.getParameter(SSOConstants.Login.PARAM_PASSWORD);
		String redirectURL =  request.getParameter(SSOConstants.Login.PARAM_REDIRECTURL);
		String is = request.getParameter(SSOConstants.Login.PARAM_IS);
		String ns = request.getParameter(SSOConstants.Login.PARAM_NS);
		String hs = request.getParameter(SSOConstants.Login.PARAM_HS);
		
		PrintWriter responseWriter = SSOUtils.getPrintWriter(response);
		
		if (userId == null || (userId.trim().compareTo("") == 0)  || 
			password ==null || (password.trim().compareTo("") == 0) || 
			redirectURL ==null || (redirectURL.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.Login.ERROR_WITH_LOGIN_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.Login.ERROR_WITH_LOGIN_REQUEST_PARAMETERS);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
	    UserRegistrationItimes userRegistrationItimes = iTimesDataAccessManager.getUserProfile(userId.toLowerCase());
	    
	   /* System.out.println("LoginServlet.service()login=="+userId);
	    System.out.println("LoginServlet.service()password=="+userRegistrationItimes.getPassword());
	    System.out.println("LoginServlet.service()URLpassword=="+password);*/
	    if(userRegistrationItimes!=null && userRegistrationItimes.getPassword().trim().equals(password.trim())){
	    	
	    	mylogger.debug("USER REGISTRATION SUCCESSFUL.CREATING COOKIE");
	    	try{
	    		
				createCookie(response,userRegistrationItimes,is);
				response.sendRedirect(SSOUtils.getPropertyValue("SUCCESS_RU")); 
				
				/*Tracking Login*/
				SSOUtils.logUserActivity(request, "Login", userId, "1");
			}catch(Exception e){
				
				e.printStackTrace();
			}
	    }else{
	    	
	    	mylogger.debug("USER REGISTRATION FAILED. LOGIN AGAIN.");
	    	try {
				response.sendRedirect(redirectURL);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
       	 	return;
	    }
	    
	}
	private void createCookie(HttpServletResponse response,UserRegistrationItimes userObj, String IS) throws Exception{
		CryptoUtility cryptObj=new CryptoUtility();
		String domainAndPath = " domain="+SSOConstants.Login.COOKIE_DOMAIN_NAME+"; path="+SSOConstants.Login.COOKIE_PATH+";" ;

		String encryptedUserid= cryptObj.performEncrypt(userObj.getUser_id());
		String mscsAuthDetails = "UserName="+userObj.getUser_id() ;
		String mscsAuthDetail = "DOB="+userObj.getDob()+"~Gender="+userObj.getGender()+"~Country="+userObj.getCountry()+";";
		String visitedSites = IS+";"+domainAndPath;
	
		response.addHeader("Set-Cookie", SSOConstants.Login.MSCSAUTH_COOKIE_NAME+"="+encryptedUserid+";"+domainAndPath);
		response.addHeader("Set-Cookie", SSOConstants.Login.MSCSAUTHDETAIL_COOKIE_NAME+"="+mscsAuthDetail+domainAndPath);
		response.addHeader("Set-Cookie", SSOConstants.Login.MSCSAUTHDETAILS_COOKIE_NAME+"="+mscsAuthDetails+";"+domainAndPath);
		response.addHeader("Set-Cookie", SSOConstants.Login.VISITEDSITES_COOKIE_NAME+"="+visitedSites);
	}

}
