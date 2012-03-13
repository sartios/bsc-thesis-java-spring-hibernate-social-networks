package com.sones.facebook.model.feed;

/**
 * Contains the property names and lengths for the {@link Checkin} entity.
 * @author sartios.sones@gmail.com.
 *
 */
public final class CheckinConstants 
{
	/**
	 * The name of the table.
	 */
	public	static	final	String	TABLE_NAME	=	"CHECKINS";
	
	/**
	 * The name of checkin place id property.
	 */
	public	static	final	String	PROPERTY_PLACE	=	"CHEC_PLACE_ID";
	
	/**
	 * The name of the check in message property.
	 */
	public	static	final	String	PROPERTY_MESSAGE	=	"CHEC_MESSAGE";
	
	/**
	 * The length of the place id property.
	 */
	public	static	final	int	LENGTH_PLACE_ID	=	255;
	
	/**
	 * The length of the message property.
	 */
	public	static	final	int	LENGTH_MESSAGE	=	255;
}
