package com.sones.dao;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FeedsForDisplayDaoTest {

	@Test
	public void save_Test(){
		Set<String> feeds = new HashSet<String>();
		feeds.add("1");
		feeds.add("2");
		
		FeedsForDisplayDao dao = new FeedsForDisplayDao();
		dao.saveFeeds(feeds, "keyword");
	}
}
