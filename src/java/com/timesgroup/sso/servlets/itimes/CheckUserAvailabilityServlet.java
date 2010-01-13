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
import com.timesgroup.sso.utils.SSOUtils;

public class CheckUserAvailabilityServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(CheckUserAvailabilityServlet.class);
		String userId = request.getParameter(SSOConstants.CheckUserAvailability.PARAM_USERID);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (userId == null || (userId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.CheckUserAvailability.MESSAGE_NOVALUE_USERID);
			responseWriter.write(SSOConstants.CheckUserAvailability.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && !SSOUtils.containsSpecialCharacter(userId,SSOConstants.USER_ID_NOT_PERMISSIBLE_CHARACTERS)){
			
			mylogger.error(SSOConstants.CheckUserAvailability.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.CheckUserAvailability.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && userId.length()>50){
			
			mylogger.error(SSOConstants.CheckUserAvailability.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.CheckUserAvailability.ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		boolean isUserIdAlreadyExists = iTimesDataAccessManager.isUserIdAlreadyExists(userId.toLowerCase());
		
		if(isUserIdAlreadyExists)			
			mylogger.debug(SSOConstants.CheckUserAvailability.MESSAGE_ERROR_USERID_EXIST);
		
		else mylogger.debug(SSOConstants.CheckUserAvailability.MESSAGE_USERID_AVAILABLE);
		
		responseWriter.write(SSOConstants.XML_URL+"<status>" + (!isUserIdAlreadyExists) + "</status>");
		
		return;
		
	}
}
