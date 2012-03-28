package com.sones.facebook.keywordSearcher.logic.exceptions;

/**
 * An exception that is thrown when there is not keyword search for a specific user.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public class NoKeywordSearchException extends RuntimeException 
{
	public	NoKeywordSearchException()
	{
		super();
	}
	
	public	NoKeywordSearchException( String message )
	{
		super( message );
	}
}
