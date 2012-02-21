package com.sones.facebook.model.feed.comment;

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
	public	static	final	String	PROPERTY_USER	=	"COMM_USER";
	
	/**
	 * The name of the created date.
	 */
	public	static	final	String	PROPERTY_CREATED_DATE	=	"COMM_CREATED_DATE";
	
	/**
	 * The length of comment id.
	 */
	public	static	final	int	LENGTH_COMMENT_ID	=	50;
	
	/**
	 * The length of the comment message.
	 */
	public	static	final	int	LENGTH_COMMENT_MESSAGE	=	255;
}
