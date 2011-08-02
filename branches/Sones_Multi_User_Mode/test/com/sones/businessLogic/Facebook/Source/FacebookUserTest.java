package com.sones.businessLogic.Facebook.Source;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;
import com.sones.businessLogic.KeywordManager.KeywordList;

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
		FacebookFeedList feeds = new  FacebookFeedList();
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
		FacebookFeedList feeds = new  FacebookFeedList();
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
	
	@Test
	public void setKeywordList_KeywordListIsNotNull_Test(){
		KeywordList keywords = new KeywordList();
		keywords.setApplicationUserID_(applicationUserID);
		user.setKeywords_(keywords);
		assertEquals(user.getKeywords_().getSize(), 0);
	}
	
	@Test
	public void setKeywordList_KeywordListIsNull_Test(){
		user.setKeywords_(null);
		assertEquals(user.getKeywords_().getSize(), 0);
	}
	
	@Test
	public void setKeywordList_KeywordsBelongToSomebodyElse_Test(){
		KeywordList keywords = new KeywordList();
		keywords.setApplicationUserID_("xxxxx");
		user.setKeywords_(keywords);
		assertEquals(user.getKeywords_().getSize(), 0);
	}
	
	@Test
	public void setKeywordList_KeywordsBelongToNobody_Test(){
		KeywordList keywords = new KeywordList();
		user.setKeywords_(keywords);
		assertEquals(user.getKeywords_().getSize(), 0);
	}
	
	@Test
	public void setSources_ValidSources_Test(){
		user.setSources_(new FacebookSourceList());
		assertNotNull(user.getSources_());
	}
	
	@Test
	public void setSources_NullSources_Test(){
		user.setSources_(null);
		assertNull(user.getSources_());
	}
	
	@Test
	public void getKeywords_UserHasCreateKeywords_Test(){
		FacebookUser facebookUser=new FacebookUser();
		facebookUser.setApplicationUserID_("0001");
		assertNotNull(facebookUser.getKeywords("database"));
	}
	
	@Test
	public void getKeywords_UserHasNotCreateKeywords_Test(){
		FacebookUser facebookUser=new FacebookUser();
		facebookUser.setApplicationUserID_("0021");
		assertNull(facebookUser.getKeywords("database"));
	}
}
