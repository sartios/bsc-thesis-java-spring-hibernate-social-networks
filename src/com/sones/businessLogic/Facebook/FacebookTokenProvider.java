package com.sones.businessLogic.Facebook;

import org.apache.log4j.Logger;

import com.sones.buisinessLogic.Facebook.UserManager.FacebookToken;
import com.sones.buisinessLogic.Facebook.UserManager.IFacebookToken;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.Facebook.Token.IFacebookTokenDao;

public class FacebookTokenProvider implements	IFacebookTokenProvider
{
	
	private	IFacebookTokenDao	_tokenDao;
	private	Logger	_logger;
	
	public	FacebookTokenProvider()
	{
		_logger	=	Logger.getLogger( FacebookTokenProvider.class );
	}
	
	public	FacebookTokenProvider( final IFacebookTokenDao tokenDao )
	{
		_logger	=	Logger.getLogger( FacebookTokenProvider.class );
		SetTokenDao( tokenDao );
	}
	
	public IFacebookTokenDao GetTokenDao()
	{
		if( null == _tokenDao )
		{
			_logger.error( " Set Token Dao before you continue. Token dao is null." );
		}
		return _tokenDao;
	}

	public void SetTokenDao( final IFacebookTokenDao tokenDao )
	{
		_tokenDao = tokenDao;
	}

	@Override
	public IFacebookToken GetUserToken( final IApplicationUserID applicationUserID )
	{
		IFacebookToken	token	=	new	FacebookToken();
		if( null != applicationUserID )
		{
			token	=	GetTokenDao().GetToken( applicationUserID );
		}
		return	token;
	}

}
