package com.sones.businessLogic.Facebook;

public class FacebookFriend {
	
	/**
	 * Friend's name
	 */
	private final String NAME_;

	
	/**
	 * Friend's ID
	 */
	private final String ID_;
	
	public FacebookFriend(final String name,final String id){
		this.NAME_ = name;
		this.ID_ = id;
	}
	
	public String getName(){
		return this.NAME_;
	}
	
	public String getId(){
		return this.ID_;
	}
	
	@Override
	public boolean equals(Object o){
		if(this==o){
			return true;
		}
		if(!(o instanceof FacebookFriend)){
			return false;
		}
		FacebookFriend friend = (FacebookFriend) o;
		return ((this.ID_.equals(friend.getId()))&&(this.NAME_.equals(friend.getName())));
	}
	
	@Override
	public int hashCode(){
		return	this.ID_.hashCode()+this.NAME_.hashCode();
	}
}
