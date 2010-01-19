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

public class GetActivationStatusServlet extends HttpServlet{
	
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(GetActivationStatusServlet.class);
		String userId = request.getParameter(SSOConstants.GetActivationStatus.PARAM_USERID);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (userId==null || (userId.trim().compareTo("")==0)) {

			mylogger.error(SSOConstants.GetActivationStatus.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.GetActivationStatus.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && SSOUtils.containsSpecialCharacter(userId,SSOConstants.USER_ID_NOT_PERMISSIBLE_CHARACTERS)){
			
			mylogger.error(SSOConstants.GetActivationStatus.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.GetActivationStatus.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && userId.length()>50){
			
			mylogger.error(SSOConstants.GetActivationStatus.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.GetActivationStatus.ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		int status = iTimesDataAccessManager.getActivationStatus(userId.toLowerCase());
	
		responseWriter.print(SSOConstants.XML_URL+"<string>"+status+"</string>");
		return;
		
		
	}

}
