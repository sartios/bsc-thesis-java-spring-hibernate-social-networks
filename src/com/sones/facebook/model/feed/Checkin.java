package com.sones.facebook.model.feed;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.springframework.context.annotation.Lazy;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.comment.Comment;
import com.sones.facebook.model.place.Place;
import com.sones.facebook.model.source.User;

/**
 * <b>Table:</b> FCBK.CHECKINS <br/><br/>
 * A Checkin represents a single visit by a user to a location.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=CheckinConstants.TABLE_NAME, schema=DatabaseConstants.FACEBOOK_SCHEMA)
@PrimaryKeyJoinColumn(name=FacebookPostConstants.PROPERTY_ID)
@Lazy(value=false)
public class Checkin extends FacebookPost
{	
	/**
	 * <b>Column:</b> CHEC_PLACE <br/><br/>
	 * Information about the Facebook Page that represents the locaton of checkin.
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name=CheckinConstants.PROPERTY_PLACE)
	private Place	_place;
	
	/**
	 * <b>Column:</b> CHEC_MESSAGE <br/><br/>
	 * The message the user added to the checkin.
	 */
	@Column(name=CheckinConstants.PROPERTY_MESSAGE, length=CheckinConstants.LENGTH_MESSAGE,nullable=false)
	private	String	_message;

	/**
	 * Initializes the object.
	 */
	public Checkin()
	{
		super();
	}
	
	/**
	 * Initializes the object.
	 * @param place - {@link Checkin#_place}
	 * @param message - {@link Checkin#_message}
	 * @param user
	 * @param date
	 * @param comments
	 */
	public Checkin(Place place, String message, User user, Date date,Set<Comment> comments)
	{
		super(date, user, comments);
		SetPlace(place);
		SetMessage(message);
	}

	/**
	 * @param _place the _place to set
	 */
	public void SetPlace(Place place) 
	{
		_place = place;
	}

	/**
	 * @return the _place
	 */
	public Place GetPlace() 
	{
		return _place;
	}

	/**
	 * @param _message the _message to set
	 */
	public void SetMessage(String message) 
	{
		_message = message;
	}

	/**
	 * @return the _message
	 */
	public String GetMessage() 
	{
		return _message;
	}
}
