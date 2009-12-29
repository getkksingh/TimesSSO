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
import com.timesgroup.sso.utils.UserActivityManager;

public class GetSecQstnAnsWithPassword extends HttpServlet{
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
			String userId = request.getParameter(SSOConstants.GetSecQstnAnsWithPasswordConstants.PARAM_USER);
			if(userId!=null)
				userId=userId.toLowerCase();
		
			PrintWriter responseWriter =  null ;
	        
			try {
				responseWriter = response.getWriter();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}

			if(userId == null) {
				responseWriter.write(SSOConstants.GetSecQstnAnsWithPasswordConstants.USER_DOES_NOT_EXIST_MESSAGE);
				return ;
			}
			
		    DataAccessManager accessManager = new DataAccessManager();
		  
		    UserRegistration userData = accessManager.getUserData(userId);
		    if(userData != null) {
				//UserActivity Logging
				try{
					if(getServletContext().getInitParameter("ForgotPassword").equalsIgnoreCase("true"))
						new UserActivityManager(userId,request.getRemoteAddr(),"ForgotPassword","");
				}catch(Exception e2)
				{}
				
		    	String strXML=String.format(
		    	SSOConstants.GetSecQstnAnsWithPasswordConstants.SEC_QSTN_ANS_XML_FORMAT,
		    		userData.getUser_id(),
		    		(userData.getPassword()==null)?"":userData.getPassword(),
		    			(userData.getForget_password_question()==null)?"":userData.getForget_password_question(),
		    			(userData.getForget_password_answer()==null)?"":userData.getForget_password_answer());
				responseWriter.write(strXML);
				return ; 
		    }
		    else 
		    	responseWriter.write(SSOConstants.GetSecQstnAnsWithPasswordConstants.USER_DOES_NOT_EXIST_MESSAGE);
	}
}