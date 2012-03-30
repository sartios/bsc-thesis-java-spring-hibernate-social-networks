package com.sones.facebook.downloader.logic.exceptions;

public class NoFacebookSourcesException extends	RuntimeException
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9135090912901313323L;

	public	NoFacebookSourcesException()
	{
		super();
	}
	
	public	NoFacebookSourcesException( String message )
	{
		super( message );
	}
}
