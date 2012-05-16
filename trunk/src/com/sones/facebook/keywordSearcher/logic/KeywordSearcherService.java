package com.sones.facebook.keywordSearcher.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

import com.sones.facebook.keywordSearcher.dao.IFacebookPostKeywordResultDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordSearchDao;
import com.sones.facebook.keywordSearcher.logic.exceptions.NoKeywordsException;
import com.sones.facebook.keywordSearcher.logic.exceptions.NotRegisteredRetriever;
import com.sones.facebook.keywordSearcher.logic.idmaker.IIdMaker;
import com.sones.facebook.keywordSearcher.logic.keyword.retriever.IApplicationUserKeywordRetriever;
import com.sones.facebook.keywordSearcher.logic.retriever.ICheckinSearchDataManager;
import com.sones.facebook.keywordSearcher.logic.retriever.IDataRetriever;
import com.sones.facebook.keywordSearcher.logic.retriever.ILinkSearchDataManager;
import com.sones.facebook.keywordSearcher.logic.retriever.INoteSearchDataManager;
import com.sones.facebook.keywordSearcher.logic.retriever.IPhotoSearchDataManager;
import com.sones.facebook.keywordSearcher.logic.retriever.IStatusMessageSearchDataManager;
import com.sones.facebook.keywordSearcher.logic.retriever.IVideoSearchDataManager;
import com.sones.facebook.keywordSearcher.logic.searcher.IKeywordSearcher;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;
import com.sones.sharedDto.facebook.keywordSearcher.KeywordSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.KeywordSearchResultCreateDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.usermanager.model.ApplicationUser;

/**
 * @assoc 1..0 has 0 IKeywordSearcher
 * @author sartios.sones@gmail.com.
 *
 */
public class KeywordSearcherService implements IKeywordSearcherService 
{
	/**
	 * The service logger.
	 */
	private	final	Logger	_LOGGER;
	
	/**
	 * The keyword searcher.
	 */
	private	IKeywordSearcher	searcher;
	
	/**
	 * The set of data retrievers.
	 */
	private	Set<IDataRetriever>	dataRetrievers;
	
	/**
	 * The dozer mapper.
	 */
	private	DozerBeanMapper	mapper;

	/**
	 * The keywords retriever
	 */
	private	IApplicationUserKeywordRetriever	keywordRetriever;
	
	/**
	 * The dao for {@link KeywordSearch} model.
	 */
	private	IKeywordSearchDao	keywordSearchDao;
	
	/**
	 * The dao for {@link FacebookPostKeywordResult} model
	 */
	private	IFacebookPostKeywordResultDao	keywordSearchResultDao;
	
	/**
	 * Cretes ids for the searches.
	 */
	private	IIdMaker	idMaker;
	
	/**
	 * The collection of the results that were created during searches.
	 */
	private Set<FacebookPostKeywordResult> results;
	
	/**
	 * The date after which will retrieve posts to search.
	 */
	private Date date;
	
	/**
	 * Initializes the object.
	 */
	private	KeywordSearcherService()
	{
		_LOGGER	=	Logger.getLogger( KeywordSearcherService.class );
		results = new HashSet<FacebookPostKeywordResult>();
		date = null;
	}
	
	/**
	 * @param searcher
	 * @param dataRetrievers
	 * @param mapper
	 * @param keywordRetriever
	 * @param keywordSearchDao
	 * @param keywordSearchResultDao
	 * @param idMaker
	 * @param results
	 */
	public KeywordSearcherService(IKeywordSearcher searcher,
			DozerBeanMapper mapper,
			IApplicationUserKeywordRetriever keywordRetriever,
			IKeywordSearchDao keywordSearchDao,
			IFacebookPostKeywordResultDao keywordSearchResultDao,
			IIdMaker idMaker) 
	{
		_LOGGER	=	Logger.getLogger( KeywordSearcherService.class );
		this.searcher = searcher;
		this.mapper = mapper;
		this.keywordRetriever = keywordRetriever;
		this.keywordSearchDao = keywordSearchDao;
		this.keywordSearchResultDao = keywordSearchResultDao;
		this.idMaker = idMaker;
		results = new HashSet<FacebookPostKeywordResult>();
		date = null;	
		dataRetrievers = new HashSet<IDataRetriever>();
	}
	
	@Override
	public void searchForKeywordsIntoAllFacebookPostTypes( ApplicationUser appUser ) 
	{
		if(isNull(appUser) == true)
		{
			_LOGGER.error( "Application user can't be null!" );
			throw	new	IllegalArgumentException( "Application user can't be null!" );
		}
		
		
		searchForKeywordsIntoCheckins( appUser, date );
		searchForKeywordsIntoPhotos( appUser, date );
		searchForKeywordsIntoLinks( appUser, date );
		searchForKeywordsIntoStatusMessages( appUser, date );
		searchForKeywordsIntoVideos( appUser, date );
		searchForKeywordsIntoNotes( appUser, date );
		saveKeywordSearchResults( results );
		date = getDateForNextData( appUser );
	}

	@Override
	public void searchForKeywordsIntoLinks( ApplicationUser appUser , Date date ) 
	{
		IDataRetriever	dataRetriever	=	null;
		for( IDataRetriever	temp : dataRetrievers )
		{
			if( temp instanceof ILinkSearchDataManager )
			{
				dataRetriever	=	temp;
				break;
			}
		}
		search( dataRetriever , appUser, date );

	}

	@Override
	public void searchForKeywordsIntoNotes( ApplicationUser appUser , Date date ) 
	{
		IDataRetriever	dataRetriever	=	null;
		for( IDataRetriever	temp : dataRetrievers )
		{
			if( temp instanceof INoteSearchDataManager )
			{
				dataRetriever	=	temp;
				break;
			}
		}
		search( dataRetriever , appUser, date );

	}

