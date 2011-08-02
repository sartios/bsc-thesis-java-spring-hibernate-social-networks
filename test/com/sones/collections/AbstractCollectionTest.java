package com.sones.collections;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sones.businessLogic.Facebook.Feed;

import static org.junit.Assert.*;

public class AbstractCollectionTest {

	@Test
	public void listContainsFeed_Test(){
		List<Object> objects = new ArrayList<Object>();
		Feed feed = new Feed("1","2");
		objects.add(feed);
		assertTrue(objects.contains(new Feed("1","2")));
	}
	
	@Test
	public void listContainsFeed1_Test(){
		List<Object> objects = new ArrayList<Object>();
		Feed feed = new Feed("1","2");
		objects.add(feed);
		assertFalse(objects.contains(new Feed("2","3")));
	}
}
