package com.timesgroup.sso.servlets.itimes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.db2.jcc.am.u;
import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.ITimesDataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserRegistrationItimes;
import com.timesgroup.sso.utils.SSOUtils;

public class GetUserProfileServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(GetUserProfileServlet.class);
		
		String userId = request.getParameter(SSOConstants.GetUserProfile.PARAM_USERID);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (userId == null || (userId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.GetUserProfile.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.GetUserProfile.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && !SSOUtils.containsSpecialCharacter(userId,SSOConstants.USER_ID_NOT_PERMISSIBLE_CHARACTERS)){
			
			mylogger.error(SSOConstants.GetUserProfile.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.GetUserProfile.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && userId.length()>50){
			
			mylogger.error(SSOConstants.GetUserProfile.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.GetUserProfile.ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		UserRegistrationItimes userRegistrationItimes = iTimesDataAccessManager. getUserProfile(userId.toLowerCase());
		
		
		
		if(userRegistrationItimes!=null){
			
			 String userStr=SSOConstants.XML_URL+"<NewDataSet><Table>";
			 userStr+="<frst_nm_vc>"+userRegistrationItimes.getFirst_name()+"</frst_nm_vc>"; 
			 userStr+="<lst_nm_vc>"+userRegistrationItimes.getLast_name()+"</lst_nm_vc>";   
			 userStr+="<eml_vc>"+userRegistrationItimes.getEmail_id()+"</eml_vc>";   
			 userStr+="<country>"+userRegistrationItimes.getCountry()+"</country>"; 
			 userStr+="<state>"+userRegistrationItimes.getState()+"</state>"; 
			 userStr+="<dob>"+userRegistrationItimes.getDob()+"</dob>"; 
			 userStr+="<gender>"+userRegistrationItimes.getGender()+"</gender>"; 
			 userStr+="<siteReg>"+userRegistrationItimes.getRegistration_site()+"</siteReg>"; 
			 userStr+="</Table></NewDataSet>";
			 
			 responseWriter.write(userStr);
		} 
		else responseWriter.write(SSOConstants.XML_URL+"<NewDataSet></NewDataSet>");
		
		return;
		
	}

}
