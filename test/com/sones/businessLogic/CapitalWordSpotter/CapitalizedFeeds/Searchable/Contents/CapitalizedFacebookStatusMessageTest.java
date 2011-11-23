package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.CapitalizedFacebookStatusMessage;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.ICapitalWordsSearchableContent;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookStatusMessage;

/**
 * Provides methods to test the class CapitalizedFacebookStatusMessage.
 * 
 * @author Savramis Sartios
 *
 */
public class CapitalizedFacebookStatusMessageTest extends CapitalizedFacebookContentTest
{
	/**
	 * The status message that maybe contains capital letters
	 */
	private	ICapitalWordsSearchableContent _statusMessage;
	
	/**
	 * Initializes the base class
	 */
	public	CapitalizedFacebookStatusMessageTest()
	{
		super(CapitalizedFacebookStatusMessageTest.class);
	}
	
	/**
	 * Initializes and set data to the facebook status message
	 */
	@Before
	public	void	SetUp()
	{
		_statusMessage	=	new	CapitalizedFacebookStatusMessage();
		
		((IFacebookStatusMessage)_statusMessage).SetMessage( GetProperty( "status.message" ) );
	}
	
	/**
	 * Cleans up the status message
	 */
	@After
	public	void	TearDown()
	{
		_statusMessage	=	null;
	}
	
	/**
	 * Tests if the status message finds the correct number of words that start with capital letter.
	 */
	@Test
	public	void	GetCapitalWords_CheckTheNumberOfCapitaWords_Test()
	{
		assertEquals(_statusMessage.GetCapitalWords().size(), 2);
	}
}
