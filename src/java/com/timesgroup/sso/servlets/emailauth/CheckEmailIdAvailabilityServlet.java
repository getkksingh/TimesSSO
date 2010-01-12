package com.timesgroup.sso.servlets.emailauth;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fieldvalidation.validators.FieldValidator;
import com.timesgroup.sso.constants.ErrorMessages;
import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.constants.SSOUtils;
import com.timesgroup.sso.hibernate.apis.AliasAuthDataAccessManager;

public class CheckEmailIdAvailabilityServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(InsertUserProfileServlet.class);
		String emailId = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_EMAILID);
		PrintWriter responseWriter = SSOUtils.getPrintWriter(response);
		
		boolean isValidEmailId=FieldValidator.isValidField(FieldValidator.FIELDNAME_EMAILID, 
				FieldValidator.FIELDTYPE_EMAILID, emailId, true);
		
		if(!isValidEmailId){
			
			responseWriter.write(FieldValidator.errorMsg);
			return;
		}
		
		AliasAuthDataAccessManager aliasAuthDataAccessManager=new AliasAuthDataAccessManager();
		int status = aliasAuthDataAccessManager.ifEmailIdAvailable(emailId.toLowerCase());
		
		if(status==0) mylogger.error(ErrorMessages.MESSAGE_EMAILID_EXISTS);
		
		responseWriter.write(ErrorMessages.XML_URL+"<status>"+status+"</status>");
		
		return;		
	}
}
