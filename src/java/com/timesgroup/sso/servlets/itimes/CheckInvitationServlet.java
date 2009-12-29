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

public class CheckInvitationServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(CheckInvitationServlet.class);
        String userId = request.getParameter(SSOConstants.CheckInvitation.PARAM_USERID);

		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (userId == null || (userId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.CheckInvitation.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.CheckInvitation.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		List inviteesList=iTimesDataAccessManager.checkInvitation(userId.toLowerCase());
		
		String str="";
		
		if(inviteesList!=null && inviteesList.size()>0){
			
			Iterator iter=inviteesList.iterator();
			while(iter.hasNext()){
				
				String tmp=(String)iter.next();
				
				if(tmp!=null)	str+=tmp+",";
			}
			
			str=str.substring(0, str.length()-1);
		}else str="false";
		
		responseWriter.write("<string>"+str+"</string>");
		
		return;
	}

}
