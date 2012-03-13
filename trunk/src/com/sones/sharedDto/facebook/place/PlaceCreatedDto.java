package com.sones.sharedDto.facebook.place;

import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;

public class PlaceCreatedDto 
{
	private	PlaceIdDto id;
	private	FacebookPostIdDto postId;
	private	String	name;
	private	String	latitude;
	private	String	longitude;
	
	public PlaceCreatedDto()
	{
		
	}
	
	public PlaceCreatedDto(PlaceIdDto id, FacebookPostIdDto postId, String name, String latitude, String longitude)
	{
		setId(id);
		setPostId(postId);
		setName(name);
		setLatitude(latitude);
		setLongitude(longitude);
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
	 * @param postId the postId to set
	 */
	public void setPostId(FacebookPostIdDto postId) {
		this.postId = postId;
	}

	/**
	 * @return the postId
	 */
	public FacebookPostIdDto getPostId() {
		return postId;
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
