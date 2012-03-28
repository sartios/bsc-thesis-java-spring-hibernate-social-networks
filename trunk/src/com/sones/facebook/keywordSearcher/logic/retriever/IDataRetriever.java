package com.sones.facebook.keywordSearcher.logic.retriever;

import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.userManager.model.ApplicationUser;

public interface IDataRetriever 
{
	public	Iterable<ISearchableFacebookFeed>	getDataToBeSearched( ApplicationUser appUser );
}
