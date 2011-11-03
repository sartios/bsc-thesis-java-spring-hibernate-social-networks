package com.sones.buisinessLogic.Facebook.UserManager;

public abstract class AbstractUserToken 
{
	private	String	_userID;

	public void SetUserID( final String userID ) 
	{
		if( null != userID )
		{
			_userID = userID;
		}
	}

	public String GetUserID() 
	{
		return _userID;
	}
	
	
}
