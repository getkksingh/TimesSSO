package com.timesgroup.sso.servlets.itimes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.constants.TimesMail;
import com.timesgroup.sso.hibernate.apis.ITimesDataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserInvitation;

public class SendITimesInvitationServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(SendITimesInvitationServlet.class);
		String userId = request.getParameter(SSOConstants.SendITimesInvitation.PARAM_USERID);
		String emailIds = request.getParameter(SSOConstants.SendITimesInvitation.PARAM_EMAILID);
		
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (userId == null || (userId.trim().compareTo("") == 0)  || 
				emailIds ==null || (emailIds.trim().compareTo("") == 0)) {

			mylogger.error(SSOConstants.SendITimesInvitation.ERROR_WITH_SENDITIMESINVITATION_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.SendITimesInvitation.ERROR_WITH_SENDITIMESINVITATION_REQUEST_PARAMETERS);
			return;
		}
		
		
		if(userId!=null && !userId.matches(SSOConstants.VALID_USERID_PATTERN)){
			
			mylogger.error(SSOConstants.SendITimesInvitation.ERROR_MESSAGE_INVALIDUSERID);
			responseWriter.write(SSOConstants.SendITimesInvitation.ERROR_MESSAGE_INVALIDUSERID);
			return;
		}
		
		String emailId[]=emailIds.split(",");
		
		if(emailId.length>5){
			
			mylogger.error(SSOConstants.SendITimesInvitation.MESSAGE_INVALID_LIMITOFREQUESTS);
			responseWriter.write(SSOConstants.SendITimesInvitation.MESSAGE_INVALID_LIMITOFREQUESTS);
			return;
			
		}
		
		List<UserInvitation> userInvitations= new LinkedList<UserInvitation>();
		
		
		for (int i=0; i< emailId.length;i++){
			
			if(emailId[i]!=null && emailId[i].length()>100){
				
				mylogger.error(SSOConstants.SendITimesInvitation.MESSAGE_INVALID_EMAILID);
				responseWriter.write(SSOConstants.SendITimesInvitation.ERROR_MESSAGE_INVALIDEMAILID);
				return;
			}
			
			if(emailId[i]!=null && !emailId[i].matches(SSOConstants.VALID_EMAILID_PATTERN)){
				
				mylogger.error(SSOConstants.SendITimesInvitation.MESSAGE_INVALID_EMAILID);
				responseWriter.write(SSOConstants.SendITimesInvitation.ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH);
				return;
			}
			Base64 base64 = new Base64();
			String hashcode= new String(base64.encode((userId+":"+emailId[i]).getBytes()));
			UserInvitation userInvitation=new UserInvitation();
			userInvitation.setCreatedDate(new Date());
			userInvitation.setEmailId(emailId[i]);
			userInvitation.setHashCode(hashcode);
			userInvitation.setRefereeUserId(userId);
			userInvitation.setStatus(0);
			
			userInvitations.add(userInvitation);
			
		}
		
		System.out.println("SendITimesInvitationServlet.service()"+userInvitations);
		
		/*Send invitations via Thread*/
		if(userInvitations!=null && userInvitations.size()>0){
			
			TimesMail timesMail= TimesMail.getInstance();
			
			Iterator iter=userInvitations.iterator();
			
			while(iter.hasNext()){
				
				UserInvitation userInvitation=(UserInvitation)iter.next();
				timesMail.enQueueInvitation(userInvitation);
			}
			
		}
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		iTimesDataAccessManager.saveInvitationInformation(userInvitations);
		
		return ;
        
	}
}
