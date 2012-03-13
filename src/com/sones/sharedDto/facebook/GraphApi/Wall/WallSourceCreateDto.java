package com.sones.sharedDto.facebook.GraphApi.Wall;

import com.sones.facebook.model.source.Source;

/**
 * Create dto for {@link Source} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class WallSourceCreateDto 
{
	/**
	 * The id property of the {@link Source} model.
	 */
	private	String	id;
	
	/**
	 * The type type property of the {@link Source} model.
	 */
	private	String	type;
	
	/**
	 * Initializes the object.
	 */
	public WallSourceCreateDto()
	{
		
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
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	
}
