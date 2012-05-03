package com.sones.facebook.downloader.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.facebook.downloader.dao.IFacebookAccountFriendDao;
import com.sones.facebook.downloader.dao.IFacebookFriendDao;
import com.sones.facebook.downloader.model.FacebookAccountFriend;
import com.sones.facebook.downloader.model.FacebookFriend;
import com.sones.facebook.tokenmanager.dao.IFacebookAccountDao;
import com.sones.facebook.tokenmanager.model.FacebookAccount;
import com.sones.usermanager.model.ApplicationUser;
import com.sones.usermanager.dao.IApplicationUserDao;
public class HibernateFacebookAccountFriendDaoTester 
{
	private IFacebookAccountDao accountDao;
	private IFacebookFriendDao friendDao;
	private IFacebookAccountFriendDao accountFriendDao;
	private IApplicationUserDao appUserDao;
	
	public HibernateFacebookAccountFriendDaoTester()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("FacebookDownloader/spring-facebook-downloader-nu.xml");
		accountDao = (IFacebookAccountDao) context.getBean("facebookAccountDao");
		friendDao = (IFacebookFriendDao) context.getBean("facebookFriendDao");
		accountFriendDao = (IFacebookAccountFriendDao) context.getBean("facebookAccountFriendDao");
		appUserDao = (IApplicationUserDao) context.getBean("appUserDao");
	}
	
	@Test
	public void SaveTest()
	{
		ApplicationUser appUser = new ApplicationUser();
		appUser.setId("1");
		saveIfDoesNotExist(appUser,appUser.getId(),appUserDao);
		
		FacebookAccount owner = new FacebookAccount();
		owner.setAppUser(appUser);
		owner.setId("1");
		owner.setEmail("sartios@hotmail.com");
		saveIfDoesNotExist(owner,owner.getId(),accountDao);

		FacebookFriend friend = new FacebookFriend();
		friend.setId("1");
		friend.setName("Savramis Sartios");
		saveIfDoesNotExist(friend,friend.getId(),friendDao);

		FacebookAccountFriend accountFriend = new FacebookAccountFriend(owner,friend);
		saveIfDoesNotExist(accountFriend,accountFriend.getId(),accountFriendDao);
		
		FacebookAccountFriend dbaccountFriend = accountFriendDao.GetById(accountFriend.getId());
		assertEquals(accountFriend.getId(),dbaccountFriend.getId());
		
		deleteIfExists(accountFriend,accountFriend.getId(),accountFriendDao);
		deleteIfExists(friend,friend.getId(),friendDao);
		deleteIfExists(owner,owner.getId(),accountDao);
		deleteIfExists(appUser,appUser.getId(),appUserDao);
	}
	
	private void saveIfDoesNotExist(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) == null)
		{
			dao.Save(model);
		}
	}
	
	private void deleteIfExists(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) != null)
		{
			dao.Delete(model);
		}
	}
}
