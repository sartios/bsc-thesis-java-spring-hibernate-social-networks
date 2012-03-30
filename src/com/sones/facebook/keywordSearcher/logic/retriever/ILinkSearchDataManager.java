package com.sones.facebook.keywordSearcher.logic.retriever;

import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.usermanager.model.ApplicationUser;

public interface ILinkSearchDataManager	extends	IDataRetriever
{

	/**
	 * Finds the links that were not searched for this appUser keywords
	 * and were downloaded from appUser selected sources, and returns them.
	 * @param appUser
	 * @return Links ready for search.
	 */
	public	Iterable<ISearchableFacebookFeed>	getLinkForSearch( ApplicationUser appUser );

	
}
