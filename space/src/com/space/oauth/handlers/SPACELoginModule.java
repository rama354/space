/**
 * 
 */
package com.space.oauth.handlers;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * @author asus
 *
 */
public class SPACELoginModule implements LoginModule {

	private CallbackHandler cbh = null;
	private String username="root";
	private String password= "l00nie";
	
	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void initialize(Subject arg0, CallbackHandler arg1,
			Map<String, ?> arg2, Map<String, ?> arg3) {
		// TODO Auto-generated method stub
		cbh = arg1;
		
		
	}

	@Override
	public boolean login() throws LoginException
	{	
		boolean isLoginSuccess = false;
		
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("username");
		callbacks[1] = new PasswordCallback("password",false);
		try {
			cbh.handle(callbacks);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedCallbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String cbh_password= new String(((PasswordCallback)callbacks[1]).getPassword());
	
		System.out.println("(PasswordCallback)callbacks[1]).getPassword() "+cbh_password);
		
		if (username.equals(((NameCallback)callbacks[0]).getName()) &&
				password.equals(cbh_password))
				isLoginSuccess=true;
		
		///Subject with unique ID ?????
		return isLoginSuccess;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
