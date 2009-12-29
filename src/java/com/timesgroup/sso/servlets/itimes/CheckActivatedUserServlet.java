package com.timesgroup.sso.servlets.itimes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.ITimesDataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserRegistration;

public class CheckActivatedUserServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(CheckActivatedUserServlet.class);
		String emailId = request.getParameter(SSOConstants.CheckActivatedUser.PARAM_EMAILID);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (emailId ==null || (emailId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.CheckActivatedUser.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.XML_URL+"<status>2</status>");
			return;
		}
		
		if(emailId!=null && !emailId.matches(SSOConstants.VALID_EMAILID_PATTERN)){
			
			mylogger.error(SSOConstants.CheckActivatedUser.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.XML_URL+"<status>2</status>");
			return;
		}
		
		if(emailId!=null && emailId.length()>100){
			
			mylogger.error(SSOConstants.CheckActivatedUser.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.XML_URL+"<status>2</status>");
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		int status = iTimesDataAccessManager.checkActivatedUser(emailId.toLowerCase());
		
		responseWriter.write(SSOConstants.XML_URL+"<status>" + status + "</status>");
		
		return;
		
	}

}
