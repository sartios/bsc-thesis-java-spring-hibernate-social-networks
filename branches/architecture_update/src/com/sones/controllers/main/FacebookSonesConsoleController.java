package com.sones.controllers.main;

import com.sones.businessLogic.Facebook.FacebookDownloader;
import com.sones.businessLogic.Facebook.FacebookDownloadingTimer;
import com.sones.businessLogic.Facebook.FacebookFeedRetriever;
import com.sones.businessLogic.Facebook.FacebookFeedSaver;
import com.sones.businessLogic.Facebook.FacebookSourceProvider;
import com.sones.businessLogic.Facebook.FacebookTokenProvider;
import com.sones.businessLogic.Facebook.FacebookZeroDateProvider;
import com.sones.businessLogic.Facebook.IDownloadingTimer;
import com.sones.businessLogic.Facebook.IFacebookDownloader;
import com.sones.businessLogic.Facebook.IFacebookFeedRetriever;
import com.sones.businessLogic.Facebook.IFacebookFeedSaver;
import com.sones.businessLogic.Facebook.IFacebookSourceProvider;
import com.sones.businessLogic.Facebook.API.FacebookGraphAPI;
import com.sones.businessLogic.Facebook.API.FacebookJsonParser;
import com.sones.businessLogic.Facebook.JSON.FacebookJSONHandler;
import com.sones.businessLogic.Facebook.Rest.FacebookRestHandler;
import com.sones.businessLogic.Searcher.ISearcher;
import com.sones.businessLogic.Searcher.Facebook.FacebookSearcher;
import com.sones.businessLogic.Searcher.Locations.Facebook.FacebookLocationSearcher;
import com.sones.businessLogic.Searcher.Locations.Facebook.LocationSearcher;
import com.sones.businessLogic.UserManager.ApplicationUserID;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.Facebook.Feed.FacebookFeedDao;
import com.sones.dao.Facebook.Feed.Contents.FacebookContentDaoFactory;
import com.sones.dao.Facebook.Sources.UserFacebookSourcesDao;
import com.sones.dao.Facebook.Token.FacebookTokenDao;
import com.sones.ui.results.ResultsView;

import freemarker.log.Logger;

public class FacebookSonesConsoleController	implements	ISonesConsoleController 
{
	private	IDownloadingTimer	_timer;
	private	IFacebookDownloader	_downloader;
	private	ISearcher	_searcher;
	private	IApplicationUserID	_userID;
	
	public	FacebookSonesConsoleController()
	{
		_timer	=	new	FacebookDownloadingTimer();
		_downloader	=	null;
		_userID	=	new	ApplicationUserID("1");
	}

	@Override
	public void SetFacebookDownloadingInterval(String interval) 
	{
		_timer.SetTime(interval);
	}

	@Override
	public void StartDownload()
	{
		PrepareDownloader();
		_timer.SetDownloader(_downloader);
		_timer.StartDownloading();
	}

	@Override
	public void StopDownload()
	{
		_timer.StopDownloading();
	}
	
	private	void	PrepareDownloader()
	{
		if( null == _downloader)
		{
			_downloader	=	new	FacebookDownloader();
			( ( FacebookDownloader )_downloader).SetDateProvider( new FacebookZeroDateProvider() );
			
			IFacebookFeedRetriever retriever	=	new FacebookFeedRetriever( new FacebookGraphAPI( new FacebookRestHandler() ), new FacebookJsonParser( new FacebookJSONHandler() ) );
			( ( FacebookDownloader )_downloader).SetFeedRetriever( retriever );
			
			IFacebookFeedSaver	saver	=	new FacebookFeedSaver(new FacebookFeedDao(), new FacebookContentDaoFactory() );
			( ( FacebookDownloader )_downloader).SetFeedSaver( saver );
			
			IFacebookSourceProvider	sourceProvider	=	new	FacebookSourceProvider();
			( (FacebookSourceProvider) sourceProvider ).SetSourcesDao( new UserFacebookSourcesDao() );
			
			( ( FacebookDownloader )_downloader).SetSourceProvider( sourceProvider );
			( ( FacebookDownloader )_downloader).SetTokenProvider( new FacebookTokenProvider( new FacebookTokenDao() ) );
		}
	}

	@Override
	public void SetApplicationUser(IApplicationUserID userID)
	{
		PrepareDownloader();
		_downloader.SetApplicationUser(userID);
	}

	@Override
	public void AddKeyword(String keyword)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AddLocation(String location)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SearchForKeywords()
	{
		_searcher	=	new	FacebookSearcher();
		_searcher.SetApplicationUser(_userID);
		try
		{
			_searcher.Search();
		}
		catch(Exception e)
		{
			org.apache.log4j.Logger.getLogger(FacebookSonesConsoleController.class).error(e.getMessage());
		}
	}

	@Override
	public void SearchForLocations() 
	{
		_searcher	=	new	LocationSearcher();
		_searcher.SetApplicationUser(_userID);
		_searcher.Search();
	}

	@Override
	public void SetSearchingInterval()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ViewKeywordSearchingResults() 
	{
		ResultsView	view	=	new	ResultsView();
		view.show(true);
	}

}
