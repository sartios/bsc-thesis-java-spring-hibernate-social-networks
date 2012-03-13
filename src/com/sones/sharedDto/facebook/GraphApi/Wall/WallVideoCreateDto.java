package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.Date;
import java.util.Set;

import com.sones.facebook.model.feed.Video;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;

/**
 * Created dto for {@link Video} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class WallVideoCreateDto extends WallFacebookPostCreateDto
{
	private	String	name;
	private	String	description;
	private	String	picture;
	
	/**
	 * Initializes the object.
	 */
	public WallVideoCreateDto()
	{
		super();
	}
	
	/**
	 * Initializes the object.
	 * @param id
	 * @param userId
	 * @param comments
	 * @param date
	 * @param name
	 * @param description
	 * @param picture
	 */
	public WallVideoCreateDto(FacebookPostIdDto id, WallUserCreateDto userId, Set<WallCommentCreateDto> comments, Date date,  String type, String name, String description, String picture)
	{
		super(id, userId, comments, date, type);
		setName(name);
		setDescription(description);
		setPicture(picture);
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
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}
}
