package com.sones.businessLogic;

import com.sones.businessLogic.Facebook.FacebookFriend;

public class Status extends Feed{

	/**
	 * The message of the status
	 */
	private final String MESSAGE_;	
	/**
	 * Constructor which doesn't set comments
	 * @param ID
	 * @param FROM
	 * @param CREATED_TIME
	 * @param MESSAGE
	 */
	public Status(final String ID, final FacebookFriend FROM,final String CREATED_TIME,final String MESSAGE){
		super(ID, FROM, CREATED_TIME);
		this.MESSAGE_=MESSAGE;
	}
	
	public String getMessage(){
		return MESSAGE_;
	}
	
	/**
	 * Searches into the feed to find the keyword
	 * @return true if it will find it
	 */
	public boolean find(final String keyword){
		return getMessage().toLowerCase().contains(keyword.toLowerCase());
	}
}
