package com.sones.businessLogic.Facebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatusMessageTest {
	
	private StatusMessage status_;
	private String userId_;
	private String feedId_;
	private String message_;
	
	@Before
	public void setUp(){
		status_ = new StatusMessage();
		userId_="User ID";
		feedId_="Feed ID";
		message_="Status message";
	}
	
	@After
	public void tearDown(){
		status_=null;
	}
	
	@Test
	public void constructorWithNoArgs_CreatesUsefullFeed_Test(){
		boolean isNotThrowing = true;
		try{
			status_.getMessage().isEmpty();
		}
		catch(NullPointerException ex){
			isNotThrowing=false;
		}
		assertTrue(isNotThrowing);
	}
	
	@Test
	public void constructoWithArgs_CreatesCorrectFeed_Test(){
		StatusMessage message = new StatusMessage(message_, feedId_, userId_);
		assertEquals(message.getId_(), feedId_);
		assertEquals(message.getFromId_(), userId_);
		assertEquals(message.getMessage(), message_);
	}
	
	@Test
	public void setMessage_NormalMessage_Test(){
		status_.setMessage(message_);
		assertEquals(status_.getMessage(), message_);
	}
	
	@Test
	public void setMessage_NullMessage_Test(){
		status_.setMessage(null);
		assertTrue(status_.getMessage().isEmpty());
	}
	
	@Test
	public void find_KeywordInSameCase_Test(){
		String keyword="keyword";
		status_.setMessage(keyword);
		assertTrue(status_.find(keyword));
	}
	
	@Test
	public void find_KeywordHasSpace_Test(){
		String keyword="keyword";
		status_.setMessage(keyword+" ");
		assertTrue(status_.find(keyword));
	}
	
	@Test
	public void find_KeywordInUpperCase_Test(){
		String keyword="keyword";
		status_.setMessage("KEYWORD");
		assertTrue(status_.find(keyword));
	}
	
	@Test
	public void find_KeywordIsMixCase_Test(){
		String keyword="kEyWoRd";
		status_.setMessage("keyword");
		assertTrue(status_.find(keyword));
		
	}
	
	@Test
	public void find_keywordExists_Test(){
		CommentList comments = new CommentList();
		Comment comment = new Comment("1","keyword","2");
		comments.addComment(comment);
		status_.setComments_(comments);
		String keyword = "keyword";
		assertTrue(status_.find(keyword));
	}
	
	@Test
	public void find_keywordDoesNotExist_Test(){
		CommentList comments = new CommentList();
		Comment comment = new Comment("1","keyword","2");
		comments.addComment(comment);
		status_.setComments_(comments);
		String keyword = "ARIS";
		assertFalse(status_.find(keyword));
	}
}
