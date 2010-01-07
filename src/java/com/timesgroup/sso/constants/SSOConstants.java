package com.timesgroup.sso.constants;


public class SSOConstants {

	public final static String XML_URL="<?xml version='1.0' encoding='utf-8' ?>\n";
	public final static String VALID_USERID_PATTERN="[a-z0-9_]*";
	public final static String VALID_EMAILID_PATTERN=".+@.+\\.[a-z]+";
	public final static String VALID_INTEGER_PATTERN="[0-9]*";
	public final static String ServerURL="http://www.itimes.com/login.php?hs=";
	
	
	public final class CheckUserCredentialsServletConstants {

		public final static String LOGINSUCESSRESPONSE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<boolean xmlns=\"%s\">true</boolean>";
		public final static String LOGINFAILURERESPONSE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<boolean xmlns=\"%s\">false</boolean>";
		public final static String MISSINGUSERNAMEREPONSE = "Missing parameter: UserName.";
		public final static String MISSINGPASSWORDRESPONSE = "Missing parameter: Password.";
		public final static String PARAMUSERNAME = "username";
		public final static String PARAMPASSWORD = "password";
	}

	public final class CheckUserExistServletConstants {

		public final static String PARAMUSER = "User";
		public final static String USER_EXISTS_MESSAGE = "USER_EXISTS=true";
		public final static String USER_DOES_NOT_EXISTS_MESSAGE = "USER_EXISTS=false";
		public final static String USER_EXISTANCE_ERROR_MESSAGE = "USER_EXISTS=error";
		public final static String USER_INVALIDE_ERROR_MESSAGE = "Invalid User";
		public final static String USER_ID_NOT_PERMISSIBLE_CHARACTERS = "@()[]{}^*=%!;,:|<>?";

	}

	public final class CheckValidUserServletConstants {

		public final static String PARAMUSER = "User";
		public final static String PARAMPASSWORD = "Password";
		public final static String NOT_VALIDE_USER_WITH_GIVEN_CREDENTIALS = "VALID_USER=false";
		public final static String IS_VALIDE_USER_WITH_GIVEN_CREDENTIALS = "VALID_USER=true";
		public final static String ERROR_WITH_PASSED_REQUEST_PARAMETERS = "VALID_USER=error";

	}

	public final class UserProfileServletConstants {

		public final static String PARAMUSER = "User";
		public final static String INVALIDE_USER_PROFILE_MESSAGE = "GETRESOURCE=invaliduser";
		public final static String ERROR_WITH_PASSED_REQUEST_PARAMETERS = "GETRESOURCE=error";

	}

	public final class ForgotPasswordServletConstants {
		public final static String PARAMUSER = "User";
		public final static String PARAM_FORGOTPASSWORD_QUESTION = "PasswordQues";
		public final static String PARAMP_FORGOT_PASSWORD_ANSWER = "PasswordAns";
		public final static String ERROR_WITH_PASSED_REQUEST_PARAMETERS = "GETPASSWORD=error";
		public final static String CORRECT_ANSWER_TO_FORGOT_PASSWORD_QUESTION = "password=%s";
		public final static String WRONG_ANSWER_TO_FORGOT_PASSWORD_QUESTION = "GETPASSWORD=false";
		public final static String PASSWORD_QUESTION_FIELD_BLANK_MESSAGE = "PasswordQuestion=FieldBlank ";
		public final static String PASSWORD_ANSWER_FIELD_BLANK_MESSAGE = "asswordAnswer=FieldBlank";
	}

	public final class AddUserServletConstants {

		public final static String PARAM_USER = "User";
		public final static String PARAM_PASSWORD = "Password";
		public final static String PARAM_PASSWORD_QUESTION = "PasswordQues";
		public final static String PARAM_PASSWORD_ANSWER = "PasswordAns";
		public final static String PARAM_FIRST_NAME = "FirstName";
		public final static String PARAM_LAST_NAME = "LastName";
		public final static String PARAM_EMAIL = "Email";
		public final static String PARAM_ROOT_SITE = "RootSite";
		public final static String USER_EXISTS_MESSAGES = "USER_SAVED=exist";
		public final static String USER_SAVED_MESSAGES = "USER_SAVED=true";
		public final static String ERROR_WITH_PASSED_REQUEST_PARAMETERS = "USER_SAVED=error";
		public final static String BLANK_FIELD_MESSAGE = "FieldBlank ";
		public final static String USER_ID_NOT_PERMISSIBLE_CHARACTERS = "@()[]{}^*=%!;,:|<>?";
		public final static String INVALIDE_CHAR_MESSAGE = "invalidChars ";
		public final static String INVALIDE_EMAIL_MESSAGE = "invalidemail ";

	}

