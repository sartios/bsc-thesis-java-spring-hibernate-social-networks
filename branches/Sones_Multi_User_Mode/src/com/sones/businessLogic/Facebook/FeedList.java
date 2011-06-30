package com.sones.businessLogic.Facebook;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is keeping a collection of feeds from a specific user.
 * @author Sartios
 *
 */
public class FeedList {

	/**
	 * The ID of the user from which we will download feeds
	 */
	private String userID_;
	
	/**
	 * The collection of the user's feeds
	 */
	private Set<Feed> feeds_ = new HashSet<Feed>();
	
	/**
	 * No arguments constructor
	 */
	public FeedList(){
		userID_ = new String();
		feeds_ =  new HashSet<Feed>();
	}
	
	/**
	 * Constructor which sets the user and his feeds
	 * @param userID
	 * @param feeds of the specific user
	 */
	public FeedList(final String userID,final Set<Feed> feeds){
		userID_ = userID;
		feeds_ = feeds;
	}

	/**
	 * Returns the user's ID
	 * 
	 * @return
	 */
	public String getUserID() {
		return userID_;
	}

	/**
	 * Sets the user's ID from which we downloaded the feeds
	 * @param userID
	 */
	public void setUserID(String userID) {
		userID_ = userID;
	}

	/**
	 * Returns the collection of feeds
	 * @return set of feeds
	 */
	public Set<Feed> getFeeds_() {
		return feeds_;
	}

	/**
	 * Initialize the collection of feeds to the given set of feeds.
	 * @param feeds
	 */
	public void setFeeds(Set<Feed> feeds) {
		feeds_ = feeds;
	}
	
	/**
	 * Adds a feed into our collection of feeds
	 * @param feed to add
	 * @return true if was successfully added
	 */
	public boolean setFeed(final Feed feed){
		if(null==feed){
			return false;
		}
		return feeds_.add(feed);
	}
	
	/**
	 * Returns the ID of specific feed. The feed is specified by its index
	 * @param index of feed
	 * @return feed's ID
	 */
	public String getFeedID(final int index){
		if(index<feeds_.size()){
			return ((Feed)(feeds_.toArray())[index]).getId_();
		}
		return null;
	}
	
	
	/**
	 * Returns a feed specified by its index
	 * @param index of the feed
	 * @return feed
	 */
	public Feed getFeed(final int index){
		if(index<feeds_.size()){
			return ((Feed)(feeds_.toArray())[index]);
		}
		return null;
	}
	
	/**
	 * Returns the size of the internal collection
	 * @return size of collection
	 */
	public int getSize(){
		return feeds_.size();
	}
	
	/**
	 * Returns true if the feed exists into our set
	 * @param feed
	 * @return true if exists
	 */
	public boolean contains(final Feed feed){
		if(null!=feeds_){
			return feeds_.contains(feed);
		}
		return false;
	}
	
	/**
	 * Returns a set which contains all the feeds' ID.
	 * @return set of feeds' ID
	 */
	public Set<String> getIdFromFeeds(){
		Set<String> ids = new HashSet<String>();
		for(int i=0;i<this.getSize();i++){
			ids.add(this.getFeedID(i));
		}
		return ids;
	}
	
	/**
	 * Deletes the specified feed from our set
	 * @param feedToDelete
	 * @return true if the deletion was successfull
	 */
	public boolean deleteFeed(final Feed feedToDelete){
		return feeds_.remove(feedToDelete);
	}
	
	/**
	 * Returns the most recent creation time of feed which is the 1st feed of the set.
	 */
	public String getMostRecentCreationTime(){
		if(null!=feeds_){
			Feed feed = (Feed) (feeds_.toArray())[this.getSize()-1];
			return feed.getCreatedTime_();
		}
		return null;
	}
	
	/**
	 * Returns true if there aren't feeds into the set
	 * @return true if the set is empty
	 */
	public boolean isEmpty(){
		return this.feeds_.isEmpty();
	}
	
	/**
	 * Clears the set of feeds that we keep
	 */
	public void clearFeeds(){
		this.feeds_.clear();
		this.userID_="";
	}
	
	/**
	 * The newer feed of the list is the last of our set. So if there are feed in our list
	 * we return the newer. Else we return null.
	 * 
	 * @return the newer feed of the list.
	 */
	public Feed getNewerFeed(){
		int size = this.getSize();
		if(0<size){
			return (Feed) ((this.feeds_.toArray())[size-1]);
		}
		return null;
	}
	
	/**
	 * Return the feed in which belongs the ID
	 * @param ID of the feed
	 * @return feed
	 */
	public Feed getFeedByID(final String id){
		Feed feed=null;
		if(null!=id){
			for(int i=0;i<this.getSize();i++){
				feed=this.getFeed(i);
				if(feed.getId_().equals(id)){
					return feed;
				}
			}
		}
		return feed;
	}
}