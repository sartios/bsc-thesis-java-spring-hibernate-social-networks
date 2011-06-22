package com.sones.businessLogic.DatabaseCleaner;

import java.util.Calendar;

import com.sones.businessLogic.Facebook.FeedList;
import com.sones.dao.FeedDao;

/**
 * This class is responsible for cleaning the
 * feeds which aren't associated with keywords and 
 * was created before a time window.
 * 
 * @author Sartios
 *
 */
public class FeedCleaner {
	
	/**
	 * The time window in which we want to clean the feeds
	 */
	private CleaningWindow timeWindow_;
	private FeedDao  dao;
	
	/**
	 * Sets the {@link #timeWindow_} to zero
	 */
	public FeedCleaner(){
		dao = new FeedDao();
		timeWindow_ = new CleaningWindow(0, 0);
	}
	
	/**
	 * Sets the {@link #timeWindow_} days and months distances
	 * @param daysDistance represent how many days old will be the last feed
	 * @param monthsDistance represent how many months old will be the last feed
	 */
	public FeedCleaner(final int daysDistance,final int monthsDistance){
		dao = new FeedDao();
		timeWindow_ = new CleaningWindow(daysDistance, monthsDistance);
	}
	
	
	/**
	 * Finds the feeds that are inside the time window limits and if there are 
	 * not associations with keywords, deletes them.
	 */
	public void cleanDatabase(){
		
		String startingDate = timeWindow_.getStartingDate();
		String endingDate = timeWindow_.getEndingDate();

		FeedList feeds = dao.findFeedsBetween(startingDate, endingDate);
		dao.deleteFeedList(feeds);
	}
}
