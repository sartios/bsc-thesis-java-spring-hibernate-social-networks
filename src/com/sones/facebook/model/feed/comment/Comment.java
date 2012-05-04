package com.sones.facebook.model.feed.comment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.source.User;

/**
 * <b>Table:</b> FCBK.COMMENTS <br/><br/>
 * 
 * A comment on a Graph API object.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=CommentConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
@Lazy(value=false)
public class Comment implements Serializable
{
	/**
	 * The serial version.
	 */
	private static final long serialVersionUID = 6459245667122902630L;

	/**
	 * <b>Column:</b> COMM_ID <br/><br/>
	 * The Facebook ID of the comment.
	 */
	@Id
	@Column(name=CommentConstants.PROPERTY_COMMENT_ID,length=CommentConstants.LENGTH_COMMENT_ID)
	private String	id;
	
	/**
	 * <b>Column:</b> COMM_MESSAGE<br/><br/>
	 * The comment text.
	 */
	@Column(name=CommentConstants.PROPERTY_MESSAGE,length=CommentConstants.LENGTH_COMMENT_MESSAGE)
	private	String	message;
	
	/**
	 * <b>Column:</b> COMM_USER<br/><br/>
	 * The user that created the comment
	 */
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name=CommentConstants.PROPERTY_USER)
	private	User	user;
	
	/**
	 * <b>Column:</b> COMM_CREATED_DATE <br/><br/>
	 * The timedate the comment was created.
	 */
	@Column(name=CommentConstants.PROPERTY_CREATED_DATE)
	private	Date	createdDate;
	
	/**
	 * <b>Column:</b> FCBK_POST_ID <br/><br/>
	 * The post in which the comment has been created
	 */
	@OneToOne(targetEntity=FacebookPost.class)
	@JoinColumn(name=CommentConstants.PROPERTY_FACEBOOK_POST)
	private	FacebookPost post;
	
	/**
	 * Initialize the object.
	 */
	public Comment()
	{
	}
	
	/**
	 * Initializes the object.
	 * @param message
	 * @param createdDate
	 * @param user
	 */
	public Comment(String message, Date createdDate, User user, FacebookPost post)
	{
		setMessage(message);
		setCreatedDate(createdDate);
		setUser(user);
		setPost(post);
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

	/**
	 * @param _user the _user to set
	 */
	public void setUser(User user) 
	{
		this.user = user;
	}

	/**
	 * @return the _user
	 */
	public User getUser() 
	{
		return user;
	}

	/**
	 * @param _createdDate the _createdDate to set
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	/**
	 * @return the _createdDate
	 */
	public Date getCreatedDate() 
	{
		return createdDate;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if((obj instanceof Comment)==false)
		{
			return false;
		}
		Comment c = (Comment)obj;
		if((this.id.equals(c.id)))
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(FacebookPost post) {
		this.post = post;
	}

	/**
	 * @return the post
	 */
	public FacebookPost getPost() {
		return post;
	}
	
	
}
