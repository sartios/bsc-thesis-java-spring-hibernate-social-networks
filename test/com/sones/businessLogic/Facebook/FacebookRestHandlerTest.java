package com.sones.businessLogic.Facebook;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedList;

import static org.junit.Assert.*;


public class FacebookRestHandlerTest {
	
	private String ID_;
	private String TOKEN_;
	private FacebookRestHandler rest_;
	
	@Before
	public void setUp(){
		ID_ = new String("100000866964787");
		TOKEN_ = new String("access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y");
		rest_ = new FacebookRestHandler();
	}
	
	@After
	public void tearDown(){
		ID_ = null;
		TOKEN_ = null;
		rest_ = null;
	}
	/**
	 * Test if returns the feed when the feed exists
	 */
	@Test
	public void getFeed_FeedExists_Test(){
		final String ID = "100000866964787_180518531964358";
		Feed feed = rest_.getFeed(ID, TOKEN_);
		assertTrue(feed.getID().equals(ID));
	}
	
	/**
	 * Tests if the FacebookRestHandler can connect to the facebook and execute a REST call
	 * to graph api. Actually, we test if we can get a list from the wall feeds.
	 */
	@Test
	public void getWall_Test(){
		FeedList list=rest_.getWall(ID_, TOKEN_);
		assertEquals(list.getFeed().getFrom().getId(), ID_);
	}
	
	/**
	 * Tests if we can have a list with the feeds from all the friends
	 */
	@Test
	public void getFriendList_Test(){
		FacebookFriendList list = rest_.getFriendList(ID_, TOKEN_);
		assertEquals(list.getFriendList().get(0).getId(), "502894293");
	}
	
	/**
	 * Tests if it gets the news feeds
	 */
	@Test
	public void getNewsFeeds_Test(){
		FeedList list = rest_.getNewsFeeds(TOKEN_);
		assertFalse(list.isEmpty());
	}
	
	/**
	 * Tests the size of the friend list
	 */
	@Test
	public void getFriendList_size_Test(){
		FacebookFriendList list = rest_.getFriendList(ID_, TOKEN_);
		assertTrue(list.getFriendList().size()>20);
	}
	
	/**
	 * Tests if it returns the user's group list
	 */
	@Test
	public void getGroups_Test(){
		assertFalse(rest_.getGroups(ID_, TOKEN_).getGroups().isEmpty());
	}
	

}
