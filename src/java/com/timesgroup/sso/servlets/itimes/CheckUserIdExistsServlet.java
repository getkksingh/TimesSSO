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

public class CheckUserIdExistsServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(CheckUserIdExistsServlet.class);
		String userId = request.getParameter(SSOConstants.UpdateITimesId.PARAM_USERID);

		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		if (userId == null || (userId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.CheckUserIdExists.ERROR_WITH_CHECKUSERIDEXISTS_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.CheckUserIdExists.ERROR_WITH_CHECKUSERIDEXISTS_REQUEST_PARAMETERS);
			return;
		}
		
		if(userId!=null && !userId.matches(SSOConstants.VALID_USERID_PATTERN)){
			
			mylogger.error(SSOConstants.CheckUserIdExists.INVALID_USERID);
			responseWriter.write(SSOConstants.CheckUserIdExists.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		boolean ifUserIdExists = iTimesDataAccessManager.ifUserIdExists(userId.toLowerCase());

		mylogger.debug("ifUserIdExists=="+ifUserIdExists+" for userId="+userId.toLowerCase());
		
		int status=0;
		
		if(ifUserIdExists){
			status=1;
			responseWriter.write(SSOConstants.XML_URL+"<status>" + status + "</status>");
		}else{
			mylogger.info(SSOConstants.CheckUserIdExists.RECORD_NOT_FOUND);
			responseWriter.write(SSOConstants.CheckUserIdExists.ERROR_MESSAGE);
		}
		
		
		return;
			
	}	

}
