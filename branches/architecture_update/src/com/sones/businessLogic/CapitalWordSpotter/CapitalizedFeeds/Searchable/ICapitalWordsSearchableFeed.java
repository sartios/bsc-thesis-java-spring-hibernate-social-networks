package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable;

import com.sones.businessLogic.CapitalWordSpotter.CapitalWords.ICapitalWords;

/**
 * Provides methods for retrieving the capital words that exist into a feed.
 * 
 * @author Savramis Sartios
 *
 */
public interface ICapitalWordsSearchableFeed 
{
	/**
	 * Finds the capital words into a feed.
	 * @return A collection of capital words
	 */
	public	ICapitalWords	GetCapitalWords();
	
	/**
	 * @return ID of the feed.
	 */
	public	String	GetFeedID();
	
	public	void	SetFeedID( String id );
	
}
