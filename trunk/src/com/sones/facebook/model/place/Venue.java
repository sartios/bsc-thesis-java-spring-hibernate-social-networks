package com.sones.facebook.model.place;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;

/**
 * <b>Table:</b> FCBK.VENUES <br/><br/>
 * The location of an event.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name = VenueConstants.TABLE_NAME, schema=DatabaseConstants.FACEBOOK_SCHEMA)
public class Venue implements Serializable
{
	/**
	 * <b>Column:</b> VENU_ID<br/><br/>
	 * The venue ID.
	 */
	@Id
	@Column(name=VenueConstants.PROPERTY_ID,length=VenueConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> VENU_STREET<br/><br/>
	 * The venue street.
	 */
	@Column(name=VenueConstants.PROPERTY_STREET,length=VenueConstants.LENGTH_STREET)
	private	String	street;
	
	/**
	 * <b>Column:</b> VENU_CITY<br/><br/>
	 * The venue city.
	 */
	@Column(name=VenueConstants.PROPERTY_CITY,length=VenueConstants.LENGTH_CITY)
	private	String	city;
	
	/**
	 * <b>Column:</b> VENU_STATE<br/><br/>
	 * The venue state.
	 */
	@Column(name=VenueConstants.PROPERTY_STATE,length=VenueConstants.LENGTH_STATE)
	private	String	state;
	
	/**
	 * <b>Column:</b> VENU_ZIP<br/><br/>
	 * The venue zip.
	 */
	@Column(name=VenueConstants.PROPERTY_ZIP,length=VenueConstants.LENGTH_ZIP)
	private	String	zip;
	
	/**
	 * <b>Column:</b> VENU_COUNTRY<br/><br/>
	 * The venue country.
	 */
	@Column(name=VenueConstants.PROPERTY_COUNTRY,length=VenueConstants.LENGTH_COUNTRY)
	private	String	country;
	
	/**
	 * <b>Column:</b> VENU_LATITUDE<br/><br/>
	 * The venue latitude.
	 */
	@Column(name=VenueConstants.PROPERTY_LATITUDE,length=VenueConstants.LENGTH_LATITUDE)
	private	String	latitude;
	
	/**
	 * <b>Column:</b> VENU_LONGITUDE<br/><br/>
	 * The venue longitude.
	 */
	@Column(name=VenueConstants.PROPERTY_LONGITUDE,length=VenueConstants.LENGTH_LONGITUDE)
	private	String	longitude;

	/**
	 * Initializes the object.
	 */
	public Venue()
	{	
	}
	
	/**
	 * Initializes the object.
	 * @param street {@link Venue#_street}
	 * @param state {@link Venue#_state}
	 * @param zip {@link Venue#_zip}
	 * @param country {@link Venue#_country}
	 * @param latitude {@link Venue#_latitude}
	 * @param longitude {@link Venue#_longitude}
	 */
	public Venue(String street, String state, String zip, String country, String latitude, String longitude)
	{
		
	}

	/**
	 * @param _id the id to set
	 */
	public void SetId(String id) 
	{
		this.id = id;
	}

	/**
	 * @return the _id
	 */
	public String GetId() 
	{
		return id;
	}

	/**
	 * @param _street the street to set
	 */
	public void SetStreet(String street) 
	{
		this.street = street;
	}

	/**
	 * @return the _street
	 */
	public String GetStreet() 
	{
		return street;
	}

	/**
	 * @param _city the city to set
	 */
	public void SetCity(String city) 
	{
		this.city = city;
	}

	/**
	 * @return the _city
	 */
	public String GetCity() 
	{
		return city;
	}

	/**
	 * @param _state the state to set
	 */
	public void SetState(String state) 
	{
		this.state = state;
	}

	/**
	 * @return the _state
	 */
	public String GetState() 
	{
		return state;
	}

	/**
	 * @param _zip the zip to set
	 */
	public void SetZip(String zip)
	{
		this.zip = zip;
	}

	/**
	 * @return the _zip
	 */
	public String GetZip() 
	{
		return zip;
	}

	/**
	 * @param _country the country to set
	 */
	public void SetCountry(String country) 
	{
		this.country = country;
	}

	/**
	 * @return the _country
	 */
	public String GetCountry()
	{
		return country;
	}

	/**
	 * @param _latitude the latitude to set
	 */
	public void SetLatitude(String latitude) 
	{
		this.latitude = latitude;
	}

	/**
	 * @return the _latitude
	 */
	public String GetLatitude() 
	{
		return latitude;
	}

	/**
	 * @param _longitude the longitude to set
	 */
	public void SetLongitude(String longitude) 
	{
		this.longitude = longitude;
	}

	/**
	 * @return the _longitude
	 */
	public String GetLongitude() 
	{
		return longitude;
	}
}
