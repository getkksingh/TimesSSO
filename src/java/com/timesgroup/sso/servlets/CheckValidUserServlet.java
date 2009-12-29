package com.timesgroup.sso.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.DataAccessManager;

public class CheckValidUserServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		String userId = request.getParameter(SSOConstants.CheckValidUserServletConstants.PARAMUSER) ;
		String password = request.getParameter(SSOConstants.CheckValidUserServletConstants.PARAMPASSWORD);
		if(userId!=null)
			userId=userId.toLowerCase();
		
		PrintWriter responseWriter =  null ;     
		try {
			responseWriter = response.getWriter();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}

		if(userId == null || userId.trim().equalsIgnoreCase("") 
				|| password == null || password.trim().equalsIgnoreCase("") )
		{
			responseWriter.write(SSOConstants.CheckValidUserServletConstants.ERROR_WITH_PASSED_REQUEST_PARAMETERS);
			return ;
		}
	    DataAccessManager accessManager = new DataAccessManager();
	    
	    boolean credentialsCheck = accessManager.checkUserCredentials(userId,password);
        System.out.println("UserName and Password are: " + credentialsCheck);
       
        if(credentialsCheck)
        	responseWriter.write(SSOConstants.CheckValidUserServletConstants.IS_VALIDE_USER_WITH_GIVEN_CREDENTIALS);
        else
        	responseWriter.write(SSOConstants.CheckValidUserServletConstants.NOT_VALIDE_USER_WITH_GIVEN_CREDENTIALS);
	}

}
