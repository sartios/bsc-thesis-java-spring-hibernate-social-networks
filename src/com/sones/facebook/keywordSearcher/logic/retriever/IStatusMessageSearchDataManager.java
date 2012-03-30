package com.sones.facebook.keywordSearcher.logic.retriever;

import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.usermanager.model.ApplicationUser;

/**
 * Provides methods for retrieving status message posts for various searches.
 * @author sartios.sones@gmail.com.
 *
 */
public interface IStatusMessageSearchDataManager	extends	IDataRetriever
{

	/**
	 * Finds the status messages that were not searched for this appUser keywords
	 * and were downloaded from appUser selected sources, and returns them.
	 * @param appUser
	 * @return Status messages ready for search.
	 */
	public	Iterable<ISearchableFacebookFeed>	getStatusMessagesForSearch( ApplicationUser appUser );

}
