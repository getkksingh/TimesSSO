package com.timesgroup.sso.servlets.itimes;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.ITimesDataAccessManager;

public class GetAvailableUserIdsServlet extends HttpServlet {

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(GetAvailableUserIdsServlet.class);
		String userId = request.getParameter(SSOConstants.GetAvailableUserIds.PARAM_USERID);
		String reqdnos = request.getParameter(SSOConstants.GetAvailableUserIds.PARAM_REQNUMBER);
		String firstName = request.getParameter(SSOConstants.GetAvailableUserIds.PARAM_FIRSTNAME);
		String lastName = request.getParameter(SSOConstants.GetAvailableUserIds.PARAM_LASTNAME);
		String dateOfBirth = request.getParameter(SSOConstants.GetAvailableUserIds.PARAM_DOB);

		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (userId == null || (userId.trim().compareTo("") == 0)
				|| reqdnos == null || (reqdnos.trim().compareTo("") == 0)
				|| firstName == null || (firstName.trim().compareTo("") == 0)
				|| lastName == null || (lastName.trim().compareTo("") == 0)
				|| dateOfBirth == null
				|| (dateOfBirth.trim().compareTo("") == 0)) {

			mylogger
					.error(SSOConstants.GetAvailableUserIds.ERROR_WITH_GETAVAILABLEUSERIDS_REQUEST_PARAMETERS);
			responseWriter
					.write(SSOConstants.GetAvailableUserIds.ERROR_WITH_GETAVAILABLEUSERIDS_REQUEST_PARAMETERS);
			return;
		}

		if (userId != null
				&& !userId.matches(SSOConstants.VALID_USERID_PATTERN)) {

			mylogger
					.error(SSOConstants.GetAvailableUserIds.MESSAGE_INVALID_USERID);
			responseWriter
					.write(SSOConstants.GetAvailableUserIds.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}

		if (userId != null && userId.length() > 50) {

			mylogger
					.error(SSOConstants.GetAvailableUserIds.MESSAGE_INVALID_USERID);
			responseWriter
					.write(SSOConstants.GetAvailableUserIds.ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH);
			return;
		}

		if (reqdnos != null
				&& !reqdnos.matches(SSOConstants.VALID_INTEGER_PATTERN)) {

			mylogger
					.error(SSOConstants.GetAvailableUserIds.MESSAGE_INVALID_REQNUMBER);
			responseWriter
					.write(SSOConstants.GetAvailableUserIds.ERROR_MESSAGE_INVALID_REQNUMBER);
			return;
		}

		if (firstName != null
				&& !firstName.matches(SSOConstants.VALID_USERID_PATTERN)) {

			mylogger
					.error(SSOConstants.GetAvailableUserIds.MESSAGE_INVALID_FIRSTNAME);
			responseWriter
					.write(SSOConstants.GetAvailableUserIds.ERROR_MESSAGE_INVALIDFIRSTNAME);
			return;
		}

		if (firstName != null && firstName.length() > 50) {

			mylogger
					.error(SSOConstants.GetAvailableUserIds.MESSAGE_INVALID_FIRSTNAME);
			responseWriter
					.write(SSOConstants.GetAvailableUserIds.ERROR_MESSAGE_INVALIDFIRSTNAME_INCORRECTLENGTH);
			return;
		}

		if (lastName != null
				&& !lastName.matches(SSOConstants.VALID_USERID_PATTERN)) {

			mylogger
					.error(SSOConstants.GetAvailableUserIds.MESSAGE_INVALID_LASTNAME);
			responseWriter
					.write(SSOConstants.GetAvailableUserIds.ERROR_MESSAGE_INVALIDLASTNAME);
			return;
		}

		if (lastName != null && lastName.length() > 50) {

			mylogger
					.error(SSOConstants.GetAvailableUserIds.MESSAGE_INVALID_LASTNAME);
			responseWriter
					.write(SSOConstants.GetAvailableUserIds.ERROR_MESSAGE_INVALIDLASTNAME_INCORRECTLENGTH);
			return;
		}

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date parsedDate = null;
		try {
			parsedDate = df.parse(dateOfBirth);
			mylogger.debug("DATE =" + df.format(parsedDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (parsedDate == null) {

			mylogger.error(SSOConstants.UpdateUserProfile.MESSAGE_INVALID_DOB);
			responseWriter
					.write(SSOConstants.UpdateUserProfile.ERROR_MESSAGE_INVALIDDOB);
			return;
		}

		int requestNumber = new Integer(reqdnos);
		firstName = firstName.trim();
		lastName = lastName.trim();

		if (requestNumber < 0 || requestNumber > 12)
			requestNumber = 4;

		String[] useroptions = new String[12];
		Calendar cal = Calendar.getInstance();
		cal.setTime(parsedDate);
		
		useroptions[0] = firstName + lastName;
		useroptions[1] = lastName + firstName;
		useroptions[2] = firstName + "." + lastName;
		useroptions[3] = lastName + "." + firstName;
		useroptions[4] = firstName + "_" + lastName;
		useroptions[5] = lastName + "_" + firstName;
		useroptions[6] = firstName + lastName + cal.get(Calendar.DAY_OF_MONTH);
		useroptions[7] = firstName + cal.get(Calendar.YEAR);
		useroptions[8] = firstName + cal.get(Calendar.DAY_OF_MONTH)	+ cal.get(Calendar.MONTH);
		useroptions[9] = userId + cal.get(Calendar.DAY_OF_YEAR);
		useroptions[10] = userId + "." + cal.get(Calendar.DAY_OF_MONTH);
		useroptions[11] = userId + "_" + cal.get(Calendar.DAY_OF_YEAR);

		String xmlStr = "<USEROPTIONS>";

		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		boolean isUserIdAlreadyExists = false;
		int count = 0;

		for (int i = 0; i < 12; i++) {

			isUserIdAlreadyExists = iTimesDataAccessManager
					.isUserIdAlreadyExists(userId.toLowerCase());

			if (!isUserIdAlreadyExists) {
				xmlStr = xmlStr + "<USER>" + useroptions[i] + "</USER>";
				count++;
			}
			if (count >= requestNumber)
				break;
		}
		xmlStr = xmlStr + "</USEROPTIONS>";

		responseWriter.write(SSOConstants.XML_URL + xmlStr);

		return;

	}

}
