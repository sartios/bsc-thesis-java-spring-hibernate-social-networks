package com.sones.sharedDto.geonames;

public class CityViewDto 
{
	private String latitude;
	private String longitude;
	
	public CityViewDto()
	{}
	
	public CityViewDto(String latitude, String longitude)
	{
		this.setLatitude(latitude);
		this.setLongitude(longitude);
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
