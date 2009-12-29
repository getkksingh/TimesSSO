package com.timesgroup.sso.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class LDAPCaller {

	public String updateLDAPPassword(String strUserName, String strPassword)
			throws Exception {
		String strURLServlet = "http://tilmb29.indiatimes.com/it/updateuserService.jsp";
		String strParam = "UserId=" + replaceSpace(strUserName) + "&Password="
				+ replaceSpace(strPassword);
		String serverResponse = null;
		try {
			URLConnection URLConn = (new URL(strURLServlet)).openConnection();
			URLConn.setDoOutput(true);
			URLConn.setDoInput(true);
			URLConn.setUseCaches(false);
			DataOutputStream writer = new DataOutputStream(URLConn
					.getOutputStream());
			writer.writeBytes(strParam);
			writer.close();
			BufferedReader bfr = new BufferedReader(new InputStreamReader(
					URLConn.getInputStream()));
			String inputLine = null;
			serverResponse = inputLine = bfr.readLine();

		} catch (Exception e) {
			System.out.println("Exception-Url " + e);

		}

		return serverResponse;

	}

	private String replaceSpace(String str) {
		String encodedString = str;

		if (str != null)
			encodedString = URLEncoder.encode(str);

		return encodedString;
	}

	public static void main(String[] args) throws Exception {
		LDAPCaller caller1 = new LDAPCaller();
		System.out.println(caller1.updateLDAPPassword("poptest1", "popuser1"));

	}
}
