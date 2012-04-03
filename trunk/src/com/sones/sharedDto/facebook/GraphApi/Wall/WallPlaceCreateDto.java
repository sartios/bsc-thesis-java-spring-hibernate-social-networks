package com.sones.sharedDto.facebook.GraphApi.Wall;

import com.sones.facebook.placemanager.model.Place;
import com.sones.sharedDto.facebook.place.PlaceIdDto;

/**
 * Create dto for {@link Place} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class WallPlaceCreateDto
{
	/**
	 * The id property of the {@link Place} model.
	 */
	private	PlaceIdDto id;
	
	/**
	 * The name property of the {@link Place} model.
	 */
	private	String	name;
	
	/**
	 * The latitude property of the {@link Place} model.
	 */
	private	String latitude;
	
	/**
	 * The longitude property of the {@link Place} model.
	 */
	private String	longitude;
	
	/**
	 * Initializes the object.
	 */
	public WallPlaceCreateDto()
	{
		
	}

	/**
	 * @param id the id to set
	 */
	public void setId(PlaceIdDto id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public PlaceIdDto getId() {
		return id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
}
