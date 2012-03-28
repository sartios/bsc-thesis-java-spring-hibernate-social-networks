package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import com.sones.facebook.model.feed.Note;

/**
 * Search dto for {@link Note} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class NoteSearchDto extends FacebookPostSearchDto	implements	ISearchableFacebookFeed
{
	/**
	 * The note message.
	 */
	private	String	message;
	
	/**
	 * The note subject.
	 */
	private	String	subject;
	
	/**
	 * Initializes the object.
	 */
	public NoteSearchDto()
	{
		
	}
	
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * Returns true if note contains the value. It's not case sensitive.
	 * @param value
	 * @return True if note contains the value.
	 */
	@Override
	public	boolean	contains( String value )
	{
		boolean	noteContainsTheKeyword	=	false;
		String	keywordValue	=	value.toLowerCase();
		if( subject != null )
		{
			if( this.subject.toLowerCase().contains( keywordValue ) )
			{
				noteContainsTheKeyword	=	true;
			}
		}
		if( message != null )
		{
			if( this.message.toLowerCase().contains( keywordValue ) )
			{
				noteContainsTheKeyword	=	true;
			}
		}
		return	noteContainsTheKeyword;
	}
}
