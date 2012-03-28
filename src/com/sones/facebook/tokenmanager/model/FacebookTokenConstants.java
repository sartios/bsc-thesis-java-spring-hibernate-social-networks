package com.sones.facebook.tokenmanager.model;

/**
 * Contains properties name and length for {@link FacebookToken} model.
 * @author sartios.sones@gmail.com.
 *
 */
public final class FacebookTokenConstants 
{
	/**
	 * The facebook token table name.
	 */
	public	static	final	String	TABLE_NAME	=	"FACEBOOK_ACCESS_TOKENS";
	
	/**
	 * The id property name.
	 */
	public	static	final	String	PROPERTY_ID	=	"FTOK_ID";
	
	/**
	 * The facebook account property name.
	 */
	public	static	final	String	PROPERTY_FACEBOOK_ACCOUNT	=	FacebookAccountConstants.PROPERTY_ID;
	
	/**
	 * The value property name.
	 */
	public	static	final	String	PROPERTY_VALUE	=	"FTOK_VALUE";
	
	/**
	 * The id property length.
	 */
	public	static	final	int	LENGTH_ID	=	20;
	
	/**
	 * The value property length.
	 */
	public	static	final	int	LENGTH_VALUE	=	250;
}
