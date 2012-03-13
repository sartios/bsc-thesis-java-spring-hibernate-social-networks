package com.sones.sharedDto.facebook.GraphApi.Wall;

import com.sones.facebook.model.feed.comment.Comment;
import com.sones.sharedDto.facebook.feed.CommentIdDto;

/**
 * Create dto for {@link Comment} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class WallCommentCreateDto 
{
	/**
	 * The id property of {@link Comment} model.
	 */
	private	CommentIdDto	id;
	
	/**
	 * The message property of the {@link Comment} model.
	 */
	private	String	message;
	
	/**
	 * The user property of the {@link Comment} model.
	 */
	private WallUserCreateDto user;
	
	/**
	 * The post property of the {@link Comment} model.
	 */
	private	WallFacebookPostCreateDto	post;
	
	/**
	 * Initializes the object.
	 */
	public WallCommentCreateDto()
	{
		
	}

	/**
	 * @param id the id to set
	 */
	public void setId(CommentIdDto id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public CommentIdDto getId() {
		return id;
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
	 * @param user the user to set
	 */
	public void setUser(WallUserCreateDto user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public WallUserCreateDto getUser() {
		return user;
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(WallFacebookPostCreateDto post) {
		this.post = post;
	}

	/**
	 * @return the post
	 */
	public WallFacebookPostCreateDto getPost() {
		return post;
	}
	
}
