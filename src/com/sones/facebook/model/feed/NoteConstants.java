package com.sones.facebook.model.feed;

/**
 * Contains the property names and lengths for the {@link Note} entity.
 * @author sartios.sones@gmail.com.
 *
 */
public final class NoteConstants 
{
	/**
	 * The name of the table.
	 */
	public	static	final	String	TABLE_NAME	=	"NOTES";
	
	/**
	 * The name of subject property.
	 */
	public	static	final	String	PROPERTY_SUBJECT	=	"NOTE_SUBJECT";
	
	/**
	 * The name of the note message property.
	 */
	public	static	final	String	PROPERTY_MESSAGE	=	"NOTE_MESSAGE";
	
	/**
	 * The length of the place id property.
	 */
	public	static	final	int	LENGTH_SUBJECT	=	255;
	
	/**
	 * The length of the message property.
	 */
	public	static	final	int	LENGTH_MESSAGE	=	255;
}
