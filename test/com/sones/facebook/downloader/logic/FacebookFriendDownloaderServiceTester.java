package com.sones.facebook.downloader.logic;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.facebook.downloader.dao.IFacebookAccountFriendDao;
import com.sones.facebook.downloader.model.FacebookFriend;
import com.sones.facebook.tokenmanager.dao.IFacebookAccountDao;
import com.sones.facebook.tokenmanager.dao.IFacebookTokenDao;
import com.sones.facebook.tokenmanager.model.FacebookAccount;
import com.sones.facebook.tokenmanager.model.FacebookToken;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

public class FacebookFriendDownloaderServiceTester 
{
	private IApplicationUserDao appUserDao;
	private IFacebookAccountDao accountDao;
	private IFacebookTokenDao tokenDao;
	private IFacebookAccountFriendDao accountFriendDao;
	private IFacebookFriendDownloaderService service;
	
	private ApplicationUser appUser;
	private FacebookAccount account;
	private FacebookToken token;
	
	private Iterable<FacebookFriend> friends;
	
	public FacebookFriendDownloaderServiceTester()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("FacebookDownloader/spring-facebook-friend-downloader-service-nu.xml");
		appUserDao = (IApplicationUserDao) context.getBean("appUserDao");
		accountDao = (IFacebookAccountDao) context.getBean("accountDao");
		tokenDao = (IFacebookTokenDao) context.getBean("tokenDao");
		accountFriendDao = (IFacebookAccountFriendDao) context.getBean("accountFriendDao");
		service = (IFacebookFriendDownloaderService) context.getBean("friendDownloaderService");
	}
	
	@Before
	public void setup()
	{
		appUser = new ApplicationUser();
		appUser.setId( "1" );
		saveIfDoesNotExist(appUser,appUser.getId(),appUserDao);
		
		account = new FacebookAccount();
		account.setId("100000866964787");
		account.setAppUser(appUser);
		account.setEmail("sartios@hotmail.com");
		saveIfDoesNotExist(account,account.getId(),accountDao);

		token = new FacebookToken();
		token.setAccount(account);
		token.setId("1");
		token.setValue("access_token=AAACV6ZAIZClUQBAIUWj1gLZCZCFmkFugI4MLsBIv5MbBybzYTVTmFeWzd4LVQflYG9s50RA3GcOE771VVGe8HrMT7jHZBuNqCC0F4Ppc4CQZDZD");
		saveIfDoesNotExist(token,token.getId(),tokenDao);
	}
	
	@Test
	public void downloadFacebookFriendsShouldWorkCorrectly()
	{
		service.downloadFacebookFriends(account.getId());	
	}
	
	@Test
	public void getFacebookFriendsShouldWorkCorrectly()
	{
		friends = service.getFacebookFriends(account.getId());
		int count = 0;
		for(FacebookFriend friend : friends)
		{
			count++;
		}
		assertEquals(538,count);
	}
	
	private void saveIfDoesNotExist(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) == null)
		{
			dao.Save(model);
		}
	}
}
