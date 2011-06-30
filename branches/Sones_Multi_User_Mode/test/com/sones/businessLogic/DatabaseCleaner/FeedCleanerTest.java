package com.sones.businessLogic.DatabaseCleaner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testingUtilities.FeedReader;

import com.sones.businessLogic.Facebook.FacebookJsonHandler;
import com.sones.businessLogic.Facebook.FeedList;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.dao.FeedDao;
import com.sones.dao.KeywordDao;

import static org.junit.Assert.*;

public class FeedCleanerTest {

	private FeedCleaner cleaner;
	
	@Before
	public void setUp(){
		cleaner=new FeedCleaner();
	}
	
	@After
	public void tearDown(){
		cleaner = null;
	}
	
	@Test
	public void cleanDatabase_ThereAreFeedsWithNoAssocitionsToKeywords_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		FeedDao dao = new FeedDao();
		FeedList feeds = new FeedList();
		feeds.setFeeds(handler.getFeeds(new FeedReader().getFacebookWall()));
		dao.saveUserFeeds(feeds);
		
		FeedCleaner cleaner = new FeedCleaner(0, 2);
		cleaner.cleanDatabase();
		long feedsIntoDBAfterCleaning = dao.getNumberOfFeeds();
		assertEquals(feedsIntoDBAfterCleaning,0);
	}
	
	@Test
	public void cleanDatabase_ThereAreFeedsWithAssocitionsToKeywords_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		FeedDao dao = new FeedDao();
		FeedList feeds = new FeedList();
		feeds.setFeeds(handler.getFeeds(new FeedReader().getFacebookWall()));
		dao.saveUserFeeds(feeds);
		
		Keyword keyword = new Keyword("A Keyword");
		keyword.addID(feeds.getFeed(0));
		KeywordDao keyDao = new KeywordDao();
		keyDao.saveOrUpdate(keyword);
		
		FeedCleaner cleaner = new FeedCleaner(0, 2);
		cleaner.cleanDatabase();
		long feedsIntoDBAfterCleaning = dao.getNumberOfFeeds();
		keyDao.delete(keyword);
		cleaner.cleanDatabase();
		
		assertEquals(feedsIntoDBAfterCleaning,1);
	}
	
	@Test
	public void cleanDatabase_ThereAreNotFeedsIntoDatabase_Test(){
		
		FeedCleaner cleaner = new FeedCleaner(0, 2);
		cleaner.cleanDatabase();
		boolean isThrowing = false;
		try{
			cleaner.cleanDatabase();
		}
		catch (Exception e) {
			isThrowing=true;
		}
		assertFalse(isThrowing);
	}
}
