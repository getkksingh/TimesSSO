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

public class GetUserIdByEMailIdServlet extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger
				.getLogger(GetUserIdByEMailIdServlet.class);

		String emailId = request.getParameter(SSOConstants.GetUserIdByEmailConstants.PARAM_EMAIL);

		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (emailId == null || (emailId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.GetUserIdByEmailConstants.ERROR_WITH_USERID_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.GetUserIdByEmailConstants.ERROR_MESSAGE);
			return;
		}

		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		String userId = iTimesDataAccessManager.getUserIdByEmail(emailId);

		if (userId.compareTo(SSOConstants.GetUserIdByEmailConstants.RECORD_NOT_FOUND) == 0) {
			mylogger.info(SSOConstants.GetUserIdByEmailConstants.RECORD_NOT_FOUND);
			responseWriter.write(SSOConstants.XML_URL+"<userid></userid>");
			return;
		}

		mylogger.debug(userId);
		responseWriter.write(SSOConstants.XML_URL+"<userid>" + userId + "</userid>");

		return;

	}
}
