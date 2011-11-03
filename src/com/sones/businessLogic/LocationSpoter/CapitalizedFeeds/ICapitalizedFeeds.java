package com.sones.businessLogic.LocationSpoter.CapitalizedFeeds;

/**
 * A collection of {@link ICapitalizedWords}
 * @author Sones
 *
 */
public interface ICapitalizedFeeds 
{
	/**
	 * Get a {@link ICapitalizedWords}
	 * @param index
	 * @return {@link ICapitalizedWords}
	 */
	public	ICapitalizedWords	GetCapitalizedWordsForFeed( final int index );
	
	/**
	 * Add a {@link ICapitalizedWords} for a specific feed
	 * @param feedWords
	 */
	public	void	AddWords( final ICapitalizedWords feedWords );
	
}
