package com.sones.facebook.model.feed;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.comment.Comment;
import com.sones.facebook.model.feed.comment.CommentConstants;
import com.sones.facebook.model.source.User;

/**
 * <b>Table:</b> FCBK.FACEBOOK_POSTS <br/><br/>
 * Represents a general facebook post.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=FacebookPostConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
@Lazy(value=false)
@Inheritance(strategy=InheritanceType.JOINED)
public class FacebookPost	implements	Serializable
{
	/**
	 * <b>Column:</b> FCBK_POST_ID <BR/><br/>
	 * The id of a facebook post.
	 */
	@Id
	@Column(name=FacebookPostConstants.PROPERTY_ID,length=FacebookPostConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> FCBK_POST_CREATED_DATE<br/><br/>
	 * The created date of the post.
	 */
	@Column(name=FacebookPostConstants.PROPERTY_CREATED_DATE)
	private	Date	createdDate;
	
	/**
	 * <b>Column:</b> FCBK_POST_USER_ID <br/><br/>
	 * The user who published the facebook post.
	 */
	@ManyToOne(targetEntity=User.class)//@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name=FacebookPostConstants.PROPERTY_USER)
	private	User	user;
	
	/**
	 * <b>Table:</b> COMMENTS <br/>
	 * <b>Column:</b> COMM_FCBK_POST_ID <br/><br/>
	 * The comments of a post.
	 */
	@OneToMany(fetch=FetchType.LAZY)//@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name=CommentConstants.PROPERTY_FACEBOOK_POST)
	private	Set<Comment>	comments;
	
	/**
	 * Initializes the object.
	 */
	public FacebookPost()
	{
	}
	
	/**
	 * Initializes the object.
	 * @param createdDate
	 * @param user
	 * @param comments
	 */
	public FacebookPost(Date createdDate, User user, Set<Comment> comments)
	{
		setCreatedDate(createdDate);
		setUser(user);
		setComments(comments);
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) 
	{
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) 
	{
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() 
	{
		return createdDate;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(Set<Comment> comments) 
	{
		this.comments = comments;
	}

	/**
	 * @return the comments
	 */
	public Set<Comment> getComments()
	{
		return comments;
	}

	@Override
	public boolean equals(Object obj)
	{
		if( obj == null )
		{
			return	false;
		}
		if( (obj instanceof FacebookPost) == false )
		{
			return false;
		}
		FacebookPost post = (FacebookPost)obj;
		if(post.getId().equals(id))
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() 
	{
		return this.id.hashCode() ^ 5;
	}
}
