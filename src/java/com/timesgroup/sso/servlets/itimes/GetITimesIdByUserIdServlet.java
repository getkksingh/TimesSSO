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

public class GetITimesIdByUserIdServlet  extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger
				.getLogger(GetITimesIdByUserIdServlet.class);
        
		String userId = request.getParameter(SSOConstants.GetITimesIdByUserIdConstants.PARAM_USERID);

		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (userId == null || (userId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.GetITimesIdByUserIdConstants.ERROR_WITH_ITIMESID_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.GetITimesIdByUserIdConstants.ERROR_MESSAGE);
			return;
		}

		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		String itimesId = iTimesDataAccessManager.getITimesIdByUserId(userId);
		
		if(itimesId!=null){
			
			mylogger.debug(itimesId);
			responseWriter.write(SSOConstants.XML_URL+"<itimesid>" + itimesId + "</itimesid>");
		}else{
			
			mylogger.info(SSOConstants.GetITimesIdByUserIdConstants.RECORD_NOT_FOUND);
			responseWriter.write(SSOConstants.XML_URL+"<itimesid></itimesid>");
		}
		
		return;
	}
}
