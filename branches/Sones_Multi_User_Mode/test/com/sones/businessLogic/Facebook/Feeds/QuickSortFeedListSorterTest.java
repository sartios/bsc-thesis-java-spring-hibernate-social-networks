package com.sones.businessLogic.Facebook.Feeds;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.sones.businessLogic.Facebook.FacebookJsonHandler;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.FeedListUsingList;
import com.sones.businessLogic.Facebook.Feeds.QuickSortFeedListSorter;

import testingUtilities.FeedReader;
import static org.junit.Assert.*;

public class QuickSortFeedListSorterTest {

	@Test
	public void sortNewestFirst_FeedListIsValid_GetASortedList_Test(){
		FeedReader reader = new FeedReader();
		FacebookJsonHandler handler = new FacebookJsonHandler();
		Set<Feed> feeds = handler.getFeeds(reader.getFacebookWall());
		List<Feed> list = getListFromSet(feeds);
		System.out.println(list.isEmpty());
		QuickSortFeedListSorter sorter = new QuickSortFeedListSorter();
		int size = feeds.size();
		int lo = 0;
		int hi = size-1;
		sorter.quickSortList(list, lo,hi);
		assertNotSame(list.get(0).getCreatedTime_(),list.get(1).getCreatedTime_());
	}
	
	private List<Feed> getListFromSet(final Set<Feed> set){
		List<Feed> list = new ArrayList<Feed>();
		for(int i=set.size()-1;i>=0;i--){
			list.add((Feed) (set.toArray())[i]);
		}
		return list;
	}
	
	@Test
	public void getSortedFeedListNewestFirst_FeedListIsNotSorted_GetSortedList_Test(){
		FeedReader reader = new FeedReader();
		FacebookJsonHandler handler = new FacebookJsonHandler();
		Set<Feed> feeds = handler.getFeeds(reader.getFacebookWall());
		QuickSortFeedListSorter sorter = new QuickSortFeedListSorter();
		List<Feed> list = getListFromSet(feeds);
		List<Feed> sortedFeeds = sorter.getSortedFeedListNewestFirst(list);
		int expectedNewestDate = Integer.parseInt(sortedFeeds.get(0).getCreatedTime_());
		int expectedOldestDate = Integer.parseInt(sortedFeeds.get(list.size()-1).getCreatedTime_());
		assertTrue((expectedNewestDate)>(expectedOldestDate));
	}
}
