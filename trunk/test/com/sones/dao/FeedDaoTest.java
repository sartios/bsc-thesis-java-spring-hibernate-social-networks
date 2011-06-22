package com.sones.dao;

import org.junit.Test;

import testingUtilities.FeedReader;
import static org.junit.Assert.*;

import com.sones.businessLogic.Facebook.FacebookJsonHandler;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.businessLogic.Facebook.FacebookToken;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.FeedList;
import com.sones.dao.FeedDao;
import com.sones.dao.TokenDao;

public class FeedDaoTest {

	@Test
	public void saveUserFeeds_Test(){
		FacebookRestHandler handler = new FacebookRestHandler();
		TokenDao dao = new TokenDao();
		FacebookToken token = (FacebookToken) dao.findAll().get(0);
		FeedList feeds = handler.getFeeds("100000866964787", token.getToken());
		
		FeedDao feedDao = new FeedDao();
		feedDao.saveUserFeeds(feeds);
	}
	
	@Test
	public void findUserFeeds_Test(){
		FacebookRestHandler handler = new FacebookRestHandler();
		TokenDao dao = new TokenDao();
		FacebookToken token = (FacebookToken) dao.findAll().get(0);
		FeedDao feedDao = new FeedDao();
		FeedList feeds = feedDao.findUserFeeds("100000866964787");
		//assertNotNull(feeds.getFeeds_());
		assertNotSame(0, feeds.getFeeds_().size());
	}
	
	@Test
	public void saveCommentNumber_Test(){
		FacebookRestHandler handler = new FacebookRestHandler();
		TokenDao dao = new TokenDao();
		FacebookToken token = (FacebookToken) dao.findAll().get(0);
		FeedDao feedDao = new FeedDao();
		Feed feed =(Feed)handler.getFeed("100000866964787_152492174812686", token.getToken());
		feedDao.saveOrUpdate((Feed)feed);
		Feed new_feed = new Feed();
		new_feed=(Feed)feedDao.find("100000866964787_152492174812686");
		
		assertEquals(1, new_feed.getNumberOfComments_());
	}
	
	@Test
	public void findMostRecentUserFeedDate_GetDate_Test(){
		String userID = "100000866964787";
		FeedDao dao = new FeedDao();
		String date=dao.findMostRecentUserFeedDate(userID);
		System.out.println(date);
		assertFalse(date.isEmpty());
	}
	
	@Test
	public void findFeedsBetween_ThereAreFeedsInTheseDates_Test(){
		String startingDate = new String("2011-05-24T16:20:27+0000");
		String endingDate = new String("2011-06-21T17:17:59+0000");
		
		FacebookJsonHandler handler = new FacebookJsonHandler();
		FeedList feeds = new FeedList();
		feeds.setFeeds(handler.getFeeds(new FeedReader().getFacebookWall()));
		FeedDao  dao = new FeedDao();
		dao.saveUserFeeds(feeds);
		
		FeedList feedsFromDB = new FeedList();
		feedsFromDB = dao.findFeedsBetween(startingDate, endingDate);
		int notZero = feedsFromDB.getFeeds_().size();
		dao.deleteFeedList(feedsFromDB);
		assertTrue(notZero>0);
	}
	
	@Test
	public void findFeedsBetween_ThereAreNotFeedsInTheseDates_Test(){
		String startingDate = new String("2050-05-24T16:20:27+0000");
		String endingDate = new String("2050-06-21T17:17:59+0000");	
		FeedDao dao = new FeedDao();
		FeedList feedsFromDB = new FeedList();
		feedsFromDB = dao.findFeedsBetween(startingDate, endingDate);
		int zero = feedsFromDB.getFeeds_().size();
		assertEquals(zero, 0);
	}
	
	@Test
	public void findFeedsBetween_DatesAreNull_Test(){
		String startingDate = null;
		String endingDate =null;	
		FeedDao dao = new FeedDao();
		FeedList feedsFromDB = new FeedList();
		feedsFromDB = dao.findFeedsBetween(startingDate, endingDate);
		int zero = feedsFromDB.getFeeds_().size();
		assertEquals(zero, 0);
	}
	
	@Test
	public void findFeedsBetween_DatesNoDates_Test(){
		String startingDate = new String("Zavarakatranemia");
		String endingDate =new String("Ileos");;	
		FeedDao dao = new FeedDao();
		FeedList feedsFromDB = new FeedList();
		feedsFromDB = dao.findFeedsBetween(startingDate, endingDate);
		int zero = feedsFromDB.getFeeds_().size();
		assertEquals(zero, 0);
	}
	
	@Test
	public void findFeedsBetween_DatesAreSame_Test(){
		String startingDate = new String("2011-05-24T16:20:27+0000");
		String endingDate =new String("2011-05-24T16:20:27+0000");;	
		FeedDao dao = new FeedDao();
		FeedList feedsFromDB = new FeedList();
		feedsFromDB = dao.findFeedsBetween(startingDate, endingDate);
		int zero = feedsFromDB.getFeeds_().size();
		assertEquals(zero, 0);
	}
	
	@Test
	public void getNumberOfFeeds_ThereAreFeeds_Test(){
		FeedDao dao = new FeedDao();
		assertTrue(dao.getNumberOfFeeds()!=0);
	}
}
