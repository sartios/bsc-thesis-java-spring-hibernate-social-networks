package com.sones.sharedDto.facebook.feed;

import com.sones.facebook.model.feed.comment.Comment;

/**
 * Id dto for {@link Comment} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class CommentIdDto 
{
	/**
	 * The id property of the {@link Comment} model.
	 */
	private	String	id;
	
	/**
	 * Initializes the object.
	 */
	public CommentIdDto()
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
}
