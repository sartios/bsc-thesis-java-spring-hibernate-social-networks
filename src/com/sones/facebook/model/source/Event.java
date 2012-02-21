package com.sones.facebook.model.source;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.EventPost;
import com.sones.facebook.model.feed.EventPostConstants;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.FacebookPostConstants;
import com.sones.facebook.model.place.Venue;
import com.sones.facebook.model.place.VenueConstants;

/**
 * <b>Table:</b> FCBK.EVENTS <br/><br/>
 * Specifies information about an event.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name = EventConstants.TABLE_NAME, schema = DatabaseConstants.FACEBOOK_SCHEMA)
public class Event implements Serializable
{
	/**
	 * <b>Column:</b> EVEN_ID<br/><br/>
	 * The event ID.
	 */
	@Id
	@Column(name =  EventConstants.PROPERTY_ID , length =  EventConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> EVEN_NAME<br/><br/>
	 * The event title.
	 */
	@Column(name =  EventConstants.PROPERTY_NAME , length =  EventConstants.LENGTH_NAME)
	private	String	name;
	
	/**
	 * <b>Column:</b> EVEN_DESCRIPTION<br/><br/>
	 * The long-form description of the event.
	 */
	@Column(name =  EventConstants.PROPERTY_DESCRIPTION , length =  EventConstants.LENGTH_DESCRIPTION)
	private	String	description;
	
	/**
	 * <b>Column:</b> EVEN_START_DATE<br/><br/>
	 * The start time of the event.
	 */
	@Column(name =  EventConstants.PROPERTY_START_DATE)
	private	Date	startDate;
	
	/**
	 * <b>Column:</b> EVEN_END_DATE<br/><br/>
	 * The END time of the event.
	 */
	@Column(name =  EventConstants.PROPERTY_END_DATE)
	private	Date	endDate;
	
	/**
	 * <b>Column:</b> EVEN_LOCATION<br/><br/>
	 * The location for this event.
	 */
	@Column(name =  EventConstants.PROPERTY_LOCATION , length =  EventConstants.LENGTH_LOCATION)
	private	String	location;
	
	/**
	 * <b>Column:</b> EVEN_VENUE_ID<br/><br/>
	 * The location for this event.
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name =  EventConstants.PROPERTY_VENUE)
	private	Venue	venue;
	
	/**
	 * <b>Column:</b> EVEN_USER_ID<br/><br/>
	 * The location for this event.
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = EventConstants.PROPERTY_USER)
	private User	user;
	
	/**
	 * <b>Column:</b> EVEN_POSTS_ID <br/><br/>
	 * The event posts.
	 */
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name = FacebookPostConstants.PROPERTY_ID)
	private Set<EventPost> eventPosts;
	
	/**
	 * Initializes the object.
	 */
	public	Event()
	{
	}
	
	/**
	 * Initializes the object.
	 * @param name {@link Event#_name}
	 * @param description {@link Event#_description}
	 * @param startDate {@link Event#_startDate}
	 * @param endDate {@link Event#_endDate}
	 * @param location {@link Event#_location}
	 * @param venue {@link Event#_location}
	 * @param user {@link Event#_user}
	 */
	public	Event(String name, String description, Date startDate, Date endDate, String location, Venue venue, User user, Set<EventPost> eventPosts)
	{
		SetName(name);
		SetDescription(description);
		SetStartDate(startDate);
		SetEndDate(endDate);
		SetLocation(location);
		SetVenue(venue);
		SetUser(user);
		SetEventPosts(eventPosts);
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
	 * @param _name the _name to set
	 */
	public void SetName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the _name
	 */
	public String GetName()
	{
		return name;
	}

	/**
	 * @param _description the _description to set
	 */
	public void SetDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the _description
	 */
	public String GetDescription() 
	{
		return description;
	}

	/**
	 * @param _startDate the _startDate to set
	 */
	public void SetStartDate(Date startDate) 
	{
		this.startDate = startDate;
	}

	/**
	 * @return the _startDate
	 */
	public Date GetStartDate() 
	{
		return startDate;
	}

	/**
	 * @param _endDate the _endDate to set
	 */
	public void SetEndDate(Date endDate) 
	{
		this.endDate = endDate;
	}

	/**
	 * @return the _endDate
	 */
	public Date GetEndDate()
	{
		return endDate;
	}

	/**
	 * @param _location the _location to set
	 */
	public void SetLocation(String location) 
	{
		this.location = location;
	}

	/**
	 * @return the _location
	 */
	public String GetLocation() 
	{
		return location;
	}

	/**
	 * @param _venue the _venue to set
	 */
	public void SetVenue(Venue venue) 
	{
		this.venue = venue;
	}

	/**
	 * @return the _venue
	 */
	public Venue GetVenue() 
	{
		return venue;
	}

	/**
	 * @param user the user to set
	 */
	public void SetUser(User user) 
	{
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User GetUser()
	{
		return user;
	}

	/**
	 * @param eventPosts the eventPosts to set
	 */
	public void SetEventPosts(Set<EventPost> eventPosts) 
	{
		this.eventPosts = eventPosts;
	}

	/**
	 * @return the eventPosts
	 */
	public Set<EventPost> GetEventPosts() 
	{
		return eventPosts;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if((obj instanceof Event) == false)
		{
			return false;
		}
		Event e = (Event)obj;
		if((e.id.equals(this.id))==true)
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() 
	{
		return id.hashCode();
	}
}