	public final class UpdateUserServletConstants {

		public final static String PARAM_USER = "User";
		public final static String PARAM_PASSWORD = "Password";
		public final static String PARAM_PASSWORD_QUESTION = "PasswordQues";
		public final static String PARAM_PASSWORD_ANSWER = "PasswordAns";
		public final static String PARAM_FIRST_NAME = "FirstName";
		public final static String PARAM_LAST_NAME = "LastName";
		public final static String PARAM_EMAIL = "Email";
		public final static String USER_NOT_EXISTS_MESSAGES = "UPDATE_SUCCESS=notexist";
		public final static String USER_UPDATE_SUCCESS_MESSAGES = "UPDATE_SUCCESS=true";
		public final static String USER_UPDATE_FAILED_MESSAGES = "UPDATE_SUCCESS=false";
		public final static String ERROR_WITH_PASSED_REQUEST_PARAMETERS = "UPDATE_SUCCESS=error";
		public final static String BLANK_FIELD_MESSAGE = "FieldBlank ";
		public final static String USER_ID_NOT_PERMISSIBLE_CHARACTERS = "@()[]{}^*=%!;,:|<>?";
		public final static String INVALIDE_CHAR_MESSAGE = "invalidChars ";
		public final static String INVALIDE_EMAIL_MESSAGE = "invalidemail ";

	}

	public final class ChangePasswordServletConstants {

		public final static String PARAM_USER = "User";
		public final static String PARAM_OLD_PASSWORD = "Password";
		public final static String PARAM_NEW_PASSWORD = "NewPassword";
		public final static String PASSWORD_CHANGED_SUCCESSFULLY_MESSAGE = "PASS_CHANGED=true";
		public final static String PASSWORD_CHANGED_UNSUCCESSFUL_MESSAGE = "PASS_CHANGED=false";
		public final static String PASSWORD_CHANGED_USER_DOES_NOT_EXIST_MESSAGE = "PASS_CHANGED=notexist";
		public final static String ERROR_WITH_PASSED_REQUEST_PARAMETERS = "PASS_CHANGED=error";
		// PASS_CHANGED=error
		public final static String BLANK_FIELD_MESSAGE = "FieldBlank ";

		// PASS_CHANGED=false PASS_CHANGED=true

	}

	public final class GetSecQstnAnsWithPasswordConstants {

		public final static String PARAM_USER = "UserName";
		public final static String SEC_QSTN_ANS_XML_FORMAT = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<NewDataSet><Table><USER>%s</USER><PASSWORD>%s</PASSWORD><SQ>%s</SQ><SA>%s</SA></Table></NewDataSet>";
		public final static String USER_DOES_NOT_EXIST_MESSAGE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<NewDataSet/>";
		public final static String ERROR_WITH_PASSED_REQUEST_PARAMETERS = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<ERROR>Error</ERROR>";
	}

