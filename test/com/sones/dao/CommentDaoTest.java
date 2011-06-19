package com.sones.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.sones.businessLogic.Facebook.Comment;
import com.sones.businessLogic.Facebook.CommentList;
import com.sones.businessLogic.Facebook.Feed;


public class CommentDaoTest {

	private CommentDao dao_;
	private CommentList comments_;
	private Comment comment_;
	private String id_;
	private String message_;
	private String feedId_;
	
	@Before
	public void setUp(){
		dao_ = new CommentDao();
		comments_=new CommentList();
		comment_ = new Comment();
		id_=new String("Comment ID");
		message_=new String("Comment Message");
		feedId_=new String("Feed ID");
		comment_.setFeedId_(feedId_);
		comment_.setId_(id_);
		comment_.setMessage(message_);
		comments_.addComment(comment_);
	}
	
	@After
	public void tearDown(){	
		dao_.deleteCommentList(comments_);
		dao_.delete(comment_);
		comment_=null;
		comments_=null;
	}
	
	@Test
	public void saveOrUpdate_NormalComment_Test(){
		Feed feed = new Feed(feedId_, "1");
		FeedDao feedDao = new FeedDao();
		feedDao.saveOrUpdate(feed);
		boolean saveIsOk = dao_.saveOrUpdate(comment_);
		dao_.delete(comment_);
		feedDao.delete(feed);
		assertTrue(saveIsOk);
	}
	
	@Test
	public void saveOrUpdate_FeedDoesNotExist_Test(){
		assertFalse(dao_.saveOrUpdate(comment_));
	}
	
	@Test
	public void saveOrUpdate_CommentIsNull_Test(){
		assertFalse(dao_.saveOrUpdate(null));
	}
	
	@Test
	public void saveCommentList_FeedsExistForComments_Test(){
		Feed feed = new Feed(feedId_, "1");
		FeedDao feedDao = new FeedDao();
		feedDao.saveOrUpdate(feed);
		Comment c1 = new Comment("A new comment id", message_, feedId_);
		Comment c2 = new Comment("Another comment id", message_, feedId_);
		comments_.addComment(c1);
		comments_.addComment(c2);
		boolean saveIsOk = dao_.saveCommentList(comments_);
		dao_.deleteCommentList(comments_);
		feedDao.delete(feed);
		assertTrue(saveIsOk);
	}
	
	@Test
	public void saveCommentList_FeedsDoNotExistForComments_Test(){
		Comment c1 = new Comment("A new comment id", message_, feedId_+"Somethis more");
		Comment c2 = new Comment("Another comment id", message_, feedId_+"Somethis more");
		comments_.addComment(c1);
		comments_.addComment(c2);
		boolean saveIsOk = dao_.saveCommentList(comments_);
		assertFalse(saveIsOk);
	}
	
	@Test
	public void saveCommentList_FeedExistForOneCommentButNotForTheOthers_Test(){
		Feed feed = new Feed(feedId_, "1");
		FeedDao feedDao = new FeedDao();
		feedDao.saveOrUpdate(feed);
		Comment c1 = new Comment("A new comment id", message_, feedId_);
		Comment c2 = new Comment("Another comment id", message_, feedId_+"Something more");
		comments_.addComment(c1);
		comments_.addComment(c2);
		boolean saveIsOk = dao_.saveCommentList(comments_);
		dao_.deleteCommentList(comments_);
		feedDao.delete(feed);
		assertFalse(saveIsOk);
	}
	
	
}
