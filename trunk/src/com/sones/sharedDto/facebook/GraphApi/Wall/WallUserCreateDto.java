package com.sones.sharedDto.facebook.GraphApi.Wall;

import com.sones.facebook.model.source.User;
import com.sones.sharedDto.facebook.source.UserIdDto;

/**
 * Create dto for {@link User} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class WallUserCreateDto 
{
	/**
	 * The user id
	 */
	private	UserIdDto	id;
	
	/**
	 * The user username
	 */
	private	String	username;
	
	/**
	 * Initializes the object.
	 */
	public	WallUserCreateDto()
	{
		
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UserIdDto id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public UserIdDto getId() {
		return id;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}	
}
