package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.Date;
import java.util.Set;

import com.sones.facebook.model.feed.FacebookPost;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;

/**
 * Create dto for {@link FacebookPost} entity.
 * @author sartios.sones@gmail.com.
 *
 */
public class WallFacebookPostCreateDto 
{
	/**
	 * The id property of the {@link FacebookPost} model.
	 */
	private	FacebookPostIdDto	id;
	
	/**
	 * The user property of the {@link FacebookPost} model.
	 */
	private	WallUserCreateDto user;
	
	/**
	 * The comments of the {@link FacebookPost} model.
	 */
	private Set<WallCommentCreateDto> comments;
	
	/**
	 * The created date of the {@link FacebookPost} model.
	 */
	private Date date;
	
	private	String	type;
	
	/**
	 * Initializes the object.
	 */
	public WallFacebookPostCreateDto()
	{
	}
	
	/**
	 * Initializes the object.
	 * @param id
	 * @param user
	 * @param comments
	 * @param date
	 */
	public WallFacebookPostCreateDto(FacebookPostIdDto id, WallUserCreateDto user, Set<WallCommentCreateDto> comments, Date date, String type)
	{
		setId(id);
		setUser(user);
		setComments(comments);
		setDate(date);
		setType(type);
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
	 * @param comments the comments to set
	 */
	public void setComments(Set<WallCommentCreateDto> comments) {
		this.comments = comments;
	}

	/**
	 * @return the comments
	 */
	public Set<WallCommentCreateDto> getComments() {
		return comments;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
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
