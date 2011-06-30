package com.sones.businessLogic.Facebook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This is the list of facebook groups that a user is member.
 * @author Sartios
 */
public class FacebookGroupList {
	
	/**
	 * The list of user's groups
	 */
	private List<FacebookGroup> groups_;
	
	/**
	 * Constructor
	 */
	public FacebookGroupList(){
		this.groups_ = new ArrayList<FacebookGroup>();
	}
	
	/**
	 * Add a group to the list
	 */
	public boolean addGroup(final FacebookGroup group){
		if(null==group){
			return false;
		}
		return this.groups_.add(group);
	}
	
	/**
	 * Get the list of groups. It's unmodifiable which means that nobody can change it.
	 * @return group list
	 */
	public List<FacebookGroup> getGroups(){
		return /*Collections.unmodifiableList(*/this.groups_/*)*/;
	}
	
	/**
	 * Returns a list which contains the name of each group
	 */
	public List<String> getNamesOfGroups(){
		List<String> groupNames = new ArrayList<String>();
		for(int i=0;i<groups_.size();i++){
			groupNames.add(groups_.get(i).getName());
		}
		return groupNames;
	}
	
	public int getSize(){
		return groups_.size();
	}
}
