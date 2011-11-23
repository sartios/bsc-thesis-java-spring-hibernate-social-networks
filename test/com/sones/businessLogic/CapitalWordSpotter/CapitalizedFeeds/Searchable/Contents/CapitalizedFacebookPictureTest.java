package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.CapitalizedFacebookPicture;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.ICapitalWordsSearchableContent;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookPicture;

/**
 * Provides methods for testing {@link CapitalizedFacebookPicture}
 * 
 * @author Savramis Sartios
 *
 */
public class CapitalizedFacebookPictureTest extends	CapitalizedFacebookContentTest
{
	/**
	 * The picture that maybe contains capital letters
	 */
	private	ICapitalWordsSearchableContent _picture;
	
	/**
	 * Initializes the base class
	 */
	public	CapitalizedFacebookPictureTest()
	{
		super(CapitalizedFacebookPictureTest.class);
	}
	
	/**
	 * Initializes and set data to the facebook picture
	 */
	@Before
	public	void	SetUp()
	{
		_picture	=	new	CapitalizedFacebookPicture();
		
		((IFacebookPicture)_picture).SetMessage( GetProperty( "picture.message" ) );
		((IFacebookPicture)_picture).SetCaption( GetProperty( "picture.caption" ) );
	}
	
	/**
	 * Cleans up the status message
	 */
	@After
	public	void	TearDown()
	{
		_picture	=	null;
	}
	
	/**
	 * Tests if picture finds the correct number of words that start with capital letter.
	 */
	@Test
	public	void	GetCapitalWords_CheckTheNumberOfCapitaWords_Test()
	{
		assertEquals(_picture.GetCapitalWords().size(), 2);
	}
}
