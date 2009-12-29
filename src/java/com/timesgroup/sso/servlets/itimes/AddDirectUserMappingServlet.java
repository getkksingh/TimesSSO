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

public class AddDirectUserMappingServlet extends HttpServlet{
	
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(AddDirectUserMappingServlet.class);

		String userId = request.getParameter(SSOConstants.AddDirectUserMapping.PARAM_USERID);
		String emailId = request.getParameter(SSOConstants.AddDirectUserMapping.PARAM_EMAILID);
		String itimesId = request.getParameter(SSOConstants.AddDirectUserMapping.PARAM_ITIMESID);
		String credentialType = request.getParameter(SSOConstants.AddDirectUserMapping.PARAM_CREDENTIALTYPE);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (userId==null || (emailId.trim().compareTo("") == 0) || 
				emailId ==null || (emailId.trim().compareTo("") == 0) ||
				itimesId ==null || (itimesId.trim().compareTo("") == 0) ||
				credentialType ==null || (credentialType.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.AddDirectUserMapping.ERROR_WITH_ADDDIRECTUSERMAPPING_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.AddDirectUserMapping.ERROR_WITH_ADDDIRECTUSERMAPPING_REQUEST_PARAMETERS);
			return;
		}
		
		if(userId!=null && !userId.matches(SSOConstants.VALID_USERID_PATTERN)){
			
			mylogger.error(SSOConstants.AddDirectUserMapping.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.AddDirectUserMapping.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && userId.length()>50){
			
			mylogger.error(SSOConstants.AddDirectUserMapping.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.AddDirectUserMapping.ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH);
			return;
		}
		
		if (emailId ==null || (emailId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.AddDirectUserMapping.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.AddDirectUserMapping.ERROR_MESSAGE_INVALIDEMAILID);
			return;
		}
		
		if(emailId!=null && !emailId.matches(SSOConstants.VALID_EMAILID_PATTERN)){
			
			mylogger.error(SSOConstants.AddDirectUserMapping.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.AddDirectUserMapping.ERROR_MESSAGE_INVALIDEMAILID);
			return;
		}
		
		if(emailId!=null && emailId.length()>100){
			
			mylogger.error(SSOConstants.AddDirectUserMapping.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.AddDirectUserMapping.ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH);
			return;
		}
		
		/*USERID AND ITIMESID SHOULD BE SAME*/
		if(!userId.toLowerCase().equals(itimesId.toLowerCase())){
			 mylogger.error(SSOConstants.AddDirectUserMapping.MESSAGE_ITIMESID_EMAILID_DONOTMATCH);
			 responseWriter.write(SSOConstants.AddDirectUserMapping.MESSAGE_ITIMESID_EMAILID_DONOTMATCH);
			 return;
		}	 
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		int status = iTimesDataAccessManager.addDirectUserMapping(userId.toLowerCase(),emailId.toLowerCase(),itimesId.toLowerCase(),credentialType.toLowerCase());
		
		if(status==1)
			mylogger.debug(SSOConstants.AddDirectUserMapping.MESSAGE_ERROR_INSERTION);
		
		else mylogger.debug(SSOConstants.AddDirectUserMapping.MESSAGE_SUCCESSFUL_INSERTION);
		
		responseWriter.write(SSOConstants.XML_URL+"<status>" + status + "</status>");
		return;
	}	

}