	public final class GetUserProfileXMLConstants {
		public final static String PARAM_USER = "User";
		public final static String USER_PROFILE_XML_FORMAT = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<ProfileDocument><TimesUserProfile><General><USR_ID_VC>%s</USR_ID_VC><PSSWRD_VC>%s</PSSWRD_VC><EML_VC>%s</EML_VC><FRST_NM_VC>%s</FRST_NM_VC><LST_NM_VC>%s</LST_NM_VC><FRGT_PSSWRD_QSTN_VC>%s</FRGT_PSSWRD_QSTN_VC><FRGT_PSSWRD_ANSWR_VC>%s</FRGT_PSSWRD_ANSWR_VC><SITE_REG>%s</SITE_REG><CRT_DT>%s</CRT_DT><M_NAME>%s</M_NAME><ADDRESS>%s</ADDRESS><CITY>%s</CITY><STATE>%s</STATE><COUNTRY>%s</COUNTRY><PIN>%s</PIN><DOB>%s</DOB><GENDER>%s</GENDER><OCCUPATION>%s</OCCUPATION><INDUSTRY>%s</INDUSTRY><EDUCATION>%s</EDUCATION><INCOMEGROUP>%s</INCOMEGROUP><REFERREL>%s</REFERREL><TRAVELFREQUENCY>%s</TRAVELFREQUENCY><AIRTRAVEL>%s</AIRTRAVEL><CRT_TIME>%s</CRT_TIME><POP>%s</POP><IMAP>%s</IMAP><RESPHONE>%s</RESPHONE><MOBILIEPHONE>%s</MOBILIEPHONE><ONLINEUSAGE>%s</ONLINEUSAGE><ONLINESHOPPING>%s</ONLINESHOPPING><MARITALSTATUS>%s</MARITALSTATUS><SMS_STATUS>%s</SMS_STATUS><PROFILE_STATUS>%s</PROFILE_STATUS></General><Interests><AUCTIONS>%s</AUCTIONS><AUTO>%s</AUTO><HEALTH_BEAUTY>%s</HEALTH_BEAUTY><SPITITUALITY>%s</SPITITUALITY><TOYS_GAMES>%s</TOYS_GAMES><CONTEST_FREESTUFF>%s</CONTEST_FREESTUFF><TRAVEL>%s</TRAVEL><SHOPPING>%s</SHOPPING><COMPUTER_TECHNOLOGY>%s</COMPUTER_TECHNOLOGY><BUSINESS_FINANCE>%s</BUSINESS_FINANCE><MUSIC_MOVIES>%s</MUSIC_MOVIES><LIFESTYLE>%s</LIFESTYLE><NEWS>%s</NEWS><SPORTS>%s</SPORTS></Interests><Ownership><FRIDGE>%s</FRIDGE><GRINDER_JUICER>%s</GRINDER_JUICER><COLORTV>%s</COLORTV><CREDITCARD>%s</CREDITCARD><A_C>%s</A_C><CELLPHONE>%s</CELLPHONE><DISHWASHER>%s</DISHWASHER><COOKINGRANGE>%s</COOKINGRANGE><MICROWAVE>%s</MICROWAVE><MUSICSYSTEM>%s</MUSICSYSTEM><COMPUTER>%s</COMPUTER><TWOWHEELER>%s</TWOWHEELER><FOURWHEELER>%s</FOURWHEELER><WMACHINE>%s</WMACHINE></Ownership></TimesUserProfile></ProfileDocument>";
		public final static String USER_DOES_NOT_EXIST_MESSAGE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<ProfileDocument/>";
		public final static String ERROR_WITH_PASSED_REQUEST_PARAMETERS = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<ERROR>Error</ERROR>";
	}

	public final class CookieBasedLoginConstants {
		public final static String PARAM_LOGIN = "login";
		public final static String PARAM_PASSWORD = "passwd";
		public final static String PARAM_NEWREG = "newreg";
		public final static String PARAM_RU = "ru";
		public final static String PARAM_IS = "IS";
		public final static String PARAM_NS = "NS";
		public final static String PARAM_HS = "HS";
		public final static String PARAM_NRU = "nru";

		public final static String PARAM_RUL = "rul";

		public final static String MSCSAUTH_COOKIE_NAME = "MSCSAuthNew";
		public final static String MSCSAUTHDETAIL_COOKIE_NAME = "MSCSAuthDetail";
		public final static String MSCSAUTHDETAILS_COOKIE_NAME = "MSCSAuthDetails";
		public final static String VISITEDSITES_COOKIE_NAME = "VisitedSites";
		public final static String COOKIE_DOMAIN_NAME = ".indiatimes.com";
		public final static String COOKIE_PATH = "/";
	}

	public final class CookieValueDecryptXMLConstants {
		public final static String PARAM_STR = "str";
		public final static String DECRYPTED_VALUE_XML_FORMAT = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<string xmlns=\"http://tempuri.org/\">User=%s</string>";
		public final static String DECRYPT_ERROR_MESSAGE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<ERROR>Error</ERROR>";
		public final static String ERROR_WITH_PASSED_REQUEST_PARAMETERS = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<ERROR>Error</ERROR>";
	}

