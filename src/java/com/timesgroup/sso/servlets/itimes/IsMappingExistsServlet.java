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

public class IsMappingExistsServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger
				.getLogger(IsMappingExistsServlet.class);
        
		String userId = request.getParameter(SSOConstants.IsMappingExists.PARAM_USERID);
		String emailId = request.getParameter(SSOConstants.IsMappingExists.PARAM_EMAILID);

		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (userId == null || (userId.trim().compareTo("") == 0)  || 
				emailId ==null || (emailId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.IsMappingExists.ERROR_WITH_ISMAPPINGEXISTS_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.IsMappingExists.ERROR_WITH_ISMAPPINGEXISTS_REQUEST_PARAMETERS);
			return;
		}

		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		boolean isUserExists = iTimesDataAccessManager.isUserExistsInUserMapping(userId.toLowerCase(),emailId);

		mylogger.debug(isUserExists);
		responseWriter.write(SSOConstants.XML_URL+"<status>" + isUserExists + "</status>");

		return;

	}

}
