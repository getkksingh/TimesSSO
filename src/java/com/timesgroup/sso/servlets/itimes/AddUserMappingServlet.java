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

public class AddUserMappingServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(AddUserMappingServlet.class);

		String userId = request.getParameter(SSOConstants.AddUserMapping.PARAM_USERID);
		String emailId = request.getParameter(SSOConstants.AddUserMapping.PARAM_EMAILID);
		String credentialType = request.getParameter(SSOConstants.AddUserMapping.PARAM_CREDENTIALTYPE);

		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (userId == null || (userId.trim().compareTo("") == 0)  || 
				emailId ==null || (emailId.trim().compareTo("") == 0) || 
				credentialType ==null || (credentialType.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.AddUserMapping.ERROR_WITH_ADDUSERMAPPING_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.AddUserMapping.ERROR_WITH_ADDUSERMAPPING_REQUEST_PARAMETERS);
			return;
		}
		
		if(userId!=null && !SSOUtils.containsSpecialCharacter(userId,SSOConstants.USER_ID_NOT_PERMISSIBLE_CHARACTERS)){
			
			mylogger.error(SSOConstants.AddUserMapping.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.AddUserMapping.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && userId.length()>50){
			
			mylogger.error(SSOConstants.AddUserMapping.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.AddUserMapping.ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH);
			return;
		}
		
		if(emailId!=null && !emailId.matches(SSOConstants.VALID_EMAILID_PATTERN)){
			
			mylogger.error(SSOConstants.AddUserMapping.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.AddUserMapping.ERROR_MESSAGE_INVALIDEMAILID);
			return;
		}
		
		if(emailId!=null && emailId.length()>100){
			
			mylogger.error(SSOConstants.AddUserMapping.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.AddUserMapping.ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		int status = iTimesDataAccessManager.addUserMapping(userId.toLowerCase(),emailId.toLowerCase(),credentialType.toLowerCase());
		
		if(status==0)
			mylogger.debug(SSOConstants.AddUserMapping.MESSAGE_ERROR_INSERTION);
		
		else mylogger.debug(SSOConstants.AddUserMapping.MESSAGE_SUCCESSFUL_INSERTION);
		
		responseWriter.write(SSOConstants.XML_URL+"<status>" + status + "</status>");
		
		return;
	}
		

}
