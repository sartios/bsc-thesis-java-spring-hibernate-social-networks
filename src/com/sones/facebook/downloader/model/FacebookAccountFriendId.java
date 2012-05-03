package com.sones.facebook.downloader.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sones.facebook.tokenmanager.model.FacebookAccount;

@Embeddable
public class FacebookAccountFriendId implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5901807555052706647L;

	@ManyToOne( targetEntity = FacebookAccount.class )
	@JoinColumn( name = FacebookAccountFriendIdConstants.PROPERTY_FACEBOOK_ACCOUNT )
	private FacebookAccount owner;
	
	@ManyToOne( targetEntity = FacebookFriend.class )
	@JoinColumn( name = FacebookAccountFriendIdConstants.PROPERTY_FACEBOOK_FRIEND )
	private FacebookFriend friend;
	
	public FacebookAccountFriendId()
	{}
	
	public FacebookAccountFriendId(FacebookAccount owner, FacebookFriend friend)
	{
		this.owner = owner;
		this.friend = friend;
	}
	
	/**
	 * @return the owner
	 */
	public FacebookAccount getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(FacebookAccount owner) {
		this.owner = owner;
	}

	/**
	 * @return the friend
	 */
	public FacebookFriend getFriend() {
		return friend;
	}

	/**
	 * @param friend the friend to set
	 */
	public void setFriend(FacebookFriend friend) {
		this.friend = friend;
	}

	@Override
	public boolean equals( Object obj ) 
	{
		if( obj == null )
		{
			return	false;
		}
		if( ( obj instanceof FacebookAccountFriendId ) == false )
		{
			return	false;
		}
		FacebookAccountFriendId	id	=	(FacebookAccountFriendId)obj;
		if( ( owner.getId().equals( id.getOwner().getId() ) ) && ( friend.getId().equals( id.getFriend().getId() ) ) )
		{
			return	true;
		}
		return	false;
	}

	@Override
	public int hashCode() 
	{
		return	( owner.getId().hashCode() ^ friend.getId().hashCode() );
	}
}
