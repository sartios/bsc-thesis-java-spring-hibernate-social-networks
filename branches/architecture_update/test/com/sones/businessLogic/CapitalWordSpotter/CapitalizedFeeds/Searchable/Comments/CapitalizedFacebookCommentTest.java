package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments;

import static org.junit.Assert.assertEquals;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CapitalizedFacebookCommentTest
{
	/**
	 * Accesses the configuration file
	 */
	private	XMLConfiguration	_config;
	
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
	public CapitalizedFacebookCommentTest()
	{
		_logger	=	Logger.getLogger( CapitalizedFacebookCommentTest.class );
		try 
		{
			_config	=	new	XMLConfiguration("config/Test/comment.xml");
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
		_comment	=	new	CapitalizedFacebookComment();
		_logger.warn(_config.getString( "comment.message" ) );
		((CapitalizedFacebookComment)_comment).SetMessage( _config.getString( "comment.message" ) );
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
