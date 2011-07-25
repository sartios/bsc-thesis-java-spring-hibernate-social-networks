package com.sones.businessLogic.KeywordManager;

import java.util.ArrayList;
import java.util.List;


/**
 * This object is the list of keywords which the user wants to
 * search in the feeds.
 * @author Sartios
 */
public class KeywordList {
	
	/**
	 * The list of keywords
	 */
	private List<Keyword> keywords_;
	
	/**
	 * The index of keywords list
	 */
	private int index_;
	
	/**
	 * The user who has created the keyword list
	 */
	private String applicationUserID_=null;
	
	/**
	 * Constructor
	 */
	public KeywordList(){
		keywords_ = new ArrayList<Keyword>();
		index_ = 0;
	}
	
	/**
	 * Add a keyword into the list
	 */
	public boolean addKeyword(final Keyword keyword){
		if(null!=keyword){
			if(keywords_.isEmpty()){
				return keywords_.add(keyword);
			}
			if(haveNotAddThis(keyword)){
				return keywords_.add(keyword);
			}
		}
		return false;
	}
	
	/**
	 * Check if the keyword already exists into the list
	 * @return true if it doesn't exist
	 */
	private boolean haveNotAddThis(final Keyword keyword){
		if(keyword!=null){
			for(int i=0;i<keywords_.size();i++){
				if(keyword.equals(keywords_.get(i))){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Return the next keyword or null if the list came to the end
	 * @return keyword
	 */
	public Keyword getKeyword(){
		if(index_<keywords_.size()){
			return keywords_.get(index_++);
		}
		return null;
	}
	
	/**
	 * @return true if the list is empty
	 */
	public boolean isEmpty(){
		return keywords_.isEmpty();
	}
	
	/**
	 * Sets the index of the list into zero
	 */
	public void reset(){
		index_ = 0;
	}
	
	/**
	 * Returns the keyword from a specific index if the index isn't out of bounds
	 * @param index
	 * @return keyword 
	 */
	public Keyword getKeyword(final int index){
		if(index<keywords_.size()){
			return keywords_.get(index);
		}
		return null;
	}
	
	/**
	 * Returns the size of the list
	 * @return size of the list
	 */
	public int getSize(){
		return keywords_.size();
	}

	/**
	 * Sets the user of the list
	 * @param applicationUserID
	 */
	public void setApplicationUserID_(String applicationUserID) {
		if(null!=applicationUserID){
			this.applicationUserID_ = applicationUserID;
		}
		else if(null==this.applicationUserID_){
			this.applicationUserID_=new String();
		}
	}

	/**
	 * Returns the application user who created the list
	 * @return application user id
	 */
	public String getApplicationsUserID_() {
		return applicationUserID_;
	}
	
	/**
	 * The keyword list belongs to the user
	 * @param userID
	 * @return true if the user of the keyword list is the userID
	 */
	public boolean belongToUser(final String userID){
		try{
			if(this.getApplicationsUserID_().equals(userID)){
				return true;
			}
		}
		catch (NullPointerException ex) {
		}
		return false;
	}
	
	/**
	 * The keyword list belongs to nobody
	 * @param userID
	 * @return true if the keyword list has no user
	 */
	public boolean belongToNobody(){
		boolean belongToNobody = false;
		try{
			if(this.getApplicationsUserID_().isEmpty()){
				belongToNobody=true;
			}
		}
		catch (NullPointerException ex) {
			belongToNobody=true;
		}
		return belongToNobody;
	}
}