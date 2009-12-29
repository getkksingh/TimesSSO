package com.timesgroup.sso.servlets.itimes;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.ITimesDataAccessManager;
import com.timesgroup.sso.utils.CryptoUtility;

public class UpdateUserProfileServlet extends HttpServlet{
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		final Logger mylogger = Logger.getLogger(UpdateUserProfileServlet.class);

		String emailId = request.getParameter(SSOConstants.UpdateUserProfile.PARAM_EMAILID);
		String firstName=request.getParameter(SSOConstants.UpdateUserProfile.PARAM_FIRSTNAME);
		String lastName=request.getParameter(SSOConstants.UpdateUserProfile.PARAM_LASTNAME);
		String password=request.getParameter(SSOConstants.UpdateUserProfile.PARAM_PASSWORD);
		String dateOfBirth=request.getParameter(SSOConstants.UpdateUserProfile.PARAM_DOB);
		String gender=request.getParameter(SSOConstants.UpdateUserProfile.PARAM_GENDER);
		String city=request.getParameter(SSOConstants.UpdateUserProfile.PARAM_CITY);
		
		
		PrintWriter responseWriter = null;
		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		if (emailId ==null || (emailId.trim().compareTo("") == 0) ||
				firstName ==null || (firstName.trim().compareTo("") == 0) ||
				lastName ==null || (lastName.trim().compareTo("") == 0) ||
				dateOfBirth ==null || (dateOfBirth.trim().compareTo("") == 0) ||
				password ==null || (password.trim().compareTo("") == 0) ||
				gender ==null || (gender.trim().compareTo("") == 0)
				) {

			mylogger.error(SSOConstants.UpdateUserProfile.ERROR_WITH_UPDATEUSERPROFILE_REQUEST_PARAMETERS);
			responseWriter.write(SSOConstants.UpdateUserProfile.ERROR_WITH_UPDATEUSERPROFILE_REQUEST_PARAMETERS);
			return;
		}
		
		if(emailId!=null && !emailId.matches(SSOConstants.VALID_EMAILID_PATTERN)){
			
			mylogger.error(SSOConstants.UpdateUserProfile.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.UpdateUserProfile.ERROR_MESSAGE_INVALIDEMAILID);
			return;
		}
		
		if(emailId!=null && emailId.length()>100){
			
			mylogger.error(SSOConstants.UpdateUserProfile.MESSAGE_INVALID_EMAILID);
			responseWriter.write(SSOConstants.UpdateUserProfile.ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH);
			return;
		}
		
		if(firstName!=null && !firstName.matches(SSOConstants.VALID_USERID_PATTERN)){
			
			mylogger.error(SSOConstants.UpdateUserProfile.ERROR_MESSAGE_INVALIDFIRSTNAME);
			responseWriter.write(SSOConstants.UpdateUserProfile.ERROR_MESSAGE_INVALIDFIRSTNAME);
			return;
		}

		if(firstName!=null && firstName.length()>50){
			
			mylogger.error(SSOConstants.UpdateUserProfile.MESSAGE_INVALID_FIRSTNAME);
			responseWriter.write(SSOConstants.UpdateUserProfile.ERROR_MESSAGE_INVALIDFIRSTNAME_INCORRECTLENGTH);
			return;
		}
		
		if(lastName!=null && !lastName.matches(SSOConstants.VALID_USERID_PATTERN)){
			
			mylogger.error(SSOConstants.UpdateUserProfile.ERROR_MESSAGE_INVALIDLASTNAME);
			responseWriter.write(SSOConstants.UpdateUserProfile.ERROR_MESSAGE_INVALIDLASTNAME);
			return;
		}

		if(lastName!=null && lastName.length()>50){
			
			mylogger.error(SSOConstants.UpdateUserProfile.MESSAGE_INVALID_LASTNAME);
			responseWriter.write(SSOConstants.UpdateUserProfile.ERROR_MESSAGE_INVALIDLASTNAME_INCORRECTLENGTH);
			return;
		}
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date parsedDate=null;
		 try{
		     parsedDate = df.parse(dateOfBirth);           
		     mylogger.debug("DATE =" + df.format(parsedDate));
		 } catch (ParseException e)
		 {
		     e.printStackTrace();
		 }
		
		 if(parsedDate==null){
			 
			mylogger.error(SSOConstants.UpdateUserProfile.MESSAGE_INVALID_DOB);
			responseWriter.write(SSOConstants.UpdateUserProfile.ERROR_MESSAGE_INVALIDDOB);
			return;
		 }
		 
		password=(new CryptoUtility()).performEncrypt(password.trim());
		
		ITimesDataAccessManager iTimesDataAccessManager = new ITimesDataAccessManager();
		int status = iTimesDataAccessManager.updateUserProfile(emailId.toLowerCase(),firstName.toLowerCase(),
					lastName.toLowerCase(),parsedDate,password, gender, city);
	        
		if(status==0){
			
			mylogger.error(SSOConstants.UpdateUserProfile.MESSAGE_ERROR_UPDATION);
			responseWriter.write(SSOConstants.UpdateUserProfile.MESSAGE_ERROR_UPDATION);
			return;
		}
		
		if(status==1){
			
			String str=SSOConstants.XML_URL+"<NewDataSet>\n<Table>\n";
			str+="<usr_id_vc>iti10016</usr_id_vc>\n"; 
			str+="</Table></NewDataSet>\n";

			responseWriter.write(str);
			return;
			
		}
		
	}	

}
