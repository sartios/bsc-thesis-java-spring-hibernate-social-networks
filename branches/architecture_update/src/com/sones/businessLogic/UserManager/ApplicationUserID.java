package com.sones.businessLogic.UserManager;

public class ApplicationUserID implements	IApplicationUserID
{
	private	String	_userID;
	
	public	ApplicationUserID( final String	userID )
	{
		_userID	=	userID;
	}

	@Override
	public String GetValue() {
		return	_userID;
	}
	
}
