package com.sones.buisinessLogic.Facebook.UserManager;

import org.apache.log4j.Logger;

/**
 * @see	{@link IFacebookUserID}
 * @author Savramis Sartios
 *
 */
public	class	FacebookUserID	implements	IFacebookUserID
{

	private	String	_userID;
	private	Logger	_logger;
	
	public	FacebookUserID()
	{
		_logger	=	Logger.getLogger( FacebookUserID.class );
	}
	
	public	FacebookUserID( final String userID)
	{
		SetUserID( userID );
	}

	public	void	SetUserID( String userID ) 
	{
		this._userID	=	userID;
	}

	@Override
	public	String	GetValue() 
	{
		if( null == _userID )
		{
			_logger.error( " Facebook user's ID is null" );
		}
		return	_userID;
	}

	
}
