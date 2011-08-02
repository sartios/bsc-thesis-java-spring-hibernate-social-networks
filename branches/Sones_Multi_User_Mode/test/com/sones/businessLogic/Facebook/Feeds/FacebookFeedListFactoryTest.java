package com.sones.businessLogic.Facebook.Feeds;

import org.junit.Test;
import static org.junit.Assert.*;

import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;

public class FacebookFeedListFactoryTest {

	@Test
	public void getFeedList_GetFeedListForKeywordSearch_Test(){
		String userID="100000866964787";
		String token="access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y";
		String date="0";
		FacebookFeedList feeds_ = FacebookFeedListFactory.getInstance().getFeedList("keywordSearch", userID, token, date);
		Feed feed=feeds_.getNewerFeed();
		System.out.println(feed.getCreatedTime_());
		assertFalse(feed.getComments_().isEmpty());
	}
	
	@Test
	public void getFeedList_GetSimpleFeedList_Test(){
		String userID="100000866964787";
		String token="access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y";
		String date="0";
		FacebookFeedList feeds_ = FacebookFeedListFactory.getInstance().getFeedList("noSearch", userID, token, date);
		Feed feed=feeds_.getNewerFeed();
		System.out.println(feed.getCreatedTime_());
		assertTrue(feed.getComments_().isEmpty());
	}
}
