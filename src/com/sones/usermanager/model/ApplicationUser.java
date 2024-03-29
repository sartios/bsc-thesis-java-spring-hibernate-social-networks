package com.sones.usermanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.usermanager.model.ApplicationUserConstants;

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
	/** The generated serial version.	 */
	private static final long serialVersionUID = 7348926936270388549L;
	
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

	@Override
	public boolean equals(Object obj) 
	{
		if( obj == null )
		{
			return false;
		}
		if(( obj instanceof ApplicationUser) == false )
		{
			return false;
		}
		ApplicationUser user = (ApplicationUser)obj;
		if( user.getId().equals(this.id) )
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() 
	{
		return	this.id.hashCode()^5;
	}
	
	
}
