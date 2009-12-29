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

public class AddUserServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		String userId = request.getParameter(SSOConstants.AddUserServletConstants.PARAM_USER);
		String password = request.getParameter(SSOConstants.AddUserServletConstants.PARAM_PASSWORD);
		String forgotPasswordQuestion = request.getParameter(SSOConstants.AddUserServletConstants.PARAM_PASSWORD_QUESTION);
		String forgotPasswordAnswer = request.getParameter(SSOConstants.AddUserServletConstants.PARAM_PASSWORD_ANSWER);
		String firstName= request.getParameter(SSOConstants.AddUserServletConstants.PARAM_FIRST_NAME);
		String lastName = request.getParameter(SSOConstants.AddUserServletConstants.PARAM_LAST_NAME);
		String emailAddress = request.getParameter(SSOConstants.AddUserServletConstants.PARAM_EMAIL);
		String rootSite = request.getParameter(SSOConstants.AddUserServletConstants.PARAM_ROOT_SITE);
		if(userId!=null)
			userId=userId.toLowerCase();
		
		PrintWriter responseWriter =  null ;
        
		try {
			responseWriter = response.getWriter();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		String validationmessage = checkParams (userId,password,forgotPasswordQuestion,forgotPasswordAnswer,firstName,lastName,emailAddress,rootSite) ;
		
		if(!"".equals(validationmessage)) {
				responseWriter.write(validationmessage);
				return;
				}
		else 	{
				//Check if user Exists
				DataAccessManager dataAccessManager = new DataAccessManager();
				if(dataAccessManager.checkUserExists(userId)){
					responseWriter.write(SSOConstants.AddUserServletConstants.USER_EXISTS_MESSAGES);
					return ;
				}
				else {
					dataAccessManager.insertNewUserDetails(userId,password,forgotPasswordQuestion,forgotPasswordAnswer,firstName,lastName,emailAddress,rootSite);
					responseWriter.write(SSOConstants.AddUserServletConstants.USER_SAVED_MESSAGES);
					return ;
						
					}
				} 
	}

	private String checkParams(String userId, String password,
			String forgotPasswordQuestion, String forgotPasswordAnswer,
			String firstName, String lastName, String emailAddress ,String rootSite) {
		
		if(userId == null || password == null || forgotPasswordQuestion == null
			|| forgotPasswordAnswer == null || firstName == null || lastName == null 
			|| emailAddress == null || rootSite == null	)
			return SSOConstants.AddUserServletConstants.ERROR_WITH_PASSED_REQUEST_PARAMETERS ;
		
		String paramMessage = "";
		
		if(userId.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.AddUserServletConstants.PARAM_USER+"="+SSOConstants.AddUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(password.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.AddUserServletConstants.PARAM_PASSWORD+"="+SSOConstants.AddUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(forgotPasswordQuestion.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.AddUserServletConstants.PARAM_PASSWORD_QUESTION+"="+SSOConstants.AddUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(forgotPasswordAnswer.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.AddUserServletConstants.PARAM_PASSWORD_ANSWER+"="+SSOConstants.AddUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(firstName.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.AddUserServletConstants.PARAM_FIRST_NAME+"="+SSOConstants.AddUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(lastName.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.AddUserServletConstants.PARAM_LAST_NAME+"="+SSOConstants.AddUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(emailAddress.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.AddUserServletConstants.PARAM_EMAIL+"="+SSOConstants.AddUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(rootSite.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.AddUserServletConstants.PARAM_ROOT_SITE+"="+SSOConstants.AddUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(SSOUtils.containsSpecialCharacter(userId,SSOConstants.AddUserServletConstants.USER_ID_NOT_PERMISSIBLE_CHARACTERS))
			paramMessage += SSOConstants.AddUserServletConstants.PARAM_USER+"="+SSOConstants.AddUserServletConstants.INVALIDE_CHAR_MESSAGE;
		
		if(!SSOUtils.isValidEmailAddress(emailAddress))
			paramMessage += SSOConstants.AddUserServletConstants.PARAM_EMAIL+"="+SSOConstants.AddUserServletConstants.INVALIDE_EMAIL_MESSAGE;
		
		paramMessage = paramMessage.trim() ;
		
		return paramMessage.trim();
	}
}
