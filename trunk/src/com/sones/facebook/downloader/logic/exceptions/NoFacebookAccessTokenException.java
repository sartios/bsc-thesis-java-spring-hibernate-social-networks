package com.sones.facebook.downloader.logic.exceptions;

public class NoFacebookAccessTokenException	extends	RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8438047597081458047L;

	public	NoFacebookAccessTokenException()
	{
		super();
	}
	
	public	NoFacebookAccessTokenException( String message )
	{
		super( message );
	}
}
