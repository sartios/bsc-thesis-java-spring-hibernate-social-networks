package com.sones.businessLogic.Searcher;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testingUtilities.FeedReader;

import com.sones.businessLogic.Facebook.CommentList;
import com.sones.businessLogic.Facebook.FacebookJsonHandler;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.FeedList;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.KeywordList;
import com.sones.businessLogic.Searcher.CommentTrafficSearcher;
import com.sones.dao.FeedDao;
import com.sones.dao.KeywordDao;

import static org.junit.Assert.*;

public class CommentTrafficSearcherTest {

	private CommentTrafficSearcher searcher_;
	
	@Before
	public void setUp(){
		searcher_ = new CommentTrafficSearcher();
	}
	
	@After
	public void tearDown(){
		searcher_=null;
	}
	
	@Test
	public void constructorWithNoArguments_CreatesUsefullObject_Test(){
		boolean isThrowing=true;
		try{
			searcher_.getCurrentFeedsWithCommentTraffic().isEmpty();
			searcher_.search();
			searcher_.getFeeds().getIdFromFeeds();
			isThrowing=false;
		}
		catch (NullPointerException ex) {
			 isThrowing=true;
		}
		assertFalse(isThrowing);
	}
	
	@Test
	/**
	 * We test if it finds the feeds with comment traffic
	 */
	public void findFeedsWithCommentTraffice_ThereIsCommentTraffice_FeedsFromFile_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		FeedReader reader = new FeedReader();
		FeedList initialStateFeeds = new FeedList();
		initialStateFeeds.setFeeds(handler.getFeeds(reader.getFacebookWall()));
		saveFeedsToDB(initialStateFeeds);
		
		FeedList feedsWithMoreComments = new FeedList();
		feedsWithMoreComments.setFeeds(handler.getFeeds(reader.getWallWithMoreComments()));
		
		System.out.println(feedsWithMoreComments.getSize());

		searcher_.setFeeds(feedsWithMoreComments);
		searcher_.findFeedsWithCommentTraffic();
		int listSize = searcher_.getCurrentFeedsWithCommentTraffic().size();
		
		
		deleteFeedsFromDB(feedsWithMoreComments);
		assertEquals(listSize,2);
	}
	
	@Test
	/**
	 * We test if it returns the ID of the comments to search
	 */
	public void getNewComments_ThereAreTwoNewComments_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		FeedReader reader = new FeedReader();
		FeedList initialStateFeeds = new FeedList();
		initialStateFeeds.setFeeds(handler.getFeeds(reader.getFacebookWall()));
		saveFeedsToDB(initialStateFeeds);
		FeedList feedsWithMoreComments = new FeedList();
		feedsWithMoreComments.setFeeds(handler.getFeeds(reader.getWallWithMoreComments()));
		searcher_.setFeeds(feedsWithMoreComments);
		searcher_.findFeedsWithCommentTraffic();
		Set<String> feeds = searcher_.getCurrentFeedsWithCommentTraffic();
		String feedID = (String) feeds.toArray()[0];
		CommentList comments=searcher_.getNewComments(feedID);
		deleteFeedsFromDB(feedsWithMoreComments);
		assertEquals(comments.getComment(1).getId_(),"100000866964787_220884114600511_3821926");
	}
	
	/**
	 * Save a feed list to database for testing purposes
	 * @param feeds
	 */
	private void saveFeedsToDB(final FeedList feeds){
		FeedDao feedDao = new FeedDao();
			feedDao.saveUserFeeds(feeds);
	}
	
	/**
	 * Delete a feed list from database. We use it for testing purposes
	 * @param feeds
	 */
	private void deleteFeedsFromDB(final FeedList feeds){
		FeedDao feedDao = new FeedDao();
		for(int i=0;i<feeds.getSize();i++){
			feedDao.delete(feeds.getFeed(i));
		}
	}
	
	@Test
	/**
	 * Search for keywords that exists only in the new comments. It must save the feeds for the keywords.
	 */
	public void search_KeywordsExistOnlyInNewComments_Test(){
		Keyword keyword = new Keyword("commentXXXXXX");
		KeywordDao keywordDao = new KeywordDao();
		keywordDao.saveOrUpdate(keyword);
		FacebookJsonHandler handler = new FacebookJsonHandler();
		FeedReader reader = new FeedReader();
		FeedList initialStateFeeds = new FeedList();
		initialStateFeeds.setFeeds(handler.getFeeds(reader.getFacebookWall()));
		saveFeedsToDB(initialStateFeeds);
		FeedList feedsWithMoreComments = new FeedList();
		feedsWithMoreComments.setFeeds(handler.getFeeds(reader.getWallWithMoreComments()));
		searcher_.setFeeds(feedsWithMoreComments);
		searcher_.search();
		Keyword keywordWithFeeds=keywordDao.getKeywordWithFeeds(keyword.getValue());
		
		boolean thereAreNotFeedsForThisKeyword = keywordWithFeeds.getFeedsIds().isEmpty();
		
		keywordDao.delete(keywordWithFeeds);
	//	deleteFeedsFromDB(feedsWithMoreComments);
		
		assertFalse(thereAreNotFeedsForThisKeyword);
	}
	@Test
	/**
	 * We want to search only in the new comments of a feed.
	 * 
	 * How we are going to do that
	 * ---------------------------
	 * 
	 * We have save a feed in a file. We are going to create a second file that
	 * contains the same feed but with 2 extra comments. The keyword will exist only
	 * in the new comments. So the first time searching must give true and the second time
	 * must give false.
	 * But what we want to test more, is that it's searching only in the new comments.
	 * So, we must see the number of comments that has and the id of them.
	 */
	public void test(){
		
	}
}
