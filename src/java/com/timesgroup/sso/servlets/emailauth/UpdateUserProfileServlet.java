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
import com.timesgroup.sso.hibernate.apis.AliasAuthDataAccessManager;
import com.timesgroup.sso.utils.SSOUtils;

public class UpdateUserProfileServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(InsertUserProfileServlet.class);
		
		String emailId = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_EMAILID);
		String firstName = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_FIRSTNAME);
		String lastName = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_LASTNAME);
		String password = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_PASSWORD);
		String dob = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_DOB);
		String ipAddress = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_IPADDRESS);
		String siteReg = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_SITEREG);
		String gender = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_GENDER);
		String city = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_CITY);
		String state = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_STATE);
		String country = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_COUNTRY);
		String secQues = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_SECQUEST);
		String secAns = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_SECANS);
		String mobile = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_MOBILE);
		String pin = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_PIN);
		
		PrintWriter responseWriter = SSOUtils.getPrintWriter(response);
		
		/*****Validating each field****/
		if(!FieldValidator.isValidField(FieldValidator.FIELDNAME_EMAILID,FieldValidator.FIELDTYPE_EMAILID, emailId, true) ||
		   !FieldValidator.isValidField(FieldValidator.FIELDNAME_FIRSTNAME,FieldValidator.FIELDTYPE_NAME, firstName, true) ||
		   !FieldValidator.isValidField(FieldValidator.FIELDNAME_LASTNAME,FieldValidator.FIELDTYPE_NAME, lastName, true) ||
		   !FieldValidator.isValidField(FieldValidator.FIELDNAME_DOB,FieldValidator.FIELDTYPE_DOB, dob, true) ||
		   !FieldValidator.isValidField(FieldValidator.FIELDNAME_PASSWORD,FieldValidator.FIELDTYPE_PASSWORD, password, true) ||
		   !FieldValidator.isValidField(FieldValidator.FIELDNAME_MOBILENUMBER,FieldValidator.FIELDTYPE_MOBILENUMBER, mobile, true)
		   ){
			
			responseWriter.write(FieldValidator.errorMsg);
			return;
		}
		
		Date parsedDate=SSOUtils.getDate(dob);
		AliasAuthDataAccessManager aliasAuthDataAccessManager=new AliasAuthDataAccessManager();
		boolean status = aliasAuthDataAccessManager.updateUserProfile(emailId.toLowerCase(),firstName,
				lastName,password, parsedDate, ipAddress, siteReg, gender, 
				city, state, country, secQues, secAns, mobile, pin);
		
		if(!status)
			mylogger.error(ErrorMessages.MESSAGE_ERROR_UPDATION);
		
		responseWriter.write(ErrorMessages.XML_URL+"<status>"+status+"</status>\n");
		return;
	}
}
