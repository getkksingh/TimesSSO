package com.timesgroup.sso.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.apis.DataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserInterest;
import com.timesgroup.sso.hibernate.mapping.UserOwnership;
import com.timesgroup.sso.hibernate.mapping.UserRegistration;
import com.timesgroup.sso.utils.UserActivityManager;

public class GetUserProfileXMLServlet extends javax.servlet.http.HttpServlet {
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
			String userId = request.getParameter(SSOConstants.GetUserProfileXMLConstants.PARAM_USER);
			if(userId!=null)
				userId=userId.toLowerCase();
			
			PrintWriter responseWriter =  null ;
	        
			try {
				responseWriter = response.getWriter();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}

			if(userId == null) {
				responseWriter.write(SSOConstants.GetUserProfileXMLConstants.USER_DOES_NOT_EXIST_MESSAGE);
				return ;
			}
			
		    DataAccessManager accessManager = new DataAccessManager();
		  
		    UserRegistration userData = accessManager.getUserData(userId);
		    UserInterest userIntrst = accessManager.getUserInterest(userId);
		    UserOwnership userOwnDetls = accessManager.getUserOwnership(userId);
		    if(userIntrst==null) userIntrst=new UserInterest();
		    if(userOwnDetls==null) userOwnDetls=new UserOwnership();
		    if(userData != null) {
				//UserActivity Logging
				try{
					if(getServletContext().getInitParameter("GetUserProfile").equalsIgnoreCase("true"))
						new UserActivityManager(userId,request.getRemoteAddr(),"GetUserProfile","");
				}catch(Exception e2)
				{}

		    	String strXML=String.format(
		    	SSOConstants.GetUserProfileXMLConstants.USER_PROFILE_XML_FORMAT,
		    		userData.getUser_id(),
		    		(userData.getPassword()==null)?"":userData.getPassword(),
					(userData.getEmail_id()==null)?"":userData.getEmail_id(),
				    (userData.getFirst_name()==null)?"":userData.getFirst_name(),
					(userData.getLast_name()==null)?"":userData.getLast_name(),
		    		(userData.getForget_password_question()==null)?"":userData.getForget_password_question(),
		    		(userData.getForget_password_answer()==null)?"":userData.getForget_password_answer(),
					(userData.getRegistration_site()==null)?"":userData.getRegistration_site(),
					(userData.getCreate_date()==null)?"":userData.getCreate_date(),
					(userData.getMiddle_name()==null)?"":userData.getMiddle_name(),
					(userData.getAddress()==null)?"":userData.getAddress(),		
					(userData.getCity()==null)?"":userData.getCity(),
					(userData.getState()==null)?"":userData.getState(),
					(userData.getCountry()==null)?"":userData.getCountry(),
					(userData.getPin()==null)?"":userData.getPin(),
					(userData.getDob()==null)?"":userData.getDob(),
					(userData.getGender()==null)?"":userData.getGender(),
					(userData.getOccupation()==null)?"":userData.getOccupation(),
					(userData.getIndustry()==null)?"":userData.getIndustry(),
					(userData.getEducation()==null)?"":userData.getEducation(),
					(userData.getIncomegroup()==null)?"":userData.getIncomegroup(),
					(userData.getReferrel()==null)?"":userData.getReferrel(),
					(userData.getTravelfrequency()==null)?"":userData.getTravelfrequency(),
					(userData.getAirtravel()==null)?"":userData.getAirtravel(),
					(userData.getCreate_time()==null)?"":userData.getCreate_time(),
					(userData.getPop()==null)?"":userData.getPop(),
					(userData.getImap()==null)?"":userData.getImap(),
					(userData.getRes_phone()==null)?"":userData.getRes_phone(),
					(userData.getMobilephone()==null)?"":userData.getMobilephone(),
					(userData.getOnlineusage()==null)?"":userData.getOnlineusage(),
					(userData.getOnlineshopping()==null)?"":userData.getOnlineshopping(),
					(userData.getMaritalstatus()==null)?"":userData.getMaritalstatus(),
					(userData.getSms_status()==null)?"":userData.getSms_status(),
					(userData.getProfile_status()==null)?"":userData.getProfile_status(),							
					(userIntrst.getAuctions()==null)?"":userIntrst.getAuctions(),
					(userIntrst.getAuto()==null)?"":userIntrst.getAuto(),
					(userIntrst.getHealth_beauty()==null)?"":userIntrst.getHealth_beauty(),
					(userIntrst.getSpirituality()==null)?"":userIntrst.getSpirituality(),
					(userIntrst.getToys_games()==null)?"":userIntrst.getToys_games(),
					(userIntrst.getContest_freestuff()==null)?"":userIntrst.getContest_freestuff(),
					(userIntrst.getTravel()==null)?"":userIntrst.getTravel(),
					(userIntrst.getShopping()==null)?"":userIntrst.getShopping(),
					(userIntrst.getComputer_technology()==null)?"":userIntrst.getComputer_technology(),
					(userIntrst.getBusiness_finance()==null)?"":userIntrst.getBusiness_finance(),
					(userIntrst.getMusic_movies()==null)?"":userIntrst.getMusic_movies(),
					(userIntrst.getLifestyle()==null)?"":userIntrst.getLifestyle(),
					(userIntrst.getNews()==null)?"":userIntrst.getNews(),
					(userIntrst.getSports()==null)?"":userIntrst.getSports(),
					(userOwnDetls.getFridge()==null)?"":userOwnDetls.getFridge(),
					(userOwnDetls.getGrinder_juicer()==null)?"":userOwnDetls.getGrinder_juicer(),
					(userOwnDetls.getColortv()==null)?"":userOwnDetls.getColortv(),
					(userOwnDetls.getCreditcard()==null)?"":userOwnDetls.getCreditcard(),
					(userOwnDetls.getA_c()==null)?"":userOwnDetls.getA_c(),
					(userOwnDetls.getCellphone()==null)?"":userOwnDetls.getCellphone(),
					(userOwnDetls.getDishwasher()==null)?"":userOwnDetls.getDishwasher(),
					(userOwnDetls.getCookingrange()==null)?"":userOwnDetls.getCookingrange(),
					(userOwnDetls.getMicrowave()==null)?"":userOwnDetls.getMicrowave(),
					(userOwnDetls.getMusicsystem()==null)?"":userOwnDetls.getMusicsystem(),
					(userOwnDetls.getComputer()==null)?"":userOwnDetls.getComputer(),
					(userOwnDetls.getTwowheeler()==null)?"":userOwnDetls.getTwowheeler(),
					(userOwnDetls.getFourwheeler()==null)?"":userOwnDetls.getFourwheeler(),
					(userOwnDetls.getWmachine()==null)?"":userOwnDetls.getWmachine()
		    	);
				responseWriter.write(strXML);
				return ; 
		    }
		    else 
		    	responseWriter.write(SSOConstants.GetUserProfileXMLConstants.USER_DOES_NOT_EXIST_MESSAGE);	
	}
}