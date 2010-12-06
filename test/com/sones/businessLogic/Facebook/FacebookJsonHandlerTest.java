package com.sones.businessLogic.Facebook;

import java.util.List;
import java.util.Vector;

import org.junit.Test;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedList;

import static org.junit.Assert.*;


public class FacebookJsonHandlerTest {

	/**
	 * Test if the JsonHandler returns the correct friends list
	 */
	@Test
	public void getFriends_Test(){
		String jsonString = "{" +
					"\"data\" : [" +
						"{" +
							"\"name\" : \"Savramis Sartios\"," +
							"\"id\" : \"123456789\"" +
						"}" +
					"]" +
				"}";
		
		FacebookJsonHandler jsonHandler = new FacebookJsonHandler();
		FacebookFriendList list = jsonHandler.getFriends(jsonString);
	
		assertEquals(list.getFriendList().get(0).getName(), "Savramis Sartios");
		assertEquals(list.getFriendList().get(0).getId(), "123456789");
	}
	
/*	*//**
	 * Test if the JsonHandler returns the correct feed list
	 *//*
	@Test
	public void getFeeds_Test(){
		FacebookJsonHandler jsonHandler = new FacebookJsonHandler();
		List<Feed> list = jsonHandler.getFeeds(this.getFeedsInJSON());
		assertEquals(list.get(0).getID(), "123456789123");
		assertEquals(list.get(0).getCreatedTime(), "2010-11-30T09:51:00+0000");
		assertEquals(list.get(0).getFrom().getName(), "Sartios Savramis");
		assertEquals(list.get(0).getFrom().getId(), "123456789");
	}*/
	
	/**
	 * Test if the JsonHandler can create the correct objects with the FeedFactory
	 */
	@Test
	public void getWallFeeds_Test(){
		FacebookJsonHandler jsonHandler = new FacebookJsonHandler();
		FeedList feeds = jsonHandler.getWallFeeds(this.getWallInJSON());
		assertEquals(feeds.getFeed().getClass().toString(),"class com.sones.businessLogic.Status");
		assertEquals(feeds.getFeed().getClass().toString(),"class com.sones.businessLogic.Note");
	}
	/**
	 * @return feed in a JSON format
	 */
	private String getFeedsInJSON(){
		String jsonString = "{" +
					"\"data\" : [" +
						"{" +
							"\"id\" : \"123456789123\"," +
							"\"created_time\" : \"2010-11-30T09:51:00+0000\"," +
							"\"from\" : {" +
								"\"id\" : \"123456789\"," +
								"\"name\" : \"Sartios Savramis\"" +
							"}" +
						"}" +
					"]" +
				"}";
		
		return jsonString;
	}
	
	/**
	 * @return wall feeds in JSON format
	 */
	private String getWallInJSON(){
		String jsonString = "{" +
			"\"data\" : [" +
				"{" +
					"\"id\" : \"123456789\"," +
					"\"created_time\" : \"2010-11-30T09:51:00+0000\"," +
					"\"from\" : {" +
						"\"id\" : \"123456789\"," +
						"\"name\" : \"Sartios Savramis\"" +
					"}," +
					"\"type\" : \"status\"," +
					"\"message\" : \"hello\""+
				"}," +
				"{" +
					"\"id\" : \"12345678910\"," +
					"\"created_time\" : \"2010-11-30T09:51:00+0000\"," +
					"\"from\" : {" +
						"\"id\" : \"123456789\"," +
						"\"name\" : \"Sartios Savramis\"" +
					"}," +
					"\"type\" : \"note\"," +
					"\"message\" : \"hello\"," +
					"\"subject\" : \"hey\""+
				"}" +
			"]" +
		"}";
		return jsonString;	
	}
}
