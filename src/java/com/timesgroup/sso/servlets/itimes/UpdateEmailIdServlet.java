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

public class UpdateEmailIdServlet  extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(UpdateEmailIdServlet.class);
		String userId = request.getParameter(SSOConstants.UpdateEmailId.PARAM_USERID);
		String newEmailId = request.getParameter(SSOConstants.UpdateEmailId.PARAM_NEW_EMAILID);
		String oldEmailId = request.getParameter(SSOConstants.UpdateEmailId.PARAM_OLD_EMAILID);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (userId==null || (userId.trim().compareTo("")==0) 
				||  newEmailId ==null || (newEmailId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.UpdateEmailId.ERROR_WITH_UPDATEEMAILID_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.UpdateEmailId.ERROR_WITH_UPDATEEMAILID_REQUEST_PARAMETERS);
			return;
		}
		
		if(userId!=null && SSOUtils.containsSpecialCharacter(userId,SSOConstants.USER_ID_NOT_PERMISSIBLE_CHARACTERS)){
			
			mylogger.error(SSOConstants.UpdateEmailId.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.UpdateEmailId.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && userId.length()>50){
			
			mylogger.error(SSOConstants.UpdateEmailId.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.UpdateEmailId.ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH);
			return;
		}
		
		if(newEmailId!=null && !newEmailId.matches(SSOConstants.VALID_EMAILID_PATTERN)){
			
			mylogger.error(SSOConstants.UpdateEmailId.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.UpdateEmailId.ERROR_MESSAGE_INVALIDEMAILID);
			return;
		}
		
		if(newEmailId!=null && newEmailId.length()>100){
			
			mylogger.error(SSOConstants.UpdateEmailId.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.UpdateEmailId.ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH);
			return;
		}
		
		if(oldEmailId!=null && !oldEmailId.matches(SSOConstants.VALID_EMAILID_PATTERN)){
			
			mylogger.error(SSOConstants.UpdateEmailId.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.UpdateEmailId.ERROR_MESSAGE_INVALIDEMAILID);
			return;
		}
		
		if(oldEmailId!=null && oldEmailId.length()>100){
			
			mylogger.error(SSOConstants.UpdateEmailId.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.UpdateEmailId.ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH);
			return;
		}
		
		if(newEmailId.toLowerCase().equals(oldEmailId.toLowerCase())){
			
			mylogger.error(SSOConstants.UpdateEmailId.MESSAGE_EMAILIDS_MATCH);
			responseWriter.write(SSOConstants.UpdateEmailId.ERROR_MESSAGE_EMAILIDSMATCH);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		boolean status = iTimesDataAccessManager.updateEmailId(userId.toLowerCase(),oldEmailId.toLowerCase(),newEmailId.toLowerCase());
	
		responseWriter.print(SSOConstants.XML_URL+"<status>"+status+"</status>");
		return;
	}	

}
