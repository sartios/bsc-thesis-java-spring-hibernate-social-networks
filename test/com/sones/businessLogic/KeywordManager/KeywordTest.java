package com.sones.businessLogic.KeywordManager;


import static org.junit.Assert.*;

import java.awt.RenderingHints.Key;

import org.junit.Test;

import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;

public class KeywordTest {

	@Test
	public void setKeywordID_KeywordIDIsValid_Test(){
		String keywordID = new String("1");
		Keyword keyword = new Keyword();
		keyword.setKeywordID_(keywordID);
		assertEquals(keyword.getKeywordID_(), keywordID);
	}
	
	@Test
	public void setKeywordID_KeywordIDIsNull_Test(){
		Keyword keyword = new Keyword();
		keyword.setKeywordID_(null);
		assertTrue(keyword.getKeywordID_().isEmpty());
	}
	
	@Test
	public void setKeywordValue_KeywordValueIsValid_Test(){
		String keywordValue = new String("value");
		Keyword keyword = new Keyword();
		keyword.setValue_(keywordValue);
		assertEquals(keyword.getValue_(), keywordValue);
	}
	
	@Test
	public void setKeywordValue_KeywordValueIsNull_Test(){
		Keyword keyword = new Keyword();
		keyword.setValue_(null);
		assertTrue(keyword.getValue_().isEmpty());
	}
	
	@Test
	public void equals_KeywordsAreSame_Test(){
		String keywordID = new String("id");
		String keywordValue = new String("Value");
		Keyword keyword = new Keyword();
		keyword.setValue_(keywordValue);
		keyword.setKeywordID_(keywordID);
		assertTrue(keyword.equals(keyword));
	}
	
	@Test
	public void equals_KeywordsAreNotSame_Test(){
		String keywordID = new String("id");
		String keywordValue = new String("Value");
		Keyword keyword = new Keyword();
		keyword.setValue_(keywordValue);
		keyword.setKeywordID_(keywordID);
		
		String keywordID2 = new String("new_id");
		String keywordValue2 = new String("new_value");
		Keyword keyword2 = new Keyword();
		keyword2.setValue_(keywordValue2);
		keyword2.setKeywordID_(keywordID2);
		
		assertFalse(keyword.equals(keyword2));
	}

	@Test
	public void equals_KeywordsAreSameButHaveDiffirentCase_Test(){
		String keywordID = new String("id");
		String keywordValue = new String("Value");
		Keyword keyword = new Keyword();
		keyword.setValue_(keywordValue);
		keyword.setKeywordID_(keywordID);
		
		String keywordID2 = new String("ID");
		String keywordValue2 = new String("VALUE");
		Keyword keyword2 = new Keyword();
		keyword2.setValue_(keywordValue2);
		keyword2.setKeywordID_(keywordID2);
		
		assertFalse(keyword.equals(keyword2));
	}
	
	@Test
	public void equals_KeywordsHasSameValueButDiffirentID_Test(){
		String keywordID = new String("id");
		String keywordValue = new String("Value");
		Keyword keyword = new Keyword();
		keyword.setValue_(keywordValue);
		keyword.setKeywordID_(keywordID);
		
		String keywordID2 = new String("ID");
		String keywordValue2 = new String("value");
		Keyword keyword2 = new Keyword();
		keyword2.setValue_(keywordValue2);
		keyword2.setKeywordID_(keywordID2);
		
		assertFalse(keyword.equals(keyword2));
	}
	
	@Test
	public void equals_KeywordsHasSameIDButDiffirentValue_Test(){
		String keywordID = new String("id");
		String keywordValue = new String("VALUE");
		Keyword keyword = new Keyword();
		keyword.setValue_(keywordValue);
		keyword.setKeywordID_(keywordID);
		
		String keywordID2 = new String("id");
		String keywordValue2 = new String("value");
		Keyword keyword2 = new Keyword();
		keyword2.setValue_(keywordValue2);
		keyword2.setKeywordID_(keywordID2);
		
		assertFalse(keyword.equals(keyword2));
	}
	
	@Test
	public void equals_OneKeywordHasNotID_Test(){
		String keywordID = new String("id");
		String keywordValue = new String("value");
		Keyword keyword = new Keyword();
		keyword.setValue_(keywordValue);
		keyword.setKeywordID_(keywordID);
		
		String keywordValue2 = new String("value");
		Keyword keyword2 = new Keyword();
		keyword2.setValue_(keywordValue2);
		
		assertFalse(keyword.equals(keyword2));
	}
	
	@Test
	public void equals_OneKeywordHasNotValue_Test(){
		String keywordID = new String("id");
		Keyword keyword = new Keyword();
		keyword.setKeywordID_(keywordID);
		
		String keywordID2 = new String("id");
		String keywordValue2 = new String("value");
		Keyword keyword2 = new Keyword();
		keyword2.setValue_(keywordValue2);
		keyword2.setKeywordID_(keywordID2);

		assertFalse(keyword.equals(keyword2));
	}
	
	@Test
	public void setFeeds_FeedListIsValid_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		Feed feed = new Feed("feed_id", "user_id");
		feeds.setFeed(feed);
		Keyword keyword = new Keyword();
		keyword.setFeeds_(feeds);
		assertTrue(keyword.getFeeds_().getSize()==1);
	}
	
	@Test
	public void setFeeds_FeedListIsNull_Test(){
		Keyword keyword = new Keyword();
		keyword.setFeeds_(null);
		assertTrue(keyword.getFeeds_().getSize()==0);
	}
	
	@Test
	public void addFeed_FeedIsValid_Test(){
		Feed feed = new Feed("feed_id", "user_id");
		Keyword keyword = new Keyword();
		assertTrue(keyword.addFeed(feed));
	}
	
	@Test
	public void addFeed_FeedIsNull_Test(){
		Feed feed = new Feed("feed_id", "user_id");
		Keyword keyword = new Keyword();
		assertFalse(keyword.addFeed(null));
	}
}
