package com.timesgroup.sso.servlets.itimes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.ITimesDataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserInvitation;
import com.timesgroup.sso.utils.SSOUtils;

public class GetInviteeListServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(GetInviteeListServlet.class);
		String userId = request.getParameter(SSOConstants.GetInviteeList.PARAM_USERID);
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {
	
			e.printStackTrace();
		}
	
		if (userId == null || (userId.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.GetInviteeList.MESSAGE_NOVALUE_USERID);
			responseWriter.write(SSOConstants.GetInviteeList.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && SSOUtils.containsSpecialCharacter(userId,SSOConstants.USER_ID_NOT_PERMISSIBLE_CHARACTERS)){
			
			mylogger.error(SSOConstants.GetInviteeList.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.GetInviteeList.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		if(userId!=null && userId.length()>50){
			
			mylogger.error(SSOConstants.GetInviteeList.MESSAGE_INVALID_USERID);
			responseWriter.write(SSOConstants.GetInviteeList.ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH);
			return;
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		List users = iTimesDataAccessManager. getInviteeList(userId.toLowerCase());
		
		if(users==null){
			responseWriter.write(SSOConstants.XML_URL+"<INVITEES></INVITEES>");
			return ;
		}
		
		Iterator iter=users.iterator();
		UserInvitation userInvitation = null;
		String str=SSOConstants.XML_URL;
		str+="<INVITEES>";
		
		Set<String> usersSet=new HashSet<String>();
		Set<String> unusedUsersSet=new HashSet<String>(); 
		
		while(iter.hasNext()){
			
			userInvitation=new UserInvitation();
			userInvitation=(UserInvitation)iter.next();
			
			if(userInvitation.getStatus()==0)//unused users
			unusedUsersSet.add(userInvitation.getRefereeUserId());
			
			if(userInvitation.getStatus()==1)//used users
				usersSet.add(userInvitation.getRefereeUserId());
		}
		
		iter=usersSet.iterator();
		
		while(iter.hasNext()){
			
			str+="<USER>"+(String)iter.next()+"</USER>";
		}
		
		iter=unusedUsersSet.iterator();
		
		while(iter.hasNext()){
			
			str+="<UNUSEDUSER>"+(String)iter.next()+"</UNUSEDUSER>";
		}
	
		str+="</INVITEES>";
		responseWriter.write(str);
		
		return;
	}
}
