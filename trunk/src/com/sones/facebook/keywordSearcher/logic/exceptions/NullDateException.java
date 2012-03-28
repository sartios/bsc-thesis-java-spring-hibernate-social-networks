package com.sones.facebook.keywordSearcher.logic.exceptions;

public class NullDateException extends RuntimeException 
{
	public	NullDateException()
	{
		super();
	}
	
	public	NullDateException( String message )
	{
		super( message );
	}
}
