package com.sones.businessLogic;

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
	private List<String> keywords_;
	
	/**
	 * The index of keywords list
	 */
	private int index_;
	
	/**
	 * Constructor
	 */
	public KeywordList(){
		keywords_ = new ArrayList<String>();
		index_ = 0;
	}
	
	/**
	 * Add a keyword into the list
	 */
	public boolean addKeyword(final String keyword){
		if(keywords_.isEmpty()){
			return keywords_.add(keyword);
		}
		if(haveNotAddThis(keyword)){
			return keywords_.add(keyword);
		}
		return false;
	}
	
	/**
	 * Check if the keyword already exists into the list
	 * @return true if it doesn't exist
	 */
	private boolean haveNotAddThis(final String keyword){
		if(keyword!=null){
			for(int i=0;i<keywords_.size();i++){
				if(keyword.trim().equalsIgnoreCase(keywords_.get(i).trim().toString())){
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
	public String getKeyword(){
		if(index_<keywords_.size()){
			return keywords_.get(index_++);
		}
		return null;
	}
}