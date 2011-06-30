package com.sones.businessLogic.Facebook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Facebook.Comment;

public class CommentTest {
	
	private Comment comment_;
	private String feedId_;
	private String id_;
	private String message_;
	
	@Before
	public void setUp(){
		comment_ = new Comment();
		feedId_="Feed ID";
		id_="Comment ID";
		message_="Status message";
	}
	
	@After
	public void tearDown(){
		comment_=null;
	}
	
	@Test
	public void constructorWithNoArgs_CreatesUsefullComment_Test(){
		boolean isNotThrowing = true;
		try{
			comment_.getFeedId_().isEmpty();
			comment_.getMessage().isEmpty();
			comment_.getId_().isEmpty();
		}
		catch(NullPointerException ex){
			isNotThrowing=false;
		}
		assertTrue(isNotThrowing);
	}
	
	@Test
	public void constructoWithArgs_CreatesCorrectComment_Test(){
		Comment comment = new Comment(id_,message_,feedId_);
		assertEquals(comment.getId_(), id_);
		assertEquals(comment.getFeedId_(), feedId_);
		assertEquals(comment.getMessage(), message_);
	}
	
	@Test
	public void setMessage_NormalMessage_Test(){
		comment_.setMessage(message_);
		assertEquals(comment_.getMessage(), message_);
	}
	
	@Test
	public void setMessage_NullMessage_Test(){
		comment_.setMessage(null);
		assertTrue(comment_.getMessage().isEmpty());
	}
	
	@Test
	public void setId_NormalID_Test(){
		comment_.setId_(id_);
		assertEquals(comment_.getId_(), id_);
	}
	
	@Test
	public void setId_NullID_Test(){
		comment_.setId_(null);
		assertTrue(comment_.getId_().isEmpty());
	}
	
	@Test
	public void setFeedId_NormalID_Test(){
		comment_.setFeedId_(feedId_);
		assertEquals(comment_.getFeedId_(), feedId_);
	}
	
	@Test
	public void setFeedId_NullID_Test(){
		comment_.setFeedId_(null);
		assertTrue(comment_.getFeedId_().isEmpty());
	}
	
	@Test
	public void find_KeywordInSameCase_Test(){
		String keyword="keyword";
		comment_.setMessage(keyword);
		assertTrue(comment_.find(keyword));
	}
	
	@Test
	public void find_KeywordHasSpace_Test(){
		String keyword="keyword";
		comment_.setMessage(keyword+" ");
		assertTrue(comment_.find(keyword));
	}
	
	@Test
	public void find_KeywordInUpperCase_Test(){
		String keyword="keyword";
		comment_.setMessage("KEYWORD");
		assertTrue(comment_.find(keyword));
	}
	
	@Test
	public void find_KeywordIsMixCase_Test(){
		String keyword="kEyWoRd";
		comment_.setMessage("keyword");
		assertTrue(comment_.find(keyword));
		
	}
}
