package com.sones.businessLogic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookGroupList;

import static org.junit.Assert.*;

public class FeedListTest {

	private FacebookFriend FROM;
	private Feed feed;
	private FeedList feeds;
	
	@Before
	public void setUp(){
		FROM = new FacebookFriend("Sartios", "123456789");
		feed = new Status("123456", FROM, "20", "Aloha");
		feeds = new FeedList();
	}
	
	@After
	public void tearDown(){
		FROM = null;
		feed = null;
		feeds = null;
	}
	
	/**
	 * Test add when feed doesn't exist
	 */
	@Test
	public void addFeed_FeedDoesntExist_Test(){
		assertTrue(feeds.addFeed(feed));
	}
	
	/**
	 * Test add when feed exists
	 */
	@Test
	public void addFeed_FeedExists_Test(){
		feeds.addFeed(feed);
		assertFalse(feeds.addFeed(feed));
	}
	
	/**
	 * Test add when feed is null
	 */
	@Test
	public void addFeed_FeedIsNull_Test(){
		assertFalse(feeds.addFeed(null));
	}
	
	/**
	 * Test get when the list is empty
	 */
	@Test
	public void getFeed_ListIsEmpty_Test(){
		assertTrue(feeds.getFeed()==null);
	}
	
	/**
	 * Test get when the list contains a feed. It must
	 * return the feed which contains
	 */
	@Test
	public void getFeed_ContainsFeed_Test(){
		feeds.addFeed(feed);
		assertEquals(feeds.getFeed(), feed);
	}
	
	/**
	 * Test get when the list contains many feeds. It must
	 * return the feeds
	 */
	@Test
	public void getFeed_ContainsFeeds_Test(){
		boolean correctBehaviour = false;
		Feed[] myFeeds = new Feed[3];
		myFeeds[0] = feed;
		myFeeds[1] = new Status("0",FROM,"1","2");
		myFeeds[2] = new Status("1",FROM,"1","2");
		for(int i=0;i<3;i++){
			feeds.addFeed(myFeeds[i]);
		}
		
		for(int i=0;i<3;i++){
			if(feeds.getFeed().equals(myFeeds[i])){
				correctBehaviour = true;
			}
		}
		if(feeds.getFeed()!=null){
			correctBehaviour = false;
		}
		assertTrue(correctBehaviour);
	}
	
	/**
	 * Test delete when the deleted item sits somewhere in the middle.
	 * In this position mustn't return null, but the item which was
	 * in front of it.
	 */
	@Test
	public void delete_FeedInTheMiddle_Test(){
		boolean correctBehaviour = false;
		Feed[] myFeeds = new Feed[3];
		myFeeds[0] = feed;
		myFeeds[1] = new Status("0",FROM,"1","2");
		myFeeds[2] = new Status("1",FROM,"1","2");
		for(int i=0;i<3;i++){
			feeds.addFeed(myFeeds[i]);
		}
		correctBehaviour = feeds.delete(myFeeds[1]);
		assertTrue(correctBehaviour);
		feeds.getFeed();
		assertTrue(feeds.getFeed().equals(myFeeds[2]));

	}
}
