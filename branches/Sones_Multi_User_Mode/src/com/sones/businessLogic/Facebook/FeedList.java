package com.sones.businessLogic.Facebook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sones.businessLogic.Facebook.Feeds.IFeedListSorter;

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
	private List<Feed> feeds_ = new ArrayList<Feed>();
	
	/**
	 * This object sorts the list of feeds
	 */
	private IFeedListSorter sorter_;
	
	/**
	 * No arguments constructor
	 */
	public FeedList(){
		userID_ = new String();
		feeds_ =  new ArrayList<Feed>();
	}
	
	/**
	 * Constructor which sets the user and his feeds
	 * @param userID
	 * @param feeds of the specific user
	 */
	public FeedList(final String userID,final ArrayList<Feed> feeds){
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
		if(null!=userID){
			userID_ = userID;
		}
		else{
			userID_="";
		}
	}

	/**
	 * Returns the collection of feeds
	 * @return set of feeds
	 */
	public List getFeeds_() {
		return feeds_;
	}

	/**
	 * Initialize the collection of feeds to the given set of feeds.
	 * @param feeds
	 */
	public void setFeeds(final Set<Feed> feeds) {
		if(null!=feeds){
			for(int i=0;i<feeds.size();i++){
				feeds_.add((Feed)(feeds.toArray())[i]);
			}
		}
	}
	
	/**
	 * Initialize the collection of feeds to the given set of feeds.
	 * @param feeds
	 */
	public void setFeeds(List<Feed> feeds) {
		if(null!=feeds){
			this.feeds_ = feeds;
		}
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
		String zero="0";
		if(null!=feeds_){
			Feed feed = this.getNewerFeed();
			if(null!=feed){
				return feed.getCreatedTime_();
			}
		}
		return zero;
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
	 * To have the newer feed, we need to sort the list of feeds, by their creation time.
	 * So every time we want the newest feed, we run a sort and then we return the newest
	 * feed.
	 * 
	 * @return the newer feed of the list.
	 */
	public Feed getNewerFeed(){
		feeds_ = getSortedFeedListNewestFirst();
		if(null!=feeds_){
			return feeds_.get(0);
		}
		return null;
	}

	/**
	 * Sorts the feeds by setting the newest first
	 * 
	 * @return sorted list of feeds
	 */
	private List<Feed> getSortedFeedListNewestFirst() {
		if((null!=feeds_)&&(false==feeds_.isEmpty())){
			return sorter_.getSortedFeedListNewestFirst(this.feeds_);
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
	
	/**
	 * Set feeds from list
	 */
	public void setFeedsFromList(final List feeds){
		for(int i=0;i<feeds.size();i++){
			this.setFeed(((Feed)feeds.get(i)));
		}
	}

	/**
	 * Set the {@link #sorter_} of the list
	 * @param sorter
	 */
	public void setSorter(IFeedListSorter sorter) {
		if(null!=sorter){
			this.sorter_ = sorter;
		}
	}

	/**
	 * Return the {@link #sorter_} of the list
	 * @return sorter
	 */
	public IFeedListSorter getSorter() {
		return sorter_;
	}
}