package com.sones.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Facebook.FeedsWithCommentTraffic;

public class FeedsWithCommentTrafficDaoTest {
	
	private FeedsWithCommentTrafficDao dao=null;
	private Map<String, String> feeds=new HashMap<String, String>();
	private FeedsWithCommentTraffic list=null;
	private long listID;
	private FeedsWithCommentTraffic listFromDB;


	@Before
	public void setUp(){
		dao = new FeedsWithCommentTrafficDao();
		list = new FeedsWithCommentTraffic();
	}
	
	@After
	public void tearDown(){
		dao=null;
		list=null;
		listFromDB=null;
	}

	@Test
	public void saveOrUpdate_FullObject_Test(){
		list.setFeeds(getFeeds());
		dao.saveOrUpdate(list);
		listFromDB = dao.findAll().get(0);
		assertNotNull(listFromDB);
		dao.deleteAll();
	}
	
	@Test
	public void saveOrUpdate_ObjectWithoutFeeds_Test(){
		dao.saveOrUpdate(list);
		listFromDB = dao.findAll().get(0);
		assertNotNull(listFromDB);
		dao.deleteAll();
	}
	
	@Test
	public void saveOrUpdate_NullObject_Test(){
		FeedsWithCommentTraffic list = null;
		dao.saveOrUpdate(list);
		List listFromDB = dao.findAll();
		assertTrue(listFromDB.isEmpty());
	}
	
	@Test
	public void deleteAll_ThereAreNoLists_Test(){
		boolean exceptionRising=false;
		try{
			dao.deleteAll();
			dao.deleteAll();
		}
		catch (Exception e) {
			exceptionRising = true;
		}
		assertFalse(exceptionRising);
	}
	
	@Test
	public void findFeedsOfTheList_ListContainsFeeds_Test(){
		list.setFeeds(getFeeds());
		dao.saveOrUpdate(list);
		listID = dao.findAll().get(0).getId();
		Map feedsFromDB = dao.findFeedsOfTheList(listID);
		assertEquals(3,feedsFromDB.size());
		dao.deleteAll();
	}
	
	@Test
	public void findListWithFeeds_ListContainsFeedsNew_Test(){
		list.setFeeds(getFeeds());
		dao.saveOrUpdate(list);
		listID = dao.findAll().get(0).getId();
		listFromDB = dao.findListWithFeeds(listID);
		assertEquals(3,listFromDB.getFeeds().size());
		dao.deleteAll();
	}
	
	@Test
	public void findListWithFeeds_ListContainsNoFeeds_Test(){
		Map<String, String> feeds = null;
		list.setFeeds(feeds);
		dao.saveOrUpdate(list);
		listID = dao.findAll().get(0).getId();
		listFromDB = dao.findListWithFeeds(listID);
		assertTrue(listFromDB.getFeeds().isEmpty());
		dao.deleteAll();
	}
	
	private Map getFeeds(){
		feeds.put("1", "2");
		feeds.put("2", "1");
		feeds.put("3", "8");
		return feeds;
	}
	@Test
	public void getSearchingDatesWhereResultsFound(){
		assertEquals(dao.getSearchingDatesWhereResultsFound().size(),3);
	}
}
