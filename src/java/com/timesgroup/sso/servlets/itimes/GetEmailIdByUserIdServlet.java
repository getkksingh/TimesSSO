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

public class GetEmailIdByUserIdServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger
				.getLogger(GetEmailIdByUserIdServlet.class);
        
		String userId = request.getParameter(SSOConstants.GetEmailIdByUserIdConstants.PARAM_USERID);

		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (userId == null || (userId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.GetEmailIdByUserIdConstants.ERROR_WITH_EMAILID_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.GetEmailIdByUserIdConstants.ERROR_MESSAGE);
			return;
		}

		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		String emailId = iTimesDataAccessManager.getEmailIdByUserId(userId.toLowerCase());

		if (emailId.compareTo(SSOConstants.GetEmailIdByUserIdConstants.RECORD_NOT_FOUND) == 0) {
			mylogger.info(SSOConstants.GetEmailIdByUserIdConstants.RECORD_NOT_FOUND);
			responseWriter.write(SSOConstants.XML_URL+"<Emailid></Emailid>");
			return;
		}
		
		if (emailId.compareTo(SSOConstants.GetEmailIdByUserIdConstants.MORETHANONEEMAILIDS_ERROR_MESSAGE) == 0) {
			mylogger.info(SSOConstants.GetEmailIdByUserIdConstants.MORETHANONEEMAILIDS_ERROR_MESSAGE);
			responseWriter.write(SSOConstants.GetEmailIdByUserIdConstants.MORETHANONEEMAILIDS_ERROR_MESSAGE);
			return;
		}

		mylogger.debug(emailId);
		responseWriter.write(SSOConstants.XML_URL+"<Emailid>" + emailId + "</Emailid>");

		return;

	}

}
