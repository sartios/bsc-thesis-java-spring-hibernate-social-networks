package com.sones.businessLogic;


import static org.junit.Assert.*;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FeedFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test if the getComments method, extracts correct the 2 comments
	 */
	@Test
	public void getCommentsTest(){
		List<Comment> comments = FeedFactory.getInstance().getComments(getComments());
		assertTrue(comments.get(0).getMessage().equals("Hello my brother"));
		assertTrue(comments.get(1).getMessage().equals("Hello my big brother"));
	}
	
	/**
	 * @return DynaBean object which contains a feed and comments for this feed
	 */
	private DynaBean getComments(){
		String jsonString = new String(""+
			"{" +
			   "\"data\": ["+
			            "{" +
			               "\"id\": \"100000866964787_180768675273630\","+
			               "\"from\": {"+
			                  "\"name\": \"Stelios Stark Savramis\","+
			                  "\"id\": \"1310204556\""+
			               "},"+
			               "\"to\": {"+
			                  "\"data\": ["+
			                     "{"+
			                        "\"name\": \"Sartios Savramis\","+
			                        "\"id\": \"100000866964787\""+
			                     "}"+
			                  "]"+
			               "},"+
			               "\"message\": \"http://www.youtube.com/watch?v=Mr_uHJPUlO8\","+
			               "\"picture\": \"http://external.ak.fbcdn.net/safe_image.php?d=3e99785daa5626abdf952ce62f4c10ac&w=130&h=130&url=http%3A%2F%2Fi.ytimg.com%2Fvi%2FMr_uHJPUlO8%2F3.jpg\","+
			               "\"link\": \"http://www.youtube.com/watch?v=Mr_uHJPUlO8\","+
			               "\"source\": \"http://www.youtube.com/v/Mr_uHJPUlO8&autoplay=1\","+
			               "\"name\": \"Red Hot Chili Peppers - Give It Away (Video)\","+
			               "\"caption\": \"www.youtube.com\","+
			               "\"description\": \"\u00a9 2006 WMG Give It Away (Video)\","+
			               "\"icon\": \"http://static.ak.fbcdn.net/rsrc.php/zj/r/v2OnaTyTQZE.gif\","+
			               "\"actions\": ["+
			                  "{"+
			                     "\"name\": \"Comment\","+
			                     "\"link\": \"http://www.facebook.com/100000866964787/posts/180768675273630\""+
			                  "},"+
			                  "{"+
			                     "\"name\": \"Like\","+
			                     "\"link\": \"http://www.facebook.com/100000866964787/posts/180768675273630\""+
			                  "}"+
			               "],"+
			               "\"type\": \"video\","+
			               "\"created_time\": \"2010-12-05T19:18:42+0000\","+
			               "\"updated_time\": \"2010-12-05T23:48:19+0000\","+
			               "\"likes\": 2,"+
			               "\"comments\": {"+
			                  "\"data\": ["+
			                     "{"+
			                        "\"id\": \"100000866964787_180768675273630_2237898\","+
			                        "\"from\": {"+
			                           "\"name\": \"Stelios Stark Savramis\","+
			                           "\"id\": \"1310204556\""+
			                        "},"+
			                        "\"message\": \"Hello my brother\","+
			                        "\"created_time\": \"2010-12-05T19:19:13+0000 \""+
			                     "},"+
			                     "{"+
			                        "\"id\": \"100000866964787_180768675273630_2237898\","+
			                        "\"from\": {"+
			                           "\"name\": \"Stelios Stark Savramis\","+
			                           "\"id\": \"1310204556\""+
			                        "},"+
			                        "\"message\": \"Hello my big brother\","+
			                        "\"created_time\": \"2010-12-05T19:19:13+0000 \""+
			                     "}"+
			                   "]"+           
			                 "}"+
			                "}"+
			                "]"+
			                "}");
		
		JSONObject object = JSONObject.fromObject(jsonString);
		JSONArray jsonArray = object.getJSONArray("data");
		return (DynaBean)JSONObject.toBean(jsonArray.getJSONObject(0));
	}

}
