package com.timesgroup.sso.servlets.emailauth;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fieldvalidation.validators.FieldValidator;
import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.utils.SSOUtils;


public class CheckPasswordStrengthServlet extends HttpServlet {

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(CheckPasswordStrengthServlet.class);
		String password = request.getParameter(SSOConstants.CheckPasswordStrength.PARAM_PASSWORD);
		
		PrintWriter responseWriter = SSOUtils.getPrintWriter(response);
		
		if(!FieldValidator.isValidField(FieldValidator.FIELDNAME_PASSWORD,FieldValidator.FIELDTYPE_PASSWORD, password, true)){
			
			responseWriter.write(FieldValidator.errorMsg);
			return;
		}
		
		responseWriter.write(SSOConstants.XML_URL+"<string>"+SSOUtils.getPasswordStrength()+"</string>");
		
	}
}
