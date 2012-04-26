package com.sones.facebook.downloader.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

import com.sones.facebook.downloader.dao.IFacebookDownloadDao;
import com.sones.facebook.downloader.dao.IFacebookPostDownloadDao;
import com.sones.facebook.downloader.logic.exceptions.NoFacebookAccessTokenException;
import com.sones.facebook.downloader.logic.exceptions.NoFacebookSourcesException;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.downloader.model.FacebookPostDownloadId;
import com.sones.facebook.graphApi.GraphApiHandler.IFacebookGraphApiHandler;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.saver.IFacebookPostSaverLogic;
import com.sones.facebook.tokenmanager.model.FacebookToken;
import com.sones.facebook.tokenmanager.dao.IFacebookTokenDao;
import com.sones.facebook.usermanager.dao.IApplicationUserSourceDao;
import com.sones.facebook.usermanager.model.ApplicationUserSource;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostIdDto;
import com.sones.usermanager.model.ApplicationUser;

public class FacebookDownloaderService	implements	IFacebookDownloaderService
{
	/**
	 * The class logger.
	 */
	private	final	Logger	_LOGGER;
	
	/**
	 * The token dao.
	 */
	private	IFacebookTokenDao	tokenDao;
	
	/**
	 * The application user source dao.
	 */
	private	IApplicationUserSourceDao	appUserSourceDao;
	
	/**
	 * The facebook download dao.
	 */
	private	IFacebookDownloadDao	downloadDao;
	
	/**
	 * Provides methods that implements the facebook graph api handler.
	 */
	private	IFacebookGraphApiHandler	graphApiHandler;
	
	/**
	 * Provides methods for saving the posts associated with their source.
	 */
	private	IFacebookPostSaverLogic	saverService;
	
	/**
	 * Provides methods for mapping dtos to model objects and the inverse.
	 */
	private	DozerBeanMapper	mapper;
	
	/**
	 * Provides methods for saving the facebook posts associated with the download instance.
	 */
	private	IFacebookPostDownloadDao	postDownloadDao;
	
	/**
	 * Initializes the object.
	 */
	public	FacebookDownloaderService()
	{
		_LOGGER	=	Logger.getLogger( FacebookDownloaderService.class );
	}
	
	

	/**
	 * Initializes the object.
	 * @param tokenDao
	 * @param appUserSourceDao
	 * @param downloadDao
	 * @param graphApiHandler
	 * @param saverService
	 * @param mapper
	 * @param postDownloadDao
	 */
	public FacebookDownloaderService(IFacebookTokenDao tokenDao,IApplicationUserSourceDao appUserSourceDao,IFacebookDownloadDao downloadDao,
			IFacebookGraphApiHandler graphApiHandler,IFacebookPostSaverLogic saverService, DozerBeanMapper mapper,IFacebookPostDownloadDao postDownloadDao) 
	{
		super();
		_LOGGER	=	Logger.getLogger( FacebookDownloaderService.class );
		this.tokenDao = tokenDao;
		this.appUserSourceDao = appUserSourceDao;
		this.downloadDao = downloadDao;
		this.graphApiHandler = graphApiHandler;
		this.saverService = saverService;
		this.mapper = mapper;
		this.postDownloadDao = postDownloadDao;
	}

	/**
	 * ..
	 */
	@Override
	public void DownloadWallPosts(ApplicationUser appUser) 
	{
		ValidateApplicationUser( appUser );
		FacebookToken	accessToken	=	GetFacebookToken( appUser );
		Iterable< ApplicationUserSource >	appUserSources	=	GetApplicationUserSources( appUser );
		Date	date	=	GetDownloadDate( appUser );
		Set< WallSourceFacebookPostCreateDto >	sourcePostsDtos	=	GetSourcePosts(appUserSources , accessToken , date );
		FacebookDownload	currentDownload	=	SaveCurrentDownloadAndReturn( appUser );
		saverService.saveWallPosts( sourcePostsDtos );
		Set< FacebookPostDownload >	postDownloads	=	SaveFacebookPostsPerDownloadAndReturn( sourcePostsDtos , currentDownload);
	}
	