	public final class UserActivityConstants {
		public final static long USER_ACTIVITY_RUN_IN_SECONDS = 120;
	}
	
	public final class GetUserIdByEmailConstants{
		
		public final static String PARAM_EMAIL="emailid";
		public final static String ERROR_WITH_USERID_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String RECORD_NOT_FOUND = "NO INSTANCE AVAILABLE";
		public final static String ERROR_MESSAGE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
				"<Message>Error While fetching userId</Message><Exception>No value provided for emailId.</Exception></Error>";
	}
	
	public final class GetEmailIdByUserIdConstants{
		
		public final static String PARAM_USERID="userid";
		public final static String ERROR_WITH_EMAILID_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String RECORD_NOT_FOUND = "NO INSTANCE AVAILABLE";
		public final static String ERROR_MESSAGE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
				"<Message>Error While fetching emailId</Message><Exception>No value provided for userId.</Exception></Error>";
		public final static String MORETHANONEEMAILIDS_ERROR_MESSAGE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Error While fetching emailId</Message><Exception>More than one emailids for the user.</Exception></Error>";
	}
	
	public final class GetITimesIdByEmailIdConstants{
		
		public final static String PARAM_EMAIL="emailid";
		public final static String ERROR_WITH_ITIMESID_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String RECORD_NOT_FOUND = "NO INSTANCE AVAILABLE";
		public final static String ERROR_MESSAGE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
				"<Message>Error While fetching Itimes ID</Message><Exception>No value provided for emailId.</Exception></Error>";
	}
	
	public final class GetITimesIdByUserIdConstants{
		
		public final static String PARAM_USERID="userid";
		public final static String ERROR_WITH_ITIMESID_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String RECORD_NOT_FOUND = "NO INSTANCE AVAILABLE";
		public final static String ERROR_MESSAGE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
				"<Message>Error While fetching Itimes ID</Message><Exception>No value provided for userId.</Exception></Error>";
		
	}
	
	public final class IsMappingExists{
		
		public final static String PARAM_USERID="userid";
		public final static String PARAM_EMAILID="emailid";
		public final static String ERROR_WITH_ISMAPPINGEXISTS_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String RECORD_NOT_FOUND = "NO INSTANCE AVAILABLE";
		
	}
	
	public final class UpdateITimesId{
		
		public final static String PARAM_USERID="userid";
		public final static String PARAM_ITIMESID="itimesid";
		public final static String ERROR_WITH_UPDATEITIMESID_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String MAPPINGEXISTS = "MAPPING ALREADY EXISTS";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDITIMESID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid itimesid</Message><Exception>ItimesId cannot be more than 100 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDITIMESID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid itimesid</Message><Exception>ItimesId is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_MAPPINGEXISTS = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E105</ErrCode>" +
		"<Message>Mapping Exists</Message><Exception>Mapping already exists for the given userid and itimesid.</Exception></Error>";
	}
	
	public final class CheckUserIdExists{
		
		public final static String PARAM_USERID="userid";
		public final static String ERROR_WITH_CHECKUSERIDEXISTS_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String RECORD_NOT_FOUND = "NO INSTANCE AVAILABLE";
		public final static String INVALID_USERID="UserId is not valid.Either no value provided for userid or userid has special characters.";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Error While fetching UserId</Message><Exception>No value found for userId.</Exception></Error>";
	}
	
	public final class ISeeMailUserAuthentication{
		
		public final static String PARAM_USERID="userid";
		public final static String PARAM_PASSWORD="password";
		public final static String ERROR_WITH_GETPASSWORDFROMUSERID_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String RECORD_NOT_FOUND = "NO INSTANCE AVAILABLE";
		public final static String INVALID_USERID="UserId is not valid.Either no value provided for userid or userid has special characters.";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Error While fetching Password</Message><Exception>No records found for userId.</Exception></Error>";
	}
	
	public final class AddUserMapping{
		
