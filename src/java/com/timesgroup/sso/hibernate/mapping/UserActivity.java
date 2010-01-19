package com.timesgroup.sso.hibernate.mapping;

import java.util.Date;

public class UserActivity {
	private int id;
	private String user_id;
	private String site_id;
	private Date created_date;	
	private String ip_address;	
	private String activity;
	private String page_url;
	
	public UserActivity(){
		
	}
	
	public UserActivity(String user_id, String siteid, Date visiteddate, String ipaddress, String activity, String page_url)	
	{
		this.user_id=user_id;
		this.site_id=siteid;
		this.created_date=visiteddate;
		this.ip_address=ipaddress;
		this.activity=activity;	
		this.page_url=page_url;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String userId) {
		user_id = userId;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String siteId) {
		site_id = siteId;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date createdDate) {
		created_date = createdDate;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ipAddress) {
		ip_address = ipAddress;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getPage_url() {
		return page_url;
	}

	public void setPage_url(String pageUrl) {
		page_url = pageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
