package com.sones.facebook.model.feed;

/**
 * Contains property names and lenghts of a {@link FacebookPost} entity.
 * @author sartios.sones@gmail.com.
 *
 */
public final class FacebookPostConstants 
{
	/**
	 * The table name.
	 */
	public	static	final	String	TABLE_NAME	=	"FACEBOOK_POSTS";
	
	/**
	 * The ID of a facebook post.
	 */
	public	static	final	String	PROPERTY_ID	=	"FCBK_POST_ID";
	
	/**
	 * The created date a facebook post.
	 */
	public	static	final	String	PROPERTY_CREATED_DATE	=	"FCBK_POST_CREATED_DATE";
	
	/**
	 * The user who published the facebook post.
	 */
	public	static	final	String	PROPERTY_USER	=	"FCBK_POST_USER_ID";
	
	/**
	 * The length of the ID.
	 */
	public	static	final	int	LENGTH_ID	=	255;
}
