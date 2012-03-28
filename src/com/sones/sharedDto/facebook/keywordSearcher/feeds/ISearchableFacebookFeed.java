package com.sones.sharedDto.facebook.keywordSearcher.feeds;

/**
 * Provides methods for searching into a facebook feed for keywords.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public interface ISearchableFacebookFeed 
{
	/**
	 * Returns true if the feed contains somewhere in its content the value.
	 * @param value
	 * @return true if the feed contains somewhere in its content the value.
	 */
	public	boolean	contains( String value );
}
