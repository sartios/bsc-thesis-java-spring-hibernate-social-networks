package com.sones.facebook.tokenmanager.logic;

import org.apache.log4j.Logger;

import com.sones.dao.IGenericDao;
import com.sones.facebook.graphApi.GraphApiHandler.IFacebookGraphApiHandler;
import com.sones.facebook.tokenmanager.dao.IFacebookAccountDao;
import com.sones.facebook.tokenmanager.dao.IFacebookTokenDao;
import com.sones.facebook.tokenmanager.model.FacebookAccount;
import com.sones.facebook.tokenmanager.model.FacebookToken;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

public class FacebookTokenSaver implements IFacebookTokenSaver
{
	private final Logger _LOGGER;
	private IFacebookGraphApiHandler graphHandler;
	private IApplicationUserDao appUserDao;
	private IFacebookAccountDao accountDao;
	private IFacebookTokenDao tokenDao;
	
	private FacebookTokenSaver()
	{
		_LOGGER = Logger.getLogger(FacebookTokenSaver.class);
	}
	
	private FacebookTokenSaver(IFacebookGraphApiHandler graphHandler, IApplicationUserDao appUserDao,
			IFacebookAccountDao accountDao, IFacebookTokenDao tokenDao)
	{
		_LOGGER = Logger.getLogger(FacebookTokenSaver.class);
		this.graphHandler = graphHandler;
		this.appUserDao = appUserDao;
		this.accountDao = accountDao;
		this.tokenDao = tokenDao;
	}
	
	@Override
	public void saveFacebookToken(String appUserId,String value) 
	{
		ApplicationUser appUser = appUserDao.GetById(appUserId);
		
		FacebookToken token = new FacebookToken();
		token.setValue(value);
		
		FacebookAccount account = graphHandler.GetFacebookAccount(token);
		account.setAppUser(appUser);
		saveIfDoesNotExist(account, account.getId(), accountDao);
		
		token.setAccount(account);
		Number tokenId = tokenDao.GetRowCount();
		token.setId(tokenId.toString());
		
		saveIfDoesNotExist(token, token.getId(), tokenDao);
	}
	
	private void saveIfDoesNotExist(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) == null)
		{
			dao.Save(model);
		}
	}
	
}
