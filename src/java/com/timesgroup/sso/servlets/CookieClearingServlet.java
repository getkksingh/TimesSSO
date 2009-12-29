package com.timesgroup.sso.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.DataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserRegistration;
import com.timesgroup.sso.utils.CryptoUtility;
import com.timesgroup.sso.utils.UserActivityManager;

public class CookieClearingServlet extends HttpServlet{
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
			System.out.println("Hit the url :");
			String rul = request.getParameter(SSOConstants.CookieBasedLoginConstants.PARAM_RUL);
			System.out.println("rul : "+rul);
	       
			try {		
				deleteCookie(request,response);
			} catch (IOException ioe) {	
				ioe.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try{
				if(getServletContext().getInitParameter("Logout").equalsIgnoreCase("true"))
					new UserActivityManager("",request.getRemoteAddr(),"Logout","");
			}catch(Exception e1)
			{}
			try {
				response.sendRedirect(rul);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
			
		private void deleteCookie(HttpServletRequest request,HttpServletResponse response) throws Exception
		{
			  	Cookie cookie = null;
			    Cookie[] cookies = request.getCookies();

			    Vector cookieList=new Vector();
			    cookieList.addElement(SSOConstants.CookieBasedLoginConstants.MSCSAUTH_COOKIE_NAME);
			    cookieList.addElement(SSOConstants.CookieBasedLoginConstants.MSCSAUTHDETAIL_COOKIE_NAME);
			    cookieList.addElement(SSOConstants.CookieBasedLoginConstants.MSCSAUTHDETAILS_COOKIE_NAME);
			    cookieList.addElement(SSOConstants.CookieBasedLoginConstants.VISITEDSITES_COOKIE_NAME);
			    
			    if (cookies != null) {
			      for (int i = 0; i < cookies.length; i++) {
			        if (cookieList.contains((String)cookies[i].getName())) 
			        {
						  cookie = cookies[i];
						  cookie.setMaxAge(0);
						  cookie.setValue("");
						  cookie.setDomain(SSOConstants.CookieBasedLoginConstants.COOKIE_DOMAIN_NAME);
						  cookie.setPath(SSOConstants.CookieBasedLoginConstants.COOKIE_PATH);
	
						  response.addCookie(cookie);
						  System.out.println("Removed "+cookie.getName());
			        }
			      }
			    }
		}
}
