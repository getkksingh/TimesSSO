package com.timesgroup.sso.filters;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SiteIntegrationCheckFilter implements Filter {
	private String permittedSiteFileName = null ;
	private boolean caching = false ;
	private int cacheReloadInSeconds = -1 ;
	private long startTime = 0 ;
	private Hashtable<String, String> siteList=null;
	private String put;
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
									
		System.out.println("Init Parameters are as below ");
		System.out.println(permittedSiteFileName + " permittedSiteFileName" +  caching + " caching" +cacheReloadInSeconds  +" cacheReloadInSeconds");
		
		if(System.currentTimeMillis() - startTime > cacheReloadInSeconds * 1000 ) {
			reloadSiteConfig();
		}
		boolean permittedSite = checkSite(request,response);
		if(permittedSite)
		filterChain.doFilter(request, response);
		else return;
	}

	private boolean checkSite(ServletRequest request, ServletResponse response) {
		System.out.println("Checking Site "+request.getParameter("IS").toString().trim());
		String siteid=siteList.get(request.getParameter("IS"));
		
		if(siteid!=null)
		{
			System.out.println("Site Found.."+siteid);
			return true ;
		}
		else return false;
	}

	private void reloadSiteConfig() {
		System.out.println("Just entering reloadSiteConfig");
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new FileInputStream(permittedSiteFileName));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader br=new BufferedReader(isr);
		this.siteList=new Hashtable<String,String>();
		String textline=null;
		try {
			while((textline = br.readLine()) != null)
			{
				String[] siteconfig;
				siteconfig = textline.split(",");
				if(siteconfig.length >=2)
				{
					put = siteList.put((String)siteconfig[0],(String)siteconfig[1]);
//					System.out.println("SiteConfig from File : "+siteconfig[0]);
				}
				else
					System.out.println("Ignoring from site List : "+textline.toString());
			}
		} catch (IOException e) {	
			e.printStackTrace();
		}
		startTime = System.currentTimeMillis() ;
		System.out.println("Coming out of SITELOAD");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
		permittedSiteFileName = filterConfig.getInitParameter("permittedSiteFileName");
		System.out.println("Siet File Name : "+permittedSiteFileName);

		if(filterConfig.getInitParameter("caching") != null)
		caching = new Boolean (filterConfig.getInitParameter("caching"));
		
		if(filterConfig.getInitParameter("cacheReloadInSeconds") != null )
		cacheReloadInSeconds =  new Integer(filterConfig.getInitParameter("cacheReloadInSeconds"));
		
		System.out.println("Caching " +caching +" cacheReloadInSeconds " +cacheReloadInSeconds);
		
		reloadSiteConfig();
	}

}

