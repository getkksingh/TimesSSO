package com.timesgroup.sso.servlets.itimes;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.utils.SSOUtils;
import com.timesgroup.sso.utils.UserActivityManager;

public class LogoutServlet extends HttpServlet {

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		final Logger mylogger = Logger.getLogger(LogoutServlet.class);
		
		try {		
			deleteCookie(request,response);
		} catch (IOException ioe) {	
			ioe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			response.sendRedirect(SSOUtils.getPropertyValue("ITIMES_LOGOUT"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void deleteCookie(HttpServletRequest request,HttpServletResponse response) throws Exception{
		  	Cookie cookie = null;
		    Cookie[] cookies = request.getCookies();

		    Vector cookieList=new Vector();
		    cookieList.addElement(SSOConstants.Login.MSCSAUTH_COOKIE_NAME);
		    cookieList.addElement(SSOConstants.Login.MSCSAUTHDETAIL_COOKIE_NAME);
		    cookieList.addElement(SSOConstants.Login.MSCSAUTHDETAILS_COOKIE_NAME);
		    cookieList.addElement(SSOConstants.Login.VISITEDSITES_COOKIE_NAME);
		    
		    if (cookies != null) {
		      for (int i = 0; i < cookies.length; i++) {
		        if (cookieList.contains((String)cookies[i].getName()))   {
					  cookie = cookies[i];
					  cookie.setMaxAge(0);
					  cookie.setValue("");
					  cookie.setDomain(SSOConstants.Login.COOKIE_DOMAIN_NAME);
					  cookie.setPath(SSOConstants.Login.COOKIE_PATH);

					  response.addCookie(cookie);
					  System.out.println("Removed "+cookie.getName());
		        }
		      }
		    } 
	}
		    
	

}
