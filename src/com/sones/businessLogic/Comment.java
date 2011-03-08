package com.sones.businessLogic;

import com.sones.businessLogic.Facebook.FacebookFriend;

/**
 * Facebook comment class
 * @author Sartios
 */
public class Comment extends Feed{

	/**
	 * The message of comment
	 */
	private final String message_;
	
	/**
	 * Constructor
	 * @param
	 * @param
	 * @param	 
	 * @param
	 */
	public Comment(final String ID, final FacebookFriend FROM,final String CREATED_TIME,final String MESSAGE){
		super(ID, FROM, CREATED_TIME);
		message_ = MESSAGE;
	}
	
	public String getMessage(){
		return this.message_;
	}
	
	/**
	 * Searches into the feed to find the keyword
	 * @return true if it will find it
	 */
	public boolean find(final String keyword){
		return getMessage().toLowerCase().contains(keyword.toLowerCase());
	}
}
