package com.sones.businessLogic.Facebook.Source;

import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;
import com.sones.businessLogic.Facebook.Feeds.FacebookFeedListFactory;
import com.sones.businessLogic.Facebook.Rest.IFacebookRestHandler;
import com.sones.businessLogic.Facebook.Rest.FacebookRestHandlerFactory;

/**
 * This class represents a facebook user which he has create feeds into facebook
 * and he can get them back.
 * 
 * @author Sartios
 *
 */
public class FacebookFriend implements ISource{
	
	/**
	 * Friend's name
	 */
	private String name;

	
	/**
	 * Friend's ID
	 */
	private String id;
	
	/**
	 * This is the creation date of the newest feed which user uploaded
	 */
	private String dateOfNewestFeed="0";
	
	/**
	 * Facebook rest handler, which implements the graph API
	 */
	private IFacebookRestHandler facebookRestHandler;
	
	/**
	 * Constructor with no arguments for Hibernate purposes
	 */
	public FacebookFriend(){}
	
	/**
	 * Constructor with arguments
	 * @param name of facebook friend
	 * @param id of facebook friend
	 */
	public FacebookFriend(final String name,final String id){
		this.name = name;
		this.id = id;
	}
	/**
	 * Set facebook friend name
	 * @param name
	 */
	public void setName(final String name) {
		if(null!=name){
			this.name = name;
		}
		else{
			this.name="";
		}
	}

	/**
	 * Set facebook friend ID
	 * @param id
	 */
	public void setId(final String id) {
		if(null!=id){
			this.id = id;
		}
		else{
			this.id="";
		}	}
	
	/**
	 * Get facebook friend's name
	 * @return facebook friend's name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Get facebook friend's ID
	 * 
	 * @return facebook friend's ID
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * Set the date of newest user's feed
	 * @param dateOfNewestFeed
	 */
	public void setDateOfNewestFeed(final String dateOfNewestFeed) {
		this.dateOfNewestFeed = dateOfNewestFeed;
	}
	
	
	/**
	 * Set the date of newest user's feed
	 * @param dateOfNewestFeed
	 */
	public void setDateOfNewestFeedFromFeedList(final FacebookFeedList feeds) {
		this.dateOfNewestFeed = feeds.getMostRecentCreationTime();
	}

	/**
	 * Get the date of newest user's feed
	 * @return {@link #dateOfNewestFeed}
	 */
	public String getDateOfNewestFeed() {
		return dateOfNewestFeed;
	}
	
	/**
	 * Set facebook rest handler
	 * 
	 * @param restHandler
	 */
	public void setFacebookRestHandler(final String restHandlerType) {
		if(null!=restHandlerType){
			this.facebookRestHandler = FacebookRestHandlerFactory.getInstance().getFacebookRestHandler(restHandlerType);
		}
	}

	/**
	 * Get facebook rest handler
	 * @return facebook rest handler
	 */
	public IFacebookRestHandler getFacebookRestHandler() {
		return facebookRestHandler;
	}

	/**
	 * Downloads from facebook his feeds and returns them
	 */
	@Override
	public FacebookFeedList getFeeds(final String token){
		FacebookFeedList feeds=null;
		if(token!=null){
			feeds=facebookRestHandler.getFeedsSinceDate(this.getId(), token, this.getDateOfNewestFeed());
			this.setDateOfNewestFeed(feeds.getMostRecentCreationTime());
		}
		return feeds;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object o){
		if(this==o){
			return true;
		}
		if(!(o instanceof FacebookFriend)){
			return false;
		}
		FacebookFriend friend = (FacebookFriend) o;
		return ((this.id.equals(friend.getId()))&&(this.name.equals(friend.getName())));
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode(){
		return	this.id.hashCode()+this.name.hashCode();
	}

	@Override
	public FacebookFeedList getFeeds(final String typeOfList,final String token) {
		if((null!=typeOfList)&&(null!=token)){
			return FacebookFeedListFactory.getInstance().getFeedList(typeOfList, this.id, token, this.dateOfNewestFeed);
		}
		return null;
	}

}