		public final static String PARAM_USERID="userid";
		public final static String PARAM_EMAILID="emailid";
		public final static String PARAM_CREDENTIALTYPE="credentialType";
		public final static String ERROR_WITH_ADDUSERMAPPING_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String MESSAGE_INVALID_USERID="INVALID USERID";
		public final static String MESSAGE_INVALID_EMAILID="INVALID EMAILID";
		public final static String MESSAGE_SUCCESSFUL_INSERTION="RECORD INSERTED SUCCESSFULLY";
		public final static String MESSAGE_ERROR_INSERTION="UNABLE TO INSERTED RECORD";
		public final static String INVALID_USERID="UserId is not valid.Either no value provided for userid or userid has special characters.";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>UserId length cannot be more than 50 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId cannot be more than 100 characters.</Exception></Error>";
		public final static String ERROR_INSERTION="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E105</ErrCode>" +
		"<Message>Insertion error</Message><Exception>Error while inserting record.</Exception></Error>";
	}
	
	public final class FetchInactiveUsers{
		
		public final static String PARAM_EMAILID="emailid";
		public final static String MESSAGE_INVALID_EMAILID="INVALID EMAILID";
		public final static String MESSAGE_ERROR_FETCHING="UNABLE TO FETCH RECORD";
		public final static String ERROR_MESSAGE_INVALIDEMAILID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId cannot be more than 100 characters.</Exception></Error>";
		public final static String ERROR_FETCHING="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Error While fetching emailid</Message><Exception>Object reference not set to an instance of an object.</Exception></Error>";
	}
	
	public final class AddDirectUserMapping{
		
		public final static String PARAM_USERID="userid";
		public final static String PARAM_EMAILID="emailid";
		public final static String PARAM_ITIMESID="itimesid";
		public final static String PARAM_CREDENTIALTYPE="credentialType";
		public final static String ERROR_WITH_ADDDIRECTUSERMAPPING_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String MESSAGE_INVALID_USERID="INVALID USERID";
		public final static String MESSAGE_INVALID_EMAILID="INVALID EMAILID";
		public final static String MESSAGE_INVALID_ITIMESID="INVALID ITIMESID";
		public final static String MESSAGE_ITIMESID_EMAILID_DONOTMATCH="ITIMESID AND EMAILID DO NOT MATCH";
		public final static String MESSAGE_SUCCESSFUL_INSERTION="RECORD INSERTED SUCCESSFULLY";
		public final static String MESSAGE_ERROR_INSERTION="UNABLE TO INSERTED RECORD";
		public final static String INVALID_USERID="UserId is not valid.Either no value provided for userid or userid has special characters.";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>UserId length cannot be more than 50 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId cannot be more than 100 characters.</Exception></Error>";
	}
	
	public final class InsertUserProfile{
		
		public final static String PARAM_EMAILID="emailId";
		public final static String PARAM_FIRSTNAME="FirstName";
		public final static String PARAM_LASTNAME="LastName";
		public final static String PARAM_PASSWORD="psswrd_vc";
		public final static String PARAM_DOB="dob";
		public final static String PARAM_IPADDRESS="Ipaddess";
		public final static String PARAM_SITEREGISTERED="SiteReg";
		public final static String PARAM_GENDER="Gender";
		public final static String PARAM_HS="hs";
		public final static String PARAM_CITY="city";
		public final static String ERROR_WITH_INSERTUSERPROFILE_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String MESSAGE_INVALID_EMAILID="INVALID EMAILID";
		public final static String MESSAGE_INVALID_FIRSTNAME="INVALID FIRST NAME";
		public final static String MESSAGE_INVALID_LASTNAME="INVALID LAST NAME";
		public final static String MESSAGE_INVALID_GENDER="INVALID GENDER";
		public final static String MESSAGE_INVALID_DOB="INVALID Date of Birth";
		public final static String MESSAGE_ERROR_INSERTION="INSERTION ERROR";
		public final static String ERROR_MESSAGE_INVALIDEMAILID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId cannot be more than 100 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDFIRSTNAME = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid FirstName</Message><Exception>Either no value provided for FirstName or FirstName has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDFIRSTNAME_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid FirstName</Message><Exception>FirstName length cannot be more than 50 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDLASTNAME = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E105</ErrCode>" +
		"<Message>Invalid LastName</Message><Exception>Either no value provided for LastName or LastName has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDLASTNAME_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E106</ErrCode>" +
		"<Message>Invalid LastName</Message><Exception>LastName length cannot be more than 50 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDGENDER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E106</ErrCode>" +
		"<Message>Invalid Gender</Message><Exception>Gender cannot be left blank.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDDOB = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E107</ErrCode>" +
		"<Message>Invalid DOB</Message><Exception>Date of Birth is not valid.</Exception></Error>";
		public final static String ERROR_INSERTION="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E108</ErrCode>" +
		"<Message>Insertion error</Message><Exception>Error while inserting record.</Exception></Error>";
	}
	
