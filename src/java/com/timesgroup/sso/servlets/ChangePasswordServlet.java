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

public class ChangePasswordServlet extends HttpServlet {

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
			
		String userId = request.getParameter(SSOConstants.ChangePasswordServletConstants.PARAM_USER);
		String oldPassword =  request.getParameter(SSOConstants.ChangePasswordServletConstants.PARAM_OLD_PASSWORD) ;
		String newPassword = request.getParameter(SSOConstants.ChangePasswordServletConstants.PARAM_NEW_PASSWORD);
		if(userId!=null)
			userId=userId.toLowerCase();

		PrintWriter responseWriter = null ;
		try {
			 responseWriter = response.getWriter();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		if(userId == null || oldPassword == null || newPassword == null){
			responseWriter.write(SSOConstants.ChangePasswordServletConstants.ERROR_WITH_PASSED_REQUEST_PARAMETERS);
			return ;
		}
		if(userId.trim().equalsIgnoreCase("")) {
			responseWriter.write(SSOConstants.ChangePasswordServletConstants.PARAM_USER+"="+SSOConstants.ChangePasswordServletConstants.BLANK_FIELD_MESSAGE);
			return;
		}
		
		if(oldPassword.trim().equalsIgnoreCase("")) {
			responseWriter.write(SSOConstants.ChangePasswordServletConstants.PARAM_OLD_PASSWORD+"="+SSOConstants.ChangePasswordServletConstants.BLANK_FIELD_MESSAGE);
			return;
	
		}
		
		if(newPassword.trim().equalsIgnoreCase("")) {
			responseWriter.write(SSOConstants.ChangePasswordServletConstants.PARAM_NEW_PASSWORD+"="+SSOConstants.ChangePasswordServletConstants.BLANK_FIELD_MESSAGE);
			return;
		}
		
		DataAccessManager dataAccessManager = new DataAccessManager();
		if(dataAccessManager.checkUserExists(userId)) {
			if(dataAccessManager.checkUserCredentials(userId, oldPassword)) {
				
				dataAccessManager.changePassword(userId,oldPassword,newPassword);

				try{
					if(getServletContext().getInitParameter("ChangePassword").equalsIgnoreCase("true"))
						new UserActivityManager(userId,request.getRemoteAddr(),"ChangePassword","");
				}catch(Exception e2)
				{}

				responseWriter.write(SSOConstants.ChangePasswordServletConstants.PASSWORD_CHANGED_SUCCESSFULLY_MESSAGE);
				return ;
			}
			else {
				responseWriter.write(SSOConstants.ChangePasswordServletConstants.PASSWORD_CHANGED_UNSUCCESSFUL_MESSAGE);
				return ;
			}
		}
		else {
			responseWriter.write(SSOConstants.ChangePasswordServletConstants.PASSWORD_CHANGED_USER_DOES_NOT_EXIST_MESSAGE);
			return;

		}
		
		
	}
}
