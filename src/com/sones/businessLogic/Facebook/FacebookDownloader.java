package com.sones.businessLogic.Facebook;


import java.util.Iterator;
import java.util.TimerTask;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookToken;
import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;
import com.sones.businessLogic.Facebook.DateManager.IFacebookDate;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeeds;
import com.sones.businessLogic.Facebook.FeedSources.IFacebookFeedSource;
import com.sones.businessLogic.Facebook.FeedSources.IFacebookSources;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.Facebook.Sources.IUserFacebookSourcesDao;

/**
 * @see {@link IFacebookDownloader}
 * @author Savramis Sartios
 *
 */
public class FacebookDownloader extends TimerTask implements IFacebookDownloader
{

	private	IFacebookSourceProvider	_sourceProvider;
	private	IFacebookFeedRetriever	_feedRetriever;
	private	IFacebookFeedSaver	_feedSaver;
	private	IFacebookDateProvider	_dateProvider;
	private	IFacebookTokenProvider	_tokenProvider;
	private	IApplicationUserID	_applicationUserID;
	
	private	Logger	_logger;
	private	PropertiesConfiguration _config;
	
	public	FacebookDownloader()
	{
		_logger	=	Logger.getLogger( FacebookDownloader.class );
		InitiateConfiguration();
	}
	
	@Override
	public boolean DownloadFacebookFeeds( IApplicationUserID applicationUserID )
	{
		boolean	isDownloadingCompleted	=	true;
		if( null == applicationUserID)
		{
			_logger.error( " Application user is null. Please give a valid user. " );
			isDownloadingCompleted	=	false;
		}
		
		IFacebookDate	facebookDate	=	GetDateProvider().GetDate( applicationUserID );
		IFacebookToken	facebookToken	=	GetTokenProvider().GetUserToken( applicationUserID );
		IFacebookSources	sources	=	GetSourceProvider().GetUserSources( applicationUserID );
		Iterator< IFacebookUserID >	sourceID	=	sources.GetIterator();
		
		for( ; sourceID.hasNext(); )
		{
			IFacebookUserID	userID	=	sourceID.next();
			_logger.warn( userID.getClass() );
			_logger.warn( facebookToken.getClass() );
			_logger.warn( facebookDate.getClass() );
			IFacebookFeeds	feeds	=	GetFeedRetriever().DownloadFeeds( userID, facebookToken, facebookDate );
			GetFeedSaver().SaveUserFacebookFeeds( applicationUserID, feeds );
		}
		return	isDownloadingCompleted;
	}

	public IFacebookSourceProvider GetSourceProvider() 
	{
		if( null == _sourceProvider )
		{
			_logger.error( _config.getString( "SourceProvider.IsNull" ) );
		}
		return _sourceProvider;
	}

	public void SetSourceProvider(IFacebookSourceProvider sourceProvider) 
	{
		if( null == sourceProvider )
		{
			_logger.error( _config.getString( "SourceProvider.IsNull" ) );
		}
		_sourceProvider = sourceProvider;
	}

	public IFacebookFeedRetriever GetFeedRetriever() 
	{
		if( null == _feedRetriever)
		{
			_logger.error( _config.getString( "FeedRetriever.IsNull" ) );
		}
		return _feedRetriever;
	}

	public void SetFeedRetriever(IFacebookFeedRetriever feedRetriever) 
	{
		if( null == feedRetriever)
		{
			_logger.error( _config.getString( "FeedRetriever.IsNull" ) );
		}
		_feedRetriever = feedRetriever;
	}

	public IFacebookFeedSaver GetFeedSaver()
	{
		if( null == _feedSaver)
		{
			_logger.error( _config.getString( "FeedSaver.IsNull" ) );
		}
		return _feedSaver;
	}

	public void SetFeedSaver(IFacebookFeedSaver feedSaver) 
	{
		if( null == feedSaver)
		{
			_logger.error( _config.getString( "FeedSaver.IsNull" ) );
		}
		_feedSaver = feedSaver;
	}

	public IFacebookDateProvider GetDateProvider() 
	{
		if( null == _dateProvider)
		{
		_logger.error( _config.getString( "DateProvider.IsNull" ) );
		}
		return _dateProvider;
	}

	public void SetDateProvider(IFacebookDateProvider dateProvider) 
	{
		if( null == dateProvider)
		{
		_logger.error( _config.getString( "DateProvider.IsNull" ) );
		}
		_dateProvider = dateProvider;
	}

	public IFacebookTokenProvider GetTokenProvider() 
	{
		if( null == _tokenProvider)
		{
			_logger.error( _config.getString( "TokenProvider.IsNull" ) );
		}
		return _tokenProvider;
	}

	public void SetTokenProvider(IFacebookTokenProvider tokenProvider)
	{
		if( null == tokenProvider)
		{
			_logger.error( _config.getString( "TokenProvider.IsNull" ) );
		}
		_tokenProvider = tokenProvider;
	}
	

	@Override
	public void run()
	{
		DownloadFacebookFeeds( _applicationUserID );
	}
	
	@Override
	public void SetApplicationUser(IApplicationUserID applicationUserID)
	{
		if( null != applicationUserID)
		{
			_applicationUserID	=	applicationUserID;
		}
	}
	
	private	void	InitiateConfiguration()
	{
		try 
		{
			_config	=	new	PropertiesConfiguration("config/Facebook/FacebookDownloader.properties");
		}
		catch (ConfigurationException e) 
		{
			_logger.error( e.getMessage() );
		}
	}


	
}