	public final class UpdateUserProfile{
		
		public final static String PARAM_EMAILID="emailid";
		public final static String PARAM_FIRSTNAME="firstname";
		public final static String PARAM_LASTNAME="lastname";
		public final static String PARAM_PASSWORD="psswrd_vc";
		public final static String PARAM_DOB="dob";
		public final static String PARAM_GENDER="gender";
		public final static String PARAM_CITY="city";
		public final static String ERROR_WITH_UPDATEUSERPROFILE_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String MESSAGE_INVALID_EMAILID="INVALID EMAILID";
		public final static String MESSAGE_INVALID_FIRSTNAME="INVALID FIRST NAME";
		public final static String MESSAGE_INVALID_LASTNAME="INVALID LAST NAME";
		public final static String MESSAGE_INVALID_GENDER="INVALID GENDER";
		public final static String MESSAGE_INVALID_DOB="INVALID Date of Birth";
		public final static String MESSAGE_ERROR_UPDATION="INSERTION ERROR";
		public final static String ERROR_MESSAGE_INVALIDEMAILID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId cannot be more than 100 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDFIRSTNAME = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid FirstName</Message><Exception>Either no value provided for FirstName or FirstName has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDFIRSTNAME_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid FirstName</Message><Exception>FirstName length cannot be more than 50 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDLASTNAME = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E105</ErrCode>" +
		"<Message>Invalid LastName</Message><Exception>Either no value provided for LastName or LastName has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDLASTNAME_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E106</ErrCode>" +
		"<Message>Invalid LastName</Message><Exception>LastName length cannot be more than 50 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDGENDER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E106</ErrCode>" +
		"<Message>Invalid Gender</Message><Exception>Gender cannot be left blank.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDDOB = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E107</ErrCode>" +
		"<Message>Invalid DOB</Message><Exception>Date of Birth is not valid.</Exception></Error>";
		public final static String ERROR_UPDATION="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E108</ErrCode>" +
		"<Message>Insertion error</Message><Exception>Error while inserting record.</Exception></Error>";
	}
	
	public final class CheckUserAvailability{
		
		public final static String PARAM_USERID="userid";
		public final static String MESSAGE_NOVALUE_USERID="NO VALUE GIVEN FOR USERID";
		public final static String MESSAGE_INVALID_USERID="INVALID USERID";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>UserId length cannot be more than 50 characters.</Exception></Error>";
		public final static String MESSAGE_ERROR_USERID_EXIST="USERID ALREADY EXISTS.";
		public final static String MESSAGE_USERID_AVAILABLE="USERID AVAILABLE.";
	}
	
	public final class ChangePassword{
		
		public final static String PARAM_USERID="userid";
		public final static String PARAM_OLDPASSWORD="oldpassword";
		public final static String PARAM_NEWPASSWORD="newpassword";
		public final static String MESSAGE_NOVALUE_USERID="NO VALUE GIVEN FOR USERID";
		public final static String MESSAGE_INVALID_USERID="INVALID USERID";
		public final static String MESSAGE_PASSWORD_CHANGED="NEW PASSWORD CHANGED SUCCESSFULLY";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>UserId length cannot be more than 50 characters.</Exception></Error>";
		public final static String ERROR_CHANGEPASSWORD="UNABLE TO CHANGE PASSWORD.";
	}
	
	public final class CheckActivatedUser{
		
