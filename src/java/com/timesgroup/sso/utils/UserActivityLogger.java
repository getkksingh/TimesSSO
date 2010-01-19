package com.timesgroup.sso.utils;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

import com.timesgroup.sso.hibernate.apis.ITimesDataAccessManager;
import com.timesgroup.sso.hibernate.mapping.UserActivity;

public class UserActivityLogger implements Runnable{

	private static Queue<UserActivity> userActivityQueue = new LinkedList<UserActivity>();
	final Logger mylogger = Logger.getLogger(TimesMail.class);
	public static UserActivityLogger userActivityLogger = null;
	
	private UserActivityLogger() {}
	
	public static UserActivityLogger getInstance() {
		
		if(userActivityLogger==null) {
			userActivityLogger=new UserActivityLogger();
		}
		return userActivityLogger;
	}
	
	public void run() {
		
		while(true){
			
			if(userActivityQueue!=null && userActivityQueue.size()>=20){
				
				ITimesDataAccessManager iTimesDataAccessManager=new ITimesDataAccessManager();
				iTimesDataAccessManager.insertUserActivity(userActivityLogger);
			}
			
		try {
				  Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
	}
	
	public synchronized void enQueueUserActivity(UserActivity userActivity){
		
		userActivityQueue.add(userActivity);
	}

	
	public synchronized UserActivity deQueueUserActivity(){
		
		if(userActivityQueue!=null && userActivityQueue.size()==0) return null;
		
		return userActivityQueue.remove();
	}
	
	public synchronized int getQueueSize(){
		
		return userActivityQueue.size();
	}
	
    public static void main(String[] args) {
		
    	
    	
    	UserActivityLogger userActivityLogger = UserActivityLogger.getInstance();
		
		for(int i=0;i<100;i++){
			 
			UserActivity userActivity=new UserActivity();
			userActivity.setActivity("Login");
			userActivity.setCreated_date(new Date());
			userActivity.setIp_address("172.28.121.214");
			userActivity.setUser_id(i+"");
			userActivityLogger.enQueueUserActivity(userActivity);
		}
		
		
		new Thread(userActivityLogger).start();
		System.out.println("Daemon Started!!!");
		
		ShutDownHook shutDownHook = new ShutDownHook();
		new Thread(shutDownHook).start();
		System.out.println("Hook Started!!!");
		Runtime.getRuntime().addShutdownHook( shutDownHook );
		
		
	}


}
