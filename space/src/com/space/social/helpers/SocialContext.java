package com.space.social.helpers;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import com.space.security.helpers.SecurityContext;
import com.space.security.helpers.SecurityContext.User;

@Service
public class SocialContext implements ConnectionSignUp, SignInAdapter {

	/** Store the user id between calls to the server */
	 private static Random rand;

	/** Store the user id between calls to the server */
	private static final ThreadLocal<String> currentUser = new ThreadLocal<String>();


	private final UsersConnectionRepository connectionRepository;
	private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();

    private final Facebook facebook;
    
	@Inject
	public SocialContext(UsersConnectionRepository connectionRepository
		      )
	{
		//this.requestCache = requestCache;
		this.connectionRepository = connectionRepository;
	    //this.userCookieGenerator = userCookieGenerator;
	    this.facebook = new FacebookTemplate();
	}

	@Override
	public String signIn(String userId, Connection<?> arg1, NativeWebRequest request) 
	{	 		
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));
		//SecurityContext.setCurrentUser(new User(userId));
		userCookieGenerator.addCookie(userId, (HttpServletResponse)(request.getNativeResponse()));
		return null;
		 
	}

	
	@Override
	public String execute(Connection<?> arg0) {
		// TODO Auto-generated method stub
		return Long.toString(rand.nextLong());
	}
	

	public String getUserId() {

	    //return SecurityContext.getCurrentUser().get();
		return currentUser.get();
	  }

	public Facebook getFacebook() {

	    return facebook;
	  }
	
	public boolean isSignedIn(HttpServletRequest request, HttpServletResponse response) 
	{
	
		    boolean retVal = false;
		 
		    String userId = userCookieGenerator.readCookieValue(request);
		    if (isValidId(userId)) 
		    {
	
		      if (isConnectedFacebookUser(userId)) {

		        retVal = true;
		      } else {
		
		        userCookieGenerator.removeCookie(response);
		      }
		    }
		    currentUser.set(userId);
		    return retVal;
	}
	
	private boolean isValidId(String id) 
	{
		return id != null && (id.length() > 0);
	}
		 

	private boolean isConnectedFacebookUser(String userId) 
	{
	    ConnectionRepository connectionRepo = connectionRepository.createConnectionRepository(userId);
	    Connection<Facebook> facebookConnection = connectionRepo.findPrimaryConnection(Facebook.class);
	    return facebookConnection != null;
    }
	

}
