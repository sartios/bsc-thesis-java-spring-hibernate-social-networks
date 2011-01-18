package com.sones.businessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookRestHandler;

import static org.junit.Assert.*;

public class SearchManagerTest {
	
	private FeedList 	feeds_;
	private KeywordList keywords_;
	
	@Before
	public void setUp(){
		feeds_ = new FeedList();
		keywords_ = new KeywordList();
		initialize();
	}
	
	@After
	public void tearDown(){
		feeds_=null;
		keywords_=null;
	}
	
	private void initialize(){
		Feed[] myFeeds = new Feed[4];
		Keyword[] keywords = new Keyword[4];
		FacebookFriend FROM = new FacebookFriend("Sartios", "123456");
		myFeeds[0] = new Status("1", FROM, "02", "Hello world, my name is the Sartios");
		myFeeds[1] = new Status("2", FROM, "02", "I like to move it the move it.");
		myFeeds[2] = new Status("3", FROM, "02", "The Facebook API allows web developers to create Facebook applications and access Facebook data from other applications. ");
		myFeeds[3] = new Status("4", FROM, "02", "Come my the lady, you are my butterfly");
		keywords[0] = new Keyword("world");
		keywords[1] = new Keyword("my");
		keywords[2] = new Keyword("the");
		keywords[3] = new Keyword("Aris");

		for(int i=0;i<4;i++){
			keywords_.addKeyword(keywords[i]);
			feeds_.addFeed(myFeeds[i]);
		}
	}

	/**
	 * Test the results of search for the keyword world
	 */
	@Test
	public void search_myAsKeyword_Test(){
		SearchingManager manager = new SearchingManager(feeds_,keywords_);
		ResultList results = manager.search();
		List<String> ids = results.getIDs("my");
		assertEquals(ids.size(),2);
	}
	
	/**
	 * Test the results of search for the keyword world
	 */
	@Test
	public void search_theAsKeyword_Test(){
		SearchingManager manager = new SearchingManager(feeds_,keywords_);
		ResultList results = manager.search();
		List<String> ids = results.getIDs("the");
		assertEquals(ids.size(),4);
	}
	
	/**
	 * Test the results of search for the keyword world
	 */
	@Test
	public void search_ArisAsKeyword_Test(){
		SearchingManager manager = new SearchingManager(feeds_,keywords_);
		ResultList results = manager.search();
		List<String> ids = results.getIDs("Aris");
		assertEquals(ids.size(),0);
	}
	
	/**
	 * Test the results of search for the keyword world
	 */
	@Test
	public void search_worldAsKeyword_Test(){
		SearchingManager manager = new SearchingManager(feeds_,keywords_);
		ResultList results = manager.search();
		List<String> ids = results.getIDs("world");
		assertEquals(ids.size(),1);
	}
	/**
	 * Test the results of search for the keyword world
	 */
	@Test
	public void searchNew_myAsKeyword_Test(){
		SearchingManager manager = new SearchingManager(feeds_,keywords_);
		KeywordList results = manager.search_new();
		List<String> ids = results.getKeyword(1).getIDs();
		assertEquals(ids.size(),2);

	}
	
	/**
	 * Test the results of search for the keyword world
	 */
	@Test
	public void searchNew_theAsKeyword_Test(){
		SearchingManager manager = new SearchingManager(feeds_,keywords_);
		KeywordList results = manager.search_new();
		List<String> ids = results.getKeyword(2).getIDs();
		assertEquals(ids.size(),4);
	}
	
	/**
	 * Test the results of search for the keyword world
	 */
	@Test
	public void searchNew_ArisAsKeyword_Test(){
		SearchingManager manager = new SearchingManager(feeds_,keywords_);
		KeywordList results = manager.search_new();
		List<String> ids = results.getKeyword(3).getIDs();
		assertEquals(ids.size(),0);
	}
	
	/**
	 * Test the results of search for the keyword world
	 */
	@Test
	public void searchNew_worldAsKeyword_Test(){
		SearchingManager manager = new SearchingManager(feeds_,keywords_);
		KeywordList results = manager.search_new();
		List<String> ids = results.getKeyword(0).getIDs();
		assertEquals(ids.size(),1);
	}
	
	/**
	 * Search online
	 */
	@Test
	public void searchAndToComments_Online_Test(){
		String ID = "100000866964787";
		String TOKEN = new String("access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y");
		
		FeedList feeds = new FacebookRestHandler().getWall(ID, TOKEN);
		KeywordList keywords = new KeywordList();
		keywords.addKeyword(new Keyword("κόφι"));
		SearchingManager searcher = new SearchingManager(feeds, keywords);
		assertTrue(searcher.search().getIDs("κόφι")!=null);
	}
	
	/**
	 * Search for feeds with a specified comment number
	 */
	@Test
	public void searchForFeedsWithCommentNumber(){
		String ID = "100000866964787";
		String TOKEN = new String("access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y");
		FeedList feeds = new FacebookRestHandler().getWall(ID, TOKEN);
		SearchingManager searcher = new SearchingManager(feeds);
		assertEquals(searcher.getFeedsWithCommentNumber(5).size(),1);
	}
}
