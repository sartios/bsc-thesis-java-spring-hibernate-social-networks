package com.sones.businessLogic.Facebook;

import java.util.ArrayList;
import java.util.List;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedList;
import com.sones.dao.FeedDao;
import com.sones.dao.TokenDao;

/**
 * This class is responsible for providing the end user with the data she wants 
 * @author sartios
 *
 */
public class FacebookDataManager {

	/**
	 * Collects the feeds from a specified list and returns them.
	 * The list includes IDs that may be from Friends, Groups, Pages etc.
	 * @param sources list
	 * @return feed list
	 */
	public FeedList getFeedsFrom(final FacebookSearchingList sources, final String TOKEN){
		String currentID;
		FeedList list = new FeedList();
		FacebookRestHandler handler = new FacebookRestHandler();
		while(null!=(currentID=sources.getID())){
			list.append(handler.getWall(currentID, TOKEN));
		}
		return list;
	}
	
	/**
	 * Downloads every window_time the feeds. Loads the feeds that already exist
	 * into the database and compares the comment number. If the diff between comment
	 * numbers is >= to a given bound return the feeds.
	 * 
	 * @param window_size how much to wait until it searches
	 * @param bound the lower bound of comment number
	 * @return FeedList
	 */
	public FeedList getHotFeeds(final FacebookSearchingList sources){
		Feed feed;
		Feed dbFeed;
		TokenDao tokenDao = new TokenDao();
		FacebookToken token = (FacebookToken) tokenDao.findAll().get(0);
		FeedList newList = getFeedsFrom(sources, token.getToken());
		FeedList	savedList = new FeedDao().findAll();
		
		while(null!=(feed = newList.getFeed() )){
			for(int i=0;i<0;i++){
				if(feed.getID()==null){
					
				}
			}
		}
		return newList;
	}
}
