package com.sones.businessLogic;

import com.sones.businessLogic.Facebook.FacebookFriend;

public class Note extends Feed{

	/**
	 * The title of the note
	 */
	private final String SUBJECT_;
	
	/**
	 * The content of the note
	 */
	private final String MESSAGE_;
	
	public Note(final String ID, final FacebookFriend FROM,final String CREATED_TIME,final String SUBJECT,final String MESSAGE){
		super(ID, FROM, CREATED_TIME);
		this.SUBJECT_ = SUBJECT;
		this.MESSAGE_ = MESSAGE;
	}
	
	public String getSubject(){
		return this.SUBJECT_;
	}
	
	public String getMessage(){
		return this.MESSAGE_;
	}
	
	/**
	 * Searches into the feed to find the keyword
	 * @return true if it will find it
	 */
	public boolean find(final String keyword){
		if(getSubject().toLowerCase().contains(keyword.toLowerCase())){
			return true;
		}
		if(getMessage().toLowerCase().contains(keyword.toLowerCase())){
			return true;
		}
		return false;
	}
}
