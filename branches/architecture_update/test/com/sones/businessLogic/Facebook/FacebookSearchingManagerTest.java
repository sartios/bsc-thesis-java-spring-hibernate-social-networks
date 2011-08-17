package com.sones.businessLogic.Facebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testingUtilities.FeedReader;

import static org.junit.Assert.*;

import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.Searcher.AbstractSearcher;
import com.sones.businessLogic.Searcher.KeywordSearcher;
import com.sones.businessLogic.Searcher.SearchingTimer;
import com.sones.dao.FeedDao;
import com.sones.dao.KeywordDao;
import com.sones.utilities.DateConverter;

public class FacebookSearchingManagerTest {

	private FacebookSearchingManager manager = null;
	private	FacebookSearchingList sources;
	
	@Before
	public void setUp(){
		manager = new FacebookSearchingManager("2011-06-13T10:11:24+0000");
		sources = new FacebookSearchingList();
		sources.addID("100000866964787");
		manager.setSources(sources);
	}
	
	@After
	public void tearDown(){
		manager = null;
	}
	
	@Test
	public void perfomSearch_settingSinceDate_Test(){	
		manager.performSearch();
		assertNotNull(manager.getSearcher().getFeeds());	
	}
	
	@Test
	/**
	 * We should make two searches and see if the second feeds are only new feeds.
	 */
	public void performSearch_gettingOnlyNewFeeds_Test(){
		manager.performSearch();
		manager.performSearch();
		manager.performSearch();
		int zero=manager.getSearcher().getFeeds().getSize();
		assertEquals(zero, 0);
	}
	
	@Test
	/**
	 * Try to search into null sources
	 */
	public void performSearch_NullSources_Test(){
		manager.setSources(null);
		manager.performSearch();
		int size=manager.getSearcher().getFeeds().getSize();
		assertTrue(size==0);
	}
	
	
	@Test
	/**
	 * We want to test by giving a user who exists in database
	 * and the date we set is in the future. So it must return
	 * the date of future.
	 */
	public void getDate_UserExistsAndTheDateIsInFuture_Test(){
		String futureDate = "2050-04-18T10:26:56+0000";
		String userID = "100000490788728";
		DateConverter converter = new DateConverter();
		manager.setSinceDateInUnixTimestamp(futureDate);
		assertEquals(manager.getDate(userID),converter.getUnixTimestamp(futureDate));
	}
		
	@Test
	/**
	 * We want to test that it returns the given stated date,
	 * when the user doesn't exist in database.
	 */
	public void getDate_UserDoesNotExist_Test(){
		String date = "2011-04-18T10:26:56+0000";
		manager.setSinceDateInUnixTimestamp(date);
		DateConverter converter = new DateConverter();
		String userID = "doesn't exist";
		assertEquals(manager.getDate(userID),converter.getUnixTimestamp(date));
	}
	
	@Test
	/**
	 * We want to test by giving a user who exists in database
	 * and the date we set is in the past. So it must return
	 * the date of most recent feed.
	 */
	public void getDate_UserExistsAndTheDateIsInPast_Test(){
		String pastDate = "2000-04-18T10:26:56+0000";
		String userID = "100000490788728";
		DateConverter converter = new DateConverter();
		manager.setSinceDateInUnixTimestamp(pastDate);
		assertNotSame(manager.getDate(userID),converter.getUnixTimestamp(pastDate));
	}
	
	@Test
	/**
	 * We want to test by giving a user who exists in database
	 * and the date we set is in the past. So it must return
	 * the date of most recent feed.
	 */
	public void getDate_UserIsNull_Test(){
		String pastDate = "2000-04-18T10:26:56+0000";
		String userID = null;
		manager.setSinceDateInUnixTimestamp(pastDate);
		assertNotSame(manager.getDate(userID),0);
	}
	
	@Test
	/**
	 * We want to test if it's also searching in the new comments.
	 * So we will give a json string in which the keyword that
	 * we are searching exists only in the new comments.
	 */
	public void performSearch_KeywordExistsOnlyInNewComments_WallFromFile_Test(){
		
		FacebookJsonHandler handler = new FacebookJsonHandler();
		FeedReader reader = (new FeedReader());
		FacebookSearchingManager_ForTestingSearchingPerform manager = new FacebookSearchingManager_ForTestingSearchingPerform("2000-04-18T10:26:56+0000");
		KeywordDao keyDao = new KeywordDao();

		saveInitialFeeds(handler,reader);
		FeedList feedsWithCommentTraffic=this.getFeedsWithCommentTraffic(handler,reader);
		manager.setFeeds(feedsWithCommentTraffic);
		saveKeyword(keyDao);
		manager.setSearcher(new KeywordSearcher());
		manager.performSearch();
		Keyword keyFromDB = getKeyword(keyDao);
		int numberOfFeeds = keyFromDB.getFeedsIds().size();
		keyDao.delete(keyFromDB);
		clearDatabaseFromFeeds(feedsWithCommentTraffic);
		assertEquals(numberOfFeeds, 1);
	}
	
	private void saveInitialFeeds(FacebookJsonHandler handler,FeedReader reader){
		FeedList initialFeeds = new FeedList();
		initialFeeds.setFeeds(handler.getFeeds(reader.getFacebookWall()));
		FeedDao feedDao = new FeedDao();
		feedDao.saveUserFeeds(initialFeeds);
	}
	
	private FeedList getFeedsWithCommentTraffic(FacebookJsonHandler handler,FeedReader reader){
		FeedList feedsWithCommentTraffic = new FeedList();
		feedsWithCommentTraffic.setFeeds(handler.getFeeds(reader.getWallWithMoreComments()));
		return feedsWithCommentTraffic;
	}
	
	private void saveKeyword(KeywordDao keyDao){
		Keyword keyword = new Keyword("commentXXXXXX");
		keyDao.saveOrUpdate(keyword);
	}
	
	private Keyword getKeyword(KeywordDao keyDao){
		return keyDao.getKeywordWithFeeds("commentXXXXXX");
	}
	
	private void clearDatabaseFromFeeds(FeedList feeds){
		FeedDao dao = new FeedDao();
		dao.deleteFeedList(feeds);
	}

}
