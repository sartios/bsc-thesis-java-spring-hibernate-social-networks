package com.sones.businessLogic.Facebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PictureTest {
	
	private Feed picture_;
	private String userId_;
	private String feedId_;
	private String caption_;
	private String url_;
	private String message_;
	
	@Before
	public void setUp(){
		picture_ = new Picture();
		userId_="User ID";
		feedId_="Feed ID";
		caption_="Picture caption";
		url_="Picture url";
		message_="Status message";
	}
	
	@After
	public void tearDown(){
		picture_=null;
	}
	
	@Test
	public void constructorWithNoArgs_CreatesUsefullPicture_Test(){
		boolean isNotThrowing = true;
		try{
			((Picture) picture_).getMessage().isEmpty();
			((Picture) picture_).getCaption().isEmpty();
			((Picture) picture_).getUrl().isEmpty();
		}
		catch(NullPointerException ex){
			isNotThrowing=false;
		}
		assertTrue(isNotThrowing);
	}
	
	@Test
	public void constructoWithArgs_CreatesCorrectFeed_Test(){
		Picture picture = new Picture(caption_, feedId_, userId_);
		assertEquals(picture.getId_(), feedId_);
		assertEquals(picture.getFromId_(), userId_);
		assertEquals(picture.getCaption(), caption_);
	}
	
	@Test
	public void setMessage_NormalMessage_Test(){
		((Picture) picture_).setMessage(message_);
		assertEquals(((Picture) picture_).getMessage(), message_);
	}
	
	@Test
	public void setMessage_NullMessage_Test(){
		((Picture) picture_).setMessage(null);
		assertTrue(((Picture) picture_).getMessage().isEmpty());
	}
	
	@Test
	public void setUrl_NormalMessage_Test(){
		((Picture) picture_).setUrl(url_);
		assertEquals(((Picture) picture_).getUrl(), url_);
	}
	
	@Test
	public void setUrl_NullMessage_Test(){
		((Picture) picture_).setUrl(null);
		assertTrue(((Picture) picture_).getUrl().isEmpty());
	}
	
	@Test
	public void setCaption_NormalMessage_Test(){
		((Picture) picture_).setCaption(caption_);
		assertEquals(((Picture) picture_).getCaption(), caption_);
	}
	
	@Test
	public void setCaption_NullMessage_Test(){
		((Picture) picture_).setCaption(null);
		assertTrue(((Picture) picture_).getCaption().isEmpty());
	}
	
	@Test
	public void find_KeywordInSameCase_Test(){
		String keyword="keyword";
		((Picture) picture_).setMessage(keyword);
		assertTrue(picture_.find(keyword));
	}
	
	@Test
	public void find_KeywordHasSpace_Test(){
		String keyword="keyword";
		((Picture) picture_).setMessage(keyword+" ");
		assertTrue( picture_.find(keyword));
	}
	
	@Test
	public void find_KeywordInUpperCase_Test(){
		String keyword="keyword";
		((Picture) picture_).setMessage("KEYWORD");
		assertTrue( picture_.find(keyword));
	}
	
	@Test
	public void find_KeywordIsMixCase_Test(){
		String keyword="kEyWoRd";
		((Picture) picture_).setMessage("keyword");
		assertTrue( picture_.find(keyword));
	}
	
	@Test
	public void find_keywordExists_Test(){
		CommentList comments = new CommentList();
		Comment comment = new Comment("1","keyword","2");
		comments.addComment(comment);
		picture_.setComments_(comments);
		String keyword = "keyword";
		assertTrue(picture_.find(keyword));
	}
	
	@Test
	public void find_keywordDoesNotExist_Test(){
		CommentList comments = new CommentList();
		Comment comment = new Comment("1","keyword","2");
		comments.addComment(comment);
		picture_.setComments_(comments);
		String keyword = "ARIS";
		assertFalse(picture_.find(keyword));
	}
}
