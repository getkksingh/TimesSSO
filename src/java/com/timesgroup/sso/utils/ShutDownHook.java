package com.timesgroup.sso.utils;

import com.timesgroup.sso.hibernate.apis.ITimesDataAccessManager;

public class ShutDownHook extends Thread {
	
	public void run(){
		
		UserActivityLogger userActivityLogger=UserActivityLogger.getInstance();
		System.out.println("ShutDownHook "+userActivityLogger.userActivityQueue.size());
		ITimesDataAccessManager iTimesDataAccessManager=new ITimesDataAccessManager();
		iTimesDataAccessManager.insertUserActivity(userActivityLogger.userActivityQueue);
		
	}

}
