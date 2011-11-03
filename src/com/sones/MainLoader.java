package com.sones;

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
import com.sones.businessLogic.UserManager.ApplicationUserID;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.Facebook.Feed.FacebookFeedDao;
import com.sones.dao.Facebook.Feed.Contents.FacebookContentDaoFactory;
import com.sones.dao.Facebook.Sources.UserFacebookSourcesDao;
import com.sones.dao.Facebook.Token.FacebookTokenDao;



public class MainLoader {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		IFacebookDownloader	_downloader	=	new	FacebookDownloader();
		( ( FacebookDownloader )_downloader).SetDateProvider( new FacebookZeroDateProvider() );
		
		IFacebookFeedRetriever retriever	=	new FacebookFeedRetriever( new FacebookGraphAPI( new FacebookRestHandler() ), new FacebookJsonParser( new FacebookJSONHandler() ) );
		( ( FacebookDownloader )_downloader).SetFeedRetriever( retriever );
		
		IFacebookFeedSaver	saver	=	new FacebookFeedSaver(new FacebookFeedDao(), new FacebookContentDaoFactory() );
		( ( FacebookDownloader )_downloader).SetFeedSaver( saver );
		
		IFacebookSourceProvider	sourceProvider	=	new	FacebookSourceProvider();
		( (FacebookSourceProvider) sourceProvider ).SetSourcesDao( new UserFacebookSourcesDao() );
		
		( ( FacebookDownloader )_downloader).SetSourceProvider( sourceProvider );
		( ( FacebookDownloader )_downloader).SetTokenProvider( new FacebookTokenProvider( new FacebookTokenDao() ) );

		IApplicationUserID	userID	=	new	ApplicationUserID( "1" );
		_downloader.SetApplicationUser(userID);
		IDownloadingTimer	_timer	=	new	FacebookDownloadingTimer();
		_timer.SetDownloader(_downloader);
		_timer.StartDownloading();
		
		
	}

}
