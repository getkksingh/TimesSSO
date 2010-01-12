package com.timesgroup.sso.hibernate.mapping;

import java.util.Date;

public class AliasMapping {
	
	private String aliasId;
	private String userId;
	private String userProfile;
	private Character isPrimary;
	private Character userType;
	private String hashCode;
	private Date createdDate;
	private Date updatedDate;
	
	public String getAliasId() {
		return aliasId;
	}
	public void setAliasId(String aliasId) {
		this.aliasId = aliasId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}
	public Character getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(Character isPrimary) {
		this.isPrimary = isPrimary;
	}
	public Character getUserType() {
		return userType;
	}
	public void setUserType(Character userType) {
		this.userType = userType;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getHashCode() {
		return hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
	

}
