package com.sones.businessLogic.LocationSpoter;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeed;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeeds;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComments;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.ILocatedFeed;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.ILocatedFeeds;

/**
 * Provides methods for retrieving feeds with the locations they refer to.
 * 
 * @author Savramis	Sartios
 *
 */
public interface IFeedLocationSpotter 
{
	/**
	 * Returns the feeds with the locations that they refer to
	 * @param feeds that contain capital letters
	 * @return Feeds related with locations.
	 */
	public	ILocatedFeeds	GetFeedsWithLocations( final ICapitalWordsSearchableFeeds feeds );
	
	/**
	 * Returns the feed with the locations that it refers to
	 * @param feed that contains capital letters
	 * @return Feeds related with locations.
	 */
	public	ILocatedFeed	GetFeedWithLocations( final ICapitalWordsSearchableFeed feed );
	
	/**
	 * Returns feeds with the locations that exist into their comments or their content.
	 * @param startDate	- the date after which we retrieve feeds.
	 * @param endDate	-	the date until which we retrieve feeds.
	 * @return	Feeds related with locations.
	 */
	public	ILocatedFeeds	GetFeedsWithLocations(String startDate, String endDate);
	
	/**
	 * Returns the feeds with the locations that they exist into their comments
	 * @param comments to be searched for locations
	 * @return	Feeds related with locations.
	 */
	public	ILocatedFeeds	GetFeedWithLocations( final ICapitalWordsSearchableComments comments );
}
