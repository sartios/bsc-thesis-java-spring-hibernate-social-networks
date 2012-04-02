package com.sones.facebook.usermanager.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sones.facebook.publicsource.model.Criteria;
import com.sones.usermanager.model.ApplicationUser;

@Embeddable
public class ApplicationUserPlaceCriteriaId	implements	Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8027055799085448574L;

	@ManyToOne( targetEntity = ApplicationUser.class )
	@JoinColumn( name = ApplicationUserPlaceCriteriaConstants.PROPERTY_APPLICATION_USER )
	private	ApplicationUser	appUser;
	
	@ManyToOne( targetEntity = Criteria.class )
	@JoinColumn( name = ApplicationUserPlaceCriteriaConstants.PROPERTY_PLACE_CRITERIA )
	private	Criteria	placeCriteria;
	
	/**
	 * Initializes the object.
	 */
	public	ApplicationUserPlaceCriteriaId()
	{}
	
	/**
	 * Initializes the object.
	 * @param appUser	the application user
	 * @param placeCriteria	the place criteria
	 */
	public	ApplicationUserPlaceCriteriaId( ApplicationUser appUser , Criteria placeCriteria )
	{
		setAppUser( appUser );
		setPlaceCriteria( placeCriteria );
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

	/**
	 * @param placeCriteria the placeCriteria to set
	 */
	public void setPlaceCriteria(Criteria placeCriteria) {
		this.placeCriteria = placeCriteria;
	}

	/**
	 * @return the placeCriteria
	 */
	public Criteria getPlaceCriteria() {
		return placeCriteria;
	}

	/**
	 * Returns true if the object is equal to this.
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if( obj == null )
		{
			return	false;
		}
		if( ( obj instanceof ApplicationUserPlaceCriteriaId ) == false )
		{
			return	false;
		}
		
		ApplicationUserPlaceCriteriaId	id	=	( ApplicationUserPlaceCriteriaId )	obj;
		if( id.getAppUser().equals( appUser ) && id.getPlaceCriteria().equals( placeCriteria ) )
		{
			return	true;
		}
		return	false;
	}

	/**
	 * Returns the hash code
	 */
	@Override
	public int hashCode() 
	{
		int	hashCode	=	super.hashCode();
		int	appUserHashCode	=	0;
		int	criteriaHashCode	=	0;
		
		if( appUser != null )
		{
			appUserHashCode	=	appUser.hashCode();
		}
		if( placeCriteria != null )
		{
			criteriaHashCode	=	placeCriteria.hashCode();
		}
		
		return	hashCode	+	( appUserHashCode ^ criteriaHashCode );
	}
	
	
}
