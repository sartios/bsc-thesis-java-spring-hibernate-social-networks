package com.sones.facebook.keywordSearcher.logic.keyword.retriever;

import com.sones.facebook.keywordSearcher.logic.exceptions.NoKeywordsException;
import com.sones.sharedDto.facebook.keywordSearcher.KeywordSearchDto;
import com.sones.userManager.model.ApplicationUser;

public interface IApplicationUserKeywordRetriever 
{
	/**
	 * Returns the Keywords of the application user.
	 * @param appUser
	 * @return the Keywords of the application user.
	 * @throws IllegalArgumentException	if application user is null.
	 * @throws NoKeywordsException if the application user has not keyword.
	 */
	public	Iterable<KeywordSearchDto>	getApplicationUserKeywords( ApplicationUser appUser );
}
