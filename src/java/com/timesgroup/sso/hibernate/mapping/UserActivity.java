package com.timesgroup.sso.hibernate.mapping;

import java.util.Date;

public class UserActivity {
	private long activity_id;
	private String user_id;
	private String siteid;
	private Date visiteddate;	
	private String ipaddress;	
	private String activity;
	
	public UserActivity(String user_id, String siteid, Date visiteddate, String ipaddress, String activity)	
	{
		this.user_id=user_id;
		this.siteid=siteid;
		this.visiteddate=visiteddate;
		this.ipaddress=ipaddress;
		this.activity=activity;	
	}

	public long getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}	

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSiteid() {
		return siteid;
	}
	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}
	public Date getVisiteddate() {
		return visiteddate;
	}
	public void setVisiteddate(Date visiteddate) {
		this.visiteddate = visiteddate;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
}
