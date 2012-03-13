package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.Date;
import java.util.Set;

import com.sones.facebook.model.feed.StatusMessage;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;

/**
 * Create dto for {@link StatusMessage} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class WallStatusMessageCreateDto extends WallFacebookPostCreateDto
{
	private	String	message;
	
	public WallStatusMessageCreateDto()
	{
		super();
	}
	
	public WallStatusMessageCreateDto(FacebookPostIdDto id, WallUserCreateDto userId, Set<WallCommentCreateDto> comments, Date date,  String type, String message)
	{
		super(id, userId, comments, date, type);
		setMessage(message);
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
