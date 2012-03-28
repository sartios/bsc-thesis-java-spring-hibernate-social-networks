package com.sones.facebook.keywordSearcher.logic.searcher;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.sones.facebook.keywordSearcher.logic.searcher.IKeywordSearcher;
import com.sones.facebook.keywordSearcher.logic.searcher.KeywordSearcher;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;
import com.sones.sharedDto.facebook.keywordSearcher.KeywordSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.KeywordSearchResultCreateDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.StatusMessageSearchDto;

public class KeywordSearcherTester 
{
	@Test
	public	void	testGetKeywordSearchResults()
	{
		Set<ISearchableFacebookFeed> posts = new HashSet<ISearchableFacebookFeed>();
		FacebookPostIdDto id1 = new FacebookPostIdDto();
		FacebookPostIdDto id2 = new FacebookPostIdDto();
		id1.setId("1");
		id2.setId("2");
		
		StatusMessageSearchDto post1 = new StatusMessageSearchDto();
		post1.setId(id2);
		post1.setMessage("Status message");
		
		StatusMessageSearchDto post2 = new StatusMessageSearchDto();
		post2.setId(id2);
		post2.setMessage("Status MESSAGE");
		
		posts.add(post1);
		posts.add(post2);
		
		Set<KeywordSearchDto> keywords = new HashSet<KeywordSearchDto>();
		KeywordSearchDto keyword = new KeywordSearchDto();
		keyword.setValue("message");
		keywords.add(keyword);
		
		IKeywordSearcher searcher = new KeywordSearcher();
		
		Set<KeywordSearchResultCreateDto> results = (Set<KeywordSearchResultCreateDto>) searcher.getKeywordSearchResults(posts, keywords);
		
		Assert.assertEquals( 2, results.size() );
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	testGetKeywordSearchResultsWithNullKeywords()
	{
		IKeywordSearcher searcher = new KeywordSearcher();
		searcher.getKeywordSearchResults( new HashSet<ISearchableFacebookFeed>(), null );
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	testGetKeywordSearchResultsWithNullPosts()
	{
		IKeywordSearcher searcher = new KeywordSearcher();
		searcher.getKeywordSearchResults( null, new HashSet<KeywordSearchDto>() );
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	testGetKeywordSearchResultsWithPostsAndKeywordsAsNull()
	{
		IKeywordSearcher searcher = new KeywordSearcher();
		searcher.getKeywordSearchResults( null, null );
	}
}
