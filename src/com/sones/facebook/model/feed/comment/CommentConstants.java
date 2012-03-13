package com.sones.facebook.model.feed.comment;

import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.FacebookPostConstants;
import com.sones.facebook.model.source.UserConstants;

/**
 * Contains property names and lengths of {@link Comment} entity.
 * @author sartios.sones@gmail.com.
 *
 */
public final class CommentConstants 
{
	/**
	 * The table name.
	 */
	public	static	final	String	TABLE_NAME	=	"COMMENTS";
	
	/**
	 * The name of comment id.
	 */
	public	static	final	String	PROPERTY_COMMENT_ID	=	"COMM_ID";
	
	/**
	 * The name of the comment message property.
	 */
	public	static	final	String	PROPERTY_MESSAGE	=	"COMM_MESSAGE";
	
	/**
	 * The name of the user who published the comment.
	 */
	public	static	final	String	PROPERTY_USER	=	UserConstants.PROPERTY_ID;
	
	/**
	 * The name of the created date.
	 */
	public	static	final	String	PROPERTY_CREATED_DATE	=	"COMM_CREATED_DATE";
	
	/**
	 * The name of the post property.
	 */
	public	static	final	String	PROPERTY_FACEBOOK_POST	=	FacebookPostConstants.PROPERTY_ID;
	
	/**
	 * The length of comment id.
	 */
	public	static	final	int	LENGTH_COMMENT_ID	=	50;
	
	/**
	 * The length of the comment message.
	 */
	public	static	final	int	LENGTH_COMMENT_MESSAGE	=	255;
	
	/**
	 * The length of the user id
	 */
	public	static	final	int	LENGTH_USER_ID	=	UserConstants.LENGTH_ID;
	
	/**
	 * The length of the post id
	 */
	public	static	final	int	LENGTH_FACEBOOK_POST_ID	=	FacebookPostConstants.LENGTH_ID;
}
