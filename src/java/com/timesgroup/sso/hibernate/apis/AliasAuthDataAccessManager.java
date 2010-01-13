package com.timesgroup.sso.hibernate.apis;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.mapping.AliasMapping;
import com.timesgroup.sso.hibernate.mapping.UserRegistration;
import com.timesgroup.sso.hibernate.mapping.UserRegistrationItimes;
import com.timesgroup.sso.utils.HibernateUtil;

public class AliasAuthDataAccessManager {
	
	public int insertUserProfile(String aliasId, String userId, String userProfile, String hashCode, Date createdDate){
		
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			
			AliasMapping aliasMapping=new AliasMapping();
			aliasMapping.setAliasId(aliasId);
			aliasMapping.setUserId(userId);
			aliasMapping.setUserType('0');
			aliasMapping.setIsPrimary('0');
			aliasMapping.setHashCode(hashCode);
			aliasMapping.setUserProfile(userProfile);
			aliasMapping.setCreatedDate(createdDate);
			
			session.save(aliasMapping);
			tx.commit();
			
			return 1;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return 0;
	}
	
	public String ifEmailIdAvailable(String emailId){
		
		Session session = null;
		Transaction tx = null;
		
		try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx=session.beginTransaction();
				List result = session.createQuery("from AliasMapping am where am.aliasId = :ameid")
				.setParameter("ameid", emailId)
				.list();
			
				tx.commit();
				
				if (result != null && result.size()>0){
					
					AliasMapping aliasMapping = (AliasMapping) result.get(0);
					return aliasMapping.getUserProfile();
				}
				
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return "";
	}
	
	public boolean updateUserProfile(String emailId,String firstName,
			String lastName,String password,Date dob,String ipAddress,String siteReg,String gender, 
			String city,String state,String country,String secQues,String secAns,String mobile,String pin){
		
		
		Session session = null;
		Transaction tx=null;
		
		try {
				
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			
			List result = session.createQuery("from UserRegistration urt where urt.email_id = :urt")
			.setParameter("urt", emailId)
			.list(); // TODO
		
			if (result != null && result.size() >= 1){
				
				UserRegistration userRegistration = (UserRegistration) result.get(0);
				
				userRegistration.setFirst_name(firstName);
				userRegistration.setLast_name(lastName);
				userRegistration.setDob(dob);
				userRegistration.setPassword(password);
				userRegistration.setGender(gender.toCharArray()[0]);
				userRegistration.setAddress(ipAddress);
				userRegistration.setRegistration_site(siteReg);
				userRegistration.setCity(city);
				userRegistration.setState(state);
				userRegistration.setCountry(country);
				userRegistration.setForget_password_question(secQues);
				userRegistration.setForget_password_answer(secAns);
				userRegistration.setMobilephone(mobile);
				userRegistration.setPin(pin);
				
				session.update(userRegistration);
			}
			
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean activateEmailId(String emailId, UserRegistration userRegistration){
		
		Session session = null;
		Transaction tx = null;
		
		try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx=session.beginTransaction();
				int updatedRecords = session.createQuery("update AliasMapping am set am.userType = :amut where am.aliasId = :ameid")
				.setString("ameid", emailId)
				.setCharacter("amut", '1')
				.executeUpdate();
			
				session.save(userRegistration);
				
				tx.commit();
				
				if(updatedRecords>0) return true;
				
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}
	
	public UserRegistration getUserProfile(String emailId){
		
		UserRegistration userRegistration=null;
		Session session = null;
		Transaction tx = null;
		
		try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx=session.beginTransaction();
				
				List result = session.createQuery("from UserRegistration ur where ur.user_id  in" +
						"(select am.userId from AliasMapping am where am.aliasId = :ameid)")
				.setParameter("ameid", emailId)
				.list();
			
				tx.commit();
				
				if (result != null && result.size()==1){
					
					userRegistration = (UserRegistration) result.get(0);
					System.out
							.println("AliasAuthDataAccessManager.getUserProfile()"+userRegistration);
				}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		
		return userRegistration;
	}
	

    public static void main(String[] args) {
    	
		AliasAuthDataAccessManager aliasAuthDataAccessManager=new AliasAuthDataAccessManager();
		UserRegistration userRegistration=aliasAuthDataAccessManager.getUserProfile("nitin@gmail.com");
		
		System.out.println("AliasAuthDataAccessManager.enclosing_method()"+userRegistration);
		if(userRegistration!=null){
			
			String str=SSOConstants.XML_URL+"<NewDataSet>\n<Table>\n";
			if(userRegistration.getUser_id()!=null)
				str+="<usr_id_vc>"+userRegistration.getUser_id()+"</usr_id_vc>\n"; 
			if(userRegistration.getUser_id()!=null)
			str+="<eml_vc>"+userRegistration.getEmail_id()+"</eml_vc>\n"; 
			if(userRegistration.getPassword()!=null)
			str+="<psswrd_vc>"+userRegistration.getPassword()+"</psswrd_vc>\n"; 
			str+="<CRT_DT>"+userRegistration.getCreate_date()+"</CRT_DT>\n"; 
			str+="<DateRegistered>"+userRegistration.getCreate_date()+"</DateRegistered>\n"; 
			str+="<site_reg>"+userRegistration.getRegistration_site()+"</site_reg>\n"; 
			str+="<frst_nm_vc>"+userRegistration.getFirst_name()+"</frst_nm_vc>\n";  
			str+="<lst_nm_vc>"+userRegistration.getLast_name()+"</lst_nm_vc>\n";  
			str+="<Address>"+userRegistration.getAddress()+"</Address>\n";  
			str+="<Gender>"+userRegistration.getGender()+"</Gender>\n";
			str+="<city>"+userRegistration.getCity()+"</city>\n";  
			str+="<state>"+userRegistration.getState()+"</state>\n";  
			str+="<country>"+userRegistration.getCountry()+"</country>\n";  
			str+="<dob>"+userRegistration.getDob()+"</dob>\n";  
			str+="<pin>"+userRegistration.getPin()+"</pin>\n"; 
			str+="<frgt_psswrd_qstn_vc>"+userRegistration.getForget_password_question()+"</frgt_psswrd_qstn_vc>\n";
			str+="<frgt_psswrd_answr_vc>"+userRegistration.getForget_password_answer()+"</frgt_psswrd_answr_vc>\n";
			str+="<mobilephone>"+userRegistration.getMobilephone()+"</mobilephone>\n";
			str+="<Referrel>"+userRegistration.getReferrel()+"</Referrel>\n";
			str+="<UserType>1</UserType>\n"; 
			str+="</Table>\n";
			str+="</NewDataSet>\n";
			
			System.out.println("AliasAuthDataAccessManager.main()"+str);
		}
	}	
}