	private Set< WallSourceFacebookPostCreateDto > GetSourcePosts(Iterable<ApplicationUserSource> appUserSources , FacebookToken accessToken, Date date)
	{
		Set< WallSourceFacebookPostCreateDto >	sourcePostsDtos	=	new	HashSet< WallSourceFacebookPostCreateDto >();
		for( ApplicationUserSource appUserSource : appUserSources )
		{
			Source	source	=	appUserSource.getId().getSource();
			CheckSourceAndThrow( source );
			WallSourceCreateDto	sourceDto	=	GetSourceDto(source);
			Iterable<WallFacebookPostCreateDto> downloadedPostsDto	=	graphApiHandler.GetWallPostsAfterDate( source, accessToken, date);
			AddPostsIntoSourcePostColletion(downloadedPostsDto, sourcePostsDtos, sourceDto);
		}
		return sourcePostsDtos;
	}
	
	private WallSourceCreateDto GetSourceDto(Source source)
	{
		WallSourceCreateDto	sourceDto	=	new	WallSourceCreateDto();
		mapper.map(source ,  sourceDto );
		return sourceDto;
	}
	
	private FacebookToken GetFacebookToken( ApplicationUser appUser )
	{
		FacebookToken accessToken = tokenDao.GetByApplicationUser( appUser );
		ValidateAccessToken( accessToken );
		return accessToken;
	}
	
	private Iterable< ApplicationUserSource > GetApplicationUserSources( ApplicationUser appUser )
	{
		Iterable< ApplicationUserSource >	appUserSources	=	appUserSourceDao.getApplicationUserSourcesByUser( appUser );
		ValidateApplicationUserSources( appUserSources );
		return appUserSources;
	}

	/**
	 * @return the tokenDao
	 */
	public IFacebookTokenDao getTokenDao() {
		return tokenDao;
	}


	/**
	 * @param tokenDao the tokenDao to set
	 */
	public void setTokenDao(IFacebookTokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}


	/**
	 * @return the appUserSourceDao
	 */
	public IApplicationUserSourceDao getAppUserSourceDao() {
		return appUserSourceDao;
	}


	/**
	 * @param appUserSourceDao the appUserSourceDao to set
	 */
	public void setAppUserSourceDao(IApplicationUserSourceDao appUserSourceDao) {
		this.appUserSourceDao = appUserSourceDao;
	}


	/**
	 * @return the downloadDao
	 */
	public IFacebookDownloadDao getDownloadDao() {
		return downloadDao;
	}


	/**
	 * @param downloadDao the downloadDao to set
	 */
	public void setDownloadDao(IFacebookDownloadDao downloadDao) {
		this.downloadDao = downloadDao;
	}


	/**
	 * @return the graphApiHandler
	 */
	public IFacebookGraphApiHandler getGraphApiHandler() {
		return graphApiHandler;
	}


	/**
	 * @param graphApiHandler the graphApiHandler to set
	 */
	public void setGraphApiHandler(IFacebookGraphApiHandler graphApiHandler) {
		this.graphApiHandler = graphApiHandler;
	}


	/**
	 * @return the saverService
	 */
	public IFacebookPostSaverLogic getSaverService() {
		return saverService;
	}


	/**
	 * @param saverService the saverService to set
	 */
	public void setSaverService(IFacebookPostSaverLogic saverService) {
		this.saverService = saverService;
	}


	/**
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}


	/**
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}


	/**
	 * @return the postDownloadDao
	 */
	public IFacebookPostDownloadDao getPostDownloadDao() {
		return postDownloadDao;
	}


	/**
	 * @param postDownloadDao the postDownloadDao to set
	 */
	public void setPostDownloadDao(IFacebookPostDownloadDao postDownloadDao) {
		this.postDownloadDao = postDownloadDao;
	}


