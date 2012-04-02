package com.sones.facebook.placemanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.sones.dao.DatabaseConstants;


/**
 * <b>Table:</b> FCBK.PLACES <br/><br/>
 * A location.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=PlaceConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
@Lazy(value=false)
public class Place	implements	Serializable
{
	/**
	 * The serial version.
	 */
	private static final long serialVersionUID = -6627954326631095152L;

	/**
	 * <b>Column:</b> PLAC_ID <br/><br/>
	 * The place ID.
	 */
	@Id
	@Column(name=PlaceConstants.PROPERTY_ID,length=PlaceConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> PLAC_NAME <br/><br/>
	 * The place name.
	 */
	@Column(name=PlaceConstants.PROPERTY_NAME,length=PlaceConstants.LENGTH_NAME)
	private	String	name;
	
	/**
	 * <b>Column:</b> PLAC_LATIDUTE <br/><br/>
	 * The place latitude.
	 */
	@Column(name=PlaceConstants.PROPERTY_LATITUDE,length=PlaceConstants.LENGTH_LATITUDE)
	private	String	latitude;
	
	/**
	 * <b>Column:</b> PLAC_LONGITUDE <br/><br/>
	 * The place longitude.
	 */
	@Column(name=PlaceConstants.PROPERTY_LONGITUDE,length=PlaceConstants.LENGTH_LONGITUDE)
	private	String	longitude;
	
	@Column( name = PlaceConstants.PROPERTY_CATEGORY )
	private	PlaceCategory	category;
	
	@Column( name = PlaceConstants.PROPERTY_NUMBER_OF_CHECKINS )
	private	int	numberOfCheckins;
	
	/**
	 * Initializes the object.
	 */
	public Place()
	{}
	
	/**
	 * Initializes the object.
	 * @param name {@link Place#_name}
	 * @param latitude {@link Place#_latitude}
	 * @param longitude {@link Place#_longitude}
	 */
	public Place(String name, String latitude, String longitude)
	{
		setName(name);
		setLatitude(latitude);
		setLongitude(longitude);
	}

	/**
	 * @param _id the id to set
	 */
	public void setId(String id) 
	{
		this.id = id;
	}

	/**
	 * @return the _id
	 */
	public String getId() 
	{
		return id;
	}

	/**
	 * @param _name the name to set
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * @return the _name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * @param _latitude the latitude to set
	 */
	public void setLatitude(String latitude) 
	{
		this.latitude = latitude;
	}

	/**
	 * @return the _latitude
	 */
	public String getLatitude()
	{
		return latitude;
	}

	/**
	 * @param _longitude the longitude to set
	 */
	public void setLongitude(String longitude) 
	{
		this.longitude = longitude;
	}

	/**
	 * @return the _longitude
	 */
	public String getLongitude()
	{
		return longitude;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(PlaceCategory category) {
		this.category = category;
	}

	/**
	 * @return the category
	 */
	public PlaceCategory getCategory() {
		return category;
	}

	/**
	 * @param numberOfCheckins the numberOfCheckins to set
	 */
	public void setNumberOfCheckins(int numberOfCheckins) {
		this.numberOfCheckins = numberOfCheckins;
	}

	/**
	 * @return the numberOfCheckins
	 */
	public int getNumberOfCheckins() {
		return numberOfCheckins;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if((obj instanceof Place)==false)
		{
			return false;
		}
		Place p = (Place)obj;
		if((p.id.equals(this.id)))
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() 
	{
		return this.id.hashCode();
	}
}
