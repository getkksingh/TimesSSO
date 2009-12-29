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
import com.timesgroup.sso.hibernate.mapping.UserRegistrationItimes;

public class GetInactiveUserDetailServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(GetInactiveUserDetailServlet.class);
		String emailId = request.getParameter(SSOConstants.GetInactiveUserDetail.PARAM_EMAILID);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (emailId ==null || (emailId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.GetInactiveUserDetail.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.GetInactiveUserDetail.MESSAGE_INVALID_EMAILID);
			return;
		}
		
		if(emailId!=null && !emailId.matches(SSOConstants.VALID_EMAILID_PATTERN)){
			
			mylogger.error(SSOConstants.GetInactiveUserDetail.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.GetInactiveUserDetail.ERROR_MESSAGE_INVALIDEMAILID);
			return;
		}
		
		if(emailId!=null && emailId.length()>100){
			
			mylogger.error(SSOConstants.GetInactiveUserDetail.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.GetInactiveUserDetail.ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		UserRegistrationItimes userRegistrationItimes = iTimesDataAccessManager.getInactiveUserDetail(emailId.toLowerCase());
        
		if(userRegistrationItimes==null){
		
			mylogger.error(SSOConstants.GetInactiveUserDetail.MESSAGE_ERROR_FETCHING);
			responseWriter.write(SSOConstants.GetInactiveUserDetail.ERROR_FETCHING);
			return;
		}
		
		
		if(userRegistrationItimes!=null){
			
			String str=SSOConstants.XML_URL+"<InactiveUser>\n<user>\n";
			str+="<usr_id_vc>"+userRegistrationItimes.getUser_id()+"</usr_id_vc>\n"; 
			str+="<eml_vc>"+userRegistrationItimes.getEmail_id()+"</eml_vc>\n"; 
			str+="<psswrd_vc>"+userRegistrationItimes.getPassword()+"</psswrd_vc>\n"; 
			str+="<CRT_DT>"+userRegistrationItimes.getCreate_date()+"</CRT_DT>\n"; 
			str+="<DateRegistered>"+userRegistrationItimes.getCreate_time()+"</DateRegistered>\n"; 
			str+="<site_reg>"+userRegistrationItimes.getRegistration_site()+"</site_reg>\n"; 
			str+="<frst_nm_vc>"+userRegistrationItimes.getFirst_name()+"</frst_nm_vc>\n";  
			str+="<lst_nm_vc>"+userRegistrationItimes.getLast_name()+"</lst_nm_vc>\n";  
			str+="<Address>"+userRegistrationItimes.getAddress()+"</Address>\n";  
			str+="<Gender>"+userRegistrationItimes.getGender()+"</Gender>\n";  
			str+="<state>"+userRegistrationItimes.getState()+"</state>\n";  
			str+="<country>"+userRegistrationItimes.getCountry()+"</country>\n";  
			str+="<dob>"+userRegistrationItimes.getDob()+"</dob>\n";  
			str+="<Referrel>OpenInv</Referrel>\n";  
			str+="<UserType>0</UserType>\n"; 
			str+="</user></InactiveUser>\n";

			responseWriter.write(str);
			return;
			
		}
		
		
	}

}
