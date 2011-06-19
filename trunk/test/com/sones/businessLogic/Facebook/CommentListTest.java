package com.sones.businessLogic.Facebook;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;

public class CommentListTest {

	private CommentList comments_;
	private String id_;
	private String feedId_;
	private String message_;
	private Comment comment_;
	
	@Before
	public void setUp(){
		comments_=new CommentList();
		id_=new String("Comment ID");
		feedId_=new String("Feed ID");
		message_=new String("Comment message");
		comment_=new Comment();
		comment_.setId_(id_);
		comment_.setFeedId_(feedId_);
		comment_.setMessage(message_);
	}
	
	@After
	public void tearDown(){
		comment_=null;
		comments_=null;
	}
	
	@Test
	public void addComment_CommentDoesNotExist_Test(){
		comments_.clear();
		assertTrue(comments_.addComment(comment_));
	}
	
	@Test
	public void addComment_CommentExists_Test(){
		comments_.addComment(comment_);
		assertFalse(comments_.addComment(comment_));
	}
	
	@Test
	public void addComment_CommentIsNull_Test(){
		assertFalse(comments_.addComment(null));
	}
	
	@Test
	public void getComment_ThereAreCommentsInTheSet_Test(){
		comments_.addComment(comment_);
		assertNotNull(comments_.getComment(0));
	}
	
	@Test
	public void getComment_ThereAreNotCommentsInTheSet_Test(){
		comments_.clear();
		assertNull(comments_.getComment(0));
	}
	
	@Test
	public void clear_ThereAreComments_Test(){
		comments_.clear();
		comments_.addComment(comment_);
		int sizeOnFullSet = comments_.getSize();
		comments_.clear();
		assertFalse(sizeOnFullSet==comments_.getSize());
	}
	
	@Test
	public void clear_ThereAreNotComments_Test(){
		comments_.clear();
		int sizeBefore = comments_.getSize();
		comments_.clear();
		assertTrue(sizeBefore==comments_.getSize());
	}
	
	@Test
	public void getSize_ThereAreComments_Test(){
		comments_.addComment(comment_);
		assertTrue(0<comments_.getSize());
	}
	
	@Test
	public void getSize_ThereAreNoComments_Test(){
		comments_.clear();
		assertTrue(0==comments_.getSize());
	}
	
	@Test
	public void contain_KeywordExistsInTheFirstComment_Test(){
		comments_.addComment(comment_);
		String keyword = "message";
		assertTrue(comments_.contain(keyword));
	}
	
	@Test
	public void contain_KeywordExistsNotInTheFirstComment_Test(){
		comments_.addComment(comment_);
		Comment c1 = new Comment(id_, message_, feedId_);
		Comment c2 = new Comment(id_+" 1", "exist", feedId_);
		Comment c3 = new Comment(id_+" 1", message_, feedId_);
		comments_.addComment(c1);
		comments_.addComment(c2);
		comments_.addComment(c3);
		String keyword = "exist";
		assertTrue(comments_.contain(keyword));
	}
	
	@Test
	public void contain_KeywordDoesNotExistInComments_Test(){
		comments_.addComment(comment_);
		Comment c1 = new Comment(id_, message_, feedId_);
		Comment c2 = new Comment(id_+" 1", "exist", feedId_);
		Comment c3 = new Comment(id_+" 1", message_, feedId_);
		comments_.addComment(c1);
		comments_.addComment(c2);
		comments_.addComment(c3);
		String keyword = "Aris";
		assertFalse(comments_.contain(keyword));
	}
	
	@Test
	public void contain_KeywordIsNull_Test(){
		comments_.addComment(comment_);
		assertFalse(comments_.contain(null));
	}
}
