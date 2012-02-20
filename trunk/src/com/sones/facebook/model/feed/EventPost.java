package com.sones.facebook.model.feed;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.comment.Comment;
import com.sones.facebook.model.source.Event;
import com.sones.facebook.model.source.EventConstants;
import com.sones.facebook.model.source.User;

/**
 * <b>Table:</b> FCBK.EVENT_POSTS <br/><br/>
 * Represents a post that was published on an {@link Event}.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name = EventPostConstants.TABLE_NAME, schema = DatabaseConstants.FACEBOOK_SCHEMA)
@PrimaryKeyJoinColumn(name=FacebookPostConstants.PROPERTY_ID)
@Lazy(value=false)
public class EventPost extends FacebookPost
{
	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=Event.class)
	@JoinColumn(name=EventConstants.PROPERTY_ID)
	private Event event;
	
	/**
	 * Initializes the object.
	 */
	public EventPost()
	{
		super();
	}
	
	/**
	 * Initalizes the object.
	 * @param createdDate
	 * @param user
	 * @param comments
	 */
	public EventPost(Date createdDate, User user, Set<Comment> comments)
	{
		super(createdDate,user,comments);
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	} 
}
