package com.timesgroup.sso.hibernate.apis;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.mapping.UserIdGenerator;
import com.timesgroup.sso.hibernate.mapping.UserInvitation;
import com.timesgroup.sso.hibernate.mapping.UserMapping;
import com.timesgroup.sso.hibernate.mapping.UserRegistrationItimes;
import com.timesgroup.sso.utils.HibernateUtil;

public class ITimesDataAccessManager {

	final Logger mylogger = Logger.getLogger(ITimesDataAccessManager.class);

	public String getUserIdByEmail(String emailId) {

		UserMapping userMapping = null;
		Session session = null;
		Transaction tx =  null;

		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			userMapping = (UserMapping) session.get(UserMapping.class,emailId);
			tx.commit();

			if (userMapping == null)// we got no record
				return SSOConstants.GetUserIdByEmailConstants.RECORD_NOT_FOUND;
		
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		mylogger.debug(userMapping.getUserId());
		return userMapping.getUserId();
	}

	public String getEmailIdByUserId(String userId) {

		UserMapping userMapping = null;
		Session session = null;
		Transaction tx = null;

		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			List result = session.createQuery("from UserMapping um where um.userId = :uid  and um.credentialType!='itimes.com'")
						.setParameter("uid", userId).list(); // TODO
			tx.commit();
			
			if (result != null && result.size() > 0) {

				if (result.size() > 1)
					return SSOConstants.GetEmailIdByUserIdConstants.MORETHANONEEMAILIDS_ERROR_MESSAGE;
				else
					// we have a unique record
					userMapping = (UserMapping) result.get(0);
			}

			if (userMapping == null)// we got no record
				return SSOConstants.GetEmailIdByUserIdConstants.RECORD_NOT_FOUND;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		mylogger.debug(userMapping.getEmailId());
		return userMapping.getEmailId();
	}

	public String getITimesIdByEmailId(String emailId) {

		Session session = null;
		Transaction tx = null;
		UserMapping userMapping = null;

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			userMapping = (UserMapping) session.get(UserMapping.class,emailId);
			tx.commit();
			
			if (userMapping == null)// we got no record
				return SSOConstants.GetUserIdByEmailConstants.RECORD_NOT_FOUND;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		mylogger.debug(userMapping.getItimesId());
		return userMapping.getItimesId();
	}

