package com.sones.sharedDto.facebook.GraphApi.Wall;

import com.sones.facebook.model.feed.SourceFacebookPost;

/**
 * Create dto for {@link SourceFacebookPost} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class WallSourceFacebookPostCreateDto
{
	private WallSourceFacebookPostIdDto id;
	
	public WallSourceFacebookPostCreateDto()
	{
		
	}

	/**
	 * @param id the id to set
	 */
	public void setId(WallSourceFacebookPostIdDto id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public WallSourceFacebookPostIdDto getId() {
		return id;
	}
}
