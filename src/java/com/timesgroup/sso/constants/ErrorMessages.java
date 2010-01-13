package com.timesgroup.sso.constants;

public class ErrorMessages {

	public final static String XML_URL="<?xml version='1.0' encoding='utf-8' ?>\n";
	
	public final static String MESSAGE_INVALIDPARAMETERS="Invalid Parameters";
	public final static String MESSAGE_ERROR_INSERTION="Error in inserting record.";
	public final static String MESSAGE_ERROR_UPDATION="Error in updating record.";
	public final static String MESSAGE_ERROR_FETCHINGRECORD="Error in fetching record.";
	public final static String MESSAGE_EMAILID_EXISTS="New emailid already exists";
	public final static String MESSAGE_INVALID_HASHCODE="Invalid hash code.";
	
	public final static String XMLMESSAGE_ERROR_INSERTION="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>" +
	"<Message>Insertion Error</Message><Exception>Unable to insert record.</Exception></Error>";
	public final static String XMLMESSAGE_ERROR_UPDATION="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>" +
	"<Message>Updation Error</Message><Exception>Unable to update record.</Exception></Error>";
	public final static String XMLMESSAGE_ERROR_EMAILID_EXISTS="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E103</ErrCode>" +
	"<Message>Emailid already exists</Message><Exception>Emailid already exists.</Exception></Error>";
	public final static String XMLMESSAGE_ERROR_FETCHING_USERPROFILE="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E104</ErrCode>" +
	"<Message>Fetching Error</Message><Exception>No user profile exists with respect to given emailid.</Exception></Error>";
}
