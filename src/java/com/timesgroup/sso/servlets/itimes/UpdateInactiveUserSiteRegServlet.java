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

public class UpdateInactiveUserSiteRegServlet extends HttpServlet{
	
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(UpdateInactiveUserSiteRegServlet.class);
		String emailId = request.getParameter(SSOConstants.UpdateInactiveUserSite.PARAM_EMAILID);
		String siteReg = request.getParameter(SSOConstants.UpdateInactiveUserSite.PARAM_NEWSITEREGVAL);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
		if (emailId == null || (emailId.trim().compareTo("") == 0) ||
				siteReg == null || (siteReg.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.UpdateInactiveUserSite.ERROR_WITH_UPDATEINACTIVEUSERSITE_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.UpdateInactiveUserSite.ERROR_WITH_UPDATEINACTIVEUSERSITE_REQUEST_PARAMETERS);
			return;
		}
		
		if(emailId!=null && !emailId.matches(SSOConstants.VALID_EMAILID_PATTERN)){
			
			mylogger.error(SSOConstants.UpdateInactiveUserSite.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.UpdateInactiveUserSite.ERROR_MESSAGE_INVALIDEMAILID);
			return;
		}
		
		if(emailId!=null && emailId.length()>100){
			
			mylogger.error(SSOConstants.UpdateInactiveUserSite.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.UpdateInactiveUserSite.ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH);
			return;
		}
		
		if(siteReg!=null && !siteReg.matches(SSOConstants.VALID_USERID_PATTERN)){
			
			mylogger.error(SSOConstants.UpdateInactiveUserSite.MESSAGE_INVALID_NEWSITEREGVAL);
			responseWriter.write(SSOConstants.UpdateInactiveUserSite.ERROR_MESSAGE_NEWSITEREGVAL);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		boolean status = iTimesDataAccessManager.updateInactiveUserSite(emailId.toLowerCase(), siteReg.toLowerCase());
        
		responseWriter.write(SSOConstants.XML_URL+"<NewDataSet><Table><status>" + status + "</status></Table></NewDataSet>");
		
		return;
	}
}
