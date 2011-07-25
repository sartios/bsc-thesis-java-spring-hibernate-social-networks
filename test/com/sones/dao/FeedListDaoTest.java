package com.sones.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Facebook.FacebookUser;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.FeedList;

import static org.junit.Assert.*;


public class FeedListDaoTest {

	private FeedListDao dao=new FeedListDao();
	private FeedList feeds;
	private Feed feed;
	private FeedDao feedDao;
	private FacebookUser user;
	private FacebookUserDao userDao;
	
	@Before
	public void setUp(){
		feeds = new FeedList();
		feed = new Feed("001","007");
		user = new FacebookUser("007","0001");
		feeds.setUserID(user.getFacebookUserID_());
		feeds.setFeed(feed);
		
		userDao = new FacebookUserDao();
		userDao.saveOrUpdate(user);
		feedDao = new FeedDao();
		feedDao.saveOrUpdate(feed);
	}
	
	@After
	public void tearDown(){
		userDao.delete(user);
		feedDao.delete(feed);
		feed=null;
		user=null;
		userDao=null;
		feedDao=null;
		feeds=null;
	}
	
	/**
	 * We try to save a feed list which contains feeds that exist into database
	 */
	@Test
	public void saveUserFeeds_feedsAndUserExistIntoDatabase_Test(){
		boolean result=dao.saveUserFeeds(feeds);
		dao.deleteUserFeeds(feeds);
		assertTrue(result);
	}
	
	/**
	 * We try to save a feed list which contains feeds that don't exist into database
	 */
	@Test
	public void saveUserFeeds_feedsDoNotExistIntoDatabase_Test(){
		Feed feed1 = new Feed("something", user.getFacebookUserID_());
		feeds.setFeed(feed1);
		boolean result=dao.saveUserFeeds(feeds);
		dao.deleteUserFeeds(feeds);
		assertTrue(result);
	}
	
	/**
	 * We try to delete user's feeds that exist into database
	 * Everything should be ok and the feeds will be deleted
	 */
	@Test
	public void deleteUserFeeds_FeedsExistIntoDatabase_Test(){
		dao.saveUserFeeds(feeds);
		boolean result=dao.deleteUserFeeds(feeds);
		assertTrue(result);
	}
	
	/**
	 * We try to delete user's feeds that don't exist into database
	 * We shouldn't care if the feed exists or doesn't exist into database.
	 * If it doesn't we move on to the next
	 */
	@Test
	public void deleteUserFeeds_FeedsDoNotExistIntoDatabase_Test(){
		feeds.setFeed(new Feed("xxx","xxx"));
		boolean result=dao.deleteUserFeeds(feeds);
		assertTrue(result);
	}
	
	@Test
	public void findUserFeeds_getTheCorrectUserID_Test(){
		dao.saveUserFeeds(feeds);
		FeedList feedsFromDB = dao.findUserFeeds(user.getFacebookUserID_());
		dao.deleteUserFeeds(feeds);
		assertEquals(feedsFromDB.getUserID(), user.getFacebookUserID_());
	}
	
	@Test
	public void findUserFeeds_getTheCorrectFeedID_Test(){
		dao.saveUserFeeds(feeds);
		FeedList feedsFromDB = dao.findUserFeeds(user.getFacebookUserID_());
		dao.deleteUserFeeds(feeds);
		assertEquals(feedsFromDB.getFeed(0).getId_(), feed.getId_());
	}
	
	@Test
	public void findUserFeeds_getTheCorrectNumberOfFeeds_Test(){
		dao.saveUserFeeds(feeds);
		FeedList feedsFromDB = dao.findUserFeeds(user.getFacebookUserID_());
		dao.deleteUserFeeds(feeds);
		assertEquals(feedsFromDB.getSize(), feeds.getSize());
	}
	
	@Test
	public void findUserFeeds_getTheCorrectCommentNumber_Test(){
		dao.saveUserFeeds(feeds);
		FeedList feedsFromDB = dao.findUserFeeds(user.getFacebookUserID_());
		dao.deleteUserFeeds(feeds);
		assertEquals(feedsFromDB.getFeed(0).getNumberOfComments_(), feeds.getFeed(0).getNumberOfComments_());
	}
	
	@Test
	public void findUserFeeds_getTheCorrectFeedCreator_Test(){
		dao.saveUserFeeds(feeds);
		FeedList feedsFromDB = dao.findUserFeeds(user.getFacebookUserID_());
		dao.deleteUserFeeds(feeds);
		assertEquals(feedsFromDB.getFeed(0).getFromId_(), feeds.getFeed(0).getFromId_());
	}
	
	@Test
	public void findUserFeeds_getTheCorrectFeedCreationTime_Test(){
		dao.saveUserFeeds(feeds);
		FeedList feedsFromDB = dao.findUserFeeds(user.getFacebookUserID_());
		dao.deleteUserFeeds(feeds);
		assertEquals(feedsFromDB.getFeed(0).getCreatedTime_(), feeds.getFeed(0).getCreatedTime_());
	}
	
	@Test
	public void findUserFeeds_UserDoesNotExist_Test(){
		dao.saveUserFeeds(feeds);
		FeedList feedsFromDB = dao.findUserFeeds("blablabla");
		dao.deleteUserFeeds(feeds);
		assertTrue(feedsFromDB.getSize()==0);
	}
}
