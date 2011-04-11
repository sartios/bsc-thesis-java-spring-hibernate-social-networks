package com.sones.businessLogic;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class MultiSourceFeedsTest {

	@Test
	public void setFacebookFeeds_Test(){
		Set<Feed> feeds = new HashSet<Feed>();
		feeds.add(new Feed("1","2"));
		feeds.add(new Feed("2","3"));
		
		MultiSourceFeeds multiFeeds = new MultiSourceFeeds();
		multiFeeds.setFacebookFeeds(feeds);
		
		assertEquals(2, multiFeeds.getFacebookFeeds().size());
	}
}
