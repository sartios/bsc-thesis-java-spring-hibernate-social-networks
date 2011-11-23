package com.sones.businessLogic.LocationSpoter;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeeds;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComments;

/**
 * Provides methods for retrieving feeds that contain words starting with capital letter.
 * 
 * @author Savramis	Sartios
 *
 */
public interface IFeedProvider 
{
	/**
	 * Returns a list of feeds that can search into their information for words that start with capital letter.
	 * @param startDate
	 * @param endDate
	 * @return List of feeds.
	 */
	public	ICapitalWordsSearchableFeeds	GetFeedsBetweenDates(String startDate, String endDate);

	/**
	 * Returns a list of comments that can search into their information for words that start with capital letter.
	 * @param startDate
	 * @param endDate
	 * @return List of comments.
	 */
	public	ICapitalWordsSearchableComments	GetCommentsBetweenDates(String startDate, String endDate);
}
