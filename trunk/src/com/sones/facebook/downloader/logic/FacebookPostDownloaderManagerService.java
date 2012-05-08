package com.sones.facebook.downloader.logic;

import java.util.Timer;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sones.facebook.downloader.dao.IFacebookDownloadOptionDao;
import com.sones.facebook.downloader.model.FacebookDownloadOption;
import com.sones.facebook.tokenmanager.dao.IFacebookAccountDao;
import com.sones.facebook.tokenmanager.model.FacebookAccount;
import com.sones.usermanager.model.ApplicationUser;

public class FacebookPostDownloaderManagerService implements IFacebookPostDownloaderManagerService
{
	private final Logger _LOGGER;
	private IFacebookDownloadOptionDao optionDao;
	private IFacebookAccountDao accountDao;
	private Timer timer;
	private PostDownloadingTimerTask task;
	
	public FacebookPostDownloaderManagerService()
	{
		_LOGGER = Logger.getLogger( FacebookPostDownloaderManagerService.class );
	}
	
	public FacebookPostDownloaderManagerService(IFacebookDownloadOptionDao optionDao, 
			IFacebookAccountDao accountDao,PostDownloadingTimerTask task)
	{
		_LOGGER = Logger.getLogger( FacebookPostDownloaderManagerService.class );
		this.optionDao = optionDao;
		this.accountDao = accountDao;
		this.task = task;
		timer = new Timer();
	}

	@Override
	public void startDownloading(String facebookUserId) throws DataAccessException
	{
		FacebookAccount account = accountDao.GetById(facebookUserId);
		if(account == null)
		{
			_LOGGER.error("Facebook account id doesn't match to a persisted account.");
			throw new NullPointerException("Facebook account id doesn't match to a persisted account.");
		}
		
		FacebookDownloadOption option = optionDao.getByAccount(account);
		if(option == null)
		{
			_LOGGER.error("Facebook account didn't set an option.");
			throw new NullPointerException("Facebook account didn't set an option.");
		}
		long delay = Long.parseLong(option.getDownloadInterval());
		ApplicationUser appUser = account.getAppUser();
		if(appUser == null)
		{
			_LOGGER.error("Null application user");
		}
		task.setAppUser( appUser );
		timer.schedule(task, 0, delay);
	}
	
	@Override
	public void saveOptions(String facebookUserId, String interval) 
	{
		FacebookAccount account = accountDao.GetById(facebookUserId);
		if(account == null)
		{
			_LOGGER.error("Facebook account id doesn't match to a persisted account.");
			throw new NullPointerException("Facebook account id doesn't match to a persisted account.");
		}
		
		FacebookDownloadOption existedOption = optionDao.getByAccount(account);
		if(existedOption != null)
		{
			_LOGGER.warn("Facebook account has already set options.");
			_LOGGER.warn("Clearing up the account previous options.");
			optionDao.Delete(existedOption);
		}
		FacebookDownloadOption option = new FacebookDownloadOption(account, interval);
		Number optionID =accountDao.GetRowCount();
		option.setId( optionID.toString() );
		optionDao.Save(option);
	}

	@Override
	public void stopDownloading() 
	{
		timer.cancel();
	}
}
