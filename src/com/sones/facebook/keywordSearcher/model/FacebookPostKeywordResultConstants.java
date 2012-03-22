package com.sones.facebook.keywordSearcher.model;

import com.sones.facebook.model.feed.FacebookPostConstants;
import com.sones.userManager.model.ApplicationUserConstants;

/**
 * Contains property names and lengths for {@link FacebookPostKeywordResult} model.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public final class FacebookPostKeywordResultConstants 
{
	/**
	 * The name of the table.
	 */
	public	static	final	String	TABLE_NAME	=	"FACEBOOK_POST_KEYWORD_RESULTS";
	
	/**
	 * The id property name.
	 */
	public	static	final	String	PROPERTY_ID	=	"FKRE_ID";
	
	/**
	 * The keyword property name.
	 */
	public	static	final	String	PROPERTY_KEYWORD	=	KeywordConstants.PROPERTY_ID;
	
	/**
	 * The facebook post property name.
	 */
	public	static	final	String	PROPERTY_FACEBOOK_POST	=	FacebookPostConstants.PROPERTY_ID;
	
	/**
	 * The keyword search property name.
	 */
	public	static	final	String	PROPERTY_KEYWORD_SEARCH	=	KeywordSearchConstants.PROPERTY_ID;
	
	/**
	 * The application user property name.
	 */
	public	static	final	String	PROPERTY_APPLICATION_USER	=	ApplicationUserConstants.PROPERTY_ID;
	
	/**
	 * The id property length.
	 */
	public	static	final	int	LENGTH_ID	=	20;
}
