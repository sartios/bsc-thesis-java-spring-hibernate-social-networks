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
 * <b>Table:</b> FCBK.NOTES <br/><br/>
 * A Facebook note.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=NoteConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
@PrimaryKeyJoinColumn(name = FacebookPostConstants.PROPERTY_ID)
@Lazy(value=false)
public class Note extends FacebookPost
{
	/**
	 * <b>Column:</b> NOTE_SUBJECT<br/><br/>
	 * The title of the note.
	 */
	@Column(name = NoteConstants.PROPERTY_SUBJECT,length = NoteConstants.LENGTH_SUBJECT)
	private	String	_subject;
	
	/**
	 * <b>Column:</b> NOTE_MESSAGE<br/><br/>
	 * The content of the note.
	 */
	@Column(name = NoteConstants.PROPERTY_MESSAGE,length = NoteConstants.LENGTH_MESSAGE)
	private	String	_message;

	/**
	 * Initializes the object.
	 */
	public Note()
	{
		super();
	}
	
	/**
	 * Initializes the object.
	 * @param user {@link Note#_user}
	 * @param subject {@link Note#_subject}
	 * @param message {@link Note#_message}
	 * @param createdTime {@link Note#_createdTime}
	 * @param comments {@link Note#_comments}
	 */
	public Note(User user, String subject, String message, Date createdTime,Set<Comment> comments)
	{	
		super(createdTime,user,comments);
		setSubject(subject);
		setMessage(message);
	}

	/**
	 * @param _subject the _subject to set
	 */
	public void setSubject(String subject)
	{
		_subject = subject;
	}

	/**
	 * @return the _subject
	 */
	public String getSubject()
	{
		return _subject;
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
