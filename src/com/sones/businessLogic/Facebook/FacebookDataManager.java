package com.sones.businessLogic.Facebook;

import java.util.ArrayList;
import java.util.List;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedList;

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
}
