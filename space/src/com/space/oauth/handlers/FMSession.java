/**
 * 
 */
package com.space.oauth.handlers;

import java.io.Serializable;

import javax.security.auth.Subject;

import com.space.security.helpers.SecurityToken;

/**
 * @author asus
 *
 */
public class FMSession implements Serializable {

	/**
	 * 
	 */
	private static FMSession fmSessionInstance = null;
	
	private boolean sessionLogin; 
	private final long securityToken;
	private int homepagehit;
	//private Subject subject;
	private final int fmid;

	private FMSession() 
	{
	  //this.sessionLogin=sessionLogin;
		securityToken = SecurityToken.createUniqueToken();
		fmid=SecurityToken.createUserID();
		sessionLogin=true;
		homepagehit=1;
	}

	private void init() 
	{
		System.out.println("Session is logged "+sessionLogin);
		System.out.println("Homepagehit "+homepagehit);
		System.out.println("SecurityToken "+securityToken);
	}
	
	/**
	 * @return the fmid
	 */
	public final int getFmid() {
		return fmid;
	}

	
	public static void destroy()
	{
		fmSessionInstance = null;	
	}
	
	public static FMSession getSessionInstance()
	{
	   if (fmSessionInstance == null)
		{
			synchronized(FMSession.class)
			{
				if (fmSessionInstance == null)
				{
					fmSessionInstance = new FMSession();
					fmSessionInstance.init();
				}
			}
		}
	   
	   return fmSessionInstance;
	}
	/**
	 * @return the homepagehit
	 */
	public int getHomepagehit() {
		return homepagehit;
	}

	/**
	 * @param homepagehit the homepagehit to set
	 */
	public void setHomepagehit(int homepagehit) {
		this.homepagehit = homepagehit;
	}

	/**
	 * @return the securityToken
	 */
	public final long getSecurityToken() {
		return securityToken;
	}

	
	/**
	 * @return the sessionLogin
	 */
	public boolean isSessionLogin() 
	{
		return sessionLogin;
	}

	/**
	 * @param sessionLogin the sessionLogin to set
	 */
	public void setSessionLogin(boolean sessionLogin) {
		this.sessionLogin = sessionLogin;
	}
	
}
