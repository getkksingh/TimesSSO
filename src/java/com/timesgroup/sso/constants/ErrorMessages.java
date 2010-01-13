package com.timesgroup.sso.constants;

public class ErrorMessages {

	public final static String XML_URL="<?xml version='1.0' encoding='utf-8' ?>\n";
	
	public final static String MESSAGE_INVALIDPARAMETERS="Invalid Parameters";
	public final static String MESSAGE_ERROR_INSERTION="Error in inserting record.";
	public final static String MESSAGE_ERROR_UPDATION="Error in updating record.";
	public final static String MESSAGE_ERROR_FETCHINGRECORD="Error in fetching record.";
	public final static String MESSAGE_EMAILID_EXISTS="New emailid already exists";
	public final static String MESSAGE_INVALID_HASHCODE="Invalid hash code.";
	public final static String MESSAGE_ALREADYACTIVATED_EMAIL="The email is already activated.";
	
	public final static String XMLMESSAGE_ERROR_INSERTION="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
	"<Message>Insertion Error</Message><Exception>Unable to insert record.</Exception></Error>";
	public final static String XMLMESSAGE_ERROR_UPDATION="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
	"<Message>Updation Error</Message><Exception>Unable to update record.</Exception></Error>";
	public final static String XMLMESSAGE_ERROR_EMAILID_EXISTS="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
	"<Message>Emailid already exists</Message><Exception>Emailid already exists.</Exception></Error>";
	public final static String XMLMESSAGE_ERROR_FETCHING_USERPROFILE="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
	"<Message>Fetching Error</Message><Exception>No user profile exists with respect to given emailid.</Exception></Error>";
	public final static String XMLMESSAGE_ERROR_INVALID_HASHCODE="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E105</ErrCode>" +
	"<Message>Invalid HashCode</Message><Exception>The hashcode given is invalid.</Exception></Error>";
	public final static String XMLMESSAGE_ERROR_ALREADYACTIVATED_EMAIL="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E106</ErrCode>" +
	"<Message>Email Already Activated</Message><Exception>The email already activated.</Exception></Error>";
}
