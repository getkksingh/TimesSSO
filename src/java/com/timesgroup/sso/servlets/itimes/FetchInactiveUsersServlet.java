package com.timesgroup.sso.servlets.itimes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.ITimesDataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserRegistration;
import com.timesgroup.sso.hibernate.mapping.UserRegistrationItimes;

public class FetchInactiveUsersServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(FetchInactiveUsersServlet.class);

		String emailId = request.getParameter(SSOConstants.FetchInactiveUsers.PARAM_EMAILID);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (emailId ==null || (emailId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.FetchInactiveUsers.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.FetchInactiveUsers.ERROR_MESSAGE_INVALIDEMAILID);
			return;
		}
		
		if(emailId!=null && !emailId.matches(SSOConstants.VALID_EMAILID_PATTERN)){
			
			mylogger.error(SSOConstants.FetchInactiveUsers.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.FetchInactiveUsers.ERROR_MESSAGE_INVALIDEMAILID);
			return;
		}
		
		if(emailId!=null && emailId.length()>100){
			
			mylogger.error(SSOConstants.FetchInactiveUsers.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.FetchInactiveUsers.ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH);
			return;
		}
		
		mylogger.debug("INPUT EMAILID=="+emailId.toLowerCase());
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		List inactiveUsersList = iTimesDataAccessManager.fetchInactiveUsers(emailId.toLowerCase());
		
		mylogger.info(inactiveUsersList);
		
		if(inactiveUsersList!=null && inactiveUsersList.size()>0){
			
			String str="<InactiveUser>\n";
			Iterator<UserRegistrationItimes> iter=inactiveUsersList.iterator();
			
			while(iter.hasNext()){
				String userStr="<user>\n";
				UserRegistrationItimes tmp=(UserRegistrationItimes)iter.next();
				userStr+="<userid>"+tmp.getUser_id()+"</userid>\n";
				userStr+="<CreatedDate>"+tmp.getCreate_date()+"</CreatedDate>\n";
				userStr+="</user>\n";
				
				str+=userStr;
			}
			
			str+="</InactiveUser>\n";
			
			responseWriter.write(SSOConstants.XML_URL+str);
		}else{
			mylogger.error(SSOConstants.FetchInactiveUsers.MESSAGE_ERROR_FETCHING);
			responseWriter.write(SSOConstants.FetchInactiveUsers.ERROR_FETCHING);
		}
		
		return;
		
	}

}
