package com.sones.businessLogic.Searcher;

import org.junit.Test;

import com.sones.businessLogic.SocialNetworkUser;
import com.sones.businessLogic.Facebook.Source.FacebookOfflineUser;

import static org.junit.Assert.*;

public class KeywordSearcherTest {

	@Test
	public void search_FeedsContainKeywords_Test(){
		SocialNetworkUser user = new FacebookOfflineUser();
		KeywordSearcher searcher = new KeywordSearcher(user);
		searcher.search();
		assertTrue(searcher.getResults().getKeyword(0).getFeeds_().getSize()>0);
	}
	
	@Test
	public void search_FeedsDoesNotContainKeywords_Test(){
		SocialNetworkUser user = new FacebookOfflineUser();
		((FacebookOfflineUser)user).setKeywordsThatDoesNotExist();
		KeywordSearcher searcher = new KeywordSearcher(user);
		searcher.search();
		assertFalse(searcher.getResults().getKeyword(0).getFeeds_().getSize()>0);
	}
}
