package com.sones.facebook.placemanager.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.FacebookPost;

@Entity
@Table( name = PlaceFacebookPostConstants.TABLE_NAME , schema = DatabaseConstants.FACEBOOK_SCHEMA )
public class PlaceFacebookPost	implements	Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5635779965743804588L;
	
	@EmbeddedId
	private	PlaceFacebookPostId	id;
	
	/**
	 * Initializes the object.s
	 */
	public	PlaceFacebookPost()
	{}
	
	/**
	 * Initializes the object
	 * @param place	the place from which the post was downloaded.
	 * @param post	the post that was donwloaded.
	 */
	public	PlaceFacebookPost( Place place, FacebookPost post )
	{
		setId(new	PlaceFacebookPostId( place, post ));
	}

	/**
	 * @param id the id to set
	 */
	public void setId(PlaceFacebookPostId id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public PlaceFacebookPostId getId() {
		return id;
	}

	/**
	 * Returns true if the object is equal with this object.
	 * @param obj
	 */
	@Override
	public boolean equals(Object obj)
	{
		if( obj == null )
		{
			return	false;
		}
		if( ( obj instanceof PlaceFacebookPost ) == false )
		{
			return	false;
		}
		
		PlaceFacebookPost	post	=	( PlaceFacebookPost )	obj;
		if( post.getId().equals( id ) == true )
		{
			return	true;
		}
		return	false;
	}

	/**
	 * Returns the hash code of this object.
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	
	
}
