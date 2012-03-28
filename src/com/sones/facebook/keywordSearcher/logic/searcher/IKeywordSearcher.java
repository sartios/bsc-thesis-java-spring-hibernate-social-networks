package com.sones.facebook.keywordSearcher.logic.searcher;

import com.sones.sharedDto.facebook.keywordSearcher.KeywordSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.KeywordSearchResultCreateDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;

/**
 * Provides methods to search for keywords into facebook posts.
 * @author sartios.sones@gmail.com.
 *
 */
public interface IKeywordSearcher 
{
	/**
	 * Searches for the keywords into posts and returns the results.
	 * @param posts
	 * @param keywords
	 * @return keyword search results
	 */
	public	Iterable<KeywordSearchResultCreateDto>	getKeywordSearchResults(Iterable<ISearchableFacebookFeed> posts, Iterable<KeywordSearchDto> keywords);
}
