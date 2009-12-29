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

public class UpdateITimesIdServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger
				.getLogger(UpdateITimesIdServlet.class);

		String userId = request.getParameter(SSOConstants.UpdateITimesId.PARAM_USERID);
		String itimesId = request.getParameter(SSOConstants.UpdateITimesId.PARAM_ITIMESID);

		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (userId == null || (userId.trim().compareTo("") == 0)  || 
				itimesId ==null || (itimesId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.UpdateITimesId.ERROR_WITH_UPDATEITIMESID_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.UpdateITimesId.ERROR_WITH_UPDATEITIMESID_REQUEST_PARAMETERS);
			return;
		}
		
		
		if(userId!=null && !userId.matches(SSOConstants.VALID_USERID_PATTERN)){
			
			mylogger.error(SSOConstants.UpdateITimesId.ERROR_MESSAGE_INVALIDUSERID);
			responseWriter.write(SSOConstants.UpdateITimesId.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(itimesId!=null && itimesId.length()>100){
			
			mylogger.error(SSOConstants.UpdateITimesId.ERROR_MESSAGE_INVALIDITIMESID_INCORRECTLENGTH);
			responseWriter.write(SSOConstants.UpdateITimesId.ERROR_MESSAGE_INVALIDITIMESID_INCORRECTLENGTH);
			return;
		}
		
		if(itimesId!=null && !itimesId.matches(SSOConstants.VALID_EMAILID_PATTERN)){
			
			mylogger.error(SSOConstants.UpdateITimesId.ERROR_MESSAGE_INVALIDITIMESID);
			responseWriter.write(SSOConstants.UpdateITimesId.ERROR_MESSAGE_INVALIDITIMESID);
			return;
		}
		
		String str[]=itimesId.split("@");
		itimesId=str[0];
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		int updateITimesId = iTimesDataAccessManager.updateITimesId(userId.toLowerCase(),itimesId.toLowerCase());
        
		if(updateITimesId==2){
		
			mylogger.error(SSOConstants.UpdateITimesId.MAPPINGEXISTS);
			responseWriter.write(SSOConstants.UpdateITimesId.ERROR_MESSAGE_MAPPINGEXISTS);
			return;
		}
		
		mylogger.debug(updateITimesId);
		responseWriter.write(SSOConstants.XML_URL+"<status>" + updateITimesId + "</status>");

		return;
	}
}
