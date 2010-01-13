package com.timesgroup.sso.servlets.emailauth;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fieldvalidation.validators.FieldValidator;
import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.AliasAuthDataAccessManager;
import com.timesgroup.sso.utils.SSOUtils;

public class CheckPasswordStrengthUserServlet extends HttpServlet {

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(CheckPasswordStrengthUserServlet.class);
		String emailId = request.getParameter(SSOConstants.CheckPasswordStrengthUser.PARAM_EMAILID);
		
		PrintWriter responseWriter = SSOUtils.getPrintWriter(response);
		
		boolean isValidEmailId=FieldValidator.isValidField(FieldValidator.FIELDNAME_EMAILID, 
				FieldValidator.FIELDTYPE_EMAILID, emailId, true);
		
		if(!isValidEmailId){
			
			responseWriter.write(FieldValidator.errorMsg);
			return;
		}
		
		AliasAuthDataAccessManager aliasAuthDataAccessManager=new AliasAuthDataAccessManager();
		//String userProfile = aliasAuthDataAccessManager.getPasswordByEmailId(emailId.toLowerCase());
		
		responseWriter.write(SSOConstants.XML_URL+"<string>"+SSOUtils.getPasswordStrength()+"</string>");
	}
}
