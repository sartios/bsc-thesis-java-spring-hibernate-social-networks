package com.sones.facebook.model.feed;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.publicsource.model.Criteria;

@Entity
@Table( name = FacebookPostCriteriaConstants.TABLE_NAME, schema = DatabaseConstants.FACEBOOK_SCHEMA )
public class FacebookPostCriteria	implements	Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1689395854749005444L;
	
	@EmbeddedId
	private	FacebookPostCriteriaId	id;
	
	public	FacebookPostCriteria()
	{}
	
	public	FacebookPostCriteria( FacebookPost post, Criteria criteria )
	{
		setId(new	FacebookPostCriteriaId(post, criteria));
	}

	/**
	 * @param id the id to set
	 */
	public void setId(FacebookPostCriteriaId id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public FacebookPostCriteriaId getId() {
		return id;
	}
	
	
}
