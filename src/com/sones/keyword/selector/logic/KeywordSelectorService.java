package com.sones.keyword.selector.logic;

import org.apache.log4j.Logger;

import com.sones.dao.exception.DataAccessLayerException;
import com.sones.facebook.keywordSearcher.dao.IApplicationUserKeywordDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.usermanager.model.ApplicationUser;

public class KeywordSelectorService implements IKeywordSelectorService
{
	private final Logger _LOGGER;
	private IApplicationUserKeywordDao appUserKeywordDao;
	private IKeywordDao keywordDao;
	
	public KeywordSelectorService(IApplicationUserKeywordDao appUserKeywordDao, IKeywordDao keywordDao)
	{
		_LOGGER = Logger.getLogger( KeywordSelectorService.class );
		this.appUserKeywordDao = appUserKeywordDao;
		this.keywordDao = keywordDao;
	}
	
	@Override
	public void selectKeyword(ApplicationUser appUser, Keyword keyword) 
	{
		checkNullability(appUser,"Application user can't be null");
		checkNullability(keyword, "Keyword can't be null");
		
		Keyword dbkeyword = keywordDao.getByValue(keyword);
		if(dbkeyword == null)
		{
			_LOGGER.error( "The keyword doesn't exist." );
			throw new DataAccessLayerException( "The keyword doesn't exist." );
		}
		
		ApplicationUserKeyword appUserKeyword = new ApplicationUserKeyword(dbkeyword, appUser);
		if( appUserKeywordDao.GetById( appUserKeyword.getId() ) == null )
		{
			appUserKeywordDao.Save( appUserKeyword );
		}
	}
	
	private void checkNullability(Object object, String message) throws IllegalArgumentException
	{
		if(object == null)
		{
			_LOGGER.error(message);
			throw new IllegalArgumentException(message);
		}
	}
	
}