		public final static String PARAM_EMAILID="emailid";
		public final static String MESSAGE_INVALID_EMAILID="INVALID EMAILID";
		public final static String MESSAGE_ERROR_FETCHING="UNABLE TO FETCH RECORD";
		public final static String ERROR_MESSAGE_INVALIDEMAILID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId cannot be more than 100 characters.</Exception></Error>";
		public final static String ERROR_FETCHING="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E105</ErrCode>" +
		"<Message>Error While fetching emailid</Message><Exception>Object reference not set to an instance of an object.</Exception></Error>";
		
		
	}
	
	public final class UpdateEmailId{
		
		public final static String PARAM_USERID="userid";
		public final static String PARAM_NEW_EMAILID="newemailid";
		public final static String PARAM_OLD_EMAILID="oldemailid";
		public final static String MESSAGE_INVALID_EMAILID="INVALID EMAILID";
		public final static String MESSAGE_ERROR_FETCHING="UNABLE TO FETCH RECORD";
		public final static String MESSAGE_EMAILID_EXISTS="NEW EMAILID ALREADY EXISTS";
		public final static String MESSAGE_INVALID_USERID="INVALID USERID";
		public final static String MESSAGE_EMAILIDS_MATCH="NEW AND OLD EMAIL IDS MATCH";
		public final static String ERROR_WITH_UPDATEEMAILID_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>UserId length cannot be more than 50 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId cannot be more than 100 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_EMAILIDSMATCH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E105</ErrCode>" +
		"<Message>Emailids Match</Message><Exception>New emailid and old emailId are matching.</Exception></Error>";
		public final static String ERROR_FETCHING="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E106</ErrCode>" +
		"<Message>Error While fetching emailid</Message><Exception>Object reference not set to an instance of an object.</Exception></Error>";
	}
	
	public final class GetInactiveUserDetail{
		
		public final static String PARAM_EMAILID="emailid";
		public final static String MESSAGE_INVALID_EMAILID="INVALID EMAILID";
		public final static String MESSAGE_ERROR_FETCHING="UNABLE TO FETCH RECORD";
		public final static String ERROR_MESSAGE_INVALIDEMAILID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId cannot be more than 100 characters.</Exception></Error>";
		public final static String ERROR_FETCHING="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E105</ErrCode>" +
		"<Message>No Matching Data found</Message><Exception>The emailid entered does not exists.</Exception></Error>";
	}
	
	public final class GetActivationStatus{
		
		public final static String PARAM_USERID="userid";
		public final static String MESSAGE_INVALID_USERID="INVALID USERID";
		public final static String MESSAGE_ERROR_FETCHING="UNABLE TO FETCH RECORD";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Userid is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>UserId cannot be more than 50 characters.</Exception></Error>";
		public final static String ERROR_FETCHING="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>No Matching Data found</Message><Exception>The userid entered does not exists.</Exception></Error>";
	}
	
	public final class GetAvailableUserIds{
		
		public final static String PARAM_USERID="userid";
		public final static String PARAM_FIRSTNAME="firstname";
		public final static String PARAM_LASTNAME="lastname";
		public final static String PARAM_REQNUMBER="reqdnos";
		public final static String PARAM_DOB="dob";
		public final static String MESSAGE_INVALID_USERID="INVALID USERID";
		public final static String MESSAGE_INVALID_FIRSTNAME="INVALID FIRST NAME";
		public final static String MESSAGE_INVALID_LASTNAME="INVALID LAST NAME";
		public final static String MESSAGE_INVALID_REQNUMBER="INVALID REQUEST NUMBER";
		public final static String MESSAGE_INVALID_DOB="INVALID DATE OF BIRTH";
		public final static String ERROR_WITH_GETAVAILABLEUSERIDS_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String ERROR_MESSAGE_INVALIDFIRSTNAME = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid FirstName</Message><Exception>Either no value provided for FirstName or FirstName has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDFIRSTNAME_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid FirstName</Message><Exception>FirstName length cannot be more than 50 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDLASTNAME = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid LastName</Message><Exception>Either no value provided for LastName or LastName has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDLASTNAME_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E106</ErrCode>" +
		"<Message>Invalid LastName</Message><Exception>LastName length cannot be more than 50 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALID_REQNUMBER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid Gender</Message><Exception>Gender cannot be left blank.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDDOB = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E105</ErrCode>" +
		"<Message>Invalid DOB</Message><Exception>Date of Birth is not valid.</Exception></Error>";
		public final static String ERROR_FETCHING="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E106</ErrCode>" +
		"<Message>Fetching error</Message><Exception>Error while fetching record.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E107</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Userid is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E108</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>UserId cannot be more than 50 characters.</Exception></Error>";
	}
	
