package com.sones.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedList;
import com.sones.businessLogic.Status;
import com.sones.businessLogic.Facebook.FacebookFriend;

public class FeedDaoTest {
	
	private FeedDao feedDao;
	private Feed feed;
	private String id;
	
	@Before
	public void setUp(){
		id = new String("1");
		FacebookFriend FROM = new FacebookFriend("Me", "1");
		feed =new Feed(id,FROM,"01-12-2010");
		feed.setNumberOfComments(10);
		feedDao = new FeedDao();
	}
	
	@After
	public void tearDown(){
		feed = null;
		id = null;
		feedDao=null;
	}
	
	@Test
	public void saveOrUpdate_Test(){
		feedDao.saveOrUpdate(feed);
	}
	

	
	@Test
	public void find_Test(){
		assertNotNull(feedDao.find(id));
	}
	
	@Test
	public void findFeedsWithMoreCommentsThan_Test(){
		feedDao.saveOrUpdate(feed);
		FeedList feeds = feedDao.findFeedsWithMoreCommentsThan(9);
		assertNotNull(feeds.getFeed());
	}
}
