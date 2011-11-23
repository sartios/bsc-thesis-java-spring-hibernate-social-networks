package com.sones.businessLogic.Facebook;

import org.apache.log4j.Logger;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookToken;
import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;
import com.sones.businessLogic.Facebook.API.FacebookGraphAPI;
import com.sones.businessLogic.Facebook.API.FacebookJsonParser;
import com.sones.businessLogic.Facebook.API.IFacebookAPI;
import com.sones.businessLogic.Facebook.API.IFacebookAPIResultParser;
import com.sones.businessLogic.Facebook.DateManager.IFacebookDate;
import com.sones.businessLogic.Facebook.FeedManager.FacebookFeedList;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeeds;

public class FacebookFeedRetriever implements	IFacebookFeedRetriever
{
	private	IFacebookAPI	_api;
	private	IFacebookAPIResultParser	_parser;
	private	Logger	_logger	=	Logger.getLogger( FacebookFeedRetriever.class );
	
	public	FacebookFeedRetriever()
	{
		_api	=	new	FacebookGraphAPI();
		_parser	=	new	FacebookJsonParser();
	}
	
	public	FacebookFeedRetriever( final IFacebookAPI api, final IFacebookAPIResultParser parser)
	{
		if( (null != api) && ( null != parser ))
		{
			_api	=	api;
			_parser	=	parser;
		}
	}

	@Override
	public IFacebookFeeds DownloadFeeds(IFacebookUserID facebookUserID,
			IFacebookToken facebookToken, IFacebookDate facebookDate) 
	{
		if( isFacebookUserIDValid(facebookUserID) && isFacebookTokenValid(facebookToken) && isFacebookDateValid(facebookDate) )
		{
			_logger.warn( " Downloading feeds from user: " + facebookUserID.GetValue() );
			return	_parser.ParseFacebookFeeds( _api.GetFacebookFeeds( facebookUserID, facebookToken, facebookDate ) );
		}
		return	new	FacebookFeedList();
	}
	
	private	boolean	isFacebookUserIDValid( IFacebookUserID facebookUserID )
	{
		if( null == facebookUserID)
		{
			return	false;
		}
		return	true;
	}
	
	private	boolean	isFacebookTokenValid( IFacebookToken facebookToken )
	{
		if( null == facebookToken)
		{
			return	false;
		}
		return	true;
	}
	
	private	boolean	isFacebookDateValid( IFacebookDate facebookDate )
	{
		if( null == facebookDate)
		{
			return	false;
		}
		return	true;
	}
	
	
}
