package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import com.sones.facebook.model.feed.Video;

/**
 * Search dto for {@link Video} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class VideoSearchDto extends FacebookPostSearchDto	implements	ISearchableFacebookFeed
{
	/**
	 * The video description.
	 */
	private	String	description;
	
	/**
	 * The video name.
	 */
	private	String	name;
	
	/**
	 * Initializes the object.
	 */
	public	VideoSearchDto()
	{
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
	
	/**
	 * Returns true if the video contains the value. It's not case sensitive.
	 * @param value
	 * @return
	 */
	@Override
	public	boolean	contains( String value )
	{
		if( name != null )
		{
			if( name.toLowerCase().contains( value.toLowerCase() ) )
			{
				return true;
			}
		}
		if( description != null )
		{
			if( description.toLowerCase().contains( value.toLowerCase() ) )
			{
				return true;
			}
		}
		return false;
	}
}
