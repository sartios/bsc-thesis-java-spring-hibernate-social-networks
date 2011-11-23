package com.sones.dao.CapitalWordsSpotter.Facebook;

import java.util.List;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeed;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeeds;

/**
 * Provides methods for retrieving the Facebook feeds that were downloaded and contain words
 * which start with capital letter.
 * 
 * @author Savramis	Sartios
 *
 */
public interface IFacebookFeedCapitalWordSpotterDao
{
	/**
	 * Returns a list of feeds that can search into their information for words that start with capital letter.
	 * @param startDate
	 * @param endDate
	 * @return List of feeds.
	 */
	public	List<ICapitalWordsSearchableFeed>	GetFeedsBetweenDates(String startDate, String endDate);
	
	/**
	 * Returns a list of feeds that can search into their information for words that start with capital letter.
	 * @param startDate
	 * @param endDate
	 * @return List of feeds.
	 */
	public	ICapitalWordsSearchableFeeds	GetFeedListBetweenDates(String startDate, String endDate);
}
