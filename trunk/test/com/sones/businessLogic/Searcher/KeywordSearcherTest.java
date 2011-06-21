package com.sones.businessLogic.Searcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import testingUtilities.FeedReader;

import com.sones.businessLogic.Facebook.FacebookJsonHandler;
import com.sones.businessLogic.Facebook.FeedList;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.KeywordList;
import com.sones.dao.KeywordDao;

/**
 * List of tests
 * -------------
 * 
 * 1. There are keywords in database
 * 2. There are not keywords in database
 * 3. There are feeds to search
 * 4. There are not feeds to search
 * 5. The keywords exist into feeds
 * 6. All the keywords exist into feed
 * 7. The keywords don't exist into feeds
 * 8. The keywords exist only in the comments of feeds
 * 
 * @author Sartios
 *
 */


public class KeywordSearcherTest {
	
	private FeedList feeds_=null;
	
	@Before
	public void setUp(){
		init();
	}
	
	
	@After
	public void tearDown(){
	}
	
	
	@Test
	/**
	 * We make sure that there are some keywords into the database
	 * and we try to search. The point to this test is to see that
	 * searching process is working if there are keywords into database.
	 */
	public void search_ThereAreKeywordsIntoDatabase_Test(){
		initiateDatabaseKeywords();
		boolean isThrowing = false;
		try{
			KeywordSearcher searcher_=new KeywordSearcher();
			searcher_.search(feeds_);
		}
		catch (Exception e) {
			isThrowing = true;
			e.printStackTrace();
		}
		deleteDatabaseKeywords();
		assertFalse(isThrowing);
	}
	
	@Test
	/**
	 * We make sure that there are not keywords into the database
	 * and we try to search. The point to this test is to see that
	 * searching process is working if there are not keywords into database.
	 */
	public void search_ThereAreNotKeywordsIntoDatabase_Test(){
		boolean isThrowing = false;
		KeywordDao keyDao = new KeywordDao();
		KeywordList keywords = keyDao.getKeywordListWithFeeds();
		keyDao.deleteKeywordList(keywords);
		try{
			KeywordSearcher searcher_=new KeywordSearcher();
			searcher_.search(feeds_);
		}
		catch (Exception e) {
			isThrowing = true;
		}
		keyDao.saveKeywordList(keywords);
		assertFalse(isThrowing);
	}
	
	
	@Test
	/**
	 * We test what is going on when the feed list is empty
	 */
	public void search_FeedListIsEmpty_Test(){
		FeedList feeds = new FeedList();
		boolean isThrowing = false;
		try{
			KeywordSearcher searcher_=new KeywordSearcher();
			searcher_.search(feeds);
			isThrowing = false;
		}
		catch (Exception e) {
			isThrowing = true;
		}
		assertFalse(isThrowing);
	}
	
	@Test
	/**
	 * We have some feeds that contain the keywords. So
	 * the searcher must spot the feeds and we can retrieve them
	 */
	public void search_keywordExistIntoFeeds_Test(){
		Keyword keyword = new Keyword("1.3.1.2");
		KeywordDao dao1 = new KeywordDao();
		dao1.saveOrUpdate(keyword);
		dao1 = null;
		try{
			KeywordSearcher searcher = new  KeywordSearcher();
			searcher.search(feeds_);		
		}
		catch (Exception e) {
		}
		KeywordDao dao2 = new KeywordDao();
		Keyword keyFromDB=dao2.getKeywordWithFeeds(keyword.getValue());
		int size = keyFromDB.getFeedsIds().size();

		dao2.delete(keyFromDB);
		assertEquals(size, 1);
	}
	
	@Test
	/**
	 * There are no feeds which contains the keyword. So
	 * the searcher must spot zero feeds.
	 */
	public void search_keywordDoNotExistIntoFeeds_Test(){
		Keyword keyword = new Keyword("abcdefghijklmnopqr");
		KeywordDao dao1 = new KeywordDao();
		dao1.saveOrUpdate(keyword);
		dao1 = null;
		try{
			KeywordSearcher searcher = new  KeywordSearcher();
			searcher.search(feeds_);		
		}
		catch (Exception e) {
		}
		KeywordDao dao2 = new KeywordDao();
		Keyword keyFromDB=dao2.getKeywordWithFeeds(keyword.getValue());
		int zero = keyFromDB.getFeedsIds().size();

		dao2.delete(keyFromDB);
		assertEquals(zero, 0);
	}
	
	@Test
	/**
	 * There is a comment of a feed which contains the keyword.
	 * After the search, we can retrieve the keyword with the feed.
	 */
	public void search_keywordExistsIntoComment_Test(){
		Keyword keyword = new Keyword("Oppp arxisame??? :P");
		KeywordDao dao1 = new KeywordDao();
		dao1.saveOrUpdate(keyword);
		dao1 = null;
		try{
			KeywordSearcher searcher = new  KeywordSearcher();
			searcher.search(feeds_);		
		}
		catch (Exception e) {
		}
		KeywordDao dao2 = new KeywordDao();
		Keyword keyFromDB=dao2.getKeywordWithFeeds(keyword.getValue());
		int one = keyFromDB.getFeedsIds().size();

		dao2.delete(keyFromDB);
		assertEquals(one, 1);
	}
	
	/**
	 * Private methods for initiating searching data
	 * ##############################################
	 */
	
	private void init(){
		initiateFeedList();
	}
	
	private void initiateFeedList(){
		if(null==feeds_){
			feeds_ = new FeedList();
			FacebookJsonHandler handler = new FacebookJsonHandler();
			feeds_.setFeeds(handler.getFeeds(new FeedReader().getFacebookWall()));
		}
	}
	
	private void initiateDatabaseKeywords(){
		Keyword keyword1 = new Keyword("This is a keyword");
		Keyword keyword2 = new Keyword("Something");
		Keyword keyword3 = new Keyword("SomethingElse");

		KeywordDao keyDao = new KeywordDao();
		keyDao.saveOrUpdate(keyword1);
		keyDao.saveOrUpdate(keyword2);
		keyDao.saveOrUpdate(keyword3);

	}
	
	private void deleteDatabaseKeywords(){
		Keyword keyword1 = new Keyword("This is a keyword");
		Keyword keyword2 = new Keyword("Something");
		Keyword keyword3 = new Keyword("SomethingElse");

		KeywordDao keyDao = new KeywordDao();
		keyDao.delete(keyword1);
		keyDao.delete(keyword2);
		keyDao.delete(keyword3);

	}
}
