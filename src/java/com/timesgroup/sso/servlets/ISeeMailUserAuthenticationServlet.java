package com.timesgroup.sso.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.DataAccessManager;
import com.timesgroup.sso.hibernate.apis.ITimesDataAccessManager;
import com.timesgroup.sso.servlets.itimes.GetUserIdByEMailIdServlet;
import com.timesgroup.sso.utils.LDAPCaller;
import com.timesgroup.sso.utils.MD5;

public class ISeeMailUserAuthenticationServlet extends HttpServlet{
	
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(ISeeMailUserAuthenticationServlet.class);

		String userId = request.getParameter(SSOConstants.ISeeMailUserAuthentication.PARAM_USERID);
		/*MD5 ENCRYPTED PASSWORD*/
		String password = request.getParameter(SSOConstants.ISeeMailUserAuthentication.PARAM_PASSWORD);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (userId == null || (userId.trim().compareTo("") == 0) ||
				password ==null || (password.trim().compareTo("")==0)) {

			mylogger.error(SSOConstants.ISeeMailUserAuthentication.ERROR_WITH_GETPASSWORDFROMUSERID_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.ISeeMailUserAuthentication.ERROR_WITH_GETPASSWORDFROMUSERID_REQUEST_PARAMETERS);
			return;
		}
		
		if(userId!=null && !userId.matches(SSOConstants.VALID_USERID_PATTERN)){
			
			mylogger.error(SSOConstants.ISeeMailUserAuthentication.INVALID_USERID);
			responseWriter.write(SSOConstants.ISeeMailUserAuthentication.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		DataAccessManager dataAccessManager = new DataAccessManager();
		String  str = dataAccessManager.getPasswordfromUserId(userId.toLowerCase());

		if(str==null || str.equals(SSOConstants.ISeeMailUserAuthentication.RECORD_NOT_FOUND)){
			
			mylogger.info(SSOConstants.ISeeMailUserAuthentication.RECORD_NOT_FOUND);
			//responseWriter.write(SSOConstants.XML_URL+"<response>\n<userid>" + userId + "</userid>\n"+"<password></password>\n</response>\n");
			responseWriter.write(SSOConstants.XML_URL+"<response>\n<status>false</status>\n</response>\n");
			return;
		}else{
			
			/*COMPARE THE TWO PASSWORDS*/
			MD5 md5;
			try {
				md5 = MD5.getInstance();
				str=md5.hashData(str.getBytes());
				
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(!password.equals(str)){
				mylogger.warn("Authentication Failed: URL password not matched with  the database password.");
				responseWriter.write(SSOConstants.XML_URL+"<response>\n<status>false</status>\n</response>\n");
				return;
			}
			
			/*RECORD FOUND UPDATE TO LDAP*/
			String status="0";
			LDAPCaller ldapCaller = new LDAPCaller();
			try {
				
				status=ldapCaller.updateLDAPPassword(userId.toLowerCase(), str);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			if(status.equals("1")){
				
				responseWriter.write(SSOConstants.XML_URL+"<response>\n<status>true</status>\n</response>\n");
			}else{
				
				mylogger.warn("UNABLE TO UPDATE PASSWORD IN LDAP FOR USERID="+userId);
				responseWriter.write(SSOConstants.XML_URL+"<response>\n<status>false</status>\n</response>\n");
			}
			
			return;
		}
		
	}	

}
