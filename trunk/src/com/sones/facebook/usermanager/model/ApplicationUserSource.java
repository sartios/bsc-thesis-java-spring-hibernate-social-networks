package com.sones.facebook.usermanager.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.source.Source;
import com.sones.usermanager.model.ApplicationUser;

@Entity
@Table(name=ApplicationUserSourceConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
public class ApplicationUserSource implements Serializable 
{
	/**
	 * <b>Column:</b> APSO_SOUR_ID <br/>
	 * <b>Column:</b> APSO_APUS_ID <br/><br/>
	 * The composite id.
	 */
	@EmbeddedId
	private	ApplicationUserSourceId id;
	
	/**
	 * Initializes the object.
	 */
	public	ApplicationUserSource()
	{
	}
	
	/**
	 * Initializes the object.
	 * @param source
	 * @param appUser
	 */
	public ApplicationUserSource(Source source, ApplicationUser appUser)
	{
		setId(new ApplicationUserSourceId(source, appUser));
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ApplicationUserSourceId id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public ApplicationUserSourceId getId() {
		return id;
	}
}
