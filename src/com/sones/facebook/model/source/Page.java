package com.sones.facebook.model.source;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.FacebookPostConstants;
import com.sones.facebook.model.feed.PagePost;

/**
 * <b>Table:</b> FCBK.PAGES <br/><br/>
 * A Facebook page.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name = PageConstants.TABLE_NAME, schema = DatabaseConstants.FACEBOOK_SCHEMA)
@Lazy(value=false)
public class Page implements Serializable
{
	/**
	 * <b>Column:</b> PAGE_ID <br/><br/>
	 * The page's ID
	 */
	@Id
	@Column(name=PageConstants.PROPERTY_ID,length=PageConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> PAGE_ID <br/><br/>
	 * The page's name
	 */
	@Column(name=PageConstants.PROPERTY_NAME,length=PageConstants.LENGTH_NAME)
	private	String	name;
	
	/**
	 * <b>Column:</b> PAGE_LINK <br/><br/>
	 * Link to the page on Facebook.
	 */
	@Column(name=PageConstants.PROPERTY_LINK,length=PageConstants.LENGTH_LINK)
	private	String	link;
	
	/**
	 * <b>Column:</b> PAGE_CATEGORY <br/><br/>
	 * The page's category
	 */
	@Column(name=PageConstants.PROPERTY_CATEGORY,length=PageConstants.LENGTH_CATEGORY)
	private	String	category;
	
	/**
	 * <b>Column:</b> PAGE_LOCATION <br/><br/>
	 * The page's street address, latitude, and longitude
	 */
	@Embedded
	private	Location	location;
	
	/**
	 * <b>Column:</b> PAGE_POST_ID <br/><br/>
	 * The page posts.
	 */
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name = FacebookPostConstants.PROPERTY_ID)
	private Set<PagePost>	posts;
	
	/**
	 * Initializes the object.
	 */
	public Page()
	{
	}
	
	/**
	 * Initializes the object.
	 * @param name {@link Page#_name}
	 * @param link {@link Page#_link}
	 * @param category {@link Page#_category}
	 * @param location {@link Page#_location}
	 */
	public Page(String name, String link, String category, Location location)
	{
		setName(name);
		setLink(link);
		setCategory(category);
		setLocation(location);
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
	 * @param _category the _category to set
	 */
	public void setCategory(String category)
	{
		this.category = category;
	}

	/**
	 * @return the _category
	 */
	public String getCategory()
	{
		return category;
	}

	/**
	 * @param _location the _location to set
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}

	/**
	 * @return the _location
	 */
	public Location getLocation() 
	{
		return location;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(Set<PagePost> posts)
	{
		this.posts = posts;
	}

	/**
	 * @return the posts
	 */
	public Set<PagePost> getPosts() 
	{
		return posts;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if((obj instanceof Page) == false)
		{
			return false;
		}
		Page g = (Page)obj;
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
