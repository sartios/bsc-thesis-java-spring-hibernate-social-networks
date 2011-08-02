package com.sones.businessLogic.Facebook.Source;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FacebookSourceListTest {

	private FacebookSourceList sources_;
	
	@Before
	public void setUp(){
		sources_ = new FacebookSourceList();
		sources_.addSource(new FacebookFriend("Sartios","1"));
		sources_.addSource(new FacebookFriend("Stelios","2"));
		sources_.addSource(new FacebookFriend("Spiros","3"));
	}
	
	@After
	public void tearDown(){
		sources_=null;
	}
	
	@Test
	public void getNext_ThereAreSourcesInTheList_test(){
		assertEquals(sources_.getNext(),new FacebookFriend("Sartios","1") );
	}
	
	@Test
	public void getNext_ThereAreNotSourcesInTheList_test(){
		FacebookSourceList sources = new FacebookSourceList();
		assertNull(sources.getNext());
	}
	
	@Test
	public void hasNext_ThereAreSourcesInTheList_test(){
		assertTrue(sources_.hasNext());
	}
	
	@Test
	public void hasNext_ThereAreNotSourcesInTheList_test(){
		FacebookSourceList sources = new FacebookSourceList();
		assertFalse(sources.hasNext());
	}
	
	@Test
	public void isEmpty_ThereAreSourcesInTheList_test(){
		assertFalse(sources_.isEmpty());
	}
	
	@Test
	public void isEmpty_ThereAreNotSourcesInTheList_test(){
		FacebookSourceList sources = new FacebookSourceList();
		assertTrue(sources.isEmpty());
	}
	
}
