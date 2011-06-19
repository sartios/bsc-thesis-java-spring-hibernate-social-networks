package com.sones.dao;

import org.junit.Test;
import static org.junit.Assert.*;

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
}
