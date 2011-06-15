package com.sones.businessLogic.Facebook;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;

public class VideoTest {

	private Video video_;
	private String caption_;
	private String userId_;
	private String feedId_;
	
	@Before
	public void setUp(){
		video_=new  Video();
		caption_=new String("Video caption");
		userId_=new String("User ID");
		feedId_=new String("Feed ID");
	}
	
	@After
	public void tearDown(){
		video_=null;
	}
	
	@Test
	public void constructorWithNoArguments_CreatesUsefullVideo_Test(){
		boolean isThrowing = false;
		try{
			video_.getCaption().isEmpty();
		}
		catch (NullPointerException e) {
			isThrowing=true;
		}
		assertFalse(isThrowing);
	}
	
	@Test
	public void constructoWithArgs_CreatesCorrectVideo_Test(){
		Video video = new Video(caption_,feedId_,userId_);
		assertEquals(video.getId_(), feedId_);
		assertEquals(video.getFromId_(), userId_);
		assertEquals(video.getCaption(), caption_);
	}
	
	@Test
	public void setCaption_NormalMessage_Test(){
		video_.setCaption(caption_);
		assertEquals(video_.getCaption(), caption_);
	}
	
	@Test
	public void setMessage_NullMessage_Test(){
		video_.setCaption(null);
		assertTrue(video_.getCaption().isEmpty());
	}
	
	@Test
	public void find_KeywordInSameCase_Test(){
		String keyword="keyword";
		video_.setCaption(keyword);
		assertTrue(video_.find(keyword));
	}
	
	@Test
	public void find_KeywordHasSpace_Test(){
		String keyword="keyword";
		video_.setCaption(keyword+" ");
		assertTrue(video_.find(keyword));
	}
	
	@Test
	public void find_KeywordInUpperCase_Test(){
		String keyword="keyword";
		video_.setCaption("KEYWORD");
		assertTrue(video_.find(keyword));
	}
	
	@Test
	public void find_KeywordIsMixCase_Test(){
		String keyword="kEyWoRd";
		video_.setCaption("keyword");
		assertTrue(video_.find(keyword));
		
	}
}
