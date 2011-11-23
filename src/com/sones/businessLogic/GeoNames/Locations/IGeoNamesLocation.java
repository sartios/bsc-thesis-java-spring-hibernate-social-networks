package com.sones.businessLogic.GeoNames.Locations;

/**
 * Represents a GeoName location.
 * 
 * @author Savramis	Sartios
 *
 */
public interface IGeoNamesLocation 
{
	/**
	 * Sets the name of the location.
	 * @param locationName to be setted.
	 */
	public	void	SetLocationName( final String locationName );
	
	/**
	 * Returns the name of the location.
	 * @return location's name.
	 */
	public	String	GetLocationName();
	
	/**
	 * Sets the country that the location exists.
	 * @param country of the location.
	 */
	public	void	SetCountry( final String country );
	
	/**
	 * Returns the country in which the location exists.
	 * @return country of the location.
	 */
	public	String	GetCountry();
}
