package com.sones.facebook.placemanager.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sones.facebook.publicsource.model.Criteria;

/**
 * Represents the id for {@link PlaceCriteria} model.
 * @author sartios.sones@gmail.com.
 *
 */
@Embeddable
public class PlaceCriteriaId	implements	Serializable
{
	/**
	 * The serial version.
	 */
	private static final long serialVersionUID = -3970206253054953201L;

	/**
	 * <b>Column:</b> PLCR_PLAC_ID <br/><br/>
	 * The place id.
	 */
	@ManyToOne( targetEntity = Place.class )
	@JoinColumn( name = PlaceCriteriaConstants.PROPERTY_PLACE )
	private	Place	place;
	
	/**
	 * <b>Column:</b> PLCR_CRIT_ID <br/><br/>
	 * The criteria id.
	 */
	@ManyToOne( targetEntity = Criteria.class )
	@JoinColumn( name = PlaceCriteriaConstants.PROPERTY_CRITERIA )
	private	Criteria	criteria;
	
	/**
	 * Initializes the object.
	 */
	public	PlaceCriteriaId()
	{}
	
	/**
	 * Initializes the object.
	 * @param place the place into which the criteria will be applied.
	 * @param criteria the criteria to be applied.
	 */
	public	PlaceCriteriaId( Place place, Criteria criteria )
	{
		setPlace( place );
		setCriteria( criteria );
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(Place place) {
		this.place = place;
	}

	/**
	 * @return the place
	 */
	public Place getPlace() {
		return place;
	}

	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	/**
	 * @return the criteria
	 */
	public Criteria getCriteria() {
		return criteria;
	}

	/**
	 * Returns true if place and criteria are equal.
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if( obj == null )
		{
			return	false;
		}
		if( ( obj instanceof PlaceCriteriaId ) == false )
		{
			return	false;
		}
		
		PlaceCriteriaId	id	=	( PlaceCriteriaId )	obj;
		if( id.getPlace().equals( place ) && id.getCriteria().equals( criteria ) )
		{
			return	true;
		}
		return	false;
	}

	/**
	 * Returns the hash code.
	 */
	@Override
	public int hashCode() 
	{
		int	placeHashCode	=	2;
		int	criteriaHashCode	=	super.hashCode();
		
		if( place != null )
		{
			placeHashCode	=	place.hashCode();
		}
		if( criteria != null )
		{
			criteriaHashCode	=	criteria.hashCode();
		}
		return	placeHashCode	^	criteriaHashCode;
	}
	
	
	
	
}
