package com.sones.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.Keyword;

public class KeywordDaoTest {

	@Test
	public void saveOrUpdate_feedsExist_Test(){
		
		Feed feed1 = new Feed("1","2");
		Feed feed2 = new Feed("2","3");
		
		FeedDao feedDao = new FeedDao();
		feedDao.saveOrUpdate(feed1);
		feedDao.saveOrUpdate(feed2);
		
		Keyword keyword = new  Keyword("something");
		keyword.addID(feed1);
		keyword.addID(feed2);
		
		KeywordDao keywordDao = new KeywordDao();
		keywordDao.saveOrUpdate(keyword);

		assertNotNull(keywordDao.findAll());
	}
	
	@Test
	public void saveOrUpdate_feedsDontExist_Test(){
		Feed feedWhichDoesntExist = new Feed("18","3");
		Keyword keyword = new  Keyword("somethingElse");
		keyword.addID(feedWhichDoesntExist);
		KeywordDao keywordDao = new KeywordDao();
		keywordDao.saveOrUpdate(keyword);
		
		List keywordList = keywordDao.findAll();
		assertEquals(1, keywordDao.getKeywordWithFeeds("somethingElse").getIds().size());
	}
	
}
