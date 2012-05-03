package com.sones.facebook.downloader.logic;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.dao.IGenericDao;
import com.sones.facebook.downloader.dao.IFacebookAccountFriendDao;
import com.sones.facebook.downloader.dao.IFacebookFriendDao;
import com.sones.facebook.downloader.model.FacebookAccountFriend;
import com.sones.facebook.downloader.model.FacebookFriend;
import com.sones.facebook.graphApi.GraphApiHandler.IFacebookGraphApiHandler;
import com.sones.facebook.tokenmanager.dao.IFacebookAccountDao;
import com.sones.facebook.tokenmanager.dao.IFacebookTokenDao;
import com.sones.facebook.tokenmanager.model.FacebookAccount;
import com.sones.facebook.tokenmanager.model.FacebookToken;

public class FacebookFriendDownloaderService implements IFacebookFriendDownloaderService
{
	/**
	 * The class logger.
	 */
	private	final	Logger	_LOGGER;
	
	/**
	 * Provides methods that implements the facebook graph api handler.
	 */
	private	IFacebookGraphApiHandler	graphApiHandler;
	
	private IFacebookAccountDao accountDao;
	private IFacebookTokenDao tokenDao;
	private IFacebookAccountFriendDao accountFriendDao;
	private IFacebookFriendDao friendDao;
	
	private FacebookFriendDownloaderService()
	{
		_LOGGER = Logger.getLogger(FacebookFriendDownloaderService.class);
	}
	
	public FacebookFriendDownloaderService(IFacebookGraphApiHandler	graphApiHandler, IFacebookAccountDao accountDao,
			IFacebookTokenDao tokenDao,IFacebookAccountFriendDao accountFriendDao, IFacebookFriendDao friendDao )
	{
		_LOGGER = Logger.getLogger(FacebookFriendDownloaderService.class);
		this.graphApiHandler = graphApiHandler;
		this.accountDao = accountDao;
		this.tokenDao = tokenDao;
		this.accountFriendDao = accountFriendDao;
		this.friendDao = friendDao;
	}

	@Override
	public void downloadFacebookFriends(String facebookUserId) 
	{
		FacebookAccount account = accountDao.GetById(facebookUserId);
		if(account == null)
		{
			throw new NullPointerException("Account doesn't exist.");
		}
		
		FacebookToken token = tokenDao.GetByAccount(account);
		if(account == null)
		{
			throw new NullPointerException("Token doesn't exist.");
		}
		
		Iterable<FacebookFriend> friends = graphApiHandler.GetFacebookFriends(account, token);
		for(FacebookFriend friend : friends)
		{
			saveIfDoesNotExist(friend,friend.getId(),friendDao);
			FacebookAccountFriend accountFriend = new FacebookAccountFriend(account, friend);
			saveIfDoesNotExist(accountFriend,accountFriend.getId(),accountFriendDao);
		}
	}
	
	@Override
	public Iterable<FacebookFriend> getFacebookFriends(String facebookUserId)
	{
		FacebookAccount owner = accountDao.GetById(facebookUserId);
		Iterable<FacebookAccountFriend> accountFriends = accountFriendDao.getByOwner(owner);
		Set<FacebookFriend> friends = new HashSet<FacebookFriend>();
		for(FacebookAccountFriend accountFriend : accountFriends )
		{
			FacebookFriend friend = accountFriend.getId().getFriend();
			friends.add(friend);
		}
		return friends;
	}
	
	private void saveIfDoesNotExist(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) == null)
		{
			dao.Save(model);
		}
	}
}
