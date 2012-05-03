package com.sones.facebook.downloader.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;

@Entity
@Table(name = FacebookFriendConstants.TABLE_NAME, schema = DatabaseConstants.FACEBOOK_SCHEMA)
public class FacebookFriend implements Serializable
{

	/**
	 * The serial version.
	 */
	private static final long serialVersionUID = -1661060189966996934L;
	
	@Id
	@Column(name=FacebookFriendConstants.PROPERTY_ID,length=FacebookFriendConstants.LENGTH_ID)
	private String id;
	
	@Column(name=FacebookFriendConstants.PROPERTY_NAME,length=FacebookFriendConstants.LENGTH_NAME)
	private String name;	
	
	public FacebookFriend()
	{}
	
	public FacebookFriend(String name)
	{
		this.setName(name);
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
