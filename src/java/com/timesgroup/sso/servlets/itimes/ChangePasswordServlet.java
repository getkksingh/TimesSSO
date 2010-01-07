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
import com.timesgroup.sso.utils.CryptoUtility;

public class ChangePasswordServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(ChangePasswordServlet.class);
		String userId = request.getParameter(SSOConstants.ChangePassword.PARAM_USERID);
		String oldPassword = request.getParameter(SSOConstants.ChangePassword.PARAM_OLDPASSWORD);
		String newPassword = request.getParameter(SSOConstants.ChangePassword.PARAM_NEWPASSWORD);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (userId == null || (userId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.ChangePassword.MESSAGE_NOVALUE_USERID);
			responseWriter.write(SSOConstants.ChangePassword.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && !userId.matches(SSOConstants.VALID_USERID_PATTERN)){
			
			mylogger.error(SSOConstants.ChangePassword.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.ChangePassword.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && userId.length()>50){
			
			mylogger.error(SSOConstants.ChangePassword.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.ChangePassword.ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH);
			return;
		}
		
		oldPassword=(new CryptoUtility()).performEncrypt(oldPassword.trim());
		newPassword=(new CryptoUtility()).performEncrypt(newPassword.trim());
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		boolean status = iTimesDataAccessManager.changePassword(userId.toLowerCase(), oldPassword, newPassword);
		
		
		if(status)
			mylogger.debug(SSOConstants.ChangePassword.MESSAGE_PASSWORD_CHANGED);
		else mylogger.debug(SSOConstants.ChangePassword.ERROR_CHANGEPASSWORD);
		
		responseWriter.write(SSOConstants.XML_URL+"<status>" + status + "</status>");
		
		return;
	}	

}
