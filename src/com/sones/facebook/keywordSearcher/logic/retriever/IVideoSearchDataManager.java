package com.sones.facebook.keywordSearcher.logic.retriever;

import java.util.Date;

import com.sones.facebook.model.feed.Video;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.usermanager.model.ApplicationUser;

/**
 * Provides methods for retrieving {@link Video}s ready for search.
 * @author sartios.sones@gmail.com.
 *
 */
public interface IVideoSearchDataManager	extends	IDataRetriever
{

	/**
	 * Finds the videos that were not searched for this appUser keywords
	 * and were downloaded from appUser selected sources, and returns them.
	 * @param appUser
	 * @return Videos ready for search.
	 */
	public	Iterable<ISearchableFacebookFeed>	getVideoForKeywordSearch( ApplicationUser appUser , Date date );
	
}
