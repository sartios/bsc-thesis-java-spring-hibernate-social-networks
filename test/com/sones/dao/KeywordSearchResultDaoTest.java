package com.sones.dao;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Facebook.Source.FacebookUser;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.KeywordManager.Keyword;

public class KeywordSearchResultDaoTest {
	
	private KeywordSearchResultDao dao;
	
	@Before
	public void setUp(){
		dao = new KeywordSearchResultDao();
	}
	
	@After
	public void tearDown(){
		dao = null;
	}
	
	@Test
	public void saveKeywordSearchingResults_ValidKeyword_Test(){
		Keyword keyword = getKeywordWithFeeds();
		boolean result = dao.saveKeywordSearchingResults(keyword);
		dao.deleteKeywordSearchingResults(keyword);
		assertTrue(result);
	}
	
	@Test
	public void saveKeywordSearchingResults_KeywordHaveEmptyFeedList_Test(){
		Keyword keyword = getKeywordWithFeeds();
		keyword.getFeeds_().clearFeeds();
		assertFalse(dao.saveKeywordSearchingResults(keyword));
	}
	
	/*@Test
	public void saveKeywordSearchingResults_KeywordHaveEmptyUser_Test(){
		Keyword keyword = getKeywordWithFeeds();
		keyword.getFeeds_().clearUserID();
		assertFalse(dao.saveKeywordSearchingResults(keyword));
	}*/
	
	public void deleteKeywordSearchingResults_ValidKeyword_Test(){
		Keyword keyword = getKeywordWithFeeds();
		dao.saveKeywordSearchingResults(keyword);
		boolean result = dao.deleteKeywordSearchingResults(keyword);
		assertTrue(result);
	}
	
	@Test
	public void deleteKeywordSearchingResults_KeywordHaveEmptyFeedList_Test(){
		Keyword keyword = getKeywordWithFeeds();
		keyword.getFeeds_().clearFeeds();
		assertFalse(dao.deleteKeywordSearchingResults(keyword));
	}
	
	/*@Test
	public void deleteKeywordSearchingResults_KeywordHaveEmptyUser_Test(){
		Keyword keyword = getKeywordWithFeeds();
		keyword.getFeeds_().clearUserID();
		assertFalse(dao.deleteKeywordSearchingResults(keyword));
	}*/
	
	private Keyword getKeywordWithFeeds(){
		Keyword keyword = this.getKeyword();
		Feed feed = this.getValidFeed();
		FacebookUser user = this.getValidFacebookUser();
		
		saveFacebookUser(user);
		saveFeed(feed);
		saveKeyword(keyword);
		
		keyword.addFeed(feed);
		keyword.getFeeds_().setUserID(user.getFacebookUserID_());

		return keyword;
	}
	
	private void deleteKeywordWithFeeds(){
		Feed feed = this.getValidFeed();
		FacebookUser user = this.getValidFacebookUser();
		Keyword keyword = this.getKeyword();
	}
	
	private Keyword getKeyword(){
		Keyword keyword = new Keyword();
		keyword.setKeywordID_("keyword_id");
		keyword.setValue_("keyword_value");
		return keyword;
	}
	
	private boolean saveKeyword(Keyword keyword){
		KeywordDao keywordDao = new KeywordDao();
		return	keywordDao.saveOrUpdate(keyword);
	}
	
	private Feed getValidFeed(){
		Feed feed = new Feed();
		feed.setId_("feed_id");
		return feed;
	}
	
	private void saveFeed(Feed feed){
		FeedDao feedDao = new FeedDao();
		feedDao.saveOrUpdate(feed);
	}
	
	private FacebookUser getValidFacebookUser(){
		return new FacebookUser("0001", "0001");
	}
	
	private boolean saveFacebookUser(FacebookUser facebookUser){
		FacebookUserDao facebookUserDao = new FacebookUserDao();
		return 	facebookUserDao.saveOrUpdate(facebookUser);
	}
}
