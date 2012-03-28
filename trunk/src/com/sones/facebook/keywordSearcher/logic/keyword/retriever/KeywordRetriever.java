package com.sones.facebook.keywordSearcher.logic.keyword.retriever;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

import com.sones.facebook.keywordSearcher.dao.IApplicationUserKeywordDao;
import com.sones.facebook.keywordSearcher.logic.exceptions.NoKeywordsException;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.sharedDto.facebook.keywordSearcher.KeywordSearchDto;
import com.sones.userManager.model.ApplicationUser;

public class KeywordRetriever implements IApplicationUserKeywordRetriever 
{
	/**
	 * The class logger.
	 */
	private	Logger	_LOGGER;
	
	/**
	 * The dozer mapper.
	 */
	private	DozerBeanMapper	mapper;
	
	/**
	 * The application user keyword dao.
	 */
	private	IApplicationUserKeywordDao	appUserKeywordDao;

	/**
	 * Initiates the object.
	 */
	public	KeywordRetriever()
	{
		initiateLogger();
	}

	/**
	 * Initiates the object.
	 * @param mapper
	 * @param appUserKeywordDao
	 */
	public	KeywordRetriever( DozerBeanMapper mapper , IApplicationUserKeywordDao appUserKeywordDao )
	{
		initiateLogger();
		setMapper( mapper );
		setAppUserKeywordDao( appUserKeywordDao );
	}
	
	@Override
	public Iterable<KeywordSearchDto> getApplicationUserKeywords( ApplicationUser appUser )
	{
		if( appUser == null )
		{
			_LOGGER.error( "Application user can't be null!" );
			throw	new	IllegalArgumentException( "Application user can't be null!" );
		}
		Iterable<ApplicationUserKeyword>	appUserkeywords	=	appUserKeywordDao.getByApplicationUser(appUser);
		if( appUserkeywords == null )
		{
			_LOGGER.error( "There are not keywords for this user." );
			throw	new	NoKeywordsException( "There are not keywords for this user." );
		}
		Set<KeywordSearchDto>	keywordsDto	=	new	HashSet<KeywordSearchDto>();
		for( ApplicationUserKeyword appUserKeyword : appUserkeywords )
		{
			Keyword	keyword	=	appUserKeyword.getId().getKeyword();
			KeywordSearchDto	keywordDto	=	new	KeywordSearchDto();
			mapper.map(keyword, keywordDto);
			keywordsDto.add( keywordDto );
		}
		return	keywordsDto;
	}

	/**
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}
	
	public IApplicationUserKeywordDao getAppUserKeywordDao() {
		return appUserKeywordDao;
	}

	public void setAppUserKeywordDao(IApplicationUserKeywordDao appUserKeywordDao) {
		this.appUserKeywordDao = appUserKeywordDao;
	}
	
	/**
	 * Initiates the class logger.
	 */
	private void initiateLogger() 
	{
		_LOGGER	=	Logger.getLogger( KeywordRetriever.class );
	}
}
