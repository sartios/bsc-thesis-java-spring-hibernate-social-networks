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
import com.sones.userManager.model.ApplicationUser;

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
	
	private	IKeywordSearchDao	keywordSearchDao;
	
	private	IFacebookPostKeywordResultDao	keywordSearchResultDao;
	
	private	IIdMaker	idMaker;

	/**
	 * Initializes the object.
	 */
	public	KeywordSearcherService()
	{
		_LOGGER	=	Logger.getLogger( KeywordSearcherService.class );
	}
	
	@Override
	public void searchForKeywordsIntoAllFacebookPostTypes( ApplicationUser appUser ) 
	{
		searchForKeywordsIntoCheckins( appUser );
		searchForKeywordsIntoPhotos( appUser );
		searchForKeywordsIntoLinks( appUser );
		searchForKeywordsIntoStatusMessages( appUser );
		searchForKeywordsIntoVideos( appUser );
		searchForKeywordsIntoNotes( appUser );

	}
	
	@Override
	public void searchForKeywordsIntoLinks(ApplicationUser appUser) 
	{
		checkApplicationUser( appUser );
		IDataRetriever	dataRetriever	=	null;
		for( IDataRetriever	temp : dataRetrievers )
		{
			if( temp instanceof ILinkSearchDataManager )
			{
				dataRetriever	=	temp;
				break;
			}
		}
		search( dataRetriever , appUser );

	}

	@Override
	public void searchForKeywordsIntoNotes(ApplicationUser appUser) 
	{
		checkApplicationUser( appUser );
		IDataRetriever	dataRetriever	=	null;
		for( IDataRetriever	temp : dataRetrievers )
		{
			if( temp instanceof INoteSearchDataManager )
			{
				dataRetriever	=	temp;
				break;
			}
		}
		search( dataRetriever , appUser );

	}

	@Override
	public void searchForKeywordsIntoPhotos(ApplicationUser appUser) 
	{
		checkApplicationUser( appUser );
		IDataRetriever	dataRetriever	=	null;
		for( IDataRetriever	temp : dataRetrievers )
		{
			if( temp instanceof IPhotoSearchDataManager )
			{
				dataRetriever	=	temp;
				break;
			}
		}
		search( dataRetriever , appUser );
	}

	@Override
	public void searchForKeywordsIntoStatusMessages(ApplicationUser appUser) 
	{
		checkApplicationUser( appUser );
		IDataRetriever	dataRetriever	=	null;
		for( IDataRetriever	temp : dataRetrievers )
		{
			if( temp instanceof IStatusMessageSearchDataManager )
			{
				dataRetriever	=	temp;
				break;
			}
		}
		search( dataRetriever , appUser );

	}

	@Override
	public void searchForKeywordsIntoVideos(ApplicationUser appUser) 
	{
		checkApplicationUser( appUser );
		IDataRetriever	dataRetriever	=	null;
		for( IDataRetriever	temp : dataRetrievers )
		{
			if( temp instanceof IVideoSearchDataManager )
			{
				dataRetriever	=	temp;
				break;
			}
		}
		search( dataRetriever , appUser );

	}

	@Override
	public void searchForKeywordsIntoCheckins(ApplicationUser appUser) 
	{
		checkApplicationUser( appUser );
		IDataRetriever	dataRetriever	=	null;
		for( IDataRetriever	temp : dataRetrievers )
		{
			if( temp instanceof ICheckinSearchDataManager )
			{
				dataRetriever	=	temp;
				break;
			}
		}
		search( dataRetriever , appUser );
	}

	private void search( IDataRetriever dataRetriever , ApplicationUser appUser ) 
	{
		validateDataRetriever(  dataRetriever );
		Iterable<KeywordSearchDto>	keywords	=	keywordRetriever.getApplicationUserKeywords( appUser );
		validateKeywords( keywords );
		Iterable< ISearchableFacebookFeed >	posts	=	dataRetriever.getDataToBeSearched( appUser );
		validatePosts( posts );
		KeywordSearch	search	=	getKeywordSearch( appUser );	
		keywordSearchDao.Save( search );
		if( posts != null )
		{
			Iterable< KeywordSearchResultCreateDto >	resultsDto	= searcher.getKeywordSearchResults(posts, keywords);
			validateResults( resultsDto );
			if( resultsDto != null )
			{
				Set< FacebookPostKeywordResult >	results	=	getResultsFromDto( resultsDto , search , appUser );
				saveKeywordSearchResults( results );
			}
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

	private void validatePosts(Iterable<ISearchableFacebookFeed> posts)
	{
 		if( posts == null )
		{
			_LOGGER.error( "The posts that are to be searched are null." );
		}
		
	}

	private void validateKeywords(Iterable<KeywordSearchDto> keywords)
	{
		if( keywords == null )
		{
			_LOGGER.error( "Application user's keywords are null!" );
			throw	new	NoKeywordsException( "There are not keywords for this application user" );
		}
		
	}

	private void validateDataRetriever(IDataRetriever dataRetriever) 
	{
		if( dataRetriever == null )
		{
			_LOGGER.error( "There is not checkin data retriever registered!" );
			throw	new	NotRegisteredRetriever( "There is not checkin data retriever registered!" );
		}
		
	}
	
	private	void	checkApplicationUser( ApplicationUser appUser )
	{
		if( appUser == null )
		{
			_LOGGER.error( "Application user can't be null!" );
			throw	new	IllegalArgumentException( "Application user can't be null!" );
		}
	}

	/**
	 * @param dataRetrievers the dataRetrievers to set
	 */
	public void setDataRetrievers(Set<IDataRetriever> dataRetrievers) {
		this.dataRetrievers = dataRetrievers;
	}

	/**
	 * @return the dataRetrievers
	 */
	public Set<IDataRetriever> getDataRetrievers() {
		return dataRetrievers;
	}

	/**
	 * @param searcher the searcher to set
	 */
	public void setSearcher(IKeywordSearcher searcher) {
		this.searcher = searcher;
	}

	/**
	 * @return the searcher
	 */
	public IKeywordSearcher getSearcher() {
		return searcher;
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
	

	
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	public IApplicationUserKeywordRetriever getKeywordRetriever() {
		return keywordRetriever;
	}

	public void setKeywordRetriever(
			IApplicationUserKeywordRetriever keywordRetriever) {
		this.keywordRetriever = keywordRetriever;
	}

	/**
	 * @param keywordSearchDao the keywordSearchDao to set
	 */
	public void setKeywordSearchDao(IKeywordSearchDao keywordSearchDao) {
		this.keywordSearchDao = keywordSearchDao;
	}

	/**
	 * @return the keywordSearchDao
	 */
	public IKeywordSearchDao getKeywordSearchDao() {
		return keywordSearchDao;
	}

	/**
	 * @param keywordSearchResultDao the keywordSearchResultDao to set
	 */
	public void setKeywordSearchResultDao(IFacebookPostKeywordResultDao keywordSearchResultDao) {
		this.keywordSearchResultDao = keywordSearchResultDao;
	}

	/**
	 * @return the keywordSearchResultDao
	 */
	public IFacebookPostKeywordResultDao getKeywordSearchResultDao() {
		return keywordSearchResultDao;
	}

	/**
	 * @param idMaker the idMaker to set
	 */
	public void setIdMaker(IIdMaker idMaker) {
		this.idMaker = idMaker;
	}

	/**
	 * @return the idMaker
	 */
	public IIdMaker getIdMaker() {
		return idMaker;
	}
}
