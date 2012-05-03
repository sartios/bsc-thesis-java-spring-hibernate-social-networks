package com.sones.facebook.JsonHandler.Factory;

import net.sf.ezmorph.MorphException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.log4j.Logger;

import com.sones.facebook.downloader.model.FacebookFriend;

public class FacebookFriendFactory implements IFacebookFriendFactory
{
	private	final Logger _LOGGER;

	public FacebookFriendFactory()
	{
		_LOGGER = Logger.getLogger( FacebookFriendFactory.class );
	}
	
	@Override
	public FacebookFriend GetFriend(DynaBean beanObject)
	{
		String id = getProperty(beanObject, "id", "Facebook friend id occured error!");
		String name = getProperty(beanObject, "name", "Facebook friend name occured error!");
		FacebookFriend friend = new FacebookFriend();
		friend.setId(id);
		friend.setName(name);
		return friend;
	}
	
	private String getProperty( DynaBean beanObject , String propertyName , String message )
	{
		String property = null;
		try
		{
			property = beanObject.get( propertyName ).toString();
		}
		catch(MorphException ex)
		{
			_LOGGER.error( message + "\n" + ex.getLocalizedMessage() );
		}
		return property;
	}

}
