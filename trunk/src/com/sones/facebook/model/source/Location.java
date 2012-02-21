package com.sones.facebook.model.source;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * <b>Component Class</b><br/><br/>
 * Represents page's location.
 * @author sartios.sones@gmail.com.
 * 
 */
@Embeddable
public class Location implements Serializable
{
	@Column(name = LocationConstants.PROPERTY_STREET,length = LocationConstants.LENGTH_STREET)
	private	String	street;
	
	@Column(name = LocationConstants.PROPERTY_CITY,length = LocationConstants.LENGTH_CITY)
	private String	city;
	
	@Column(name = LocationConstants.PROPERTY_COUNTRY,length = LocationConstants.LENGTH_COUNTRY)
	private	String	country;
	
	@Column(name = LocationConstants.PROPERTY_ZIP,length = LocationConstants.LENGTH_ZIP)
	private	String	zip;
	
	/**
	 * Initializes the object.
	 */
	public Location()
	{
		
	}

	/**
	 * @param _street the street to set
	 */
	public void setStreet(String street)
	{
		this.street = street;
	}

	/**
	 * @return the _street
	 */
	public String getStreet() 
	{
		return street;
	}

	/**
	 * @param _country the country to set
	 */
	public void setCountry(String country) 
	{
		this.country = country;
	}

	/**
	 * @return the _country
	 */
	public String getCountry() 
	{
		return country;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getCity() 
	{
		return city;
	}

	public void setZip(String zip) 
	{
		this.zip = zip;
	}

	public String getZip() 
	{
		return zip;
	}
}
