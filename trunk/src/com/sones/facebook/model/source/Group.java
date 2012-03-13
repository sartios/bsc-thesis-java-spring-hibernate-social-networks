package com.sones.facebook.model.source;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.FacebookPostConstants;
import com.sones.facebook.model.feed.GroupPost;
import com.sones.facebook.model.feed.PagePost;

/**
 * <b>Table:</b> FCBK.GROUPS <br/><br/>
 * A Facebook group.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name = GroupConstants.TABLE_NAME, schema = DatabaseConstants.FACEBOOK_SCHEMA)
@Lazy(value=false)
public class Group implements Serializable
{
	/**
	 * <b>Column:</b> GROU_ID <br/><br/>
	 * The group ID
	 */
	@Id
	@Column(name=GroupConstants.PROPERTY_ID,length=GroupConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> GROU_NAME <br/><br/>
	 * The name of the group.
	 */
	@Column(name=GroupConstants.PROPERTY_NAME,length=GroupConstants.LENGTH_NAME)
	private	String	name;
	
	/**
	 * <b>Column:</b> GROU_DESCRIPTION <br/><br/>
	 * A brief description of the group.
	 */
	@Column(name=GroupConstants.PROPERTY_DESCRIPTION,length=GroupConstants.LENGTH_DESCRIPTION)
	private	String	description;
	
	/**
	 * <b>Column:</b> GROU_LINK <br/><br/>
	 * The URL for the group's website.
	 */
	@Column(name=GroupConstants.PROPERTY_LINK,length=GroupConstants.LENGTH_LINK)
	private	String	link;
	
	/**
	 * <b>Column:</b> GROU_UPDATED_TIME <br/><br/>
	 * The last time the group was updated.
	 */
	@Column(name=GroupConstants.PROPERTY_UPDATED_TIME)
	private	Date	updatedTime;
	
	/**
	 * <b>Column:</b> PAGE_POST_ID <br/><br/>
	 * The page posts.
	 */
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name = FacebookPostConstants.PROPERTY_ID)
	private Set<GroupPost>	posts;
	
	/**
	 * Initializes the object.
	 */
	public	Group()
	{
		
	}
	
	/**
	 * Initializes the object.
	 * @param name {@link Group#_name}
	 * @param description {@link Group#_description}
	 * @param link {@link Group#_link}
	 * @param updatedTime {@link Group#_updatedTime}
	 */
	public	Group(String name, String description, String link, Date updatedTime)
	{
		setName(name);
		setDescription(description);
		setLink(link);
		setUpdatedTime(updatedTime);
	}

	/**
	 * @param _id the _id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the _id
	 */
	public String getId() 
	{
		return id;
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
	 * @param _updatedTime the _updatedTime to set
	 */
	public void setUpdatedTime(Date updatedTime) 
	{
		this.updatedTime = updatedTime;
	}

	/**
	 * @return the _updatedTime
	 */
	public Date getUpdatedTime() 
	{
		return updatedTime;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(Set<GroupPost> posts) 
	{
		this.posts = posts;
	}

	/**
	 * @return the posts
	 */
	public Set<GroupPost> getPosts() 
	{
		return posts;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if((obj instanceof Group) == false)
		{
			return false;
		}
		Group g = (Group)obj;
		if(this.id.equals(g.getId()))
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() 
	{
		return this.id.hashCode();
	}
}
