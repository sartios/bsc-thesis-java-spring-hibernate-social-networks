package com.sones.facebook.keywordSearcher.logic;

import java.util.Date;
import java.util.Timer;

import org.apache.log4j.Logger;

import com.sones.facebook.keywordSearcher.dao.IKeywordSearchOptionDao;
import com.sones.facebook.keywordSearcher.model.KeywordSearchOption;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

public class KeywordSearcherManagerService implements IKeywordSearcherManagerService
{
	private final Logger _LOGGER;
	private IKeywordSearchOptionDao optionDao;
	private IApplicationUserDao appUserDao;
	private Timer timer;
	private IKeywordSearcherService service;
	private KeywordSearchTimerTask task;
	
	private KeywordSearcherManagerService()
	{
		_LOGGER = Logger.getLogger(KeywordSearcherManagerService.class);
	}
	
	/**
	 * @param optionDao
	 * @param appUserDao
	 * @param timer
	 * @param task
	 */
	public KeywordSearcherManagerService(IKeywordSearchOptionDao optionDao,
			IApplicationUserDao appUserDao) 
	{
		_LOGGER = Logger.getLogger(KeywordSearcherManagerService.class);
		this.optionDao = optionDao;
		this.appUserDao = appUserDao;
	}

	@Override
	public void saveOptions(String appUserId, String interval) 
	{
		if(appUserId == null)
		{
			_LOGGER.error("Application user id can't be null");
			throw new NullPointerException("Application user id can't be null");
		}
		
		if(interval == null)
		{
			_LOGGER.error("Interval value can't be null");
			throw new NullPointerException("Interval value can't be null");
		}
		
		ApplicationUser appUser = appUserDao.GetById(appUserId);
		if(appUser == null)
		{
			_LOGGER.error("Application user doesn't exist.");
			throw new NullPointerException("Application user doesn't exist");
		}
		
		KeywordSearchOption prevOption = optionDao.getByApplicationUser(appUser);
		if(prevOption != null)
		{
			optionDao.Delete(prevOption);
		}
		
		Number optionId = optionDao.GetRowCount();
		KeywordSearchOption option = new KeywordSearchOption(interval, appUser);
		option.setId(optionId.toString());
		optionDao.Save(option);
	}

	@Override
	public void startKeywordSearch(String appUserId, Date date) 
	{
		ApplicationUser appUser = appUserDao.GetById(appUserId);
		if(appUser == null)
		{
			_LOGGER.error("Application user doesn't exist.");
			throw new NullPointerException("Application user doesn't exist");
		}
		
		KeywordSearchOption option = optionDao.getByApplicationUser(appUser);
		if(option == null)
		{
			_LOGGER.error("Application user hasn't set option.");
			throw new NullPointerException("Application user hasn't set option.");
		}
		
		String interval = option.getInterval();
		long delay = Long.parseLong(interval);
		
		task = new KeywordSearchTimerTask(service);
		task.setApplicationUser(appUser);
		task.setFirstDate(date);
		
		timer = new Timer();
		timer.schedule(task, 0, delay);
	}

	@Override
	public void stopKeywordSearch() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSearchService(IKeywordSearcherService service) 
	{
		this.service = service;
	}

	@Override
	public String getSearchInterval(String appUserId) 
	{
		ApplicationUser appUser = appUserDao.GetById(appUserId);
		if(appUser == null)
		{
			_LOGGER.error("Application user doesn't exist.");
			throw new NullPointerException("Application user doesn't exist");
		}
		
		KeywordSearchOption option = optionDao.getByApplicationUser(appUser);
		if(option == null)
		{
			_LOGGER.error("Application user hasn't set option.");
			throw new NullPointerException("Application user hasn't set option.");
		}
		return option.getInterval();
	}
	
}
