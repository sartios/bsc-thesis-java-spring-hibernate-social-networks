package com.sones.facebook.model.place;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;

/**
 * <b>Table:</b> FCBK.PLACE_CATEGORIES <br/><br/>
 * Represents a place category.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table( name = PlaceCategoryConstants.TABLE_NAME , schema = DatabaseConstants.FACEBOOK_SCHEMA )
public class PlaceCategory	implements	Serializable
{
	/**
	 * The serial versions
	 */
	private static final long serialVersionUID = -1408971424500098141L;

	/**
	 * <b>Column:</b> PCAT_ID <br/><br/>
	 * The id property.
	 */
	@Id
	@Column( name = PlaceCategoryConstants.PROPERTY_ID , length = PlaceCategoryConstants.LENGTH_ID )
	private	String	id;
	
	/**
	 * <b>Column:</b> PCAT_DESCRIPTION <br/><br/>
	 * The description property
	 */
	@Column( name = PlaceCategoryConstants.PROPERTY_DESCRIPTION , length = PlaceCategoryConstants.LENGTH_DESCRIPTION )
	private	String	description;
	
	/**
	 * Initializes the object.
	 */
	public	PlaceCategory()
	{}
	
	/**
	 * Initializes the object
	 * @param description the place category description
	 */
	public	PlaceCategory( String description )
	{
		this.setDescription(description);
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

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
}
