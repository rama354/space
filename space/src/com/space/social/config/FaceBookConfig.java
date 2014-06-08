package com.space.social.config;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import com.space.social.helpers.SocialContext;

@Configuration
public class FaceBookConfig implements InitializingBean {

	  private static final String appId = "315150365301026";
	  private static final String appSecret = "a4b8908625e9e8b53dd048ce987da43d";

	  private SocialContext socialContext;

	  private UsersConnectionRepository usersConnectionRepositiory;

	  @Inject
	  private DataSource dataSource;

	  /**
	   * Point to note: the name of the bean is either the name of the method
	   * "socialContext" or can be set by an attribute
	   * 
	   * @Bean(name="myBean")
	   */
	  @Bean
	  public SocialContext socialContext() {

	    return socialContext;
	  }

	  @Bean
	  public ConnectionFactoryLocator connectionFactoryLocator() {

	    //logger.info("getting connectionFactoryLocator");
	    ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
	    registry.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));
	    return registry;
	  }

	  /**
	   * Singleton data access object providing access to connections across all
	   * users.
	   */
	  @Bean
	  public UsersConnectionRepository usersConnectionRepository() {

	    return usersConnectionRepositiory;
	  }

	  /**
	   * Request-scoped data access object providing access to the current user's
	   * connections.
	   */
	  @Bean
	  @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	  public ConnectionRepository connectionRepository() {
	    String userId = socialContext.getUserId();
	    //logger.info("Createung ConnectionRepository for user: " + userId);
	    return usersConnectionRepository().createConnectionRepository(userId);
	  }

	  /**
	   * A proxy to a request-scoped object representing the current user's
	   * primary Facebook account.
	   * 
	   * @throws NotConnectedException
	   *             if the user is not connected to facebook.
	   */
	  @Bean
	  @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	  public Facebook getFacebook() {
	    return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
	  }

	  /**
	   * Create the ProviderSignInController that handles the OAuth2 stuff and
	   * tell it to redirect back to /posts once sign in has completed
	   */
	  @Bean
	  public ProviderSignInController providerSignInController() {
	    ProviderSignInController providerSigninController = new ProviderSignInController(connectionFactoryLocator(),
	        usersConnectionRepository(), socialContext);
	    providerSigninController.setPostSignInUrl("/posts");
	    return providerSigninController;
	  }

	  @Override
	  public void afterPropertiesSet() throws Exception 
	  {

	    JdbcUsersConnectionRepository usersConnectionRepositiory = new JdbcUsersConnectionRepository(dataSource,
	        connectionFactoryLocator(), Encryptors.noOpText());

	    this.usersConnectionRepositiory = usersConnectionRepositiory;
	    socialContext = new SocialContext(usersConnectionRepositiory, getFacebook());

	    usersConnectionRepositiory.setConnectionSignUp(socialContext);
	    
	  }
}
