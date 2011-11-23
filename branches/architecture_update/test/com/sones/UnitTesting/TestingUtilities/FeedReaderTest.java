package com.sones.UnitTesting.TestingUtilities;

import org.junit.Test;

import testingUtilities.FeedReader;
import static org.junit.Assert.*;

public class FeedReaderTest {

	@Test
	public void getFacebookStatusMessage_Test(){
		FeedReader reader = new FeedReader();
		assertFalse(reader.getFacebookStatusMessage().isEmpty());
	}
	
	@Test
	public void getWallWithMoreComments_Test(){
		FeedReader reader = new FeedReader();
		assertFalse(reader.getWallWithMoreComments().isEmpty());
	}
}
