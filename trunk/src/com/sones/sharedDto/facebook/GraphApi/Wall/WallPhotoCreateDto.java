package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.Date;
import java.util.Set;

import com.sones.facebook.model.feed.Photo;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;

/**
 * Create dto for {@link Photo} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class WallPhotoCreateDto extends WallFacebookPostCreateDto
{
	private	String	name;
	private	String	icon;
	private	String	picture;
	private	String	link;
	
	public WallPhotoCreateDto()
	{
		super();
	}
	
	public WallPhotoCreateDto(FacebookPostIdDto id, WallUserCreateDto userId, Set<WallCommentCreateDto> comments, Date date,  String type, String name, String icon, String picture, String link)
	{
		super(id, userId, comments, date, type);
		setName(name);
		setIcon(icon);
		setPicture(picture);
		setLink(link);
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
