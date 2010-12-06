package com.sones.businessLogic;

import com.sones.businessLogic.Facebook.FacebookFriend;

/**
 * Is the super class for feeds. Contains the general data that all the feeds contain.
 * This version doesn't implements the rules for the data.
 * @author Sartios
 * @version 1.0
 *
 */
public abstract class Feed {
	
	/**
	 * Feed's ID
	 */
	private final String ID_;
	
	/**
	 * The user who posted the feed
	 */
	private final FacebookFriend FROM_ ;
	
	/**assertEquals
	 * The time that feed was published
	 */
	private final String CREATED_TIME_;
	
	/**
	 * Constructor
	 * @param ID
	 * @param FROM
	 * @param CREATED_TIME
	 * @param TYPE
	 */
	public Feed(final String ID, final FacebookFriend FROM,final String CREATED_TIME){
		this.ID_ = ID;
		this.FROM_= FROM;
		this.CREATED_TIME_ = CREATED_TIME;
	}
	
	/**
	 * @return feed's ID
	 */
	public String getID(){
		return this.ID_;
	}
	
	/**
	 * @return user who created the feed
	 */
	public FacebookFriend getFrom(){
		return this.FROM_;
	}
	
	/**
	 * @return creation time
	 */
	public String getCreatedTime(){
		return this.CREATED_TIME_;
	}
	
	/**
	 * Feed's equality
	 * @param feed
	 * @return true if the feeds are same
	 */
	@Override
	public boolean equals(Object feed){
		if(!(feed instanceof Feed)){
			return false;
		}
		String ID = ((Feed)feed).getID();
		return this.getID().equals(ID);
	}
	
	/**
	 * Feed's hash code
	 */
	@Override
	public int hashCode(){
		return this.getID().hashCode();
	}
	
	public abstract boolean find(final String keyword);
	
}
