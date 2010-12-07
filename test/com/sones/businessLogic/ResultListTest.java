package com.sones.businessLogic;

import java.util.ArrayList;
import java.util.List;

import javax.activity.InvalidActivityException;
import javax.xml.ws.Action;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ResultListTest {
	
	/**
	 * Object under test
	 */
	private ResultList results_;
	
	/**
	 * We instanciate the results_ object and create its keyword list
	 */
	@Before
	public void setUp(){
		List<String> keywords = new ArrayList<String>();
		keywords.add("my");
		keywords.add("world");
		keywords.add("is");
		keywords.add("paradise");
		results_ = new ResultList(keywords);
	}
	
	@After
	public void tearDown(){
		results_ = null;
	}
	
	/**
	 * Constructor without arguments must throw an exception. This happens
	 * because we must know the keywords for which we will keep the feeds.
	 */
	@Test
	public void constructor_NoArguments_Test(){
		boolean isThrowing = false;
		try{
			ResultList results = new ResultList();
		}
		catch(InvalidActivityException e){
			isThrowing=true;
		}
		assertTrue(isThrowing);
	}
	
	/**
	 * We add ID for a keyword that we've not add it again.
	 */
	@Test
	public void addID_DoesntExist_Test(){
		results_.addID("my", "0");
		results_.addID("my", "1");
		results_.addID("my", "2");
		results_.addID("world", "3");
		results_.addID("world", "4");
		results_.addID("world", "5");
		
		assertEquals(results_.getIDs("my").size(),3);
		assertEquals(results_.getIDs("world").size(),3);

	}
	
	/**
	 * We add ID for a keyword that we add it again.
	 */
	@Test
	public void addID_Exists_Test(){
		results_.addID("my", "0");
		results_.addID("my", "0");
		results_.addID("my", "2");
		results_.addID("world", "3");
		results_.addID("world", "3");
		results_.addID("world", "5");
		
		assertEquals(results_.getIDs("my").size(),2);
		assertEquals(results_.getIDs("world").size(),2);

	}
	
	/**
	 * Get the list of IDs for a keyword that exists
	 */
	@Test
	public void getIDs_KeywordExists_Test(){
		results_.addID("my", "0");
		results_.addID("my", "1");
		results_.addID("my", "2");
		assertEquals(results_.getIDs("my").size(),3);
	}
	
	/**
	 * Get the list of IDs for a keyword that doesn't exist
	 */
	@Test
	public void getIDs_KeywordDoesNotExist_Test(){
		boolean isThrowing = false;
		try{
			results_.getIDs("hello");
		}
		catch(NullPointerException e){
			isThrowing=true;
		}
		assertTrue(isThrowing);
	}
}
