package com.sones.model;

import java.io.Serializable;

/**
 * <b>Table:</b> FCBK.APPLICATION_USERS <br/><br/>
 * Represents a user of the application.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public class ApplicationUser implements Serializable
{
	/**
	 * <b>Column:</b> APUS_ID <br/><br/>
	 * The id of the user is an assigned number.
	 */
	private	String	id;
	
	public ApplicationUser()
	{
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
}
