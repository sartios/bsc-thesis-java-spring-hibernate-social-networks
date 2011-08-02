package com.sones.businessLogic.Facebook;

import org.junit.Test;

import com.sones.businessLogic.Facebook.Rest.FacebookRestHandler;
import com.sones.businessLogic.Facebook.Rest.IFacebookRestHandler;
import com.sones.businessLogic.Facebook.Source.FacebookFriend;
import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;


import static org.junit.Assert.*;

public class FacebookFriendTest {

	@Test
	public void FacebookFriendsAreEqual_Test(){
		FacebookFriend friend1 = new FacebookFriend("sartios", "12345678");
		FacebookFriend friend2 = new FacebookFriend("sartios", "12345678");
		
		assertEquals(friend1, friend2);
	}
	
	@Test
	public void setID_IDIsNotNull_GetTheSameID_Test(){
		String friendID = new String("100000866964787");
		FacebookFriend friend = new  FacebookFriend();
		friend.setId(friendID);
		assertTrue(friend.getId().equals(friendID));
	}
	
	@Test
	public void setID_IDIsNull_GetEmptyID_Test(){
		FacebookFriend friend = new  FacebookFriend();
		friend.setId(null);
		assertTrue(friend.getId().equals(""));
	}
	
	@Test
	public void setName_NameIsNotNull_GetTheSameName_Test(){
		FacebookFriend friend = new  FacebookFriend();
		String friendName = new String("Sartios");
		friend.setName(friendName);
		assertTrue(friend.getName().equals(friendName));
	}
	
	@Test
	public void setName_NameIsNull_GetEmptyName_Test(){
		FacebookFriend friend = new  FacebookFriend();
		friend.setName(null);
		assertTrue(friend.getName().equals(""));
	}
	
	@Test
	/**
	 * Test if the user can get his feeds. The token we enter, is valid, so the user
	 * must return his feeds.
	 */
	public void getFeeds_TokenIsValid_NotNullFeedList_Test(){
		String friendID = new String("100000866964787");
		String token = new String("access_token=164830413559108|c8b8a030bfb0eee2c891c311.1-100000866964787|Y9vZRi9qMlmiq3lvzYf3GX8xsv4");
		FacebookFriend friend = new  FacebookFriend();
		friend.setFacebookRestHandler("completeGraph");
		friend.setId(friendID);
		FacebookFeedList feeds = friend.getFeeds(token);
		assertNotNull(feeds);
	}
	
	
	@Test
	/**
	 * Test if the user can get his feeds. The token we enter, is valid, so the user
	 * must return his feeds.
	 */
	public void getFeeds_TokenIsValid_NotEmptyFeedList_Test(){
		String friendID = new String("100000866964787");
		String token = new String("access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y");
		FacebookFriend friend = new  FacebookFriend();
		friend.setFacebookRestHandler("completeGraph");
		friend.setId(friendID);
		friend.setDateOfNewestFeed("0");
		FacebookFeedList feeds = friend.getFeeds(token);
		assertEquals(feeds.getSize(),25);
	}
	
	@Test
	/**
	 * Test if the user can get his feeds. The token we enter, isn't valid, so the feed list
	 * must be empty
	 */
	public void getFeeds_TokenIsNotValid_FeedListIsNull_Test(){
		String friendID = new String("100000866964787");
		String token = new String("access_token=|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y");
		FacebookFriend friend = new  FacebookFriend();
		friend.setFacebookRestHandler("completeGraph");
		friend.setId(friendID);
		friend.setDateOfNewestFeed("0");
		FacebookFeedList feeds = friend.getFeeds(token);
		assertTrue(feeds.isEmpty());
	}
	
	@Test
	/**
	 * After downloading of the feeds, the date of most recent feed change
	 */
	public void getFeeds_ThereAreNewFeeds_DateShouldChange_Test(){
		String friendID = new String("100000866964787");
		String token = new String("access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y");
		FacebookFriend friend = new  FacebookFriend();
		friend.setFacebookRestHandler("completeGraph");
		friend.setId(friendID);
		friend.setDateOfNewestFeed("0");
		FacebookFeedList feeds = friend.getFeeds(token);
		assertFalse(friend.getDateOfNewestFeed().equals("0"));
	}
	
}
