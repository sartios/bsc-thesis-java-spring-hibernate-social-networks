package com.sones.businessLogic.Facebook.API;


import org.apache.log4j.Logger;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookToken;
import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;
import com.sones.businessLogic.Facebook.DateManager.IFacebookDate;
import com.sones.businessLogic.Facebook.Rest.IFacebookRestHandler;


/**
 * Facebook Graph API is a RESTful web services with which you can
 * access facebook user's graph and informations.
 * 
 * @author Sartios
 *
 */
public class FacebookGraphAPI implements IFacebookAPI{

	private	IFacebookRestHandler	_restHandler;
	private	Logger	_logger	=	Logger.getLogger( FacebookGraphAPI.class );
	
	public	FacebookGraphAPI()
	{
		RestHandlerIsNull();
	}
	
	public	FacebookGraphAPI( final	IFacebookRestHandler	restHandler )
	{
		if( null == restHandler )
		{
			RestHandlerIsNull();
		}
		_restHandler	=	restHandler;
	}
	
	@Override
	public String GetFacebookFeeds( final	IFacebookUserID facebookUserID, final	IFacebookToken facebookToken, final	IFacebookDate facebookDate) {
		if( IsFacebookIDIsNotNull(facebookUserID) && IsFacebookTokenNotNull(facebookToken) && IsFacebookDateNotNull(facebookDate) )
		{
			_logger.warn( " Downloading feeds " );
			return	_restHandler.GetFeedsSinceDate(facebookUserID,facebookToken,facebookDate);
		}
		return new String("");
	}
	
	private	boolean	IsFacebookIDIsNotNull( final	IFacebookUserID facebookUserID )
	{
		if( null != facebookUserID )
		{
			return true;
		}
		return false;
	}
	
	private	boolean	IsFacebookTokenNotNull( final	IFacebookToken facebookToken )
	{
		if( null != facebookToken )
		{
			return true;
		}
		return false;
	}
	
	private	boolean	IsFacebookDateNotNull( final	IFacebookDate facebookDate )
	{
		if( null != facebookDate )
		{
			return true;
		}
		return false;
	}
	
	private	void	RestHandlerIsNull()
	{
		String	exceptionMessage	=	"Rest handler can't be null";
		if( null == _restHandler )
		{
			_logger.error( " Rest handler is null. Initiate rest handler! " );
			throw	new	NullPointerException(exceptionMessage);
		}
	}

}
