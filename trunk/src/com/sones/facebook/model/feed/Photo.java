package com.sones.facebook.model.feed;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.comment.Comment;
import com.sones.facebook.model.source.User;

/**
 * <b>Table:</b> FCBK.PHOTO <br/><br/>
 * An individual photo.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name = PhotoConstants.TABLE_NAME,schema = DatabaseConstants.FACEBOOK_SCHEMA)
@PrimaryKeyJoinColumn(name=FacebookPostConstants.PROPERTY_ID)
@Lazy(value=false)
public class Photo extends FacebookPost
{
	
	/**
	 * <b>Column:</b> PHOT_NAME<br/><br/>
	 * The user provided caption given to this photo.
	 */
	@Column(name=PhotoConstants.PROPERTY_NAME,length=PhotoConstants.LENGTH_NAME)
	private	String	name;
	
	/**
	 * <b>Column:</b> PHOT_ICON<br/><br/>
	 * The icon that Facebook displays when photos are published to the Feed.
	 */
	@Column(name=PhotoConstants.PROPERTY_ICON,length=PhotoConstants.LENGTH_ICON)
	private	String	icon;
	
	/**
	 * <b>Column:</b> PHOT_PICTURE<br/><br/>
	 * The thumbnail-sized source of the photo.
	 */
	@Column(name=PhotoConstants.PROPERTY_PICTURE,length=PhotoConstants.LENGTH_PICTURE)
	private	String	picture;
	
	/**
	 * <b>Column:</b> PHOT_LINK<br/><br/>
	 * A link to the photo on Facebook.
	 */
	@Column(name=PhotoConstants.PROPERTY_LINK,length=PhotoConstants.LENGTH_LINK)
	private	String	link;
	
	/**
	 * Initializes the object.
	 */
	public Photo()
	{
		super();
	}
	
	/**
	 * Initializes the object.
	 * @param user
	 * @param name {@link Photo#_name}
	 * @param icon {@link Photo#_icon}
	 * @param picture {@link Photo#_picture}
	 * @param link {@link Photo#_link}
	 * @param createdTime
	 * @param comments
	 */
	public	Photo(User user, String name, String icon, String picture, String link, Date createdTime, Set<Comment> comments)
	{
		super(createdTime, user, comments);
		setName(name);
		setIcon(icon);
		setPicture(picture);
		setLink(link);
	}

	/**
	 * @param _name the _name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the _name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * @param _icon the _icon to set
	 */
	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	/**
	 * @return the _icon
	 */
	public String getIcon() 
	{
		return icon;
	}

	/**
	 * @param _picture the _picture to set
	 */
	public void setPicture(String picture) 
	{
		this.picture = picture;
	}

	/**
	 * @return the _picture
	 */
	public String getPicture() 
	{
		return picture;
	}

	/**
	 * @param _link the _link to set
	 */
	public void setLink(String link)
	{
		this.link = link;
	}

	/**
	 * @return the _link
	 */
	public String getLink()
	{
		return link;
	}
}
