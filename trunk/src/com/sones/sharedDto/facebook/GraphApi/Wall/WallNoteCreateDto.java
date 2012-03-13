package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.Date;
import java.util.Set;

import com.sones.facebook.model.feed.Note;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;

/**
 * Create dto for {@link Note} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class WallNoteCreateDto extends WallFacebookPostCreateDto
{
	private	String	subject;
	private	String	message;
	
	public WallNoteCreateDto()
	{
		super();
	}
	
	public WallNoteCreateDto(FacebookPostIdDto id, WallUserCreateDto userId, Set<WallCommentCreateDto> comments, Date date,  String type,String subject, String message)
	{
		super(id, userId, comments, date, type);
		setMessage(message);
		setSubject(subject);
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
