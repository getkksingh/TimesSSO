package com.timesgroup.sso.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.DataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserRegistration;
import com.timesgroup.sso.utils.CryptoUtility;
import com.timesgroup.sso.utils.UserActivityManager;

public class CookieBasedLoginServlet extends HttpServlet {

	protected void service(HttpServletRequest request,
		HttpServletResponse response) throws ServletException {
		System.out.println("Hit the url :");
		String userId = request.getParameter(SSOConstants.CookieBasedLoginConstants.PARAM_LOGIN);
		String password = request.getParameter(SSOConstants.CookieBasedLoginConstants.PARAM_PASSWORD);
		String newreg =  request.getParameter(SSOConstants.CookieBasedLoginConstants.PARAM_NEWREG);
		String ru = request.getParameter(SSOConstants.CookieBasedLoginConstants.PARAM_RU);
		String is = request.getParameter(SSOConstants.CookieBasedLoginConstants.PARAM_IS);
		String ns = request.getParameter(SSOConstants.CookieBasedLoginConstants.PARAM_NS);
		String hs = request.getParameter(SSOConstants.CookieBasedLoginConstants.PARAM_HS);
		String nru=request.getParameter(SSOConstants.CookieBasedLoginConstants.PARAM_NRU);
		System.out.println(userId + " " + password + " "+newreg + " "+ru + " "+is + " "+ns +" " +hs );
		System.out.println("nru : "+nru);
		if(userId!=null)
			userId=userId.toLowerCase();
		DataAccessManager accessManager = new DataAccessManager();
	    UserRegistration userData = accessManager.getUserData(userId);
	    
	    boolean credentialsCheck=false;
	    if(userData!=null)
	    {
	    	if(userData.getPassword().equals(password))
	    		credentialsCheck=true;
	    }
	    System.out.println("UserName "+userId+" and Password are: " + credentialsCheck);
       
		try {
	        if(!credentialsCheck)
	        {
	        	 System.out.println("Redirecting to nru : "+nru);
	        	 response.sendRedirect(nru);
	        	 return;
	        }
			try{
				createCookie(response,userData,is);
			}catch(Exception e1)
			{
				//Exception while creating cookie
			}
			try{
				if(getServletContext().getInitParameter("Login").equalsIgnoreCase("true"))
					new UserActivityManager(userId,request.getRemoteAddr(),"Login",is);
			}catch(Exception e2)
			{}
			
			response.sendRedirect(ru);
			return;
			
		} catch (IOException ioe) {	
			ioe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void createCookie(HttpServletResponse response,UserRegistration userObj,String IS) throws Exception
	{
		CryptoUtility cryptObj=new CryptoUtility();
		String domainAndPath = " domain="+SSOConstants.CookieBasedLoginConstants.COOKIE_DOMAIN_NAME+"; path="+SSOConstants.CookieBasedLoginConstants.COOKIE_PATH+";" ;

		String encryptedUserid= cryptObj.performEncrypt(userObj.getUser_id());
		String mscsAuthDetails = "UserName="+userObj.getUser_id() ;
		String mscsAuthDetail = "DOB="+userObj.getDob()+"~Gender="+userObj.getGender()+"~Country="+userObj.getCountry()+";";
		String visitedSites = IS+";"+domainAndPath;
	
		response.addHeader("Set-Cookie", SSOConstants.CookieBasedLoginConstants.MSCSAUTH_COOKIE_NAME+"="+encryptedUserid+";"+domainAndPath);
		response.addHeader("Set-Cookie", SSOConstants.CookieBasedLoginConstants.MSCSAUTHDETAIL_COOKIE_NAME+"="+mscsAuthDetail+domainAndPath);
		response.addHeader("Set-Cookie", SSOConstants.CookieBasedLoginConstants.MSCSAUTHDETAILS_COOKIE_NAME+"="+mscsAuthDetails+";"+domainAndPath);
		response.addHeader("Set-Cookie", SSOConstants.CookieBasedLoginConstants.VISITEDSITES_COOKIE_NAME+"="+visitedSites);
	}
}
