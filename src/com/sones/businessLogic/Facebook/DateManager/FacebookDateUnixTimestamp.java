package com.sones.businessLogic.Facebook.DateManager;

public class FacebookDateUnixTimestamp	implements	IFacebookDate
{

	private	String	_date;
	
	public	FacebookDateUnixTimestamp( final String date )
	{
		_date	=	date;
	}
	
	@Override
	public String GetDateValue() {
		return	_date;
	}
	
}
