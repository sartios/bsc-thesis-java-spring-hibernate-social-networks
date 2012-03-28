package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import com.sones.facebook.model.feed.Checkin;

/**
 * Search dto for {@link Checkin} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class CheckinSearchDto extends FacebookPostSearchDto	implements	ISearchableFacebookFeed
{
	/**
	 * The checkin message.
	 */
	private	String	message;
	
	/**
	 * Initializes the object.
	 */
	public CheckinSearchDto()
	{
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
	 * Returns true if the checkin message contains the value. It's not case sensitive.
	 * @param value
	 * @return true if checkin contains the value.
	 */
	@Override
	public boolean	contains( String value )
	{
		if( message != null )
		{
			if( message.toLowerCase().contains(value.toLowerCase() ) )
			{
				return	true;
			}
		}
		return	false;
	}
}
