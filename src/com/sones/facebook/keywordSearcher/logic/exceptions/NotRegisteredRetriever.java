package com.sones.facebook.keywordSearcher.logic.exceptions;

/**
 * An exception that is thrown when there is not registered data retriever
 * for the specified facebook data type, into the keyword searcher.
 * @author sartios.sones@gmail.com.
 *
 */
public class NotRegisteredRetriever extends	RuntimeException
{
	public	NotRegisteredRetriever()
	{
		
	}
	
	public	NotRegisteredRetriever( String message )
	{
		super( message );
	}
}