	private	Set< FacebookPostDownload >	SaveFacebookPostsPerDownloadAndReturn( Set< WallSourceFacebookPostCreateDto >	sourcePostsDtos , FacebookDownload	currentDownload  )
	{
		Set< FacebookPostDownload >	postDownloads	=	new	HashSet< FacebookPostDownload >();
		_LOGGER.warn( "Save facebook posts for the current download" );
		for( WallSourceFacebookPostCreateDto sourcePostsDto : sourcePostsDtos  )
		{
			WallFacebookPostCreateDto	postDto	=	sourcePostsDto.getId().getPost();
			FacebookPost	post	=	new	FacebookPost();
			mapper.map(postDto, post);
			FacebookPostDownload	postDownload	=	new	FacebookPostDownload();
			FacebookPostDownloadId	id	=	new	FacebookPostDownloadId();
			id.setPost( post );
			id.setDownload(	currentDownload	);
			postDownload.setId( id );
			postDownloadDao.Save( postDownload );
			postDownloads.add(postDownload);
		}
		return	postDownloads;
	}
	
	private	Date	GetDownloadDate( ApplicationUser appUser )
	{
		FacebookDownload	download	=	downloadDao.GetLastAppUserDownload( appUser );
		Date	date	=	null;
		if( IsFirstTimeThatDownloads( download ) )
		{
			date	=	new	Date(0);
		}
		else
		{
			date	=	download.getDate();
		}
		return	date;
	}
	
	private	FacebookDownload	SaveCurrentDownloadAndReturn( ApplicationUser appUser )
	{
		long	rowCount	=	downloadDao.GetRowCount().longValue();
		String	id	=	new	String( String.valueOf( rowCount ) );
		Date	now	=	Calendar.getInstance().getTime();
		FacebookDownload	donwload	=	new	FacebookDownload();
		donwload.setId(id);
		donwload.setDate(now);
		donwload.setAppUser( appUser );
		downloadDao.Save(donwload);
		return	donwload;
	}
	
	private	void	AddPostsIntoSourcePostColletion( Iterable<WallFacebookPostCreateDto> downloadedPostsDto , Set< WallSourceFacebookPostCreateDto > sourcePostsDto , WallSourceCreateDto  sourceDto )
	{
		for( WallFacebookPostCreateDto postDto : downloadedPostsDto )
		{
			WallSourceFacebookPostCreateDto	sourcePostDto	=	new	WallSourceFacebookPostCreateDto();
			WallSourceFacebookPostIdDto	id	=	new	WallSourceFacebookPostIdDto();
			id.setPost(postDto);
			id.setSource(sourceDto);
			sourcePostDto.setId(id);
			sourcePostsDto.add( sourcePostDto );
		}
	}
	
	private	void	CheckSourceAndThrow( Source	source )
	{
		if( source == null )
		{
			_LOGGER.error( "Source is null." );
			throw	new	NullPointerException( "Source is null." );
		}
	}
	
	private	boolean	IsFirstTimeThatDownloads( FacebookDownload	download )
	{
		boolean	isFirstTime	=	false;
		if( download == null )
		{
			_LOGGER.error( "It is the first time that this user downloads." );
			isFirstTime	=	true;
		}
		return	isFirstTime;
	}
	
	/**
	 * Checks if the application user sources are null and throws a {@link NoFacebookSourcesException}.
	 * @param appUserSources
	 * @throws	NoFacebookSourcesException if sources are null.
	 */
	private	void	ValidateApplicationUserSources( Iterable< ApplicationUserSource >	appUserSources )
	{
		if( appUserSources == null)
		{
			_LOGGER.error( "Application user has not define sources" );
			throw	new	NoFacebookSourcesException( "Application user has not define sources" );
		}
	}
	
	/**
	 * Checks if access token is null and throws a {@link NoFacebookAccessTokenException} exception
	 * @param token
	 * @throws NoFacebookAccessTokenException if token is null.
	 */
	private	void	ValidateAccessToken( FacebookToken token )
	{
		if( token == null )
		{
			_LOGGER.error( "Access token is null. Download can not continue!" );
			throw	new	NoFacebookAccessTokenException( "Access token is null. Download can not continue!" );
		}
	}
	
	/**
	 * Checks if the application user is null.
	 * @param appUser
	 * @throws	IllegalArgumentException if application user is null.
	 */
	private	void	ValidateApplicationUser( ApplicationUser appUser )
	{
		if( appUser == null )
		{
			_LOGGER.error( "Application user can't be null." );
			throw	new	IllegalArgumentException( "Application user can't be null." );
		}
	}
}
