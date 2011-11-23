package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents;

import java.util.List;

/**
 * Provides methods for accessing the collection of capital words
 * and the collection of the capital words.
 * 
 * @author Savramis Sartios
 *
 */
public abstract class AbstractCapitalizedFacebookContent 
{
	/**
	 * A list of the words
	 */
	private	List< String >	_words;
	
	/**
	 * Adds a list of words into the collection of words
	 * @param words
	 */
	private	void	AddWords( final List< String > words )
	{
		if( 0 < words.size())
		{
			for( int index = 0; index < words.size(); index++)
			{
				_words.add( words.get(index) );
			}
		}
	}
}