	public final class GetInviteeList{
		
		public final static String PARAM_USERID="userid";
		public final static String MESSAGE_NOVALUE_USERID="NO VALUE GIVEN FOR USERID";
		public final static String MESSAGE_INVALID_USERID="INVALID USERID";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>UserId length cannot be more than 50 characters.</Exception></Error>";
		public final static String MESSAGE_ERROR_USERID_EXIST="USERID ALREADY EXISTS.";
		public final static String MESSAGE_USERID_AVAILABLE="USERID AVAILABLE.";
	}
	
	public final class UpdateInactiveUserSite{
		
		public final static String PARAM_EMAILID="emailid";
		public final static String PARAM_NEWSITEREGVAL="newSiteregval";
		public final static String MESSAGE_INVALID_EMAILID="INVALID EMAILID";
		public final static String MESSAGE_INVALID_NEWSITEREGVAL="INVALID NEWSITEREGVAL";
		public final static String ERROR_WITH_UPDATEINACTIVEUSERSITE_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String ERROR_MESSAGE_INVALIDEMAILID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId cannot be more than 100 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_NEWSITEREGVAL = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid Newsiteregval</Message><Exception>New site reg value is not valid.</Exception></Error>";
	}
	
	public final class SendITimesInvitation{
		
		public final static String PARAM_EMAILID="emailid";
		public final static String PARAM_USERID="userid";
		public final static String MESSAGE_INVALID_EMAILID="INVALID EMAILID";
		public final static String MESSAGE_INVALID_USERID="INVALID USERID";
		public final static String MESSAGE_INVALID_LIMITOFREQUESTS="LIMIT OF SENDING REQUESTS EXCEEDED";
		public final static String ERROR_WITH_SENDITIMESINVITATION_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String ERROR_MESSAGE_INVALIDEMAILID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId cannot be more than 100 characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>UserId length cannot be more than 50 characters.</Exception></Error>";
		
	}
	
	public final class CheckInvitation{
		
		public final static String PARAM_USERID="userid";
		public final static String MESSAGE_INVALID_USERID="INVALID USERID";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>UserId length cannot be more than 50 characters.</Exception></Error>";
	}
	
	public final class GetUserProfile{
		
		public final static String PARAM_USERID="userid";
		public final static String MESSAGE_INVALID_USERID="INVALID USERID";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>UserId length cannot be more than 50 characters.</Exception></Error>";
	}
	
	public final class FetchPassword{
		
		public final static String PARAM_USERID="userid";
		public final static String MESSAGE_NOVALUE_USERID="NO VALUE GIVEN FOR USERID";
		public final static String MESSAGE_INVALID_USERID="INVALID USERID";
		public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>Either no value provided for userid or userid has special characters.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid userid</Message><Exception>UserId length cannot be more than 50 characters.</Exception></Error>";
		public final static String ERROR_FETCHPASSWORD="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Fetch Password Error</Message><Exception>Error while fetching record.</Exception></Error>";
	}
	
	public final class UpdateInactiveUser{
		
		public final static String PARAM_EMAILID="emailid";
		public final static String MESSAGE_INVALID_EMAILID="INVALID EMAILID";
		public final static String ERROR_WITH_UPDATEINACTIVEUSER_REQUEST_PARAMETERS = "INVALID PARAMETERS";
		public final static String MESSAGE_ERROR_INSERTION="UNABLE TO UPDATE RECORD";
		public final static String MESSAGE_SUCCESSFUL_INSERTION="RECORD UPDATED SUCCESSFULLY";
		public final static String ERROR_MESSAGE_INVALIDEMAILID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId is not valid.</Exception></Error>";
		public final static String ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
		"<Message>Invalid emailid</Message><Exception>EmailId cannot be more than 100 characters.</Exception></Error>";
		public final static String ERROR_INSERTION="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
		"<Message>Insertion error</Message><Exception>Error while inserting record.</Exception></Error>";
	}
	
	
}
