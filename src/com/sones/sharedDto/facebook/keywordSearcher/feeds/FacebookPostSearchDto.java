package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import com.sones.facebook.model.feed.FacebookPost;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;

/**
 * Search dto for {@link FacebookPost} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class FacebookPostSearchDto 
{
	/**
	 * The facebook post id.
	 */
	private	FacebookPostIdDto	id;
	
	/**
	 * Initializes the object.
	 */
	public FacebookPostSearchDto()
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
}
