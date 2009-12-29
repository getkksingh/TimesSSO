package com.timesgroup.sso.hibernate.mapping;

import java.util.Date;

public class UserMapping {

	private String userId;
	private String emailId;
	private Date createdDate;
	private String itimesId;
	private String credentialType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getItimesId() {
		return itimesId;
	}

	public void setItimesId(String itimesId) {
		this.itimesId = itimesId;
	}

	public String getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}

}
