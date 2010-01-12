package com.timesgroup.sso.hibernate.apis;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.timesgroup.sso.hibernate.mapping.AliasMapping;
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
	
	public int ifEmailIdAvailable(String emailId){
		
		Session session = null;
		Transaction tx = null;
		
		try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx=session.beginTransaction();
				List result = session.createQuery("from AliasMapping am where am.aliasId = :ameid")
				.setParameter("ameid", emailId)
				.list();
			
				tx.commit();
				
				if (result != null && result.size()>0)
					return 1;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return 0;
	}
	
	

}
