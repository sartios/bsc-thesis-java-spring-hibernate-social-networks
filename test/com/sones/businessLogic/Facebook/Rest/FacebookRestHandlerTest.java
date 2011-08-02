package com.sones.businessLogic.Facebook.Rest;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Facebook.FacebookToken;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;
import com.sones.businessLogic.Facebook.Rest.FacebookRestHandler;
import com.sones.businessLogic.Facebook.Source.FacebookFriend;


import static org.junit.Assert.*;


public class FacebookRestHandlerTest {
	
	private String ID_;
	private FacebookToken token_;
	private FacebookRestHandler rest_;
	private String notFriendID_="100002413412547";
	
	@Before
	public void setUp(){
		ID_ = new String("100000866964787");
		token_ = new FacebookToken();
		token_.setToken(new String("access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y"));
		rest_ = new FacebookRestHandler();
	}
	
	@After
	public void tearDown(){
		ID_ = null;
		token_ = null;
		rest_ = null;
	}
	
	@Test
	/**
	 * We test this method to see if returns the friend list of a default user.
	 * At this moment, we cannot test for different users if returns different results.
	 */
	public void getFriendList_UsingDefaultAccessToken_Test(){
		int size = rest_.getFriendList(this.token_.getToken()).getSize();
		assertTrue(size>0);
	}
	
	@Test
	/**
	 * We test this method to see if returns the groups of the user who is a friend
	 * to the user to whom belongs the access token.
	 */
	public void getGroups_ExistingUserID_Test(){
		int size = rest_.getGroupList(this.ID_,this.token_.getToken()).getSize();
		assertTrue(size>0);
	}
	
	@Test
	/**
	 * Test the method if returns groups of user who isn't friend with access token owner
	 */
	public void getGroups_FromUserWhoIsNotFriend_Test(){
		int size = rest_.getGroupList(notFriendID_,this.token_.getToken()).getSize();
		assertTrue(size==0);
	}
	
	@Test
	/**
	 * Test if returns groups from null user
	 */
	public void getGroups_NullAsUser_Test(){
		int size = rest_.getGroupList(null,this.token_.getToken()).getSize();
		assertTrue(size==0);
	}
	
	@Test
	/**
	 * We test to get friend's feeds
	 */
	public void getFeeds_FromFriend_Test(){
		int size = rest_.getFeeds(ID_, token_.getToken()).getSize();
		assertTrue(size>0);
	}
	
	@Test
	/**
	 * We test to get feeds from user who isn't friend
	 */
	public void getFeeds_UserIsNotAFriend_Test(){
		int size = (rest_.getFeeds(notFriendID_, token_.getToken())).getSize();
		assertTrue(size==0);
	}
	
	@Test
	/**
	 * Null as user
	 */
	public void getFeeds_NullAsUser_Test(){
		int size = (rest_.getFeeds(null, token_.getToken())).getSize();
		assertTrue(size==0);
	}
	
	@Test
	/**
	 * Null as token
	 */
	public void getFeeds_NullAsToken_Test(){
		int size = (rest_.getFeeds(ID_, null)).getSize();
		assertTrue(size==0);
	}
	
	@Test
	/**
	 * Get feed which exists and it's from friend
	 */
	public void getFeed_FeedExistsFromFriend_Test(){
		String friendsFeed = "100000866964787_210918882280342";
		Feed feed = rest_.getFeed(friendsFeed,this.token_.getToken());
		assertEquals(feed.getId_(), friendsFeed);
	}
	
	@Test
	/**
	 * Get feed from a user who isn't friend
	 */
	public void getFeed_UserIsNotFriend_Test(){
		String friendsFeed = "1310204556_2124804082246";
		Feed feed = rest_.getFeed(friendsFeed,this.token_.getToken());
		assertEquals(feed.getId_(), "");
	}
	
	@Test
	/**
	 * Get null as feed
	 */
	public void getFeed_NullAsFeed_Test(){
		Feed feed = rest_.getFeed(null,this.token_.getToken());
		assertEquals(feed.getId_(), "");
	}
	
