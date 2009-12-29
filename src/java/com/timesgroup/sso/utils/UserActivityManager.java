package com.timesgroup.sso.utils;

import java.util.Date;
import java.util.Vector;

import org.hibernate.cfg.Configuration;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.hibernate.mapping.UserActivity;

public class UserActivityManager
{
	private String user_id=null;
	private String remoteIP=null;
	private String activity=null;
	private String siteid=null;
	static UserActivityVector useractivities=null;
	static long startTime=0;
	private long userActivityInSeconds=0;
	
	public UserActivityManager()
	{
		
	}
	public UserActivityManager(String user_id,String remoteIP,String activity,String siteid)
	{
		this.user_id=user_id;
		this.remoteIP=remoteIP;
		this.activity=activity;
		this.siteid=siteid;
		
		try{
			addUserActivity();
		}catch(Exception e)
		{
			System.out.println("Error .");
		}
	}

	private void addUserActivity() {
		if(useractivities==null)
			useractivities=new UserActivityVector();
        Date now = new Date();
        UserActivity userActivity = 
        		new UserActivity(this.user_id, this.siteid, now,this.remoteIP, this.activity);
        
        useractivities.addElement(userActivity);
        
        userActivityInSeconds=SSOConstants.UserActivityConstants.USER_ACTIVITY_RUN_IN_SECONDS;
        
 		if(System.currentTimeMillis() - startTime > userActivityInSeconds * 1000 ) 
        {
            Vector batches = (Vector) useractivities.clone();
            useractivities=new UserActivityVector();
            new UserActivityBatchUpdate(batches);
    		startTime = System.currentTimeMillis() ;
        }
	}
}
