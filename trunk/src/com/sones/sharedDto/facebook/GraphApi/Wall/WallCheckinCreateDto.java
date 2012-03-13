package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.Date;
import java.util.Set;

import com.sones.facebook.model.feed.Checkin;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;

/**
 * Create dto for the {@link Checkin} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class WallCheckinCreateDto extends WallFacebookPostCreateDto
{
	private	String message;
	private	WallPlaceCreateDto place;
	
	public WallCheckinCreateDto()
	{
		super();
	}
	
	public WallCheckinCreateDto(FacebookPostIdDto id, WallUserCreateDto userId, Set<WallCommentCreateDto> comments, Date date, String type, String message, WallPlaceCreateDto place)
	{
		super(id, userId, comments, date, type);
		setMessage(message);
		setPlace(place);
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

	/**
	 * @param place the place to set
	 */
	public void setPlace(WallPlaceCreateDto place) {
		this.place = place;
	}

	/**
	 * @return the place
	 */
	public WallPlaceCreateDto getPlace() {
		return place;
	}
}
