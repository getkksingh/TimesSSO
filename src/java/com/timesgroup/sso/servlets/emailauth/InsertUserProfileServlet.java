package com.timesgroup.sso.servlets.emailauth;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.fieldvalidation.validators.FieldValidator;
import com.timesgroup.sso.constants.ErrorMessages;
import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.constants.SSOUtils;
import com.timesgroup.sso.hibernate.apis.AliasAuthDataAccessManager;

public class InsertUserProfileServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(InsertUserProfileServlet.class);
		
		String emailId = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_EMAILID);
		String firstName = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_FIRSTNAME);
		String lastName = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_LASTNAME);
		String password = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_PASSWORD);
		String dob = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_DOB);
		String ipAddress = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_IPADDRESS);
		String siteReg = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_SITEREG);
		String gender = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_GENDER);
		String city = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_CITY);
		String state = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_STATE);
		String country = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_COUNTRY);
		String secQues = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_SECQUEST);
		String secAns = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_SECANS);
		String mobile = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_MOBILE);
		String pin = request.getParameter(SSOConstants.InsertIntegraUserProfile.PARAM_PIN);
		
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
		
		String queryString = request.getQueryString();
		if (queryString == null || (queryString.trim().compareTo("") == 0)) {
			
			mylogger.error(ErrorMessages.MESSAGE_INVALIDPARAMETERS);
			responseWriter.write(ErrorMessages.MESSAGE_INVALIDPARAMETERS);
			return;
		}
		
		AliasAuthDataAccessManager aliasAuthDataAccessManager=new AliasAuthDataAccessManager();
		int status = aliasAuthDataAccessManager.ifEmailIdAvailable(emailId.toLowerCase());
		
		//emailid already exists
		if(status==1){
			
			responseWriter.write(ErrorMessages.XMLMESSAGE_ERROR_EMAILID_EXISTS);
			return;
		}
		
		String userId=SSOUtils.getUUID();
		Base64 base64 = new Base64();
		String hashcode= new String(base64.encode((userId+":"+emailId).getBytes()));
		Date currentDate=new Date();
		
		status = aliasAuthDataAccessManager.insertUserProfile(emailId.toLowerCase(),userId, queryString , hashcode, currentDate);
		
		if(status==0){
		
			mylogger.error(ErrorMessages.MESSAGE_ERROR_INSERTION);
			responseWriter.write(ErrorMessages.XMLMESSAGE_ERROR_INSERTION);
			return;
		}
		
		if(status>0){
			
			String str=SSOConstants.XML_URL+"<NewDataSet>\n<Table>\n";
			str+="<usr_id_vc>"+userId+"</usr_id_vc>\n"; 
			str+="<eml_vc>"+emailId+"</eml_vc>\n"; 
			str+="<psswrd_vc>"+password+"</psswrd_vc>\n"; 
			str+="<CRT_DT>"+currentDate+"</CRT_DT>\n"; 
			str+="<DateRegistered>"+currentDate+"</DateRegistered>\n"; 
			str+="<site_reg>"+siteReg+"</site_reg>\n"; 
			str+="<frst_nm_vc>"+firstName+"</frst_nm_vc>\n";  
			str+="<lst_nm_vc>"+lastName+"</lst_nm_vc>\n";  
			str+="<Address>"+ipAddress+"</Address>\n";  
			str+="<Gender>"+gender+"</Gender>\n";
			str+="<city>"+city+"</city>\n";  
			str+="<state>"+state+"</state>\n";  
			str+="<country>"+country+"</country>\n";  
			str+="<dob>"+dob+"</dob>\n";  
			str+="<pin>"+pin+"</pin>\n"; 
			str+="<frgt_psswrd_qstn_vc>"+secQues+"</frgt_psswrd_qstn_vc>\n";
			str+="<frgt_psswrd_answr_vc>"+secAns+"</frgt_psswrd_answr_vc>\n";
			str+="<mobilephone>"+mobile+"</mobilephone>\n";
			str+="<Referrel></Referrel>\n";
			str+="<UserType>0</UserType>\n"; 
			str+="</Table>";
			str+="<VerificationCode>"+hashcode+"</VerificationCode>";
			str+="<ActivationLink>http://register.itimes.com/extendedactivate.aspx?hs="+hashcode+"</ActivationLink>";
			str+="</NewDataSet>\n";

			responseWriter.write(str);
			return;
		}
		
		return;
	}
}
