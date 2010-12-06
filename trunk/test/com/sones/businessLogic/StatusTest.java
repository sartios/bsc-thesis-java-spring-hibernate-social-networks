package com.sones.businessLogic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Facebook.FacebookFriend;

import static org.junit.Assert.*;

/**
 * This class tests the Status feed
 * @author Sartios
 *
 */
public class StatusTest {
	
	private Status feed_;
	
	@Before
	public void setUp(){
		FacebookFriend from = new FacebookFriend("Sartios", "123");
		String message = "Hello world, my name is Sartios.";
		feed_ = new Status("12345",from , "12:00", message);
	}
	
	@After
	public void tearDown(){
		feed_ = null;
	}
	
	/**
	 * Test find when the keyword doesn't exists
	 */
	@Test
	public void find_KeywordDoesNotExist_Test(){
		assertFalse(feed_.find("arisfm"));
	}
	
	/**
	 * Test find when the keyword exists
	 */
	@Test
	public void find_KeywordExists_Test(){
		assertTrue(feed_.find("world"));
	}
	
	/**
	 * Test find when the keyword exists but has diffirent case
	 */
	@Test
	public void find_KeywordExistsInOtherCase_Test(){
		assertTrue(feed_.find("sARTIOS"));
	}
	
}
