package com.sones.facebook.keywordSearcher.logic.retriever;

import java.util.Date;

import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.usermanager.model.ApplicationUser;

public interface ICheckinSearchDataManager	extends	IDataRetriever
{

	/**
	 * Finds the checkins that were not searched for this appUser keywords
	 * and were downloaded from appUser selected sources, and returns them.
	 * @param appUser
	 * @return Checkins ready for search.
	 */
	public	Iterable<ISearchableFacebookFeed>	getCheckinForSearch( ApplicationUser appUser , Date date );
	
}
