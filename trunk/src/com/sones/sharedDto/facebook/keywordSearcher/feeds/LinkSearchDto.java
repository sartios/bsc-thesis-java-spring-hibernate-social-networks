package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import com.sones.facebook.model.feed.Link;

/**
 * Search dto for {@link Link} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class LinkSearchDto  extends FacebookPostSearchDto	implements	ISearchableFacebookFeed
{
	/**
	 * The link description.
	 */
	private	String	description;
	
	/**
	 * The link messsage.
	 */
	private	String	message;
	
	/**
	 * The link name.
	 */
	private	String	name;
	
	/**
	 * Initializes the object
	 */
	public LinkSearchDto()
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
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
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
	 * Returns true if the link contains the value. It's not case sensitive.
	 * @param value
	 * @return true if the link contains the value.
	 */
	@Override
	public	boolean	contains( String value )
	{
		if( description != null )
		{
			if( description.toLowerCase().contains( value.toLowerCase() ) )
			{
				return	true;
			}
		}
		if( message != null )
		{
			if( message.toLowerCase().contains( value.toLowerCase() ) )
			{
				return	true;
			}
		}
		if( name != null )
		{
			if( name.toLowerCase().contains( value.toLowerCase() ) )
			{
				return	true;
			}
		}
		return	false;
	}
}
