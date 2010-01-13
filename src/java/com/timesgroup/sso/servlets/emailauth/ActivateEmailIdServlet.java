package com.timesgroup.sso.servlets.emailauth;

import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.timesgroup.sso.constants.ErrorMessages;
import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.AliasAuthDataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserRegistration;
import com.timesgroup.sso.utils.SSOUtils;

public class ActivateEmailIdServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(InsertUserProfileServlet.class);
		String hashCode = request.getParameter(SSOConstants.ActivateEmailId.PARAM_HASHCODE);
		
		Base64 base64 = new Base64();
		String tmpStr = new String(base64.decode((hashCode).getBytes()));
		String userId=tmpStr.split(":")[0];
		String emailId=tmpStr.split(":")[1];
		
		AliasAuthDataAccessManager aliasAuthDataAccessManager=new AliasAuthDataAccessManager();
		String userProfile = aliasAuthDataAccessManager.ifEmailIdAvailable(emailId.toLowerCase());
		
		if(userProfile.trim().length()==0){ // there is no email id for the given hashcode
			mylogger.error(ErrorMessages.MESSAGE_INVALID_HASHCODE);
			return;
		}
		
		PrintWriter responseWriter = SSOUtils.getPrintWriter(response);
		
		UserRegistration userRegistration=new UserRegistration();
		userRegistration.setUser_id(userId);
		
		StringTokenizer stringTokenizer=new StringTokenizer(userProfile,"&");

		while(stringTokenizer.hasMoreTokens()){
			
			String str=(String)stringTokenizer.nextElement();
			String[] tmp=str.split("=");
			if(tmp[0].equals("emailid")) userRegistration.setEmail_id(tmp[1]);
			if(tmp[0].equals("firstname")) userRegistration.setFirst_name(tmp[1]);
			if(tmp[0].equals("lastname")) userRegistration.setLast_name(tmp[1]);
			if(tmp[0].equals("password")) userRegistration.setPassword(tmp[1]);
			if(tmp[0].equals("mobile")) userRegistration.setMobilephone(tmp[1]);
			if(tmp[0].equals("dob")) userRegistration.setDob(SSOUtils.getDate(tmp[1]));
			if(tmp[0].equals("ipaddress")) userRegistration.setAddress(tmp[1]);
			if(tmp[0].equals("sitereg")) userRegistration.setRegistration_site(tmp[1]);
			if(tmp[0].equals("gender")) userRegistration.setGender(tmp[1].charAt(0));
			if(tmp[0].equals("city")) userRegistration.setCity(tmp[1]);
			if(tmp[0].equals("state")) userRegistration.setState(tmp[1]);
			if(tmp[0].equals("country")) userRegistration.setCountry(tmp[1]);
			if(tmp[0].equals("secques")) userRegistration.setForget_password_question(tmp[1]);
			if(tmp[0].equals("secans")) userRegistration.setForget_password_answer(tmp[1]);
		
		}
		
		boolean isActivated=aliasAuthDataAccessManager.activateEmailId(emailId.toLowerCase(), userRegistration);
		
		responseWriter.write(SSOConstants.XML_URL+"<status>"+isActivated+"</status>");
		
		return;
	}

}
