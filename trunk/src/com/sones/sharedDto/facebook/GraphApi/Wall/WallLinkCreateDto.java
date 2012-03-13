package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.Date;
import java.util.Set;

import com.sones.facebook.model.feed.Link;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;

/**
 * Create dto for {@link Link} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class WallLinkCreateDto extends WallFacebookPostCreateDto
{
	private	String	link;
	private	String	name;
	private	String	description;
	private	String	icon;
	private	String	picture;
	private	String	message;
	
	public WallLinkCreateDto()
	{
		super();
	}
	
	public WallLinkCreateDto(FacebookPostIdDto id, WallUserCreateDto userId, Set<WallCommentCreateDto> comments, Date date,  String type, String link, String name, String description, String icon, String picture, String message)
	{
		super(id, userId, comments, date,type);
		setDescription(description);
		setIcon(icon);
		setLink(link);
		setMessage(message);
		setName(name);
		setPicture(picture);
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
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
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
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
	
}
