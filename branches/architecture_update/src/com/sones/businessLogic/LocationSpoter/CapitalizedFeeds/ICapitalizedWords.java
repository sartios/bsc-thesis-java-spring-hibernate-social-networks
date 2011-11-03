package com.sones.businessLogic.LocationSpoter.CapitalizedFeeds;

/**
 * The collection of capitalized words
 * that are contained in a specific feed
 * @author Sones
 *
 */
public interface ICapitalizedWords 
{
	/**
	 * Set the feed
	 * @param feedID
	 */
	public	void	SetFeed( final String feedID );
	
	/**
	 * Add a capitalized word into the collection
	 * @param word
	 */
	public	void	AddCapitalizedWord( final String word );
}
