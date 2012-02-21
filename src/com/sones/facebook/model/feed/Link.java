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
 * <b>Table:</b> FCBK.LINKS <br/><br/>
 * A link shared on a user's wall.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=LinkConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
@PrimaryKeyJoinColumn(name=FacebookPostConstants.PROPERTY_ID)
@Lazy(value=false)
public class Link extends FacebookPost
{
	/**
	 * <b>Column:</b> LINK_LINK_URL<br/><br/>
	 * The URL that was shared.
	 */
	@Column(name=LinkConstants.PROPERTY_LINK,length=LinkConstants.LENGTH_LINK)
	private	String	link;
	
	/**
	 * <b>Column:</b> LINK_NAME<br/><br/>
	 * The name of the link.
	 */
	@Column(name=LinkConstants.PROPERTY_NAME,length=LinkConstants.LENGTH_NAME)
	private	String	name;
	
	/**
	 * <b>Column:</b> LINK_DESCRIPTION<br/><br/>
	 * The description of the link.
	 */
	@Column(name=LinkConstants.PROPERTY_DESCRIPTIPON,length=LinkConstants.LENGTH_DESCRIPTION)
	private	String	description;
	
	/**
	 * <b>Column:</b> LINK_ICON_URL<br/><br/>
	 * A URL to the link icon that Facebook displays in the news feed.
	 */
	@Column(name=LinkConstants.PROPERTY_ICON,length=LinkConstants.LENGTH_ICON)
	private	String	icon;
	
	/**
	 * <b>Column:</b> LINK_PICTURE_URL<br/><br/>
	 * A URL to the thumbnail image used in the link post.
	 */
	@Column(name=LinkConstants.PROPERTY_PICTURE,length=LinkConstants.LENGTH_PICTURE)
	private	String	picture;
	
	/**
	 * <b>Column:</b> LINK_MESSAGE<br/><br/>
	 * The optional message from the user about this link.
	 */
	@Column(name=LinkConstants.PROPERTY_MESSAGE,length=LinkConstants.LENGTH_MESSAGE)
	private	String	message;
	
	/**
	 * Initializes the object.
	 */
	public Link()
	{	
		super();
	}
	
	/**
	 * Initializes the object.
	 * @param user
	 * @param link {@link Link#_link}
	 * @param name {@link Link#_name}
	 * @param description {@link Link#_description}
	 * @param icon {@link Link#_icon}
	 * @param picture {@link Link#_picture}
	 * @param message {@link Link#_message}
	 * @param createdDate
	 * @param comments
	 */
	public Link(User user, String link, String name, String description, String icon, String picture, String message, Date createdDate, Set<Comment> comments)
	{
		super(createdDate,user,comments);
		setLink(link);
		setName(name);
		setDescription(description);
		setIcon(icon);
		setPicture(picture);
		setMessage(message);
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
	 * @param _description the _description to set
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}

	/**
	 * @return the _description
	 */
	public String getDescription() 
	{
		return description;
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
	 * @param _message the _message to set
	 */
	public void setMessage(String message) 
	{
		this.message = message;
	}

	/**
	 * @return the _message
	 */
	public String getMessage() 
	{
		return message;
	}
}
