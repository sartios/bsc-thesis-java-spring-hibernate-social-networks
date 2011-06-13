package com.sones.businessLogic.Facebook;

public class FacebookFriend {
	
	/**
	 * Friend's name
	 */
	private String name;

	
	/**
	 * Friend's ID
	 */
	private String id;
	
	public FacebookFriend(){}
	
	public void setName(final String name) {
		this.name = name;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public FacebookFriend(final String name,final String id){
		this.name = name;
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getId(){
		return this.id;
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
		return ((this.id.equals(friend.getId()))&&(this.name.equals(friend.getName())));
	}
	
	@Override
	public int hashCode(){
		return	this.id.hashCode()+this.name.hashCode();
	}
}
