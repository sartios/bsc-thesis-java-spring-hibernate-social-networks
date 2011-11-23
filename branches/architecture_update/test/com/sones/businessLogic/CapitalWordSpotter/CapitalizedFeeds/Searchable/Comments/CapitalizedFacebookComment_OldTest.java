package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments;

import static org.junit.Assert.assertEquals;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.CapitalizedFacebookComment_old;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComment;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComment;

/**
 * Provides methods for testing if Facebook comment can spots words
 * that start with an upper case letter.
 * 
 * @author Savramis Sartios
 *
 */
public class CapitalizedFacebookComment_OldTest 
{
	/**
	 * Accesses the configuration file
	 */
	private	PropertiesConfiguration	_config;
	
	/**
	 * Loggs the messages
	 */
	private	Logger	_logger;
	
	/**
	 * The picture that maybe contains capital letters
	 */
	private	ICapitalWordsSearchableComment _comment;
	
	/**
	 * Initializes the configuration file reader and the logger.
	 * @param clazz the actual class
	 */
	public CapitalizedFacebookComment_OldTest()
	{
		_logger	=	Logger.getLogger( CapitalizedFacebookComment_OldTest.class );
		try 
		{
			_config	=	new	PropertiesConfiguration("com/sones/businessLogic/LocationSpoter/CapitalizedFeeds/Searchable/Comments/comment.properties");
		} 
		catch (ConfigurationException e) 
		{
			_logger.error( e.getMessage() );
		}
	}
	
	/**
	 * Initializes and set data to the facebook picture
	 */
	@Before
	public	void	SetUp()
	{
		_comment	=	new	CapitalizedFacebookComment_old();
		
		((IFacebookComment)_comment).SetMessage( _config.getString( "comment.message" ) );
	}
	
	/**
	 * Cleans up the status message
	 */
	@After
	public	void	TearDown()
	{
		_comment	=	null;
	}
	
	/**
	 * Tests if picture finds the correct number of words that start with capital letter.
	 */
	@Test
	public	void	GetCapitalWords_CheckTheNumberOfCapitaWords_Test()
	{
		assertEquals(_comment.GetCapitalWords().size(), 1);
	}
}
