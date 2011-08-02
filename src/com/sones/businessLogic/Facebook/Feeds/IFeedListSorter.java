package com.sones.businessLogic.Facebook.Feeds;

import java.util.List;
import java.util.Set;

import com.sones.businessLogic.Facebook.Feed;


public interface IFeedListSorter {

	public List getSortedFeedListNewestFirst(final List<Feed> feeds);
	public List getSortedFeedListOldestFirst(final List<Feed> feeds);

}