	public String getITimesIdByUserId(String userId) {
		
		Session session = null;
		Transaction tx = null;
		UserMapping userMapping = null;
		String itimesId = null;

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			List result = session.createQuery("from UserMapping um where um.userId = :uid  and um.credentialType!='itimes.com'")
					.setParameter("uid", userId).list(); // TODO

			tx.commit();
			
			if (result != null && result.size() > 0) {
				userMapping = (UserMapping) result.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		if(userMapping!=null){
			itimesId=userMapping.getItimesId();
		}
		
		return itimesId;
	}

	public boolean isUserExistsInUserMapping(String userId, String emailId) {

		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			List result = session.createQuery("from UserMapping um where um.userId = :uid  "
										+ " and um.emailId= :eid and um.credentialType!='itimes.com'")
						.setParameter("uid", userId).setParameter("eid", emailId).list(); // TODO

			tx.commit();
			
			if (result != null && result.size() > 0) {
				return true;
			} 
		
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	public int updateITimesId(String userId, String itimesId) {

		Session session = null;
		Transaction tx=null;

		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			
			List result = session.createQuery("from UserMapping um where um.userId = :uid")
					.setParameter("uid", userId).list(); // TODO
	
			if (result != null && result.size() >= 0){
					
					if(result.size()>=2){ /*WE ALREADY HAVE MAPPING*/
						tx.commit();
						return 2;
					}
					
					if(result.size()==1){//then we have to update and insert
						
						//Update
						UserMapping userMapping = (UserMapping) result.get(0);
						userMapping.setItimesId(itimesId);
						session.update(userMapping);
						
						//Insert
						UserMapping tmp=new UserMapping();
						tmp.setUserId(userId);
						tmp.setItimesId(itimesId);
						tmp.setCreatedDate(userMapping.getCreatedDate());
						tmp.setEmailId(itimesId);
						tmp.setCredentialType("itimes.com");
						
						session.save(tmp);
						tx.commit();
						return 1;
					}
					
					if(result.size()==0)/*NO RECORDS FOUND*/
						tx.commit();
						return 0;
			} else{
				
				tx.commit();
			}
			
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean ifUserIdExists(String userId){
		
		Session session = null;
		Transaction tx = null;

		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			List result = session.createQuery("from UserMapping um where um.userId = :uid ")
				.setParameter("uid", userId).list(); // TODO
			tx.commit();
			
			if (result != null && result.size() > 0){
				return true;
			}	
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}
	
	public int addUserMapping(String userId,String emailId,String credentialType){
		
		Session session = null;
		Transaction tx = null;

		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			
			UserMapping tmp=new UserMapping();
			tmp.setUserId(userId);
			tmp.setCreatedDate(new Date());
			tmp.setEmailId(emailId);
			tmp.setCredentialType(credentialType);
			
			session.save(tmp);
			tx.commit();
			
			return 1;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public List<UserRegistrationItimes> fetchInactiveUsers(String emailId){
		
		List<UserRegistrationItimes> inActiveUsers=null;
		Session session = null;
		Transaction tx = null;

		try {

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			List result = session.createQuery("from UserRegistrationItimes ur where ur.email_id = :eid and ur.usertype=:ut")
					.setParameter("eid", emailId)
					.setParameter("ut", new Integer("0"))
					.list(); // TODO

			tx.commit();
			
			if (result != null && result.size() > 0){
				inActiveUsers=result;
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return inActiveUsers;
	}

	public int addDirectUserMapping(String userId,String emailId,String itimesId,String credentialType){
		
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			
			mylogger.debug(userId+emailId+itimesId+credentialType);
			
			List result = session.createQuery("from UserMapping um where um.emailId = :eid")
			.setParameter("eid", emailId).list(); 

			if (result != null && result.size() >= 1){
				/*MAPPING ALREADY EXISTS*/
				tx.commit();
				return 1;
			}else{
				
				/*INSERT A MAPPING*/
				UserMapping tmp=new UserMapping();
				tmp.setUserId(userId);
				tmp.setCreatedDate(new Date());
				tmp.setEmailId(emailId);
				tmp.setCredentialType(credentialType);
				tmp.setItimesId(itimesId);
				session.save(tmp);
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public int insertUserProfile(String emailId, String firstName,String lastName,
			Date dateOfBirth,String password,String ipAddress,
			String siteReg, String gender,String city, Date date){
		
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			
			/*Get new Itimes Id*/
			UserIdGenerator userIdGenerator=new UserIdGenerator();
			session.save(userIdGenerator);
			int id=(Integer)session.createQuery("SELECT MAX(id) FROM UserIdGenerator").uniqueResult();
			
			
			UserRegistrationItimes userRegistration=new UserRegistrationItimes();
			userRegistration.setUser_id("iti"+id);
			userRegistration.setEmail_id(emailId);
			userRegistration.setFirst_name(firstName);
			userRegistration.setLast_name(lastName);
			userRegistration.setDob(dateOfBirth);
			userRegistration.setPassword(password);
			userRegistration.setAddress(ipAddress);
			userRegistration.setRegistration_site(siteReg);
			userRegistration.setGender(gender.toCharArray()[0]);
			userRegistration.setCity(city);
			userRegistration.setCreate_date(date); //TODO
			userRegistration.setDateregistered(date); //TODO
			session.save(userRegistration);
			tx.commit();
			
			return id;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public String updateUserProfile(String emailId,String firstName,
			String lastName ,Date dateOfBirth,String password,String gender,String city){
		
		Session session = null;
		Transaction tx=null;
		String userName="";
		
		try {
				
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			
			/*List result = session.createQuery("from UserRegistrationItimes urt where urt.user_id = :urt")
			.setParameter("urt", "iti12345")
			.list(); // TODO
*/			
			List result = session.createQuery("from UserRegistrationItimes urt where urt.email_id = :urt")
			.setParameter("urt", emailId)
			.list(); // TODO
		
			if (result != null && result.size() >= 1){
				
				UserRegistrationItimes userRegistration = (UserRegistrationItimes) result.get(0);
				userName=userRegistration.getUser_id();
				
				userRegistration.setEmail_id(emailId);
				userRegistration.setFirst_name(firstName);
				userRegistration.setLast_name(lastName);
				userRegistration.setDob(dateOfBirth);
				userRegistration.setPassword(password);
				userRegistration.setGender(gender.toCharArray()[0]);
				userRegistration.setCity(city);
				session.update(userRegistration);
			}
			
			tx.commit();
			return userName;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return userName;
	}
	
	public boolean isUserIdAlreadyExists(String userId){
		
		Session session = null;
		Transaction tx = null;
		
		try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx=session.beginTransaction();
				List result = session.createQuery("from UserRegistrationItimes urt where urt.user_id = :urt")
				.setParameter("urt", userId)
				.list(); // TODO
			
				tx.commit();
				if (result != null && result.size()>0)
					return true;
				
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean changePassword(String userId, String oldpassword, String newPassword){
		
		Session session = null;
		Transaction tx = null;
		boolean isPasswordChanged=false;
		
		try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx=session.beginTransaction();
			
				List result = session.createQuery("from UserRegistrationItimes urt where urt.user_id = :urt" +
						" and urt.password= :urtp")
				.setParameter("urt", userId)
				.setParameter("urtp", oldpassword)
				.list();
			
				if (result != null && result.size() == 1){
					
					UserRegistrationItimes userRegistration = (UserRegistrationItimes) result.get(0);
					userRegistration.setPassword(newPassword);
					session.update(userRegistration);
					isPasswordChanged=true;
				}
			tx.commit();	
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return isPasswordChanged;
	}
	
	public int checkActivatedUser(String emailId){
		
		Session session = null;
		Transaction tx = null;
		
		try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx=session.beginTransaction();
				
				List result = session.createQuery("from UserRegistrationItimes urt where urt.email_id = :urtem")
				.setParameter("urtem", emailId)
				.list(); // TODO
			
				tx.commit();
				
				if (result != null && result.size()==1){
					
					UserRegistrationItimes userRegistration = (UserRegistrationItimes) result.get(0);
					return userRegistration.getUsertype();
				}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public boolean updateEmailId(String userId,String oldEmailId, String newEmailId){
		
		Session session = null;
		Transaction tx = null;
		
		try {
			 	session = HibernateUtil.getSessionFactory().getCurrentSession();
			 	tx = session.beginTransaction();
				
			 	/*CHECK IF NEW EMAIL ID ALREADY EXISTS*/
				List result = session.createQuery(
				"from UserRegistrationItimes urt where urt.email_id = :urteid")
				.setParameter("urteid", newEmailId)
				.list(); 
			
				if (result != null && result.size()==1){
				
					mylogger.info(SSOConstants.UpdateEmailId.MESSAGE_EMAILID_EXISTS);
					return false;
				}
				
				UserRegistrationItimes userRegistrationItimes = (UserRegistrationItimes) session.get(UserRegistrationItimes.class,
						userId);
				if (userRegistrationItimes!=null){
					
					//we have found the record and we have to update two tables
					userRegistrationItimes.setEmail_id(newEmailId);
					session.update(userRegistrationItimes);
				}
				
				UserMapping	userMapping = (UserMapping) session.get(UserMapping.class,oldEmailId);
				
				if(userMapping!=null){
					
					UserMapping tmp=new UserMapping();
					tmp.setEmailId(newEmailId);
					tmp.setUserId(userMapping.getUserId());
					tmp.setItimesId(userMapping.getItimesId());
					tmp.setCreatedDate(userMapping.getCreatedDate());
					tmp.setCredentialType(userMapping.getCredentialType());
					
					session.save(tmp);
					session.delete(userMapping);
				}
			
			tx.commit();
			return true;
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return false;
	}
	
	public UserRegistrationItimes getInactiveUserDetail(String emailId){
		
		Session session = null;
		Transaction tx = null;
		UserRegistrationItimes userRegistrationItimes=null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			List result = session.createQuery(
				"from UserRegistrationItimes urt where urt.email_id = :urteid and urt.usertype= :urtut")
			.setParameter("urteid", emailId)
			.setParameter("urtut", new Integer("0"))
			.list(); 
	
			tx.commit();
			if (result != null && result.size()==1){ 
				
				userRegistrationItimes=(UserRegistrationItimes) result.get(0);
				return userRegistrationItimes;
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return userRegistrationItimes;
	}
	
	public int getActivationStatus(String userId){
		
		Session session = null;
		Transaction tx = null;
		UserRegistrationItimes userRegistrationItimes=null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			List result = session.createQuery(
				"from UserRegistrationItimes urt where urt.user_id = :urt")
			.setParameter("urt", userId)
			.list(); 
			
			tx.commit();
			
			if (result != null && result.size()==1){ 
				
				userRegistrationItimes=(UserRegistrationItimes) result.get(0);
				return userRegistrationItimes.getUsertype();
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public List<UserInvitation> getInviteeList(String userId){
		
		Session session = null;
		Transaction tx = null;
		mylogger.debug("USERID="+userId);
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			List result = session.createQuery(
				"from UserInvitation as uin where uin.emailId in ( select uin1.emailId from UserInvitation" +
				" as uin1 where uin1.newUserId = :uid)")
			.setParameter("uid", userId)
			.list(); 
	
			tx.commit();
			if (result != null && result.size()>0){ 
				return result;
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateInactiveUserSite(String emailId,String siteReg){
		
		Session session = null;
		Transaction tx = null;
		
		mylogger.debug("EMAILID="+emailId+" Site Regis="+siteReg);
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			
			session.createQuery(
			"update UserRegistrationItimes urt set urt.registration_site= :urtrs" +
			" where urt.email_id = :urteid and urt.usertype = :urtut")
			.setParameter("urtrs", siteReg)
			.setParameter("urteid", emailId)
			.setParameter("urtut", new Integer("0"))
			.executeUpdate(); 
			
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}
		
	public void saveInvitationInformation(List userInvitations){
		
		Session session = null;
		Transaction tx = null;
		
		try{
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			
			if (userInvitations != null && userInvitations.size()>0){ 
					
				Iterator iter=userInvitations.iterator();
				while(iter.hasNext()){
					
					UserInvitation userInvitation=(UserInvitation)iter.next();
					session.save(userInvitation);
				}
			}
			tx.commit();			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
	}
	
	public List checkInvitation(String userId){
		
		Session session = null;
		Transaction tx = null;
		mylogger.debug("USERID="+userId);
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			List result = session.createQuery("select itimesinvite.newUserId from UserInvitation as  itimesinvite" +
					" where itimesinvite.refereeUserId=:ruid and status=:st")
			.setParameter("ruid", userId)
			.setParameter("st", new Integer("1"))
			.list(); 
			
			tx.commit();
	
			if (result != null && result.size()>0){ 
				return result;
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return null;
	}
	
	public UserRegistrationItimes getUserProfile(String userId){
		
		UserRegistrationItimes userRegistrationItimes = null;
		Session session = null;
		Transaction tx = null;

		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			userRegistrationItimes = (UserRegistrationItimes) session.get(UserRegistrationItimes.class,
						userId);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
	    return userRegistrationItimes;
	}
	
	public String fetchPassword(String userId){
		
		Session session = null;
		Transaction tx = null;
		UserRegistrationItimes userRegistrationItimes = null;
		mylogger.debug("userid="+userId);
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			userRegistrationItimes = (UserRegistrationItimes) session.get(UserRegistrationItimes.class,
						userId);
			tx.commit();
			
			if(userRegistrationItimes!=null) 
				return userRegistrationItimes.getPassword();
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return "false";
	}
	
	public int updateInactiveUser(String emailId){
		
		
		
		
		return 0;
	}
}

