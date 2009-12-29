package com.timesgroup.sso.hibernate.mapping;

import java.util.Date;

public class UserInvitation {
	
	private Integer id;
	private String refereeUserId;
	private String emailId;
	private String hashCode;
	private Integer status;
	private Date createdDate;
	private String newUserId;
	private String flag;
	
	public String getRefereeUserId() {
		return refereeUserId;
	}
	public void setRefereeUserId(String refereeUserId) {
		this.refereeUserId = refereeUserId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getHashCode() {
		return hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getNewUserId() {
		return newUserId;
	}
	public void setNewUserId(String newUserId) {
		this.newUserId = newUserId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

}
