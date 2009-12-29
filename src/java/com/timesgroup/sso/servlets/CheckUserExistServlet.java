package com.timesgroup.sso.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.DataAccessManager;
import com.timesgroup.sso.utils.SSOUtils;

public class CheckUserExistServlet extends HttpServlet{
	
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException { 
					
			String userId =  request.getParameter(SSOConstants.CheckUserExistServletConstants.PARAMUSER);
			if(userId!=null)
				userId=userId.toLowerCase();

			PrintWriter responseWriter =  null ;
	        
			try {
				responseWriter = response.getWriter();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
			if(userId==null)
			{
				responseWriter.write(SSOConstants.CheckUserExistServletConstants.USER_EXISTANCE_ERROR_MESSAGE);
				return ;
			}
			
			if(userId.trim().equalsIgnoreCase("") || SSOUtils.containsSpecialCharacter(userId,SSOConstants.CheckUserExistServletConstants.USER_ID_NOT_PERMISSIBLE_CHARACTERS)){
				responseWriter.write(SSOConstants.CheckUserExistServletConstants.USER_INVALIDE_ERROR_MESSAGE);
				return ;
			}
				
			DataAccessManager dataAccessManager = new DataAccessManager();
			boolean userExists = dataAccessManager.checkUserExists(userId);
			
			if(userExists)
				responseWriter.write(SSOConstants.CheckUserExistServletConstants.USER_EXISTS_MESSAGE);
			else
				responseWriter.write(SSOConstants.CheckUserExistServletConstants.USER_DOES_NOT_EXISTS_MESSAGE);
		
	}

	
}
