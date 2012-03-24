package com.sones.facebook.downloader.model;

import com.sones.userManager.model.ApplicationUserConstants;

/**
 * Contains properties name and length for {@link FacebookDownload} model.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public final class FacebookDownloadConstants 
{
	/**
	 * The table name property.
	 */
	public	static	final	String	TABLE_NAME	=	"FACEBOOK_DOWNLOADS";
	
	/**
	 * The id property name.
	 */
	public	static	final	String	PROPERTY_ID	=	"FCDW_ID";
	
	/**
	 * The date property name.
	 */
	public	static	final	String	PROPERTY_DATE	=	"FCDW_DATE";
	
	public	static	final	String	PROPERTY_APPLICATION_USER	=	ApplicationUserConstants.PROPERTY_ID;
	
	/**
	 * The id property length.
	 */
	public	static	final	int	LENGTH_ID	=	20;
}
