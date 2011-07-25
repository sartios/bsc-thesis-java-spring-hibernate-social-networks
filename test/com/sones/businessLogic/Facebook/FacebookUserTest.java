package com.sones.businessLogic.Facebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FacebookUserTest {
	
	private FacebookUser user;
	private String facebookUserID = new String("101510");
	private String applicationUserID = new String("11111");

	@Before
	public void setUp(){
		user = new FacebookUser(facebookUserID,applicationUserID);
	}
	
	@After
	public void tearDown(){
		user=null;
	}
	
	@Test
	public void setUserID_IDNotNull_ReturnsUserID_Test(){
		user.setFacebookUserID_(facebookUserID);
		assertEquals(user.getFacebookUserID_(), facebookUserID);
	}
	
	@Test
	public void setUserID_IDNull_ReturnsEmptyUserID_Test(){
		user.setFacebookUserID_(null);
		assertEquals(user.getFacebookUserID_(), "");
	}
	
	@Test
	public void setFeedList_FeedsNotNull_ReturnsUserFeeds_Test(){
		FeedList feeds = new  FeedList();
		feeds.setUserID(facebookUserID);
		user.setFacebookUserID_(facebookUserID);
		user.setFeeds_(feeds);
		assertEquals(user.getFeeds_().getUserID(), facebookUserID);
	}
	
	@Test
	public void setUserFeeds_FeedsNull_ReturnsFeedsWhichBelongToUser_Test(){
		user.setFeeds_(null);
		assertEquals(user.getFeeds_().getUserID(),facebookUserID);
	}
	
	@Test
	public void setUserFeeds_FeedsFromDiffirentUser_ReturnsEmptyFeeds_Test(){
		FeedList feeds = new  FeedList();
		feeds.setUserID("123456");
		feeds.setFeed(new Feed());
		user.setFeeds_(feeds);
		int feedSize = feeds.getSize();
		assertFalse(feedSize==user.getFeeds_().getSize());
	}
	
	@Test
	public void setApplicationUserID_IDNotNull_ReturnsApplicationUserID_Test(){
		user.setApplicationUserID_(applicationUserID);
		assertEquals(user.getApplicationUserID_(), applicationUserID);
	}
	
	@Test
	public void setApplicationUserID_IDNull_ReturnsEmptyApplicationUserID_Test(){
		user.setApplicationUserID_(null);
		assertEquals(user.getApplicationUserID_(), "");
	}
}
