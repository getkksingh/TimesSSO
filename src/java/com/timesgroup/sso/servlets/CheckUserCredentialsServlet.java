package com.timesgroup.sso.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.DataAccessManager;
import com.timesgroup.sso.utils.SSOUtils;

public class CheckUserCredentialsServlet extends HttpServlet{
	
		
	protected void service(HttpServletRequest request,	HttpServletResponse response) throws ServletException {

		Map requestMapWithLowerCaseParameters = SSOUtils.createRequestMapWithLowerCaseParams(request);
		String userId =  (String)requestMapWithLowerCaseParameters.get(SSOConstants.CheckUserCredentialsServletConstants.PARAMUSERNAME);
		String password = (String) requestMapWithLowerCaseParameters.get(SSOConstants.CheckUserCredentialsServletConstants.PARAMPASSWORD);
		if(userId!=null)
			userId=userId.toLowerCase();

		PrintWriter responseWriter =  null ;
	        try {
				responseWriter = response.getWriter();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		    System.out.println(SSOConstants.CheckUserCredentialsServletConstants.PARAMUSERNAME +userId+"  " +SSOConstants.CheckUserCredentialsServletConstants.PARAMPASSWORD +password);
		    if(userId == null || userId.trim().equalsIgnoreCase(""))
		    {
		    	responseWriter.write(SSOConstants.CheckUserCredentialsServletConstants.MISSINGUSERNAMEREPONSE);
		    	return ;
		    }
		    if(password == null || password.trim().equalsIgnoreCase(""))
		    {
		    	responseWriter.write(SSOConstants.CheckUserCredentialsServletConstants.MISSINGPASSWORDRESPONSE);
		    	return ;
		    }
		    DataAccessManager accessManager = new DataAccessManager();
		    
		    boolean credentialsCheck = accessManager.checkUserCredentials(userId,password);
	        System.out.println("UserName and Password are: " + credentialsCheck);
	        String serverName = request.getServerName();
	        String contextPath = request.getContextPath();
	        String protocol = request.getProtocol();
	        int port = request.getServerPort();
	        String xmlns = "http" + "://" + serverName +":" + port  + contextPath ;
	       
	        if(credentialsCheck)
	        	responseWriter.write(String.format(SSOConstants.CheckUserCredentialsServletConstants.LOGINSUCESSRESPONSE, xmlns));
	        else
	        	responseWriter.write(String.format(SSOConstants.CheckUserCredentialsServletConstants.LOGINFAILURERESPONSE, xmlns));
	        
	}
	}


