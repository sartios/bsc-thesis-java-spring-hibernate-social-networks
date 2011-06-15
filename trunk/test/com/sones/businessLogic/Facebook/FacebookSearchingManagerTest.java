package com.sones.businessLogic.Facebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.sones.businessLogic.SearchOrganizer.AbstractSearcher;
import com.sones.businessLogic.SearchOrganizer.KeywordSearcher;
import com.sones.businessLogic.SearchOrganizer.SearchingTimer;

public class FacebookSearchingManagerTest {

	private FacebookSearchingManager manager = null;
	private AbstractSearcher searcher=null;
	private	FacebookSearchingList sources;
	
	@Before
	public void setUp(){
		manager = new FacebookSearchingManager("2011-06-09T10:11:24+0000");
		sources = new FacebookSearchingList();
		sources.addID("100000866964787");
		manager.setSources(sources);
		manager.setSearcher(new KeywordSearcher());
		searcher = new KeywordSearcher();
		manager.setSearcher(searcher);
	}
	
	@After
	public void tearDown(){
		manager = null;
		searcher=null;
	}
	
	@Test
	public void perfomSearch_settingSinceDate_Test(){	
		manager.performSearch();
		assertNotNull(searcher.getFeeds());	
	}
	
	@Test
	/**
	 * We should make two searches and see if the second feeds are only new feeds.
	 */
	public void performSearch_gettingOnlyNewFeeds_Test(){
		manager.performSearch();
		manager.performSearch();
		manager.performSearch();
		int zero=searcher.getFeeds().getSize();
		assertEquals(zero, 0);
	}
	
	@Test
	/**
	 * Try to search into null sources
	 */
	public void performSearch_NullSources_Test(){
		manager.setSources(null);
		manager.performSearch();
		int size=searcher.getFeeds().getSize();
		assertTrue(size==0);
	}
	
	@Test
	/**
	 * Try to search without setting searcher
	 */
	public void performSearch_NotSettingSearcher_Test(){
		manager.setSearcher(null);
		manager.performSearch();
		int size=searcher.getFeeds().getSize();
		assertTrue(size==0);
	}
		
}
