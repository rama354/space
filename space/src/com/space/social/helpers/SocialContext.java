package com.space.social.helpers;

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
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import com.space.security.helpers.SecurityContext;
import com.space.security.helpers.SecurityContext.User;

@Service
public class SocialContext implements ConnectionSignUp, SignInAdapter {

	/** Store the user id between calls to the server */
	private final AtomicLong userIdSequence = new AtomicLong();

	private final UsersConnectionRepository connectionRepository;
	private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();

    private final Facebook facebook;
    
	@Inject
	public SocialContext(UsersConnectionRepository connectionRepository,
		      Facebook facebook)
	{
		//this.requestCache = requestCache;
		this.connectionRepository = connectionRepository;
	    //this.userCookieGenerator = userCookieGenerator;
	    this.facebook = facebook;
	}

	@Override
	public String signIn(String userId, Connection<?> arg1, NativeWebRequest request) 
	{	 		
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));
		SecurityContext.setCurrentUser(new User(userId));
		userCookieGenerator.addCookie(userId, (HttpServletResponse)(request.getNativeResponse()));
		return null;
		 
	}

	
	@Override
	public String execute(Connection<?> arg0) {
		// TODO Auto-generated method stub
		return Long.toString(userIdSequence.incrementAndGet());
	}
	

	public String getUserId() {

	    return SecurityContext.getCurrentUser().getId();
	  }

	public Facebook getFacebook() {

	    return facebook;
	  }
}
