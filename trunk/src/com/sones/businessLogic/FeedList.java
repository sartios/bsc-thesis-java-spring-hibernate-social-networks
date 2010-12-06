package com.sones.businessLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * The feed list is an object which contains the feeds.
 * It provides the ability to insert, delete and get
 * feeds.
 * @author Sartios
 *
 */
public class FeedList {
	
	/**
	 * The list of feeds
	 */
	private List<Feed> feeds_;
	
	/**
	 * The index of the list
	 */
	private int index_;
	
	public FeedList(){
		index_=0;
		this.feeds_ = new  ArrayList<Feed>();
	}
	
	/**
	 * Insert a feed into the list
	 * @param feed
	 * @return true if feed's insertion is done
	 */
	public boolean addFeed(final Feed feed){
		if(feedExists(feed)){
			return false;
		}
		return this.feeds_.add(feed);
	}
	
	/**
	 * Check if the feed already exists into the list
	 * @param feed
	 * @return true if feed exists into the list
	 */
	private boolean feedExists(final Feed feed){
		if(null==feed){
			return true;
		}
		if(this.feeds_.isEmpty()){
			return false;
		}
		for(int i=0;i<this.feeds_.size();i++){
			if(feed.equals(feeds_.get(i))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return feed
	 */
	public Feed getFeed(){
		if(index_<feeds_.size()){
			return feeds_.get(index_++);
		}
		return null;
	}
	
	/**
	 * Deletes a feed from the list
	 * @param feed
	 * @return true if the feed exists and its deletion is done
	 */
	public boolean delete(final Feed feed){
		return feeds_.remove(feed);
	}
	
	/**
	 * Reset the index
	 */
	public void reset(){
		index_ = 0;
	}
	
	/**
	 * Append in the list new feeds
	 * @param feeds
	 * @return true
	 */
	public void append(final FeedList feeds){
		Feed feed;
		while(null!=(feed=feeds.getFeed())){
			feeds_.add(feed);
		}
	}
	
	public boolean isEmpty(){
		return feeds_.isEmpty();
	}
}
