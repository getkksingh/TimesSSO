package com.timesgroup.sso.servlets.emailauth;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fieldvalidation.validators.FieldValidator;
import com.timesgroup.sso.constants.ErrorMessages;
import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.AliasAuthDataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserRegistration;
import com.timesgroup.sso.utils.SSOUtils;

public class GetXMLUserProfileServlet extends HttpServlet {

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(GetXMLUserProfileServlet.class);
		String emailId = request.getParameter(SSOConstants.UpdateIntegraUserProfile.PARAM_EMAILID);
	
		PrintWriter responseWriter = SSOUtils.getPrintWriter(response);
		
		/*****Validating each field****/
		if(!FieldValidator.isValidField(FieldValidator.FIELDNAME_EMAILID,FieldValidator.FIELDTYPE_EMAILID, emailId, true)){
			
			responseWriter.write(FieldValidator.errorMsg);
			return;
		}
		
		AliasAuthDataAccessManager aliasAuthDataAccessManager=new AliasAuthDataAccessManager();
		UserRegistration userRegistration = aliasAuthDataAccessManager.getUserProfile(emailId.toLowerCase());
	
		if(userRegistration!=null){
			
			String str=SSOConstants.XML_URL+"<NewDataSet>\n<Table>\n";
			str+="<usr_id_vc>"+ (userRegistration.getUser_id()==null ? "" : userRegistration.getUser_id())+"</usr_id_vc>\n"; 
			str+="<eml_vc>"+(userRegistration.getEmail_id()==null ? "" :userRegistration.getEmail_id())+"</eml_vc>\n"; 
			str+="<psswrd_vc>"+(userRegistration.getPassword()==null ? "" :userRegistration.getPassword())+"</psswrd_vc>\n"; 
			str+="<CRT_DT>"+(userRegistration.getCreate_date()==null ? "" :userRegistration.getCreate_date())+"</CRT_DT>\n"; 
			str+="<DateRegistered>"+(userRegistration.getCreate_date()==null ? "" :userRegistration.getCreate_date())+"</DateRegistered>\n";
			str+="<site_reg>"+(userRegistration.getRegistration_site()==null ? "" :userRegistration.getRegistration_site())+"</site_reg>\n"; 
			str+="<frst_nm_vc>"+(userRegistration.getFirst_name()==null ? "" :userRegistration.getFirst_name())+"</frst_nm_vc>\n";  
			str+="<lst_nm_vc>"+(userRegistration.getLast_name()==null ? "" :userRegistration.getLast_name())+"</lst_nm_vc>\n";  
			str+="<Address>"+(userRegistration.getAddress()==null ? "" :userRegistration.getAddress())+"</Address>\n";  
			str+="<Gender>"+(userRegistration.getGender()==null ? "" :userRegistration.getGender())+"</Gender>\n";
			str+="<city>"+(userRegistration.getCity()==null ? "" :userRegistration.getCity())+"</city>\n";  
			str+="<state>"+(userRegistration.getState()==null ? "" :userRegistration.getState())+"</state>\n";  
			str+="<country>"+(userRegistration.getCountry()==null ? "" :userRegistration.getCountry())+"</country>\n";  
			str+="<dob>"+(userRegistration.getDob()==null ? "" :userRegistration.getDob())+"</dob>\n";  
			str+="<pin>"+(userRegistration.getPin()==null ? "" :userRegistration.getPin())+"</pin>\n"; 
			str+="<frgt_psswrd_qstn_vc>"+(userRegistration.getForget_password_question()==null ? "" :userRegistration.getForget_password_question())+"</frgt_psswrd_qstn_vc>\n";
			str+="<frgt_psswrd_answr_vc>"+(userRegistration.getForget_password_answer()==null ? "" :userRegistration.getForget_password_answer())+"</frgt_psswrd_answr_vc>\n";
			str+="<mobilephone>"+(userRegistration.getMobilephone()==null ? "" :userRegistration.getMobilephone())+"</mobilephone>\n";
			str+="<Referrel>"+(userRegistration.getReferrel()==null ? "" :userRegistration.getReferrel())+"</Referrel>\n";
			str+="<UserType>1</UserType>\n"; 
			str+="</Table>\n";
			str+="</NewDataSet>\n";
			
			responseWriter.write(str);
			return;
		}else{
			
			mylogger.error(ErrorMessages.MESSAGE_ERROR_FETCHINGRECORD);
			responseWriter.write(ErrorMessages.XMLMESSAGE_ERROR_FETCHING_USERPROFILE);
		}
		
		return;
	}

}
