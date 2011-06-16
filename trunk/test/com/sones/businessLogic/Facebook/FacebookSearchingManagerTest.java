package com.sones.businessLogic.Facebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.sones.businessLogic.SearchOrganizer.AbstractSearcher;
import com.sones.businessLogic.SearchOrganizer.KeywordSearcher;
import com.sones.businessLogic.SearchOrganizer.SearchingTimer;
import com.sones.utilities.DateConverter;

public class FacebookSearchingManagerTest {

	private FacebookSearchingManager manager = null;
	private AbstractSearcher searcher=null;
	private	FacebookSearchingList sources;
	
	@Before
	public void setUp(){
		manager = new FacebookSearchingManager("2011-06-13T10:11:24+0000");
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
	
}
