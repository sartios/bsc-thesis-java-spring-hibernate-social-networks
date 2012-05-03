package com.sones.facebook.downloader.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.tokenmanager.model.FacebookAccount;

@Entity
@Table( name = FacebookAccountFriendConstants.TABLE_NAME, schema = DatabaseConstants.FACEBOOK_SCHEMA )
public class FacebookAccountFriend implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2907023936662802832L;
	
	@EmbeddedId
	private FacebookAccountFriendId id;
	
	public FacebookAccountFriend()
	{	}
	
	public FacebookAccountFriend(FacebookAccount owner, FacebookFriend friend)
	{
		id = new FacebookAccountFriendId(owner, friend);
	}

	/**
	 * @param id the id to set
	 */
	public void setId(FacebookAccountFriendId id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public FacebookAccountFriendId getId() {
		return id;
	}
	
	
}
