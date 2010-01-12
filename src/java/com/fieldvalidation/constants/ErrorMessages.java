package com.fieldvalidation.constants;

public class ErrorMessages {
	
	public final static String ERROR_MESSAGE_NULLFIELDVALUE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>N101</ErrCode>\n" +
	"<Message>Null Field Value</Message>\n<Exception>Field value cannot be null.</Exception>\n</Error>\n";
	
	public final static String ERROR_MESSAGE_BLANKFIELD = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>B101</ErrCode>\n" +
	"<Message>Blank Field Value</Message>\n<Exception>Field value cannot be blank.</Exception>\n</Error>\n";
	
	public final static String ERROR_MESSAGE_INVALIDUSERID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>U101</ErrCode>\n" +
	"<Message>Invalid userid</Message>\n<Exception>Either no value provided for userid or userid has special characters.</Exception>\n</Error>\n";
	public final static String ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>U102</ErrCode>\n" +
	"<Message>Invalid userid</Message>\n<Exception>UserId length cannot be more than 50 characters.</Exception>\n</Error>\n";
	
	public final static String ERROR_MESSAGE_INVALIDEMAILID = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E101</ErrCode>\n" +
	"<Message>Invalid emailid</Message>\n<Exception>EmailId is not valid.</Exception>\n</Error>\n";
	public final static String ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>E102</ErrCode>\n" +
	"<Message>Invalid emailid</Message>\n<Exception>EmailId cannot be more than 100 characters.</Exception>\n</Error>\n";

	public final static String ERROR_MESSAGE_INVALIDDOB = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>D101</ErrCode>\n" +
	"<Message>Invalid DOB</Message>\n<Exception>Date of Birth is not valid.</Exception>\n</Error>\n";
	
	public final static String ERROR_MESSAGE_INVALIDFIRSTNAME = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>FN101</ErrCode>\n" +
	"<Message>Invalid First Name</Message>\n<Exception>Firstname has special characters.</Exception>\n</Error>\n";
	public final static String ERROR_MESSAGE_INVALIDFIRSTNAME_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>FN102</ErrCode>\n" +
	"<Message>Invalid First Name</Message>\n<Exception>First Name length cannot be more than 50 characters.</Exception>\n</Error>\n";
	
	public final static String ERROR_MESSAGE_INVALIDLASTNAME = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>LN101</ErrCode>\n" +
	"<Message>Invalid Last Name</Message>\n<Exception>Lastname has special characters.</Exception>\n</Error>\n";
	public final static String ERROR_MESSAGE_INVALIDLASTNAME_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>LN102</ErrCode>\n" +
	"<Message>Invalid Last Name</Message>\n<Exception>Last Name length cannot be more than 50 characters.</Exception>\n</Error>\n";

	public final static String ERROR_MESSAGE_INVALIDMOBILENUMBER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>MN101</ErrCode>\n" +
	"<Message>Invalid Mobile Number</Message>\n<Exception>Mobilenumber can only contains numbers.</Exception>\n</Error>\n";
	public final static String ERROR_MESSAGE_INVALIDMOBILENUMBER_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>MN102</ErrCode>\n" +
	"<Message>Invalid Mobile Number</Message>\n<Exception>Mobilenumber length should be equal to 10 characters.</Exception>\n</Error>\n";

	public final static String ERROR_MESSAGE_INVALIDPINNUMBER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>PN101</ErrCode>\n" +
	"<Message>Invalid Pin Number</Message>\n<Exception>Pinnumber can only contains numbers.</Exception>\n</Error>\n";
	public final static String ERROR_MESSAGE_INVALIDPINNUMBER_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>PN102</ErrCode>\n" +
	"<Message>Invalid Pin Number</Message>\n<Exception>Pinnumber length should be equal to 6 characters.</Exception>\n</Error>\n";
	
	public final static String ERROR_MESSAGE_INVALIDPASSWORD_INCORRECTLENGTH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Error><ErrCode>P101</ErrCode>\n" +
	"<Message>Invalid Password</Message>\n<Exception>Password length should be less than 30 characters.</Exception>\n</Error>\n";
	
}
