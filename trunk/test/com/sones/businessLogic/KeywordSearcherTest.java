package com.sones.businessLogic;

import java.awt.RenderingHints.Key;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.dao.FeedDao;
import com.sones.dao.KeywordDao;

import static org.junit.Assert.*;

public class KeywordSearcherTest {

	private KeywordSearcher searcher = new KeywordSearcher();
	private Feed hiWorld = new Feed();
	private Feed nothing = new Feed();
	private FeedList feeds;
	KeywordDao dao = new KeywordDao();

	
	@Before
	public void setUp(){
		initHiWorld();
		initNothing();
		initFeedsList();
		createAndSaveKeywords();
		searcher.setFeeds(feeds);
	}
	
	@After
	public void tearDown(){
		//feeds = null;
		//hiWorld=null;
		//helloWorld=null;
		//clearDatabase();
	}
	
	@Test
	public void search_Test(){
		searcher.search();
	}
	
	@Test
	public void retriveSearchResultsFor_HiKeyword_Test(){
		Keyword key = dao.getKeywordWithFeeds("Hi");
		assertEquals("100000866964787_193869053985325", key.getFeedsIds().get(0));
	}
	
	@Test
	public void retriveSearchResultsFor_nothingKeyword_Test(){
		Keyword key2 = dao.getKeywordWithFeeds("nothing");
		assertEquals("100000866964787_193878910651006", key2.getFeedsIds().get(0));
	}
	
	@Test
	public void clearEverything(){
		clearDatabase();
	}
	
	
	private void initHiWorld(){
		hiWorld = new Feed();
		hiWorld.setId_("100000866964787_193869053985325");
	}
	
	private void initNothing(){
		nothing = new Feed();
		nothing.setId_("100000866964787_193878910651006");
	}
	
	private void initFeedsList(){
		feeds = new FeedList();
		feeds.setFeed(nothing);
		feeds.setFeed(hiWorld);
		
		FeedDao feedDao = new FeedDao();
		feedDao.saveUserFeeds(feeds);
	}
	
	private void createAndSaveKeywords(){
		Keyword keyword1 =  new Keyword("Hi");
		Keyword keyword2 = new Keyword("nothing");
		//dao.saveOrUpdate(keyword1);
		//dao.saveOrUpdate(keyword2);
	}
	
	private void clearDatabase(){
		Keyword keyword1 =  new Keyword("Hi");
		Keyword keyword2 = new Keyword("nothing");
		dao.delete(keyword1);
		dao.delete(keyword2);
	}
}
