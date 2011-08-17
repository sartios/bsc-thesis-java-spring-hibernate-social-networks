package com.sones.businessLogic.Facebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FeedListTest {

	private FeedList feeds;
	private Feed feed;
	
	@Before
	public void setUp(){
		feed = new Feed("1", "2", 0);
		feeds=new FeedList();
		feeds.setFeed(feed);
	}
	
	@After
	public void tearDown(){
		feeds=null;
	}
	
	@Test
	public void getFeedByID_FeedExists_Test(){
		feeds.setFeed(new Feed("2", "2", 0));
		feeds.setFeed(new Feed("3", "2", 0));
		feeds.setFeed(new Feed("4", "5", 0));
		feeds.setFeed(new Feed("5", "2", 0));
		assertEquals(feeds.getFeedByID("4").getFromId_(), "5");
	}
}
