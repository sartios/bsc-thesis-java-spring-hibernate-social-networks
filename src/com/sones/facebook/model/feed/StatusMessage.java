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
 * <b>Table:</b> FCBK.STATUS_MESSAGES <br/><br/>
 * A status message on a user's wall.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=StatusMessageConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
@PrimaryKeyJoinColumn(name=FacebookPostConstants.PROPERTY_ID)
@Lazy(value=false)
public class StatusMessage extends FacebookPost
{
	/**
	 * <b>Column:</b> STME_MESSAGE<br/><br/>
	 * The status message content.
	 */
	@Column(name=StatusMessageConstants.PROPERTY_MESSAGE,length=StatusMessageConstants.LENGTH_MESSAGE)
	private	String	_message;
	
	/**
	 * Initializes the object.
	 */
	public	StatusMessage()
	{
		super();
	}
	
	/**
	 * Initializes the object.
	 * @param message {@link StatusMessage#_message}
	 * @param updatedTime
	 * @param user
	 * @param comments 
	 */
	public	StatusMessage(String message, Date updatedTime, User user, Set<Comment> comments)
	{
		super(updatedTime,user,comments);
		setMessage(message);
	}

	/**
	 * @param _message the _message to set
	 */
	public void setMessage(String message) 
	{
		_message = message;
	}

	/**
	 * @return the _message
	 */
	public String getMessage() 
	{
		return _message;
	}
}
