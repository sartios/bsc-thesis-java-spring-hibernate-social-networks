package com.sones.facebook.keywordSearcher.model;

import com.sones.userManager.model.ApplicationUserConstants;

/**
 * Contains property names and lengths of {@link KeywordSearch} model.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public class KeywordSearchConstants 
{
	/**
	 * The table name.
	 */
	public	static	final	String	TABLE_NAME	=	"KEYWORD_SEARCHES";
	
	/**
	 * The id property name.
	 */
	public	static	final	String	PROPERTY_ID	=	"KESE_ID";
	
	/**
	 * The date property name.
	 */
	public	static	final	String	PROPERTY_DATE	=	"KESE_DATE";
	
	/**
	 * The result property name.
	 */
	public	static	final	String	PROPERTY_RESULT	=	FacebookPostKeywordResultConstants.PROPERTY_ID;
	
	/**
	 * The application user property name.
	 */
	public	static	final	String	PROPERTY_APPLICATION_USER	=	ApplicationUserConstants.PROPERTY_ID;

	/**
	 * The id property length.
	 */
	public	static	final	int	LENGTH_ID	=	20;
}
