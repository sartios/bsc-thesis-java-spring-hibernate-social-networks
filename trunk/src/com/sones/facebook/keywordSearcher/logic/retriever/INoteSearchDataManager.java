package com.sones.facebook.keywordSearcher.logic.retriever;

import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.usermanager.model.ApplicationUser;

public interface INoteSearchDataManager	extends	IDataRetriever
{

	/**
	 * Finds the notes that were not searched for this appUser keywords
	 * and were downloaded from appUser selected sources, and returns them.
	 * @param appUser
	 * @return Notes ready for search.
	 */
	public	Iterable<ISearchableFacebookFeed>	getNoteForSearch( ApplicationUser appUser );
	
}
