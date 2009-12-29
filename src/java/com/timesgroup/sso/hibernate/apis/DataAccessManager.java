package com.timesgroup.sso.hibernate.apis;


import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.mapping.UserInterest;
import com.timesgroup.sso.hibernate.mapping.UserMapping;
import com.timesgroup.sso.hibernate.mapping.UserOwnership;
import com.timesgroup.sso.hibernate.mapping.UserRegistration;
import com.timesgroup.sso.utils.HibernateUtil;
import com.timesgroup.sso.utils.SSOUtils;

public class DataAccessManager {

	public boolean checkUserCredentials(String userId, String password) {
    	if(userId == null || userId.equalsIgnoreCase(""))
    		return false;
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from UserRegistration ur where ur.id = :uid and password = :password").setParameter("uid", userId).setParameter("password", password).list();
        session.getTransaction().commit();
        if(result != null && result.size() > 0)
        	return true;
        
    	return false;
    }


	public boolean checkUserExists(String userId) {
		
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from UserRegistration ur where ur.id = :uid ").setParameter("uid", userId).list();
        session.getTransaction().commit();
        if(result != null && result.size() > 0)
        	return true;
        
    	return false;
	}

   

	public String getUserProfile(String userId) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from UserRegistration ur where ur.id = :uid ").setParameter("uid", userId).list();
        session.getTransaction().commit();
        
