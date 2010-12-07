package com.sones.businessLogic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KeywordTest {
	
	private Keyword keyword_;
	
	@Before
	public void setUp(){
		keyword_ = new Keyword("Hello",10);
	}
	
	@After
	public void tearDown(){
		keyword_ = null;
	}
	
	@Test
	public void canBeNotifiable_MoreAppears_Test(){
		for(int i=0;i<12;i++){
			keyword_.increaseNumberOfAppears();
		}
		assertTrue(keyword_.canBeNotifiable());
	}
	
	@Test
	public void canBeNotifiable_EqualAppears_Test(){
		for(int i=0;i<10;i++){
			keyword_.increaseNumberOfAppears();
		}
		assertTrue(keyword_.canBeNotifiable());
	}
	
	@Test
	public void canBeNotifiable_LessAppears_Test(){
		keyword_.increaseNumberOfAppears();
		keyword_.increaseNumberOfAppears();
		assertFalse(keyword_.canBeNotifiable());
	}
	
	/**
	 * We add ID for a keyword that we've not add it again.
	 */
	@Test
	public void addID_DoesntExist_Test(){
		keyword_.addID("0");
		keyword_.addID("1");
		keyword_.addID( "2");
		keyword_.addID("3");
		keyword_.addID("4");
		keyword_.addID("5");
		
		assertEquals(keyword_.getIDs().size(),6);
	}
	
	
	/**
	 * We add ID for a keyword that we add it again.
	 */
	@Test
	public void addID_Exists_Test(){
		keyword_.addID("0");
		keyword_.addID("0");
		keyword_.addID("2");
		keyword_.addID("3");
		keyword_.addID("3");
		keyword_.addID("5");
		assertEquals(keyword_.getIDs().size(),4);
	}

}
