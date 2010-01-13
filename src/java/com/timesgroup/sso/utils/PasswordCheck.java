package com.timesgroup.sso.utils;

/* 
 * Name: 
 * 		PasswordCheck.java
 * Author: 
 * 		Jim Sloey - jsloey@justwild.us
 * Requirements:
 * 		Java 1.4 or greater
 * Usage:
 *		Bundled usage: java -jar PasswordCheck.jar <password>
 *		Unbundled usage: java PasswordCheck <password>
 * History:
 * 		Created May 19, 2006 by Jim Sloey
 * Derived from: 
 * 		Steve Moitozo's passwdmeter
 * 		See http://www.geekwisdom.com/dyn/passwdmeter
 * License:
 * 		Open Software License 2.1 or Academic Free License 2.1 
 * 		See http://www.opensource.org
 * Description:
 * 		Need a simple way to check the strength of a password?
 * 		To check in the HTML on the front end try Steve Moitozo's 
 * 		Javascript example at http://www.geekwisdom.com/dyn/passwdmeter
 * Source URL:
 * 		http://justwild.us/examples/password/
 */
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PasswordCheck {

	// Rules variables
	private static String PASSWORD_MIXED_CASE;
	private static String PASSWORD_MIN_LENGTH;
	private static String PASSWORD_MAX_LENGTH;
	private static String PASSWORD_NUMERIC;
	private static String PASSWORD_SPECIAL;
	private static String PASSWORD_STRENGTH;
	
	public PasswordCheck () {
		super();
		// normally set from strings in a properties file
		PASSWORD_MIXED_CASE = "1";
		PASSWORD_MIN_LENGTH = "8";
		PASSWORD_MAX_LENGTH = "30";
		PASSWORD_NUMERIC = "1";
		PASSWORD_SPECIAL = "1";
		PASSWORD_STRENGTH = "30";
	}
	public static String CheckPasswordStrength(String passwd) {
		int upper = 0, lower = 0, numbers = 0, special = 0, length = 0;
		int strength = 0, intScore = 0;
		String strVerdict = "none", strLog = "";
		Pattern p;
		Matcher m;
		if (passwd == null)
			return "very Weak";
		// PASSWORD LENGTH
		length = passwd.length();
		if (length < 5) // length 4 or less
			{
			intScore = (intScore + 3);
			strLog = strLog + "3 points for length (" + length + ")\n";
		} else if (length > 4 && passwd.length() < 8) // length between 5 and 7
			{
			intScore = (intScore + 6);
			strLog = strLog + "6 points for length (" + length + ")\n";
		} else if (
			length > 7 && passwd.length() < 16) // length between 8 and 15
			{
			intScore = (intScore + 12);
			strLog = strLog + "12 points for length (" + length + ")\n";
		} else if (length > 15) // length 16 or more
			{
			intScore = (intScore + 18);
			strLog = strLog + "18 point for length (" + length + ")\n";
		}
		// LETTERS 
		p = Pattern.compile(".??[a-z]");
		m = p.matcher(passwd);
		while (m.find()) // [verified] at least one lower case letter
			{
			lower += 1;
		}
		if (lower > 0) {
			intScore = (intScore + 1);
			strLog = strLog + "1 point for a lower case character\n";
		}
		p = Pattern.compile(".??[A-Z]");
		m = p.matcher(passwd);
		while (m.find()) // [verified] at least one upper case letter
			{
			upper += 1;
		}
		if (upper > 0) {
			intScore = (intScore + 5);
			strLog = strLog + "5 point for an upper case character\n";
		}
		// NUMBERS
		p = Pattern.compile(".??[0-9]");
		m = p.matcher(passwd);
		while (m.find()) // [verified] at least one number
			{
			numbers += 1;
		}
		if (numbers > 0) {
			intScore = (intScore + 5);
			strLog = strLog + "5 points for a number\n";
			if (numbers > 1) {
				intScore = (intScore + 2);
				strLog = strLog + "2 points for at least two numbers\n";
				if (numbers > 2) {
					intScore = (intScore + 3);
					strLog = strLog + "3 points for at least three numbers\n";
				}
			}
		}
		// SPECIAL CHAR
		p = Pattern.compile(".??[:,!,@,#,$,%,^,&,*,?,_,~]");
		m = p.matcher(passwd);
		while (m.find()) // [verified] at least one special character
			{
			special += 1;
		}
		if (special > 0) {
			intScore = (intScore + 5);
			strLog = strLog + "5 points for a special character\n";
			if (special > 1) {
				intScore += (intScore + 5);
				strLog =
					strLog + "5 points for at least two special characters\n";
			}
		}
		// COMBOS
		if (upper > 0 && lower > 0) // [verified] both upper and lower case
			{
			intScore = (intScore + 2);
			strLog = strLog + "2 combo points for upper and lower letters\n";
		}
		if ((upper > 0 || lower > 0)
			&& numbers > 0) // [verified] both letters and numbers
			{
			intScore = (intScore + 2);
			strLog = strLog + "2 combo points for letters and numbers\n";
		}
		if ((upper > 0 || lower > 0)
			&& numbers > 0
			&& special > 0) // [verified] letters, numbers, and special characters
			{
			intScore = (intScore + 2);
			strLog =
				strLog
					+ "2 combo points for letters, numbers and special chars\n";
		}
		if (upper > 0 && lower > 0 && numbers > 0 && special > 0)			// [verified] upper, lower, numbers, and special characters
			{
			intScore = (intScore + 2);
			strLog =
				strLog
					+ "2 combo points for upper and lower case letters, numbers and special chars\n";
		}
		if (intScore < 16) {
			strVerdict = "very weak";
		} else if (intScore > 15 && intScore < 25) {
			strVerdict = "weak";
		} else if (intScore > 24 && intScore < 35) {
			strVerdict = "medium";
		} else if (intScore > 34 && intScore < 45) {
			strVerdict = "strong";
		} else {
			strVerdict = "very strong";
		}
		//System.out.println(strVerdict + " - " + intScore + "\n" + strLog);
		// Does it meet the password policy?
		try {
			int min = Integer.parseInt(PASSWORD_MIN_LENGTH);
			if (length < min)
				return "very Weak";
		} catch (Exception e) {
			;
		} // undefined
		try {
			int max = Integer.parseInt(PASSWORD_MAX_LENGTH);
			if (length > max)
				return "very Weak";
		} catch (Exception e) {
			;
		} // undefined
		try {
			int num = Integer.parseInt(PASSWORD_NUMERIC);
			if (numbers < num)
				return "very Weak";
		} catch (Exception e) {
			;
		} // undefined
		try {
			int mix = Integer.parseInt(PASSWORD_MIXED_CASE);
			if (upper < mix || lower < mix)
				return "very Weak";
		} catch (Exception e) {
			;
		} // undefined
		try {
			int str = Integer.parseInt(PASSWORD_STRENGTH);
			if (intScore < str)
				return "very Weak";
		} catch (Exception e) {
			;
		} // undefined
		try {
			int spec = Integer.parseInt(PASSWORD_SPECIAL);
			if (special < spec)
				return "very Weak";
		} catch (Exception e) {
			;
		} // undefined
		return strVerdict;
	}

	public static void main(String[] args) {

		/*if (args.length != 1) {
			System.out.println("Java 1.4 password strength regex example (see source)");
			System.out.println("Bundled usage: java -jar PasswordCheck.jar <password>");
			System.out.println("Unbundled usage: java PasswordCheck <password>");
			System.exit(1);
		}*/
		String password="I5daS@#";
		
		/*if (CheckPasswordStrength(password))
		System.out.println("\nPassword meets or exceeds defined security rules\n");
		else
		System.out.println("\nPassword fails to meet minimum security requirements\n");*/
		
		System.out.println("PasswordCheck.main()"+CheckPasswordStrength(password));
		
	}
}