        if(result != null && result.size() > 0){
        	UserRegistration userData = (UserRegistration)result.get(0);
        	String userProfile = getUserProfile(userData);
        	return userProfile;
        	}
        else return null;
	}
	
	public UserRegistration getUserData(String userId) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from UserRegistration ur where ur.id = :uid ").setParameter("uid", userId).list();
        session.getTransaction().commit();
        
        if(result != null && result.size() > 0){
        	UserRegistration userData = (UserRegistration)result.get(0);
        	return userData;
        	}
        else return null;
	}
	
	public UserInterest getUserInterest(String userId) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from UserInterest ur where ur.id = :uid ").setParameter("uid", userId).list();
        session.getTransaction().commit();
        
        if(result != null && result.size() > 0){
        	UserInterest userIntrst = (UserInterest)result.get(0);
        	return userIntrst;
        	}
        else return null;
	}

	public UserOwnership getUserOwnership(String userId) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from UserOwnership ur where ur.id = :uid ").setParameter("uid", userId).list();
        session.getTransaction().commit();
        
        if(result != null && result.size() > 0){
        	UserOwnership userOwnDetls = (UserOwnership)result.get(0);
        	return userOwnDetls;
        	}
        else return null;
	}
	
	public String recoverForgotPassword(String userId,
			String forgotPasswordQuestion, String forgotPasswordAnswer) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from UserRegistration ur where ur.id = :uid and  forget_password_question=:forget_password_question and forget_password_answer = :forget_password_answer").setParameter("uid", userId).setParameter("forget_password_question", forgotPasswordQuestion).setParameter("forget_password_answer", forgotPasswordAnswer).list();
        session.getTransaction().commit();
        
        if(result != null && result.size() > 0){
        	UserRegistration userData = (UserRegistration)result.get(0);
        	return userData.getPassword();
        	}
        else return null;
		
	}


	public void insertNewUserDetails(String userId, String password,
			String forgotPasswordQuestion, String forgotPasswordAnswer,
			String firstName, String lastName, String emailAddress,
			String rootSite) {
		
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setUser_id(userId);
        userRegistration.setPassword(password);
        userRegistration.setFirst_name(firstName);
        userRegistration.setLast_name(lastName);
        userRegistration.setEmail_id(emailAddress);
        userRegistration.setRegistration_site(rootSite);
        userRegistration.setForget_password_question(forgotPasswordQuestion);
        userRegistration.setForget_password_answer(forgotPasswordAnswer);
        Date now = new Date();
        userRegistration.setCreate_date(now);
        userRegistration.setCreate_time(now);
        
        session.save(userRegistration);
        session.getTransaction().commit();

		
	}
	public void changePassword(String userId, String oldPassword,
			String newPassword) {
		
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();       
        String hqlUpdate = "update UserRegistration ur set ur.password = :newPassword where ur.id = :userId";
        int updatedEntities = session.createQuery( hqlUpdate ).setString( "newPassword", newPassword ).setString( "userId", userId ).executeUpdate();
        System.out.println(updatedEntities +" Rows Updated");
        session.getTransaction().commit();
	}
	
	 public static void main(String[] args) {
			UserRegistration userData=new UserRegistration();
			userData.setUser_id("kksingh_iitk");
			userData.setPassword("kksingh_iitk1");
			userData.setFirst_name("Krishna");
			userData.setLast_name("Singh");
			userData.setForget_password_question("hi");
			userData.setForget_password_answer("hi");
			userData.setEmail_id("Krishna@wellogic.com");
			int i =new DataAccessManager().updateUser(userData);
			System.out.println(i);
	    }

	 public void testJoin() {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		  session.beginTransaction();
		  String hqlQuery = "from UserRegistration u ";
		  
		  session.getTransaction().commit();
		 
	 }
	
	public int updateUser(UserRegistration userData) {
		int status = 0 ; 
		try {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();       
        UserRegistration userRegistration = null ;
        try {
        	userRegistration =(UserRegistration)session.get(UserRegistration.class, userData.getUser_id());
        }
        catch(Exception e) {
             status = -1 ;
        }
        
        if(userRegistration == null && status == -1)
        	return -1 ;
        if(userRegistration == null)
        	return 0 ;
        
        if(userData.getPassword() != null)
        	userRegistration.setPassword(userData.getPassword());
        
        if(userData.getFirst_name() != null)
        	userRegistration.setFirst_name(userData.getFirst_name());
         
        if(userData.getLast_name() != null)
        	userRegistration.setLast_name(userData.getLast_name());
        
        if(userData.getForget_password_question() != null)
        	userRegistration.setForget_password_question(userData.getForget_password_question());
        
        if(userData.getForget_password_answer() != null)
        	userRegistration.setForget_password_answer(userData.getForget_password_answer());
        
        if(userData.getEmail_id() != null)
        	userRegistration.setEmail_id(userData.getEmail_id());

        session.update(userRegistration);
        session.getTransaction().commit();
        return 1;
		}
        catch(Exception e ) {
        	
        	e.printStackTrace() ;
        	return 2;
        }
        
	}
	
	private String getUserProfile(UserRegistration userData) {
		String userProfile = "User=" +(userData.getUser_id() == null ? "" : userData.getUser_id() )+"\n"+
							 "Password="+(userData.getPassword() == null ? "" : userData.getPassword()) +"\n"+
     						 "FirstName="+(userData.getFirst_name() == null ? "" : userData.getFirst_name()) +"\n"+
							 "LastName="+(userData.getLast_name() == null ? "" : userData.getLast_name()) +"\n"+
							 "EMail="+(userData.getEmail_id() == null ? "" : userData.getEmail_id()) +"\n"+
							 "PasswordQues="+(userData.getForget_password_question() == null ? "" : userData.getForget_password_question()) +"\n"+
							 "PasswordAns="+(userData.getForget_password_answer() == null ? "" : userData.getForget_password_answer()) +"\n"+
							 "CreationDate="+(userData.getCreate_date() == null ? "" : SSOUtils.formatDate(userData.getCreate_date(),"MM/dd/yyyy")) +"\n"+
							 "RootSite="+(userData.getRegistration_site() == null ? "" : userData.getRegistration_site()) +"\n"+
							 "MiddleName="+(userData.getMiddle_name() == null ? "" : userData.getMiddle_name()) +"\n"+
							 "Address="+(userData.getAddress() == null ? "" : userData.getAddress()) +"\n"+
							 "City="+(userData.getCity() == null ? "" : userData.getCity()) +"\n"+
							 "State="+(userData.getState() == null ? "" : userData.getState()) +"\n"+
							 "Country="+(userData.getCountry() == null ? "" : userData.getCountry()) +"\n"+
							 "Pin="+(userData.getPin() == null ? "" : userData.getPin()) +"\n"+
							 "DOB="+(userData.getDob() == null ? "" : SSOUtils.formatDate(userData.getDob(),"MM/dd/yyyy")) +"\n"+
							 "Gender="+(userData.getGender() == null ? "" : userData.getGender()) +"\n"+
							 "Occupation="+(userData.getOccupation() == null ? "" : userData.getOccupation()) +"\n"+
							 "Industry="+(userData.getIndustry() == null ? "" : userData.getIndustry()) +"\n"+
							 "Education="+(userData.getEducation() == null ? "" : userData.getEducation()) +"\n"+
							 "IncomeGroup="+(userData.getIncomegroup() == null ? "" : userData.getIncomegroup()) +"\n"+
							 "TravelFrequency="+(userData.getTravelfrequency() == null ? "" : userData.getTravelfrequency()) +"\n"+
							 "ResidencePhone="+"\n" +   //Where is Residence Phone data coming from
							 "MobilePhone="+(userData.getMobilephone() == null ? "" : userData.getMobilephone()) +"\n"+
							 "OnlineUsage="+(userData.getOnlineusage() == null ? "" : userData.getOnlineusage() )+"\n"+
							 "OnlineShopping="+(userData.getOnlineshopping() == null ? "" : userData.getOnlineshopping()) +"\n"+
							 "MaritalStatus="+(userData.getMaritalstatus() == null ? "" : userData.getMaritalstatus()) +"\n";
											 
		return userProfile;
	}
	
	public String getPasswordfromUserId(String userId){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from UserRegistration ur where ur.id = :uid").setParameter("uid", userId).list();
        session.getTransaction().commit();
        
        if(result != null && result.size() > 0){
        	UserRegistration userData = (UserRegistration)result.get(0);
        	return userData.getPassword();
        }
        
        
        return SSOConstants.ISeeMailUserAuthentication.RECORD_NOT_FOUND;

		
	}
}