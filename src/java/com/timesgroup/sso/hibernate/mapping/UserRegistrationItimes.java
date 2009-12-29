package com.timesgroup.sso.hibernate.mapping;

import java.util.Date;

public class UserRegistrationItimes extends UserRegistration{

	private String mprofiles ;
	private String subscriptions;
	private Date dateregistered;
	private Date datecreated;
	private Date datelastchanged;
	private Integer usertype;
	private Integer category;

	public String getMprofiles() {
		return mprofiles;
	}

	public void setMprofiles(String mprofiles) {
		this.mprofiles = mprofiles;
	}

	public String getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(String subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Date getDateregistered() {
		return dateregistered;
	}

	public void setDateregistered(Date dateregistered) {
		this.dateregistered = dateregistered;
	}

	public Date getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	public Date getDatelastchanged() {
		return datelastchanged;
	}

	public void setDatelastchanged(Date datelastchanged) {
		this.datelastchanged = datelastchanged;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
	
	
	
	
	
	
	
	
	
	/*private String user_id;
	private String password;
	private String email_id;
	private String first_name;
	private String last_name;
	private String forget_password_question;
	private String forget_password_answer;
	private String registration_site;
	private String middle_name;
	private String address;
	private String city;
	private String state;
	private String country;
	private String pin;
	private Character gender;
	private String occupation;
	private String industry;
	private String education;
	private String incomegroup;
	private String referrel;
	private String travelfrequency;
	private String airtravel;
	private Character pop;
	private Character imap;
	private String res_phone;
	private String mobilephone;
	private Character onlineusage;
	private Character onlineshopping;
	private Character maritalstatus;
	private String sms_status;
	private Character profile_status;
	private Date create_time;
	private Date create_date;
	private Date dob;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String userId) {
		user_id = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String emailId) {
		email_id = emailId;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String firstName) {
		first_name = firstName;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String lastName) {
		last_name = lastName;
	}

	public String getForget_password_question() {
		return forget_password_question;
	}

	public void setForget_password_question(String forgetPasswordQuestion) {
		forget_password_question = forgetPasswordQuestion;
	}

	public String getForget_password_answer() {
		return forget_password_answer;
	}

	public void setForget_password_answer(String forgetPasswordAnswer) {
		forget_password_answer = forgetPasswordAnswer;
	}

	public String getRegistration_site() {
		return registration_site;
	}

	public void setRegistration_site(String registrationSite) {
		registration_site = registrationSite;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middleName) {
		middle_name = middleName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getIncomegroup() {
		return incomegroup;
	}

	public void setIncomegroup(String incomegroup) {
		this.incomegroup = incomegroup;
	}

	public String getReferrel() {
		return referrel;
	}

	public void setReferrel(String referrel) {
		this.referrel = referrel;
	}

	public String getTravelfrequency() {
		return travelfrequency;
	}

	public void setTravelfrequency(String travelfrequency) {
		this.travelfrequency = travelfrequency;
	}

	public String getAirtravel() {
		return airtravel;
	}

	public void setAirtravel(String airtravel) {
		this.airtravel = airtravel;
	}

	public Character getPop() {

		return pop;
	}

	public void setPop(Character pop) {
		this.pop = pop;
	}

	public Character getImap() {
		return imap;
	}

	public void setImap(Character imap) {
		this.imap = imap;
	}

	public String getRes_phone() {
		return res_phone;
	}

	public void setRes_phone(String res_phone) {
		this.res_phone = res_phone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public Character getOnlineusage() {
		return onlineusage;
	}

	public void setOnlineusage(Character onlineusage) {
		this.onlineusage = onlineusage;
	}

	public Character getOnlineshopping() {
		return onlineshopping;
	}

	public void setOnlineshopping(Character onlineshopping) {
		this.onlineshopping = onlineshopping;
	}

	public Character getMaritalstatus() {
		return maritalstatus;
	}

	public void setMaritalstatus(Character maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public String getSms_status() {
		return sms_status;
	}

	public void setSms_status(String smsStatus) {
		sms_status = smsStatus;
	}

	public Character getProfile_status() {
		return profile_status;
	}

	public void setProfile_status(Character profileStatus) {
		profile_status = profileStatus;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date createDate) {
		create_date = createDate;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}*/
}
