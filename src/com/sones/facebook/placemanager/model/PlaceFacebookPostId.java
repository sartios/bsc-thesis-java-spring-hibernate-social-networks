package com.sones.facebook.placemanager.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sones.facebook.model.feed.FacebookPost;

@Embeddable
public class PlaceFacebookPostId	implements	Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -273597036252211154L;

	/**
	 * <b>Column:</b> PLFP_PLAC_ID <br/><br/>
	 * The place from which the post was downloaded.
	 */
	@ManyToOne( targetEntity = Place.class )
	@JoinColumn( name = PlaceFacebookPostConstants.PROPERTY_PLACE )
	private	Place	place;
	
	/**
	 * <b>Column:</b> PLFP_FBPO_ID <br/><br/>
	 * The facebook post that was downloaded from the place.
	 */
	@ManyToOne( targetEntity = FacebookPost.class )
	@JoinColumn( name = PlaceFacebookPostConstants.PROPERTY_POST )
	private	FacebookPost	post;
	
	/**
	 * Initializes the object.
	 */
	public	PlaceFacebookPostId()
	{}
	
	/**
	 * Initializes the object.
	 * @param place the place from which the post was downloaded.
	 * @param post the post that was downloaded.
	 */
	public	PlaceFacebookPostId( Place place, FacebookPost post )
	{
		setPlace( place );
		setPost( post );
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(Place place) {
		this.place = place;
	}

	/**
	 * @return the place
	 */
	public Place getPlace() {
		return place;
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

	/**
	 * Returns true if the facebook post and the place are the same
	 * @param obj	the object to check if is the same with this.
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if( obj == null )
		{
			return	false;
		}
		if( ( obj instanceof PlaceFacebookPostId ) == false )
		{
			return	false;
		}
		
		PlaceFacebookPostId	id	=	( PlaceFacebookPostId )	obj;
		if( ( id.getPost().equals( post ) ) && ( id.getPlace().equals( place ) ) )
		{
			return	true;
		}
		return	false;
	}

	/**
	 * Returns the hash code of this object.
	 */
	@Override
	public int hashCode() 
	{
		int	hashCode	=	super.hashCode();
		int	postHashCode	=	0;
		int	placeHashCode	=	0;
		
		if( place != null )
		{
			placeHashCode	=	place.hashCode();
		}
		if( post != null )
		{
			postHashCode	=	post.hashCode();
		}
		
		return	hashCode	+	(postHashCode ^ placeHashCode);
	}
}
