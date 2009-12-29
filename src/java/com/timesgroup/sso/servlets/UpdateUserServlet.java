package com.timesgroup.sso.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.DataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserRegistration;
import com.timesgroup.sso.utils.SSOUtils;



public class UpdateUserServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		String userId = request.getParameter(SSOConstants.UpdateUserServletConstants.PARAM_USER);
		String password = request.getParameter(SSOConstants.UpdateUserServletConstants.PARAM_PASSWORD);
		String forgotPasswordQuestion = request.getParameter(SSOConstants.UpdateUserServletConstants.PARAM_PASSWORD_QUESTION);
		String forgotPasswordAnswer = request.getParameter(SSOConstants.UpdateUserServletConstants.PARAM_PASSWORD_ANSWER);
		String firstName= request.getParameter(SSOConstants.UpdateUserServletConstants.PARAM_FIRST_NAME);
		String lastName = request.getParameter(SSOConstants.UpdateUserServletConstants.PARAM_LAST_NAME);
		String emailAddress = request.getParameter(SSOConstants.UpdateUserServletConstants.PARAM_EMAIL);
		if(userId!=null)
			userId=userId.toLowerCase();
		
		PrintWriter responseWriter =  null ;
        
		try {
			responseWriter = response.getWriter();
		} catch (IOException e1) {		
			e1.printStackTrace();
		}
		
		String validationmessage = checkParams (userId,password,forgotPasswordQuestion,forgotPasswordAnswer,firstName,lastName,emailAddress) ;
		
		if(!"".equals(validationmessage)) {
				responseWriter.write(validationmessage);
				return;
				}
		else 	{
				//Check if user Exists
				DataAccessManager dataAccessManager = new DataAccessManager();
				UserRegistration userData=new UserRegistration();
				userData.setUser_id(userId);
				userData.setPassword(password);
				userData.setFirst_name(firstName);
				userData.setLast_name(lastName);
				userData.setForget_password_question(forgotPasswordQuestion);
				userData.setForget_password_answer(forgotPasswordAnswer);
				userData.setEmail_id(emailAddress);
				
				int status = dataAccessManager.updateUser(userData);
				
				if(status == 0){
					responseWriter.write(SSOConstants.UpdateUserServletConstants.USER_NOT_EXISTS_MESSAGES);
					return ;
				}
				else if (status == 1){
					responseWriter.write(SSOConstants.UpdateUserServletConstants.USER_UPDATE_SUCCESS_MESSAGES);
					return ;
					}
				else {
					responseWriter.write(SSOConstants.UpdateUserServletConstants.USER_UPDATE_FAILED_MESSAGES);
					}
				} 
	}

	private String checkParams(String userId, String password,
			String forgotPasswordQuestion, String forgotPasswordAnswer,
			String firstName, String lastName, String emailAddress) {
		
		if(userId == null || password == null || forgotPasswordQuestion == null
			|| forgotPasswordAnswer == null || firstName == null || lastName == null 
			|| emailAddress == null )
			return SSOConstants.UpdateUserServletConstants.ERROR_WITH_PASSED_REQUEST_PARAMETERS ;
		
		String paramMessage = "";
		
		if(userId.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.UpdateUserServletConstants.PARAM_USER+"="+SSOConstants.UpdateUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(password.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.UpdateUserServletConstants.PARAM_PASSWORD+"="+SSOConstants.UpdateUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(forgotPasswordQuestion.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.UpdateUserServletConstants.PARAM_PASSWORD_QUESTION+"="+SSOConstants.UpdateUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(forgotPasswordAnswer.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.UpdateUserServletConstants.PARAM_PASSWORD_ANSWER+"="+SSOConstants.UpdateUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(firstName.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.UpdateUserServletConstants.PARAM_FIRST_NAME+"="+SSOConstants.UpdateUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(lastName.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.UpdateUserServletConstants.PARAM_LAST_NAME+"="+SSOConstants.UpdateUserServletConstants.BLANK_FIELD_MESSAGE;
		
		if(emailAddress.trim().equalsIgnoreCase(""))
			paramMessage += SSOConstants.UpdateUserServletConstants.PARAM_EMAIL+"="+SSOConstants.UpdateUserServletConstants.BLANK_FIELD_MESSAGE;
				
		if(SSOUtils.containsSpecialCharacter(userId,SSOConstants.UpdateUserServletConstants.USER_ID_NOT_PERMISSIBLE_CHARACTERS))
			paramMessage += SSOConstants.UpdateUserServletConstants.PARAM_USER+"="+SSOConstants.UpdateUserServletConstants.INVALIDE_CHAR_MESSAGE;
		
		if(!SSOUtils.isValidEmailAddress(emailAddress))
			paramMessage += SSOConstants.UpdateUserServletConstants.PARAM_EMAIL+"="+SSOConstants.UpdateUserServletConstants.INVALIDE_EMAIL_MESSAGE;
		
		paramMessage = paramMessage.trim() ;
		
		return paramMessage.trim();
	}
}
