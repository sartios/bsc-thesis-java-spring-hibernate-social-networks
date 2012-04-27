package com.sones.facebook.keywordSearcher.logic;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.facebook.keywordSearcher.dao.IFacebookPostKeywordResultDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordSearchDao;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;

import com.sones.usermanager.model.ApplicationUser;

/**
 * This class is responsible for retrieving the keywords that 
 * were found a number of times in a time range.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public class KeywordRetrieverService 
{
	private Map<Keyword, Long> keywordInfo;
	private IKeywordSearchDao keywordSearchDao;
	private IFacebookPostKeywordResultDao resultDao;
	private final Logger _LOGGER;
	private ApplicationUser appUser;
	
	public KeywordRetrieverService()
	{
		_LOGGER = Logger.getLogger( KeywordRetrieverService.class );
		initResults();
	}
	
	public KeywordRetrieverService(IKeywordSearchDao keywordSearchDao, IFacebookPostKeywordResultDao resultDao, ApplicationUser appUser)
	{
		_LOGGER = Logger.getLogger( KeywordRetrieverService.class );
		this.keywordSearchDao = keywordSearchDao;
		this.resultDao = resultDao;
		this.appUser = appUser;
		initResults();
	}
	
	public Map<Keyword, Long> getKeywords(int timeOfFounds, int time)
	{
		initResults();
		Date date = findDateBefore(time);
		Collection<KeywordSearch> searches = keywordSearchDao.getAfterDateByAppUser(date, appUser);
		for(KeywordSearch search : searches)
		{
			Collection<FacebookPostKeywordResult> results = resultDao.getByKeywordSearch(search);
			updateKeywordInfo(results);
		}
		Map<Keyword, Long> keywordResults = new HashMap<Keyword, Long>();
		Set<Keyword> keywordSet = keywordInfo.keySet();
		for(Keyword keyword : keywordSet)
		{
			Long value = keywordInfo.get(keyword);
			if(value >= timeOfFounds)
			{
				keywordResults.put(keyword, value);
			}
		}
		return keywordResults;
	}
	
	public Date findDateBefore(int minutes)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minutes);
		Date date = calendar.getTime();
		return date;
	}
	
	public void updateKeywordInfo(Iterable<FacebookPostKeywordResult> results)
	{
		for(FacebookPostKeywordResult result : results)
		{
			Keyword keyword = result.getKeyword();
			if( keywordInfo.containsKey( keyword ) == true )
			{
				Long timeOfFounds = keywordInfo.get( keyword );
				if( timeOfFounds != null )
				{
					Long value = timeOfFounds + 1;
					keywordInfo.put(keyword, value);
				}
				if( timeOfFounds == null )
				{
					Long value = new Long(1);
					keywordInfo.put(keyword, value);
				}
			}
			else
			{
				Long value = new Long(1);
				keywordInfo.put(keyword, value);
			}
		}
	}
	
	public Map<Keyword, Long> getKeywordInfo()
	{
		return keywordInfo;
	}
	
	private void checkNullability(Object object, String message) throws IllegalArgumentException
	{
		if(object == null)
		{
			_LOGGER.error(message);
			throw new IllegalArgumentException(message);
		}
	}
	
	private void initResults()
	{
		keywordInfo = new HashMap<Keyword, Long>();
	}
}
