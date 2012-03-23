package com.sones.facebook.keywordSearcher.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.userManager.model.ApplicationUser;

/**
 * <b>Table:</b> FCBK.APPLICATION_USER_KEYWORDS<br/><br/>
 * Represents a selected keyword by an application user.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=ApplicationUserKeywordConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
public class ApplicationUserKeyword implements Serializable
{	
	/**
	 * <b>Column:</b> AUKE_KEYW_ID <br/>
	 * <b>Column:</b> AUKE_APUS_ID <br/><br/>
	 * The composite id.
	 */
	@EmbeddedId
	private	ApplicationUserKeywordId id;
	
	/**
	 * Initializes the object.
	 */
	public ApplicationUserKeyword()
	{
		
	}
	
	/**
	 * Initializes the object.
	 * @param id
	 */
	public ApplicationUserKeyword(ApplicationUserKeywordId id)
	{
		setId(id);
	}
	
	/**
	 * Initializes the object.
	 * @param keyword
	 * @param appUser
	 */
	public ApplicationUserKeyword(Keyword keyword, ApplicationUser appUser)
	{
		ApplicationUserKeywordId genId = new ApplicationUserKeywordId(keyword, appUser);
		setId(genId);
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ApplicationUserKeywordId id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public ApplicationUserKeywordId getId() {
		return id;
	}
}
