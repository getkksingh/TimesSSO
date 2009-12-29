package com.timesgroup.sso.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.DataAccessManager;
import com.timesgroup.sso.utils.UserActivityManager;

public class UserProfileServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
			String userId = request.getParameter(SSOConstants.UserProfileServletConstants.PARAMUSER);
			if(userId!=null)
				userId=userId.toLowerCase();
			
			PrintWriter responseWriter =  null ;
	        
			try {
				responseWriter = response.getWriter();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}

			if(userId == null) {
				responseWriter.write(SSOConstants.UserProfileServletConstants.ERROR_WITH_PASSED_REQUEST_PARAMETERS);
				return ;
			}
			
		    DataAccessManager accessManager = new DataAccessManager();
		    
		    String userProfile = accessManager.getUserProfile(userId);
		    if(userProfile == null) {
				responseWriter.write(SSOConstants.UserProfileServletConstants.INVALIDE_USER_PROFILE_MESSAGE);
				return ; 
		    }
		    else 
		    {
				//UserActivity Logging
				try{
					if(getServletContext().getInitParameter("GetUserProfile").equalsIgnoreCase("true"))
						new UserActivityManager(userId,request.getRemoteAddr(),"GetUserProfile","");
				}catch(Exception e2)
				{}

		    	responseWriter.write(userProfile);
		    }
	}
}
