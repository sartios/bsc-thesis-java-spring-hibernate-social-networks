package com.sones.businessLogic;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.exceptions.NoContentException;
import com.sones.businessLogic.Facebook.FacebookFriend;

/**
 * Is the super class for feeds. Contains the general data that all the feeds contain.
 * This version doesn't implements the rules for the data.
 * @author Sartios
 * @version 1.0
 *
 */
public class Feed implements Serializable{
	
	/**
	 * Number of comments. It's not sure that all the feeds will have comments.
	 * That's why we don't put it in constructor. If the feed will have comments
	 * then we will extract this number.
	 */
	private int numberOfComments_ = 0;
	/**
	 * Feed's ID
	 */
	private String ID_;
	
	/**
	 * The user who posted the feed
	 */
	private FacebookFriend FROM_ ;
	
	/**assertEquals
	 * The time that feed was published
	 */
	private String CREATED_TIME_;
	
	/**
	 * Comments for this feed
	 */
	private List<Comment> comments_;
	
	public Feed(){
		
	}
	
	/**
	 * Constructor
	 * @param ID
	 * @param FROM
	 * @param CREATED_TIME
	 * @param TYPE
	 */
	public Feed(final String ID, final FacebookFriend FROM,final String CREATED_TIME){
		this.comments_ = null;
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
	 * Sets the comments for this feed
	 */
	public void setComments(final List<Comment> comments){
		if(null!=comments){
			this.comments_ = comments;
		}
	}
	
	/**
	 * @return comments
	 */
	public List<Comment> getComments(){
		return Collections.unmodifiableList(this.comments_);
	}
	
	/**
	 * This method searches to the comment list and if it finds
	 * the keyword returns just true. It can't work in the case that
	 * we need comment's ID, only feed's id.
	 * @param keyword
	 * @return true 
	 */
	public boolean	searchIntoCommentsFor(final String keyword){
		if(comments_!=null){
			for(int i=0;i<comments_.size();i++){
				if(comments_.get(i).find(keyword)){
					return true;
				}
			}
		}
		return false;
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
	
	public void setNumberOfComments(final int numberOfComments){
		this.numberOfComments_ = numberOfComments;
	}
	
	public int getNumberOfComments(){
		return numberOfComments_;
	}
	
	/**
	 * This method must be overwritten in the derived classes. Feeds don't have
	 * content to search.
	 * @throws NoContentException
	 * @param keyword
	 */
	public boolean find(final String keyword) throws NoContentException{
		throw new NoContentException("No content");
	}
	
}
