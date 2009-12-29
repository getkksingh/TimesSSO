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

public class ForgotPasswordServlet extends HttpServlet{
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		String userId = request.getParameter(SSOConstants.ForgotPasswordServletConstants.PARAMUSER) ;
		String forgotPasswordQuestion = request.getParameter(SSOConstants.ForgotPasswordServletConstants.PARAM_FORGOTPASSWORD_QUESTION) ;
		String forgotPasswordAnswer = request.getParameter(SSOConstants.ForgotPasswordServletConstants.PARAMP_FORGOT_PASSWORD_ANSWER) ;
		if(userId!=null)
			userId=userId.toLowerCase();
		
		PrintWriter responseWriter =  null ;
        
		try {
			responseWriter = response.getWriter();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		if(userId == null || forgotPasswordAnswer == null || forgotPasswordAnswer == null) {
			responseWriter.write(SSOConstants.ForgotPasswordServletConstants.ERROR_WITH_PASSED_REQUEST_PARAMETERS);
			return;
		}
		if(userId.trim().equalsIgnoreCase("")){
			responseWriter.write(SSOConstants.ForgotPasswordServletConstants.WRONG_ANSWER_TO_FORGOT_PASSWORD_QUESTION);
			return;
		}
		
		String validationMessage= "";
		if(forgotPasswordQuestion.trim().equalsIgnoreCase(""))
			validationMessage += SSOConstants.ForgotPasswordServletConstants.PASSWORD_QUESTION_FIELD_BLANK_MESSAGE;
		if(forgotPasswordAnswer.trim().equalsIgnoreCase(""))
			validationMessage += SSOConstants.ForgotPasswordServletConstants.PASSWORD_ANSWER_FIELD_BLANK_MESSAGE;
		if(forgotPasswordQuestion.trim().equalsIgnoreCase("") || forgotPasswordAnswer.trim().equalsIgnoreCase(""))
		{
		   responseWriter.write(validationMessage);	
		   return ;
		}
		
		DataAccessManager accessManager = new DataAccessManager();
		String password = accessManager.recoverForgotPassword(userId,forgotPasswordQuestion,forgotPasswordAnswer);
		if(password == null) {
			responseWriter.write(SSOConstants.ForgotPasswordServletConstants.WRONG_ANSWER_TO_FORGOT_PASSWORD_QUESTION);
			return ;
		}
		else
		{
			//UserActivity Logging
			try{
				if(getServletContext().getInitParameter("ForgotPassword").equalsIgnoreCase("true"))
					new UserActivityManager(userId,request.getRemoteAddr(),"ForgotPassword","");
			}catch(Exception e2)
			{}
			responseWriter.write(String.format(SSOConstants.ForgotPasswordServletConstants.CORRECT_ANSWER_TO_FORGOT_PASSWORD_QUESTION, password));
		}
	}
}
