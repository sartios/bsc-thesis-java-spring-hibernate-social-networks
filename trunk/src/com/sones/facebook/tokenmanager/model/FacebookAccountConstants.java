package com.sones.facebook.tokenmanager.model;

import com.sones.userManager.model.ApplicationUserConstants;

/**
 * Contains properties name and lengths for {@link FacebookAccount} model.
 * @author sartios.sones@gmail.com.
 *
 */
public final class FacebookAccountConstants 
{
	/**
	 * The facebook account table name.
	 */
	public	static	final	String	TABLE_NAME	=	"FACEBOOK_ACCOUNTS";
	
	/**
	 * The id property name.
	 */
	public	static	final	String	PROPERTY_ID	=	"FBAC_ID";
	
	/**
	 * The application user property name.
	 */
	public	static	final	String	PROPERTY_APPLICATION_USER	=	ApplicationUserConstants.PROPERTY_ID;
	
	/**
	 * The email property name.
	 */
	public	static	final	String	PROPERTY_EMAIL	=	"FBAC_EMAIL";
	
	/**
	 * The id property length.
	 */
	public	static	final	int	LENGTH_ID	=	20;
	
	/**
	 * The email property length.
	 */
	public	static	final	int	LENGTH_EMAIL	=	30;
}
