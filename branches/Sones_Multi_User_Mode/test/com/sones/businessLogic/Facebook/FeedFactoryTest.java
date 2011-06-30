package com.sones.businessLogic.Facebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testingUtilities.FeedReader;
import static org.junit.Assert.*;

public class FeedFactoryTest {
	
	private FeedReader reader;
	private FacebookJsonHandler handler;
	
	@Before
	public void setUp(){
		reader=new FeedReader();
		handler=new FacebookJsonHandler();
	}
	
	@After
	public void tearDown(){
		reader=null;
		handler=null;
	}
	
	@Test
	public void getComment_ThereAreComments_Test(){
		handler.getAllComments(reader.getFacebookWall());
	}
}
