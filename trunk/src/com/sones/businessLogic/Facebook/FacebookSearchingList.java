package com.sones.businessLogic.Facebook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FacebookSearchingList {
	
	/**
	 * The list of IDs that we are going to search
	 */
	private List<String> ids_ ;
	
	/**
	 * The index of list's current ID
	 */
	private int index_;
	
	/**
	 * Initializes the IDs list
	 */
	public FacebookSearchingList(){
		this.ids_ = new ArrayList();
		this.index_ = 0;
	}
	/**
	 * Add an ID to the list
	 * @param id
	 */
	public boolean addID(final String ID){
		return this.ids_.add(ID);
	}
	
	public Set<String> getIDs(){
		return (Set<String>) Collections.unmodifiableCollection(this.ids_);
	}
	
	/**
	 * Return the next ID from the list. If the list comes to the end, returns null
	 */
	public String getID(){
		if(index_ >= ids_.size()){
			return null;
		}
		return ids_.get(index_++);
	}
	
	/**
	 * Reset the index of the list
	 */
	public void reset(){
		index_=0;
	}
}
