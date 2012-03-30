package com.sones.facebook.keywordSearcher.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sones.facebook.model.feed.SourceFacebookPostId;
import com.sones.usermanager.model.ApplicationUser;

/**
 * Composite id for {@link ApplicationUserKeyword} model.
 * @author sartios.sones@gmail.com.
 *
 */
@Embeddable
public class ApplicationUserKeywordId implements Serializable
{
	/**
	 * <b>Column:</b> AUKE_KEYW_ID <br/><br/>
	 * The keyword id.
	 */
	@ManyToOne(targetEntity=Keyword.class)
	@JoinColumn(name=ApplicationUserKeywordConstants.PROPERTY_KEYWORD)
	private	Keyword	keyword;
	
	/**
	 * <b>Column:</b> AUKE_APUS_ID <br/><br/>
	 * The application user id.
	 */
	@ManyToOne(targetEntity=ApplicationUser.class)
	@JoinColumn(name=ApplicationUserKeywordConstants.PROPERTY_APPLICATION_USER)
	private	ApplicationUser	appUser;
	
	/**
	 * Initializes the object.
	 */
	public ApplicationUserKeywordId()
	{
		
	}
	
	/**
	 * Initializes the object,
	 * @param keyword
	 * @param appUser
	 */
	public	ApplicationUserKeywordId(Keyword keyword, ApplicationUser appUser)
	{
		setKeyword(keyword);
		setAppUser(appUser);
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the keyword
	 */
	public Keyword getKeyword() {
		return keyword;
	}

	/**
	 * @param appUser the appUser to set
	 */
	public void setAppUser(ApplicationUser appUser) {
		this.appUser = appUser;
	}

	/**
	 * @return the appUser
	 */
	public ApplicationUser getAppUser() {
		return appUser;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if((obj instanceof ApplicationUserKeywordId) == false)
		{
			return false;
		}
		ApplicationUserKeywordId id = (ApplicationUserKeywordId)obj;
		if((keyword.getId().equals(id.getKeyword().getId()))&&(appUser.getId().equals(id.getAppUser().getId())))
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() 
	{
		return keyword.getId().hashCode()^appUser.getId().hashCode();
	}	
}
