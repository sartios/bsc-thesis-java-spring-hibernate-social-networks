package com.sones.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;

/**
 * <b>Table:</b> FCBK.APPLICATION_USERS <br/><br/>
 * Represents a user of the application.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=ApplicationUserConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
public class ApplicationUser implements Serializable
{
	/**
	 * <b>Column:</b> APUS_ID <br/><br/>
	 * The id of the user is an assigned number.
	 */
	@Id
	@Column(name=ApplicationUserConstants.PROPERTY_ID,length=ApplicationUserConstants.LENGTH_ID)
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
