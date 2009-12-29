package com.timesgroup.sso.filters;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class PermittedIPCheckFilter implements Filter {
	
	private String permittedIPFileName = null ;
	private boolean caching = false ;
	private int cacheReloadInSeconds = -1 ;
	private long startTime = 0 ;
	private Hashtable<String,String> ipList=null;
	String put=null;
	
	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
							
		System.out.println("Init Parameters are as below ");
		System.out.println(permittedIPFileName + " permittedIPFileName" +  caching + " caching" +cacheReloadInSeconds  +" cacheReloadInSeconds");
		
		if(System.currentTimeMillis() - startTime > cacheReloadInSeconds * 1000 ) {
			reloadIPAddresses();
		}
		boolean permittedIP = checkIP(request,response);
		if(permittedIP)
		filterChain.doFilter(request, response);
		else return;
	}

	private boolean checkIP(ServletRequest request, ServletResponse response) {
		System.out.println("Checking IP "+request.getRemoteAddr().toString().trim());
		String ipaddress=ipList.get(request.getRemoteAddr().toString().trim());
		if(ipaddress!=null)
		{
			System.out.println("IP Checking Found..");
			return true ;
		}
		else return false;
	}

	private void reloadIPAddresses() {
		System.out.println("Just entering reloadIPAddresses");
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new FileInputStream(permittedIPFileName));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader br=new BufferedReader(isr);
		ipList=new Hashtable<String, String>();
		String textline;
		try {
			while((textline = br.readLine()) != null)
			{
				String[] ipaddress;
				ipaddress = textline.split(",");
				if(ipaddress.length >=2)
				{
					put = (String) ipList.put((String)ipaddress[0].trim(),ipaddress[1]);
//					System.out.println("IpAddress from File : "+ipaddress[0]);
				}
				else
					System.out.println("Ignoring IpAddress from File : "+textline);						
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		startTime = System.currentTimeMillis() ;
		System.out.println("Coming out of IPRELOAD");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
		permittedIPFileName = filterConfig.getInitParameter("permittedIPFileName");
		System.out.println("Ip File Name : "+permittedIPFileName);

		if(filterConfig.getInitParameter("caching") != null)
		caching = new Boolean (filterConfig.getInitParameter("caching"));
		
		if(filterConfig.getInitParameter("cacheReloadInSeconds") != null )
		cacheReloadInSeconds =  new Integer(filterConfig.getInitParameter("cacheReloadInSeconds"));
		
		System.out.println("Caching " +caching +" cacheReloadInSeconds " +cacheReloadInSeconds);
		
		reloadIPAddresses();
	}

}
