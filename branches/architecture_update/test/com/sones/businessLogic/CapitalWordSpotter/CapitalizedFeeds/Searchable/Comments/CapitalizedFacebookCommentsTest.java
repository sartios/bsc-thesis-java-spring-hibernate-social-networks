package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments;

import static org.junit.Assert.assertEquals;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.CapitalizedFacebookComment_old;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.CapitalizedFacebookComments_old;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComments;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.FacebookComments;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComment;

public class CapitalizedFacebookCommentsTest
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
	private	ICapitalWordsSearchableComments _comments;
	
	/**
	 * Initializes the configuration file reader and the logger.
	 * @param clazz the actual class
	 */
	public CapitalizedFacebookCommentsTest()
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
		IFacebookComment comment	=	new	CapitalizedFacebookComment_old();
		
		((IFacebookComment)comment).SetMessage( _config.getString( "comment.message" ) );
		
		_comments	=	new	CapitalizedFacebookComments_old();
		
		for( int index = 0; index < 3; index++ )
		{
			( ( CapitalizedFacebookComments_old )_comments ).AddComment( comment );
		}
		
		org.apache.log4j.Logger.getLogger(CapitalizedFacebookComments_old.class).error("Comment number: "+((FacebookComments)_comments).GetSize());
	}
	
	/**
	 * Cleans up the status message
	 */
	@After
	public	void	TearDown()
	{
		_comments	=	null;
	}
	
	/**
	 * Tests if picture finds the correct number of words that start with capital letter.
	 */
	@Test
	public	void	GetCapitalWords_CheckTheNumberOfCapitaWords_Test()
	{
		assertEquals(_comments.GetCapitalWords().size(), 3);
	}
}
