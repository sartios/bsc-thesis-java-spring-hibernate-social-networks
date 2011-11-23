package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.CapitalizedFacebookNote;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.ICapitalWordsSearchableContent;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookNote;

/**
 * Provides methods for testing {@link CapitalizedFacebookNote}
 * 
 * @author Savramis Sartios
 *
 */
public class CapitalizedFacebookNoteTest extends	CapitalizedFacebookContentTest
{
	/**
	 * The note that maybe contains capital letters
	 */
	private	ICapitalWordsSearchableContent _note;
	
	/**
	 * Initializes the base class
	 */
	public	CapitalizedFacebookNoteTest()
	{
		super(CapitalizedFacebookNoteTest.class);
	}
	
	/**
	 * Initializes and set data to the facebook note
	 */
	@Before
	public	void	SetUp()
	{
		_note	=	new	CapitalizedFacebookNote();
		
		((IFacebookNote)_note).SetMessage( GetProperty( "note.message" ) );
		((IFacebookNote)_note).SetSubject( GetProperty( "note.subject" ) );
	}
	
	/**
	 * Cleans up the status message
	 */
	@After
	public	void	TearDown()
	{
		_note	=	null;
	}
	
	/**
	 * Tests if the note finds the correct number of words that start with capital letter.
	 */
	@Test
	public	void	GetCapitalWords_CheckTheNumberOfCapitaWords_Test()
	{
		assertEquals(_note.GetCapitalWords().size(), 3);
	}
}
