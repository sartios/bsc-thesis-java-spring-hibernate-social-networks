package com.sones.businessLogic.KeywordManager;

import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;

/**
 * This class is represents the keyword
 * 
 * @author Sartios
 *
 */
public class Keyword {
	
	/**
	 * Keyword's ID
	 */
	private String keywordID_;
	
	/**
	 * Keyword's value
	 */
	private String value_;
	
	/**
	 * Facebook's feed list in which the keyword exists
	 */
	private FacebookFeedList feeds_;
	
	/**
	 * No args constructor
	 */
	public Keyword(){
		setKeywordID_(new String());
		setValue_(new String());
		setFeeds_(new FacebookFeedList());
	}
	
	/**
	 * Constructor which sets the keyword value
	 */
	public Keyword(final String value){
		setKeywordID_(new String());
		setValue_(value);
		setFeeds_(new FacebookFeedList());
	}

	/**
	 * Set the keyword's ID. Maybe in the future we need an ID generator.
	 * @param keywordID
	 */
	public void setKeywordID_(String keywordID) {
		if(null!=keywordID){
			this.keywordID_ = keywordID;
		}
	}

	/**
	 * @return keyword's ID
	 */
	public String getKeywordID_() {
		return keywordID_;
	}

	/**
	 * Sets the keyword's value.
	 * @param value
	 */
	public void setValue_(String value) {
		if(null!=value){
			this.value_ = value;
		}
	}

	/**
	 * @return keyword's value
	 */
	public String getValue_() {
		return value_;
	}

	/**
	 * Set the feeds that contain the keyword
	 * @param feeds
	 */
	public void setFeeds_(final FacebookFeedList feeds) {
		if(null!=feeds){
			this.feeds_ = feeds;
		}
	}

	/**
	 * @return feeds that contain the keyword
	 */
	public FacebookFeedList getFeeds_() {
		return feeds_;
	}
	
	/**
	 * Add a feed to the feed list
	 * @param feed to be added
	 * @return true if the addition was successful
	 */
	public boolean addFeed(final Feed feed){
			return feeds_.setFeed(feed);
	}
	
	/**
	 * Check if the ID and the Value of two keywords are same. And if they're then
	 * the keywords are same.
	 * @return true if the keywords are same
	 */
	public boolean equals(final Object obj){
		boolean objectsAreEqual = false;
		if ( this == obj ){
			return true;
		}
		if ( !(obj instanceof Keyword) ){
			return false;
		}
		Keyword that = (Keyword)obj;
		if(this.keywordIDIsEqual(that.getKeywordID_())){
			if(this.keywordValueIsEqual(that.getValue_())){
				objectsAreEqual=true;
			}
		}
		return objectsAreEqual;
	}
	
	/**
	 * Returns true if the keyword's {@link #keywordID_} is equal with the given ID
	 * @param id a given keyword ID
	 * @return true if the {@link #keywordID_} and the id are equal
	 */
	private boolean keywordIDIsEqual(final String id){
		return this.keywordID_.equals(id);
	}
	
	/**
	 * Returns true if the keyword's {@link #value_} is equal with the given value
	 * @param value a given keyword value
	 * @return true if the {@link #value_} and the value are equal
	 */
	private boolean keywordValueIsEqual(final String value){
		return this.value_.equals(value);
	}
	
}
