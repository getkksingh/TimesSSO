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

public class FetchPasswordServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(FetchPasswordServlet.class);
		String userId = request.getParameter(SSOConstants.FetchPassword.PARAM_USERID);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (userId == null || (userId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.FetchPassword.MESSAGE_NOVALUE_USERID);
			responseWriter.write(SSOConstants.FetchPassword.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && !userId.matches(SSOConstants.VALID_USERID_PATTERN)){
			
			mylogger.error(SSOConstants.FetchPassword.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.FetchPassword.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && userId.length()>50){
			
			mylogger.error(SSOConstants.FetchPassword.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.FetchPassword.ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		String password = iTimesDataAccessManager.fetchPassword(userId.toLowerCase());
		
		if(!password.equals("false")) password=(new CryptoUtility()).performDecrypt(password);
		
		responseWriter.write(SSOConstants.XML_URL+"<string>"+password+"</string>");
		
	}	

}
