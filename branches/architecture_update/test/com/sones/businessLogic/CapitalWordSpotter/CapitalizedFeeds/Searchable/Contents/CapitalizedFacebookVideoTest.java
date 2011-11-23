package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.CapitalizedFacebookVideo;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.ICapitalWordsSearchableContent;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookVideo;

/**
 * Provides methods to test the class CapitalizedFacebookVideo.
 * 
 * @author Savramis Sartios
 *
 */
public class CapitalizedFacebookVideoTest extends CapitalizedFacebookContentTest
{
	/**
	 * The video that maybe contains capital letters
	 */
	private	ICapitalWordsSearchableContent _video;
	
	/**
	 * Initializes the base class
	 */
	public	CapitalizedFacebookVideoTest()
	{
		super(CapitalizedFacebookVideoTest.class);
	}
	
	/**
	 * Initializes and set data to the facebook video
	 */
	@Before
	public	void	SetUp()
	{
		_video	=	new	CapitalizedFacebookVideo();
		
		((IFacebookVideo)_video).setCaption( GetProperty( "video.caption" ) );
	}
	
	/**
	 * Cleans up the status message
	 */
	@After
	public	void	TearDown()
	{
		_video	=	null;
	}
	
	/**
	 * Tests if the status message finds the correct number of words that start with capital letter.
	 */
	@Test
	public	void	GetCapitalWords_CheckTheNumberOfCapitaWords_Test()
	{
		assertEquals(_video.GetCapitalWords().size(), 3);
	}
}