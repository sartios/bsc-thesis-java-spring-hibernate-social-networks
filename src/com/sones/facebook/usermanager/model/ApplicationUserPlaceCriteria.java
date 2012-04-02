package com.sones.facebook.usermanager.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.publicsource.model.Criteria;
import com.sones.usermanager.model.ApplicationUser;

@Entity
@Table( name = ApplicationUserPlaceCriteriaConstants.TABLE_NAME , schema = DatabaseConstants.FACEBOOK_SCHEMA )
public class ApplicationUserPlaceCriteria	implements	Serializable
{
	/**
	 * The serical version.
	 */
	private static final long serialVersionUID = 4067981687248820974L;
	
	/**
	 * The id.
	 */
	@EmbeddedId
	private	ApplicationUserPlaceCriteriaId	id;
	
	/**
	 * Initializes the object.
	 */
	private	ApplicationUserPlaceCriteria()
	{}
	
	/**
	 * Initializes the object.s
	 * @param appUser
	 * @param criteria
	 */
	public	ApplicationUserPlaceCriteria( ApplicationUser appUser , Criteria criteria )
	{
		setId(new	ApplicationUserPlaceCriteriaId(appUser, criteria));
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ApplicationUserPlaceCriteriaId id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public ApplicationUserPlaceCriteriaId getId() {
		return id;
	}
}
