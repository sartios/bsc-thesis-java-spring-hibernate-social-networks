package com.sones.facebook.controller.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.tokenmanager.logic.IFacebookTokenSaver;

public class FacebookTokenController 
{
	private IFacebookTokenSaver tokenSaverService;
	
	public FacebookTokenController()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("FacebookTokenSaver/spring-facebooktokensaver-logic.xml");
		tokenSaverService = (IFacebookTokenSaver) context.getBean("facebookTokenSaverService");
	}
	
	public void saveToken(String appUserId, String value)
	{
		tokenSaverService.saveFacebookToken(appUserId, value);
	}
	
	public String getTokenRequestUrl()
	{
		String tokenRequest = "https://www.facebook.com/dialog/oauth?"+
	    "client_id=164830413559108"+
	   "&redirect_uri=https://www.facebook.com/connect/login_success.html"+
	   "&scope=user_about_me,friends_about_me,user_checkins,friends_checkins,user_events,friends_events,user_groups,friends_groups,user_hometown,friends_hometown,user_notes,friends_notes,user_photos,friends_photos,user_status,friends_status,user_videos,friends_videos"+
	   "&response_type=token";
		return tokenRequest;
	}
}
