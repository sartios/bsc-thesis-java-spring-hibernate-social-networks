package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import com.sones.facebook.model.feed.Photo;

/**
 * Search dto for {@link Photo} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class PhotoSearchDto  extends FacebookPostSearchDto	implements	ISearchableFacebookFeed
{	
	/**
	 * The photo name.
	 */
	private	String	name;
	
	/**
	 * Initializes the object.
	 */
	public PhotoSearchDto()
	{
		
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
	 * Returns true if the link name contains the value. It's not case sensitive.
	 * @param value
	 * @return true if link contains the value.
	 */
	@Override
	public	boolean	contains( String value )
	{
		if( name.toLowerCase().contains( value.toLowerCase() ) )
		{
			return	true;
		}
		return	false;
	}
}
