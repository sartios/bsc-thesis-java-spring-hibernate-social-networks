package com.sones.businessLogic.Facebook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * This object contains the feeds that have different 
 * comment number between 2 moments.
 * @author Sartios
 *
 */
public class FeedsWithCommentTraffic {
	
	/**
	 * The id of each table entry. It represents the primary key
	 */
	private long id;
	/**
	 * The map that contains the feed and the distance of the comments
	 */
	private Map<String, String> feeds;
	
	/**
	 * This is the date dd/mm/yyyy hh:mm:ss in which the list created
 	 */
	private String date;
	
	public FeedsWithCommentTraffic(){
	}
	
	public FeedsWithCommentTraffic(final boolean doYouWantToSetTheCurrentDate){
		this.initDate();
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public Map<String, String> getFeeds() {
		return feeds;
	}

	public void setFeeds(final Map<String, String> feeds) {
		this.feeds = feeds;
	}
	
	/**
	 * It set's the only if it's empty
	 * @param date
	 */
	public void setDate(final String date){
		//if(this.date.isEmpty()){
			this.date = date;
		//}
	}
	
	public String getDate(){
		return this.date;
	}
	
	/**
	 * Sets the date into the current date.
	 */
	private void initDate(){
		final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    date=sdf.format(calendar.getTime());
	}
}
