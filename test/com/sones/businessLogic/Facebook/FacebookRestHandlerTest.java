package com.sones.businessLogic.Facebook;

import java.util.List;

import org.junit.Test;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedList;

import static org.junit.Assert.*;


public class FacebookRestHandlerTest {

	/**
	 * Tests if the FacebookRestHandler can connect to the facebook and execute a REST call
	 * to graph api. Actually, we test if we can get a list from the wall feeds.
	 */
	@Test
	public void getWall_Test(){
		final String ID = "100000866964787";
		final String TOKEN = "access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y";
		FacebookRestHandler rest = new FacebookRestHandler();
		FeedList list=rest.getWall(ID, TOKEN);
		assertEquals(list.getFeed().getFrom().getId(), "100000866964787");
	}
	
	/**
	 * Tests if we can have a list with the feeds from all the friends
	 */
	@Test
	public void getFriendList_Test(){
		final String ID = "100000866964787";
		final String TOKEN = "access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y";
		FacebookRestHandler rest = new FacebookRestHandler();
		FacebookFriendList list = rest.getFriendList(ID, TOKEN);
		assertEquals(list.getFriendList().get(0).getId(), "502894293");
	}
	
	/**
	 * Tests if it gets the news feeds
	 */
	@Test
	public void getNewsFeeds_Test(){
		final String TOKEN = "access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y";
		FacebookRestHandler rest = new FacebookRestHandler();
		FeedList list = rest.getNewsFeeds(TOKEN);
		assertFalse(list.isEmpty());
	}
	
	/**
	 * Tests the size of the friend list
	 */
	@Test
	public void getFriendList_size_Test(){
		final String ID = "100000866964787";
		final String TOKEN = "access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y";
		FacebookRestHandler rest = new FacebookRestHandler();
		FacebookFriendList list = rest.getFriendList(ID, TOKEN);
		assertTrue(list.getFriendList().size()>20);
	}
	
	/**
	 * Tests if it returns the user's group list
	 */
	@Test
	public void getGroups_Test(){
		final String ID = "100000866964787";
		final String TOKEN = "access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y";
		FacebookRestHandler rest = new FacebookRestHandler();
		assertFalse(rest.getGroups(ID, TOKEN).getGroups().isEmpty());
	}
}
