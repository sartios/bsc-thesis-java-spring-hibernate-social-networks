package com.sones.sharedDto.facebook.place;

public class PlaceIdDto 
{
	private	String	id;
	
	public PlaceIdDto()
	{}
	
	public PlaceIdDto(String id)
	{
		setId(id);
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
	
	
}
