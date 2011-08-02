package com.sones.businessLogic.Facebook.Feeds;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testingUtilities.FeedReader;

import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.FeedListUsingList;
import com.sones.businessLogic.Facebook.StatusMessage;
import com.sones.businessLogic.Facebook.Video;
import com.sones.businessLogic.Facebook.Feeds.QuickSortFeedListSorter;
import com.sones.businessLogic.Facebook.JSON.FacebookJSONHandlerFactory;
import com.sones.businessLogic.Facebook.JSON.FacebookJsonHandler;
import com.sones.businessLogic.Facebook.JSON.IFacebookJsonHandler;
import com.sones.utilities.DateConverter;

import static org.junit.Assert.*;

public class FacebookFeedListTest {

	private FacebookFeedList feeds;
	private Feed feed;
	
	@Before
	public void setUp(){
		feed = new Feed("1", "2", 0);
		feeds=new FacebookFeedList();
		feeds.setFeed(feed);
	}
	
	@After
	public void tearDown(){
		feeds=null;
	}
	
	@Test
	public void setUserID_UserIDIsValid_GetUserID_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		String userID = new String("10005512151");
		feeds.setUserID(userID);
		assertEquals(feeds.getUserID(), userID);
	}
	
	@Test
	public void setUserID_UserIDIsNull_GetEmptyUserID_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		feeds.setUserID(null);
		assertEquals(feeds.getUserID(), "");
	}
	
	@Test
	public void setFeed_FeedIsValidAndListIsEmpty_GetFeed_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		Feed feed = this.getFeed("1", "2");
		feeds.setFeed(feed);
		assertEquals(feeds.getFeed(0), feed);
	}
	
	@Test
	public void setFeed_FeedIsNulldAndListIsEmpty_GetNull_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		feeds.setFeed(null);
		assertEquals(feeds.getFeed(0), null);
	}
	
	@Test
	public void setFeeds_SetOfFeedsIsValid_GetSameNumberOfFeeds_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		Set<Feed> feedSet = this.getSetOfFeeds();
		feeds.setFeeds(feedSet);
		assertTrue(feeds.getFeeds_().size()==feedSet.size());
	}
	
	@Test
	public void setFeeds_SetOfFeedsIsNull_GetZeroNumberOfFeeds_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		//feeds.setFeeds(null);
		assertTrue(feeds.getFeeds_().size()==0);
	}
	
	@Test
	public void setFeeds_SetOfFeedsIsValidTheFeedListContainsOneFeed_GetNumberOfFeedsPlusOne_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		feeds.setFeed(getFeed("34", "2"));
		Set<Feed> feedSet = this.getSetOfFeeds();
		feeds.setFeeds(feedSet);
		assertTrue(feeds.getFeeds_().size()==feedSet.size()+1);
	}
	
	@Test
	public void getFeedByID_FeedExists_Test(){
		feeds.setFeed(new Feed("2", "2", 0));
		feeds.setFeed(new Feed("3", "2", 0));
		feeds.setFeed(new Feed("4", "5", 0));
		feeds.setFeed(new Feed("5", "2", 0));
		assertEquals(feeds.getFeedByID("4").getFromId_(), "5");
	}
	
	@Test
	public void getFeedID_ExistOneFeed_GetItsID_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		Feed feed = this.getFeed("1","2");
		feeds.setFeed(feed);
		assertEquals(feeds.getFeedID(0), feed.getId_());
	}
	
	@Test
	public void getFeedID_ExistOneFeed_IndexOutOfBound_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		Feed feed = this.getFeed("1","2");
		feeds.setFeed(feed);
		assertEquals(feeds.getFeedID(2), null);
	}
	
	@Test
	public void getFeedID_FeedListIsEmpty_GetItsID_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		assertEquals(feeds.getFeedID(0), null);
	}
	
	@Test
	public void getFeed_ExistOneFeed_IndexOutOfBound_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		Feed feed = this.getFeed("1","2");
		feeds.setFeed(feed);
		assertEquals(feeds.getFeed(2), null);
	}
	
	@Test
	public void contains_FeedExistsIntoList_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		Feed feed = this.getFeed("1","2");
		feeds.setFeed(feed);
		assertTrue(feeds.contains(feed));
	}
	
	@Test
	public void contains_FeedDoesNotExistIntoList_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		Feed feed = this.getFeed("1","2");
		assertFalse(feeds.contains(feed));
	}
	
	@Test
	public void getIdFromFeeds_ListContainsFeeds_GetCorrectIDs_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		feeds.setFeeds(this.getSetOfFeeds());
		int numberOfFeeds = feeds.getSize();
		assertEquals(numberOfFeeds,3);
	}
	
	@Test
	public void getIdFromFeeds_ListDoesNotContainFeeds_GetCorrectIDs_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		feeds.setFeeds(this.getEmptySetOfFeeds());
		int numberOfFeeds = feeds.getSize();
		assertEquals(numberOfFeeds,0);
	}
	
	@Test
	public void deleteFeed_FeedExists_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		Feed feed=this.getFeed("1", "2");
		feeds.setFeed(feed);
		feeds.deleteFeed(feed);
		assertFalse(feeds.contains(feed));
	}
	
	@Test
	public void deleteFeed_FeedDoesNotExist_Test(){
		FacebookFeedList feeds = new FacebookFeedList();
		feeds.setFeed(this.getFeed("1", "2"));
		assertFalse(feeds.deleteFeed(this.getFeed("2", "2")));
	}
	
	@Test
	public void getMostRecentCreationTime_ThereAreFeedsIntoTheList_GetCorrectDate_Test(){
		FeedListUsingList feeds=new FeedListUsingList();
		feeds.setFeeds(getFeedListWithRealFeeds().getFeeds_());
		feeds.setSorter(new QuickSortFeedListSorter());
		DateConverter dateConverter = new DateConverter();
		long date=dateConverter.getUnixTimestamp("2011-06-17T19:02:36+0000");
		assertEquals(date, Long.parseLong(feeds.getMostRecentCreationTime()));
	}
	
	@Test
	public void getMostRecentCreationTime_ThereAreNotFeedsIntoTheList_GetNull_Test(){
		FeedListUsingList feeds=new FeedListUsingList();
		feeds.setSorter(new QuickSortFeedListSorter());
		long zero=0;
		assertEquals(zero, Long.parseLong(feeds.getMostRecentCreationTime()));
	}
	
	@Test
	public void setFeedsFromSet_Test(){
		FeedListUsingList feeds = new FeedListUsingList();
		feeds.setFeeds(this.getSetOfFeeds());
		assertTrue(feeds.getSize()==3);
	}
	
	@Test
	public void getFeedsThatContainTheKeyword_KeywordExistButNotInComments_Test(){
		FacebookFeedList feeds = this.getFeedListWithRealFeeds();
		assertTrue(feeds.getFeedsThatContainTheKeyword("youtube").size()>0);
	}
	
	private Set<Feed> getSetOfFeeds(){
		Set<Feed> feedSet = new HashSet<Feed>();
		feedSet.add(getFeed("1", "2"));
		feedSet.add(getFeed("2", "3"));
		feedSet.add(getFeed("3", "4"));
		return feedSet;
	}
	
	private Feed getFeed(final String feedID,final String userID){
		return new Feed(feedID,userID);
	}
	
	private Set<Feed> getEmptySetOfFeeds(){
		return new HashSet<Feed>();
	}
	
	private FacebookFeedList getFeedListWithRealFeeds(){
		FeedReader reader = new FeedReader();
		IFacebookJsonHandler handler = FacebookJSONHandlerFactory.getInstance().getFacebookJSONHandler("complete");
		FacebookFeedList feeds=new FacebookFeedList();
		feeds.setFeeds(handler.getFeeds(reader.getFacebookWall()));
		return feeds;
	}
	
	
	private Feed getRealFeed(String feedID,String userID,String creationTime){
		Feed feed = new Feed();
		feed.setId_(feedID);
		feed.setFromId_(userID);
		feed.setCreatedTime_(creationTime);
		return feed;
	}
}
