package com.sones.facebook.placemanager.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.publicsource.model.Criteria;

/**
 * <b>Table:</b> FCBK.PLACE_CRITERIA <br/><br/>
 * Represents a criteria for a place.
 * @author sartios.sones@gmail.com.
 */
@Entity
@Table( name = PlaceCriteriaConstants.TABLE_NAME , schema = DatabaseConstants.FACEBOOK_SCHEMA )
public class PlaceCriteria	implements	Serializable
{
	/**
	 * The serial version
	 */
	private static final long serialVersionUID = 1584801493619830344L;
	
	/**
	 * <b>Column:</b> PLCR_PLAC_ID <br/>
	 * <b>Column:</b> PLCR_CRIT_ID <br/><br/>
	 * The composite id.
 	 */
	@EmbeddedId
	private	PlaceCriteriaId	id;
	
	/**
	 * Initializes the object.
	 */
	public	PlaceCriteria()
	{}
	
	/**
	 * Initializes the object.
	 * @param place
	 * @param criteria
	 */
	public	PlaceCriteria( Place place, Criteria criteria )
	{
		setId(new	PlaceCriteriaId(place, criteria));
	}

	/**
	 * @param id the id to set
	 */
	public void setId(PlaceCriteriaId id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public PlaceCriteriaId getId() {
		return id;
	}

	/**
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if( obj == null )
		{
			return	false;
		}
		if( ( obj instanceof PlaceCriteria ) == false )
		{
			return	false;
		}
		
		PlaceCriteria	placeCriteria	=	( PlaceCriteria )	obj;
		if( placeCriteria.getId().equals( id ) )
		{
			return	true;
		}
		return	false;
	}

	/**
	 */
	@Override
	public int hashCode() 
	{
		int	hashCode	=	super.hashCode();
		if( id != null )
		{
			hashCode	=	id.hashCode();
		}
		return	hashCode;
	}
	
	
}