	@Override
	public void searchForKeywordsIntoPhotos( ApplicationUser appUser , Date date )
	{
		IDataRetriever	dataRetriever	=	null;
		for( IDataRetriever	temp : dataRetrievers )
		{
			if( temp instanceof IPhotoSearchDataManager )
			{
				dataRetriever	=	temp;
				break;
			}
		}
		search( dataRetriever , appUser, date );
	}

	@Override
	public void searchForKeywordsIntoStatusMessages( ApplicationUser appUser , Date date )
	{
		IDataRetriever	dataRetriever	=	null;
		for( IDataRetriever	temp : dataRetrievers )
		{
			if( temp instanceof IStatusMessageSearchDataManager )
			{
				dataRetriever	=	temp;
				break;
			}
		}
		search( dataRetriever , appUser, date );

	}

	@Override
	public void searchForKeywordsIntoVideos( ApplicationUser appUser , Date date )
	{
		IDataRetriever	dataRetriever	=	null;
		for( IDataRetriever	temp : dataRetrievers )
		{
			if( temp instanceof IVideoSearchDataManager )
			{
				dataRetriever	=	temp;
				break;
			}
		}
		search( dataRetriever , appUser, date );

	}

	@Override
	public void searchForKeywordsIntoCheckins( ApplicationUser appUser , Date date )
	{
		IDataRetriever	dataRetriever	=	null;
		for( IDataRetriever	temp : dataRetrievers )
		{
			if( temp instanceof ICheckinSearchDataManager )
			{
				dataRetriever	=	temp;
				break;
			}
		}
		search( dataRetriever , appUser, date );
	}
	
	@Override
	public	void	addDataRetriever( IDataRetriever dataRetriever )
	{
		if( dataRetriever == null )
		{
			_LOGGER.error( "Data retriever can't be null" );
			throw	new	IllegalArgumentException();
		}
		if( dataRetrievers == null )
		{
			dataRetrievers	=	new	HashSet< IDataRetriever >();
		}
		dataRetrievers.add( dataRetriever );
	}

	@Override
	public void setNextDateAfterWhichWillRetrieveData(Date date) 
	{
		this.date = date;
	}
	
	private boolean isNull(Object object)
	{
		if(object == null)
		{
			return true;
		}
		return false;
	}
	
	private KeywordSearch getKeywordSearch( ApplicationUser appUser )
	{
		KeywordSearch	search	=	new	KeywordSearch();
		Date	now	=	Calendar.getInstance().getTime();
		search.setDate( now );
		search.setUser( appUser );
		String	searchId	=	idMaker.getKeywordSearchId();
		search.setId( searchId );
		return	search;
	}
	
	private Set<FacebookPostKeywordResult> getResultsFromDto( Iterable<KeywordSearchResultCreateDto> resultsDto , KeywordSearch search, ApplicationUser appUser) 
	{
		Set<FacebookPostKeywordResult>	results	=	new	HashSet<FacebookPostKeywordResult>();
		for( KeywordSearchResultCreateDto resultDto : resultsDto )
		{
			FacebookPostKeywordResult	result	=	new	FacebookPostKeywordResult();
			mapper.map( resultDto, result );
			result.setSearch( search );
			result.setUser( appUser );
			results.add( result );
		}
		return	results;
	}

	private void validateResults(
			Iterable<KeywordSearchResultCreateDto> resultsDto) 
	{
		if( resultsDto	==	null )
		{
			_LOGGER.error( "There are not results. resultsDto is null!" );
		}	
	}
	
	private void saveKeywordSearchResults(Set<FacebookPostKeywordResult> results) 
	{
		_LOGGER.warn( "There are " + results.size() + "to be saved." );
		for( FacebookPostKeywordResult result : results )
		{
			String	id	=	idMaker.getKeywordSearchResultId();
			result.setId( id );
			keywordSearchResultDao.Save( result );
		}		
	}
	
	private Date getDateForNextData( ApplicationUser appUser )  
	{
		KeywordSearch search = keywordSearchDao.getLastKeywordSearchByAppUser(appUser);
		Date nextdate = null;
		if(search == null)
		{
			nextdate = new Date(0);
		}
		else
		{
			nextdate = search.getDate();
		}
		return nextdate;
	}
	
	private void search( IDataRetriever dataRetriever , ApplicationUser appUser, Date date ) 
	{
		if(isNull(dataRetriever) == true)
		{
			_LOGGER.error( "There is not checkin data retriever registered!" );
			throw new NotRegisteredRetriever( "There is not checkin data retriever registered!" );
		}
		Iterable<KeywordSearchDto>	keywords	=	keywordRetriever.getApplicationUserKeywords( appUser );

		if(isNull(keywords) == true)
		{
			_LOGGER.error( "Application user's keywords are null!" );
			throw new NoKeywordsException( "There are not keywords for this application user" );
		}
		Iterable< ISearchableFacebookFeed >	posts	=	dataRetriever.getDataToBeSearched( appUser, date );

		if(isNull(posts) == true)
		{
			_LOGGER.error( "The posts that are to be searched are null." );
		}

		KeywordSearch	search	=	getKeywordSearch( appUser );	
		keywordSearchDao.Save( search );
		if( posts != null )
		{
			Iterable< KeywordSearchResultCreateDto >	resultsDto	= searcher.getKeywordSearchResults(posts, keywords);
			validateResults( resultsDto );
			if( resultsDto != null )
			{
				Set< FacebookPostKeywordResult >	tmpresults	=	getResultsFromDto( resultsDto , search , appUser );
				results.addAll(tmpresults);
			}
		}
		
	}
}