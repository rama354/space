package com.space.spring.controllers;
/**
 * 
 */


import java.util.List;

import javax.security.auth.callback.CallbackHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.ParameterMethodNameResolver;

import com.space.social.helpers.SocialContext;

 
/**
 * @author asus
 *
 */
@Controller
public class FaceBookController {

	private ParameterMethodNameResolver methodNameResolver;
	
	@Autowired
	private SocialContext socialContext;


	//private FMSession userSession=null;
	private ModelAndView homepageMV = null;
	private ModelAndView loginpageMV=null;
	
	public FaceBookController() 
	{
		homepageMV = new ModelAndView("facebookposts");
		loginpageMV = new ModelAndView("/login/login");
	}
	
	@RequestMapping(value="/facebook.htm" , method= RequestMethod.GET,params="submit=signIn")
	public ModelAndView signIn(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception 
	{
			 
		ModelAndView nextView;

		if (socialContext.isSignedIn(arg0, arg1)) 
		{
				List<Post> posts = retrievePosts();
				homepageMV.addObject("posts", posts);
				 
		} 
		else 
		{
			nextView = loginpageMV;
		}
		return nextView;
			
	 }
	
	
	private List<Post> retrievePosts() 
	{

		Facebook facebook = socialContext.getFacebook();
		FeedOperations feedOps = facebook.feedOperations();
	
		List<Post> posts = feedOps.getHomeFeed();

		//logger.info("Retrieved " + posts.size() + " posts from the Facebook authenticated user");
	
		return posts;
		
		}
	
	@RequestMapping(value="/forgotPassword.htm" , method= RequestMethod.GET,params="submit=forgetPassword")
	public ModelAndView forgetPassword(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception
	{
		return null;
		
	}
	
	 
	
	/*@RequestMapping(value="/home.htm" , method= RequestMethod.POST,params="submit=signout")
	public ModelAndView logout(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception
	{
		System.out.println("Logout method");
		if (userSession !=null && userSession.isSessionLogin())
		{
			userSession.setSessionLogin(false);
			System.out.println("SecurityToken "+userSession.getSecurityToken());
			userSession = null;
			FMSession.destroy(); ////  destroy this
			arg0.getSession().invalidate();
		}

		return loginpageMV;
		
			
	}*/
	
	/**
	 * @param methodNameResolver the methodNameResolver to set
	 */
	public void setMethodNameResolver(ParameterMethodNameResolver methodNameResolver) {
		this.methodNameResolver = methodNameResolver;
	}
		
}
