package com.sones.businessLogic.Facebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NoteTest {
	
	private Note note_;
	private String userId_;
	private String feedId_;
	private String subject_;
	private String message_;
	
	@Before
	public void setUp(){
		note_ = new Note();
		userId_="User ID";
		feedId_="Feed ID";
		subject_="Note subject";
		message_="Note message";
	}
	
	@After
	public void tearDown(){
		note_=null;
	}
	
	@Test
	public void constructorWithNoArgs_CreatesUsefullNote_Test(){
		boolean isNotThrowing = true;
		try{
			note_.getMessage().isEmpty();
			note_.getSubject().isEmpty();
		}
		catch(NullPointerException ex){
			isNotThrowing=false;
		}
		assertTrue(isNotThrowing);
	}
	
	@Test
	public void constructoWithArgs_CreatesCorrectFeed_Test(){
		Note note = new Note(subject_,message_, feedId_, userId_);
		assertEquals(note.getId_(), feedId_);
		assertEquals(note.getFromId_(), userId_);
		assertEquals(note.getSubject(), subject_);
		assertEquals(note.getMessage(), message_);
	}
	
	@Test
	public void setMessage_NormalMessage_Test(){
		note_.setMessage(message_);
		assertEquals(note_.getMessage(), message_);
	}
	
	@Test
	public void setSubject_NullSubject_Test(){
		note_.setSubject(null);
		assertTrue(note_.getSubject().isEmpty());
	}
	
	@Test
	public void setSubject_NormalSubject_Test(){
		note_.setSubject(subject_);
		assertEquals(note_.getSubject(), subject_);
	}
	
	@Test
	public void setMessage_NullMessage_Test(){
		note_.setMessage(null);
		assertTrue(note_.getMessage().isEmpty());
	}
	
	@Test
	public void find_KeywordInSameCase_Test(){
		String keyword="keyword";
		note_.setMessage(keyword);
		assertTrue(note_.find(keyword));
	}
	
	@Test
	public void find_KeywordHasSpace_Test(){
		String keyword="keyword";
		note_.setMessage(keyword+" ");
		assertTrue(note_.find(keyword));
	}
	
	@Test
	public void find_KeywordInUpperCase_Test(){
		String keyword="keyword";
		note_.setMessage("KEYWORD");
		assertTrue(note_.find(keyword));
	}
	
	@Test
	public void find_KeywordIsMixCase_Test(){
		String keyword="kEyWoRd";
		note_.setMessage("keyword");
		assertTrue(note_.find(keyword));
	}
}