	@Test
	/**
	 * Get facebook user who is a friend
	 */
	public void getFacebookUser_WhoIsFriend_Test(){
		FacebookFriend friend = rest_.getFacebookUser(ID_,this.token_.getToken());
		assertEquals(friend.getId(), ID_);
	}
	
	@Test
	/**
	 * Get facebook user who isn't a friend
	 */
	public void getFacebookUser_WhoIsNotFriend_Test(){
		FacebookFriend friend = rest_.getFacebookUser(notFriendID_,this.token_.getToken());
		assertEquals(friend.getId(), notFriendID_);
	}
	
	@Test
	/**
	 * Null as user
	 */
	public void getFacebookUser_NullAsUser_Test(){
		FacebookFriend friend = rest_.getFacebookUser(null,this.token_.getToken());
		assertEquals(friend.getId(), "");
	}
	
	@Test
	/**
	 * Since date is in the past
	 */
	public void getFeedsSinceDate_DateBelongsToPast_Test(){
		String pastDate = "1276585715"; //15 Jun 2010 07:08:35 GMT
		FacebookFeedList feeds = rest_.getFeedsSinceDate(ID_, token_.getToken(),pastDate);
		int size = feeds.getSize();
		assertTrue(size>0);
	}
	
	@Test
	/**
	 * Since date is in the future
	 */
	public void getFeedsSinceDate_DateBelongsToFuture_Test(){
		String futureDate = "1339744115"; //15 Jun 2012 07:08:35 GMT
		FacebookFeedList feeds = rest_.getFeedsSinceDate(ID_, token_.getToken(),futureDate);
		int size = feeds.getSize();
		assertTrue(size==0);
	}
	
	@Test
	/**
	 * Since date is a negative long number
	 */
	public void getFeedsSinceDate_NegativeLongNumber_Test(){
		String pastDate = "-1339744115";
		FacebookFeedList feeds = rest_.getFeedsSinceDate(ID_, token_.getToken(),pastDate);
		int size = feeds.getSize();
		assertTrue(size==0);
	}
	
	@Test
	/**
	 * User ID isn't from friend
	 */
	public void getFeedsSinceDate_UserIsNotAFriend_Test(){
		String pastDate = "1276585715"; //15 Jun 2010 07:08:35 GMT
		FacebookFeedList feeds = rest_.getFeedsSinceDate(notFriendID_, token_.getToken(),pastDate);
		int size = feeds.getSize();
		assertTrue(size==0);
	}
	
	@Test
	/**
	 * User's ID isn't a valid facebook id
	 */
	public void getFeedsSinceDate_UserIsNotValid_Test(){
		String pastDate = "1276585715"; //15 Jun 2010 07:08:35 GMT
		String notValidID = "notValidID";
		FacebookFeedList feeds = rest_.getFeedsSinceDate(notValidID, token_.getToken(),pastDate);
		int size = feeds.getSize();
		assertTrue(size==0);
	}
	
	@Test
	/**
	 * User's ID is null
	 */
	public void getFeedsSinceDate_NullUserID_Test(){
		String pastDate = "1276585715"; //15 Jun 2010 07:08:35 GMT
		String nullID = null;
		FacebookFeedList feeds = rest_.getFeedsSinceDate(nullID, token_.getToken(),pastDate);
		int size = feeds.getSize();
		assertTrue(size==0);
	}
	
	@Test
	/**
	 * Not valid token
	 */
	public void getFeedsSinceDate_NotValidToken_Test(){
		String pastDate = "1276585715"; //15 Jun 2010 07:08:35 GMT
		String notValidToken="notValidToken";
		FacebookFeedList feeds = rest_.getFeedsSinceDate(ID_,notValidToken,pastDate);
		int size = feeds.getSize();
		assertTrue(size==0);
	}
	
	@Test
	/**
	 * Null token
	 */
	public void getFeedsSinceDate_NullToken_Test(){
		String pastDate = "1276585715"; //15 Jun 2010 07:08:35 GMT
		String nullToken=null;
		FacebookFeedList feeds = rest_.getFeedsSinceDate(ID_,nullToken,pastDate);
		int size = feeds.getSize();
		assertTrue(size==0);
	}
}
