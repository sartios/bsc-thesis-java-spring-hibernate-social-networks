package com.sones.facebook.JsonHandler.Factory;

import net.sf.ezmorph.MorphException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.log4j.Logger;

import com.sones.facebook.tokenmanager.model.FacebookAccount;

public class FacebookAccountFactory implements IFacebookAccountFactory
{
	private	final Logger _LOGGER;

	public FacebookAccountFactory()
	{
		_LOGGER = Logger.getLogger( FacebookAccountFactory.class );
	}
	
	@Override
	public FacebookAccount GetAccount(DynaBean beanObject) 
	{
		String id = getProperty(beanObject, "uid", "Facebook account id occured error!");
		String name = getProperty(beanObject, "name", "Facebook account name occured error!");
		String email = getProperty(beanObject, "email", "Facebook account email occured error!");
		FacebookAccount account = new FacebookAccount();
		account.setId(id);
		return account;
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
		catch(NullPointerException nullEx)
		{
			_LOGGER.error( message + "\n" + nullEx.getLocalizedMessage() );
		}
		return property;
	}
}
