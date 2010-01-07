package com.timesgroup.sso.servlets.itimes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.ITimesDataAccessManager;

public class UpdateInActiveUserServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(AddUserMappingServlet.class);

		String emailId = request.getParameter(SSOConstants.UpdateInactiveUser.PARAM_EMAILID);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (emailId ==null || (emailId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.UpdateInactiveUser.ERROR_WITH_UPDATEINACTIVEUSER_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.UpdateInactiveUser.ERROR_WITH_UPDATEINACTIVEUSER_REQUEST_PARAMETERS);
			return;
		}
		
		if(emailId!=null && !emailId.matches(SSOConstants.VALID_EMAILID_PATTERN)){
			
			mylogger.error(SSOConstants.UpdateInactiveUser.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.UpdateInactiveUser.ERROR_MESSAGE_INVALIDEMAILID);
			return;
		}
		
		if(emailId!=null && emailId.length()>100){
			
			mylogger.error(SSOConstants.UpdateInactiveUser.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.UpdateInactiveUser.ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		int status = iTimesDataAccessManager.updateInactiveUser(emailId.toLowerCase());
		
		if(status==0)
			mylogger.debug(SSOConstants.UpdateInactiveUser.MESSAGE_ERROR_INSERTION);
		
		else mylogger.debug(SSOConstants.UpdateInactiveUser.MESSAGE_SUCCESSFUL_INSERTION);
		
		responseWriter.write(SSOConstants.XML_URL+"<status>" + status + "</status>");
		
		return;
	}	

}
