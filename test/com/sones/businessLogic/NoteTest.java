package com.sones.businessLogic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Facebook.FacebookFriend;

/**
 * This class tests the Note feed
 * @author Sartios
 */
public class NoteTest {

	private Note feed_;
	
	@Before
	public void setUp(){
		FacebookFriend from = new FacebookFriend("Sartios", "123");
		String subject = "My first application";
		String message = "Hello world, my name is Sartios.";
		feed_ = new Note("12345",from , "12:00",subject, message);
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
	 * Test find when the keyword exists but has different case
	 */
	@Test
	public void find_KeywordExistsInOtherCase_Test(){
		assertTrue(feed_.find("sARTIOS"));
	}
	
	/**
	 * Test find when the keyword exists in the subject
	 */
	@Test
	public void find_KeywordExistsInSubject_Test(){
		assertTrue(feed_.find("first"));
	}
	
	/**
	 * Test find when the keyword exists in the subject but in different case
	 */
	@Test
	public void find_KeywordExistsInSubjectWithOtherCase_Test(){
		assertTrue(feed_.find("FIRST"));
	}
}
