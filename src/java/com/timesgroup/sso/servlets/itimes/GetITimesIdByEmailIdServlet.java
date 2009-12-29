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

public class GetITimesIdByEmailIdServlet extends HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger
				.getLogger(GetITimesIdByEmailIdServlet.class);

		String emailId = request.getParameter(SSOConstants.GetITimesIdByEmailIdConstants.PARAM_EMAIL);

		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (emailId == null || (emailId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.GetITimesIdByEmailIdConstants.ERROR_WITH_ITIMESID_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.GetITimesIdByEmailIdConstants.ERROR_MESSAGE);
			return;
		}

		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		String itimesId = iTimesDataAccessManager.getITimesIdByEmailId(emailId);

		if (itimesId.compareTo(SSOConstants.GetITimesIdByEmailIdConstants.RECORD_NOT_FOUND) == 0) {
			mylogger.info(SSOConstants.GetITimesIdByEmailIdConstants.RECORD_NOT_FOUND);
			responseWriter.write(SSOConstants.XML_URL+"<itimesid></itimesid>");
			return;
		}

		mylogger.debug(itimesId);
		responseWriter.write(SSOConstants.XML_URL+"<itimesid>" + itimesId + "</itimesid>");

		return;

	}
}
