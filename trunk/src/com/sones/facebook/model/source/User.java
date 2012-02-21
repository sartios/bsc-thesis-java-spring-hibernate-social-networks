package com.sones.facebook.model.source;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.FacebookPostConstants;
import com.sones.facebook.model.feed.comment.Comment;
import com.sones.facebook.model.feed.comment.CommentConstants;

/**
 * <b>Table:</b> FCBK.USERS <br/><br/>
 * A user profile.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=UserConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
public class User	implements	Serializable
{
	/**
	 * <b>Column:</b> USER_ID <br/><br/>
	 * The user ID
	 */
	@Id
	@Column(name=UserConstants.PROPERTY_ID,length=UserConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> USER_NAME <br/><br/>
	 * The user's full name.
	 */
	@Column(name=UserConstants.PROPERTY_NAME,length=UserConstants.LENGTH_NAME)
	private	String	name;
	
	/**
	 * <b>Column:</b> USER_FIRST_NAME <br/><br/>
	 * The user's first name.
	 */
	@Column(name=UserConstants.PROPERTY_FIRST_NAME,length=UserConstants.LENGTH_FIRST_NAME)
	private	String	_firstName;
	
	/**
	 * <b>Column:</b> USER_MIDDLE_NAME <br/><br/>
	 * The user's middle name.
	 */
	@Column(name=UserConstants.PROPERTY_MIDDLE_NAME,length=UserConstants.LENGTH_MIDDLE_NAME)
	private	String	middleName;
	
	/**
	 * <b>Column:</b> USER_LAST_NAME <br/><br/>
	 * The user's last name.
	 */
	@Column(name=UserConstants.PROPERTY_LAST_NAME,length=UserConstants.LENGTH_LAST_NAME)
	private	String	lastName;
	
	/**
	 * <b>Column:</b> USER_USERNAME <br/><br/>
	 * The user's Facebook username.
	 */
	@Column(name=UserConstants.PROPERTY_USERNAME,length=UserConstants.LENGTH_USER_NAME)
	private	String	username;
	
	/**
	 * <b>Column:</b> USER_HOMETOWN <br/><br/>
	 * The user's hometown.
	 */
	@Column(name=UserConstants.PROPERTY_HOME_TOWN,length=UserConstants.LENGTH_HOME_TOWN)
	private	String	hometown;
	
	/**
	 * <b>Column:</b> USER_LOCATION <br/><br/>
	 * The user's current city.
	 */
	@Column(name=UserConstants.PROPERTY_LOCATION,length=UserConstants.LENGTH_LOCATION)
	private	String	location;
	
	/**
	 * <b>Table:</b> FACEBOOK_POSTS <br/>
	 * <b>Column:</b> FCBK_POST_USER_ID <br/><br/>
	 * The posts of the user.
	 */
	@OneToMany(fetch=FetchType.LAZY,targetEntity=FacebookPost.class)
	@JoinColumn(name=FacebookPostConstants.PROPERTY_USER)
	private	Set<FacebookPost>	posts;
	
	/**
	 * <b>Table:</b> COMMENTS <br/>
	 * <b>Column:</b> COMM_ID <br/><br/>
	 * The comments of the user.
	 */
	@OneToMany(fetch=FetchType.LAZY,targetEntity=Comment.class)
	@JoinColumn(name=CommentConstants.PROPERTY_USER)
	private	Set<Comment>	comments;

	/**
	 * Initializes the object.
	 */
	public	User()
	{
	}
	
	/**
	 * Initializes the object.
	 * @param username
	 */
	public User(String username)
	{
		setUsername(username);
	}
	
	/**
	 * Initializes the object.
	 * @param name {@link User#_name}
	 * @param firstName {@link User#_firstName}
	 * @param middleName {@link User#_middleName}
	 * @param lastName {@link User#_lastName}
	 * @param username {@link User#_username}
	 * @param hometown {@link User#_hometown}
	 * @param location {@link User#_location}
	 */
	public	User(String name, String firstName, String middleName, String lastName, String username, String hometown, String location, Set<FacebookPost> posts, Set<Comment> comments)
	{
		setName(username);
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
		setUsername(username);
		setHometown(hometown);
		setLocation(location);
		setPosts(posts);
		setComments(comments);
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
	 * @param _firstName the _firstName to set
	 */
	public void setFirstName(String firstName) 
	{
		_firstName = firstName;
	}

	/**
	 * @return the _firstName
	 */
	public String getFirstName() 
	{
		return _firstName;
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
	 * @param _middleName the _middleName to set
	 */
	public void setMiddleName(String middleName) 
	{
		this.middleName = middleName;
	}

	/**
	 * @return the _middleName
	 */
	public String getMiddleName() 
	{
		return middleName;
	}

	/**
	 * @param _lastName the _lastName to set
	 */
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	/**
	 * @return the {@link User#_lastName}
	 */
	public String getLastName() 
	{
		return lastName;
	}

	/**
	 * @param _username the _username to set
	 */
	public void setUsername(String username) 
	{
		this.username = username;
	}

	/**
	 * @return the _username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param _hometown the _hometown to set
	 */
	public void setHometown(String hometown)
	{
		this.hometown = hometown;
	}

	/**
	 * @return the _hometown
	 */
	public String getHometown() 
	{
		return hometown;
	}

	/**
	 * @param _location the _location to set
	 */
	public void setLocation(String location) 
	{
		this.location = location;
	}

	/**
	 * @return the _location
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(Set<FacebookPost> posts) {
		this.posts = posts;
	}

	/**
	 * @return the posts
	 */
	public Set<FacebookPost> getPosts() {
		return posts;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the comments
	 */
	public Set<Comment> getComments() {
		return comments;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if((obj instanceof User)!=true)
		{
			return false;
		}
		User u = (User) obj;
		if(this.id.equals(u.id))
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
