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
 * <b>Table:</b> FCBK.VIDEOS <br/><br/>
 * An individual Video.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name = VideoConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
@PrimaryKeyJoinColumn(name = FacebookPostConstants.PROPERTY_ID)
@Lazy(value=false)
public class Video extends FacebookPost
{
	/**
	 * The serial version,
	 */
	private static final long serialVersionUID = 3473289702399162814L;

	/**
	 * <b>Column:</b> VIDE_NAME<br/><br/>
	 * The video title or caption.
	 */
	@Column(name=VideoConstants.PROPERTY_NAME,length=VideoConstants.LENGTH_NAME)
	private	String	name;
	
	/**
	 * <b>Column:</b> VIDE_DESCRIPTION<br/><br/>
	 * The description of the video.
	 */
	@Column(name=VideoConstants.PROPERTY_DESCRIPTION,length=VideoConstants.LENGTH_DESCRIPTION)
	private	String	description;
	
	/**
	 * <b>Column:</b> VIDE_PICTURE<br/><br/>
	 * The URL for the thumbnail picture for the video.
	 */
	@Column(name=VideoConstants.PROPERTY_PICTURE,length=VideoConstants.LENGTH_PICTURE)
	private	String	picture;
	
	/**
	 * <b>Column:</b> VIDE_SOURCE <br/><br/>
	 * A URL to the raw playable video.
	 */
	@Column(name=VideoConstants.PROPERTY_SOURCE,length=VideoConstants.LENGTH_SOURCE)
	private String source;
	
	/**
	 * Initializes the object.
	 */
	public	Video()
	{
		super();
	}
	
	/**
	 * Initializes the object.
	 * @param user
	 * @param name {@link Video#_name}
	 * @param description {@link Video#_description}
	 * @param picture {@link Video#_picture}
	 * @param createdTime
	 * @param comments
	 */
	public	Video(User user, String name, String description, String picture, Date createdTime, Set<Comment> comments)
	{
		super(createdTime,user,comments);
		setName(name);
		setDescription(description);
		setPicture(picture);
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
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
}
