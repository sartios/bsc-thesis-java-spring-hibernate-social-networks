package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import com.sones.facebook.model.source.Event;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;

/**
 * Search dto for {@link Event} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class EventSearchDto
{
	/**
	 * The event id.
	 */
	private	FacebookPostIdDto	id;
	
	/**
	 * The event description.
	 */
	private	String	description;
	
	/**
	 * The event name.
	 */
	private	String	name;

	/**
	 * Initializes the object.
	 */
	public EventSearchDto()
	{
		
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(FacebookPostIdDto id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public FacebookPostIdDto getId() {
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
}
