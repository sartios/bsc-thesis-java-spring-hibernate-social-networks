package com.sones.businessLogic.Facebook;

import java.util.Timer;
import java.util.TimerTask;

import com.sones.businessLogic.Facebook.API.FacebookGraphAPI;
import com.sones.businessLogic.Facebook.API.FacebookJsonParser;
import com.sones.businessLogic.Facebook.JSON.FacebookJSONHandler;
import com.sones.businessLogic.Facebook.Rest.FacebookRestHandler;
import com.sones.dao.Facebook.Feed.FacebookFeedDao;
import com.sones.dao.Facebook.Feed.Contents.FacebookContentDaoFactory;
import com.sones.dao.Facebook.Sources.UserFacebookSourcesDao;
import com.sones.dao.Facebook.Token.FacebookTokenDao;

public class FacebookDownloadingTimer	implements	IDownloadingTimer
{
	private	IFacebookDownloader	_downloader;
	private	Timer	_timer;
	private	long	_time;

	
	public FacebookDownloadingTimer() 
	{
		_timer	=	new	Timer();
	}

	@Override
	public void SetDownloader(IFacebookDownloader downloader) 
	{
		if( IsNotNull( downloader ) )
		{
			_downloader	=	downloader;
		}
	}

	@Override
	public void SetTime( final String time ) 
	{
		if( IsNotNull( time ) )
		{
			_time	=	new	Long( time );
		}
	}

	@Override
	public void StartDownloading()
	{
		_timer.schedule((TimerTask) _downloader, 100,100);
	}

	@Override
	public void StopDownloading() 
	{
		_timer.cancel();		
	}
	
	private	boolean	IsNotNull( final Object object )
	{
		if( null == object )
		{
			return	false;
		}
		return	true;
	}
}
