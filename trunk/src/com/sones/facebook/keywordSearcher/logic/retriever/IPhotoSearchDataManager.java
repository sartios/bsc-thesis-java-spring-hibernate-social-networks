package com.sones.facebook.keywordSearcher.logic.retriever;

import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.usermanager.model.ApplicationUser;

public interface IPhotoSearchDataManager	extends	IDataRetriever
{

	/**
	 * Finds the photos that were not searched for this appUser keywords
	 * and were downloaded from appUser selected sources, and returns them.
	 * @param appUser
	 * @return Photos ready for search.
	 */
	public	Iterable<ISearchableFacebookFeed>	getPhotoForKeywordSearch( ApplicationUser appUser );
	
}
