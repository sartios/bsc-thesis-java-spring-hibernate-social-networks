package com.sones.businessLogic.Facebook;


import org.apache.log4j.Logger;

import com.sones.businessLogic.Facebook.FeedSources.FacebookSources;
import com.sones.businessLogic.Facebook.FeedSources.IFacebookSources;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.Facebook.Sources.IUserFacebookSourcesDao;

public class FacebookSourceProvider implements	IFacebookSourceProvider
{

	private	IUserFacebookSourcesDao	_sourcesDao;
	private	Logger	_logger;
	
	public	FacebookSourceProvider()
	{
		_logger	=	Logger.getLogger( FacebookSourceProvider.class );
	}
	
	@Override
	public IFacebookSources GetUserSources(IApplicationUserID applicationUserID) 
	{
		IFacebookSources	sources	=	new	FacebookSources();
		if( null != applicationUserID )
		{
			sources	=	GetSourcesDao().GetApplicationUserSources( applicationUserID );
		}
		return	sources;
	}

	public void SetSourcesDao(IUserFacebookSourcesDao sourcesDao) 
	{
		if( null != sourcesDao)
		{
			_sourcesDao = sourcesDao;
		}
	}

	public IUserFacebookSourcesDao GetSourcesDao() 
	{
		if( null == _sourcesDao )
		{
			_logger.error( " Before you continue, must se Source Dao." );
		}
		return _sourcesDao;
	}

}
