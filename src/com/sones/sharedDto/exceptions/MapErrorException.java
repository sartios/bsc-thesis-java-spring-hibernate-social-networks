package com.sones.sharedDto.exceptions;

public class MapErrorException	extends	RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3188078704332279489L;

	public	MapErrorException()
	{
		super();
	}
	
	public	MapErrorException( String message )
	{
		super( message );
	}
}
