package com.sones.facebook.model.source;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sones.userManager.model.ApplicationUser;

/**
 * Composite id for {@link ApplicationUserSource} model.
 * @author sartios.sones@gmail.com.
 *
 */
@Embeddable
public class ApplicationUserSourceId implements Serializable
{
	/**
	 * <b>Column:</b> APSO_SOUR_ID <br/><br/>
	 * The source id.
	 */
	@ManyToOne(targetEntity=Source.class)
	@JoinColumn(name=ApplicationUserSourceConstants.PROPERTY_SOURCE)
	private	Source	source;
	
	/**
	 * <b>Column:</b> APSO_APUS_ID <br/><br/>
	 * The application user id.
	 */
	@ManyToOne(targetEntity=ApplicationUser.class)
	@JoinColumn(name=ApplicationUserSourceConstants.PROPERTY_APPLICATION_USER)
	private	ApplicationUser	appUser;
	
	/**
	 * Initializes the object
	 */
	public ApplicationUserSourceId()
	{
		
	}
	
	/**
	 * Initializes the object.
	 * @param source
	 * @param appUser
	 */
	public ApplicationUserSourceId(Source source, ApplicationUser appUser)
	{
		setAppUser(appUser);
		setSource(source);
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Source source) {
		this.source = source;
	}

	/**
	 * @return the source
	 */
	public Source getSource() {
		return source;
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
		if((obj instanceof ApplicationUserSourceId) == false)
		{
			return false;
		}
		ApplicationUserSourceId id = (ApplicationUserSourceId)obj;
		if((source.getId().equals(id.getSource().getId()))&&(appUser.getId().equals(id.getAppUser().getId())))
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() 
	{
		return source.getId().hashCode()^appUser.getId().hashCode();
	}
}
