package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.FacebookCapitalWordSearchableFeed;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeed;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.CapitalizedFacebookComment_old;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.CapitalizedFacebookComments_old;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComment;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComments;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.CapitalizedFacebookStatusMessage;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.ICapitalWordsSearchableContent;

import configuration.setting.manager.TestConfigurationFilePathFinder;
import	static	org.junit.Assert.*;

/**
 * Provides methods for testing	{@link #FacebookCapitalWordSearchableFeedTest()}
 * 
 * @author Savramis Sartios
 *
 */
public class FacebookCapitalWordSearchableFeedTest 
{
	/**
	 * A Facebook feed that can search for capital words into itself.
	 */
	private	ICapitalWordsSearchableFeed	_feed;
	
	/**
	 * A property configuration file handler.
	 */
	private	PropertiesConfiguration _config;
	
	/**
	 * Initiates the properties of the class.
	 */
	public	FacebookCapitalWordSearchableFeedTest()
	{
		TestConfigurationFilePathFinder	pathFinder	=	new	TestConfigurationFilePathFinder();
		_config	=	new	PropertiesConfiguration();
		try 
		{
			_config.load(pathFinder.GetConfigurationFilePath("FacebookFeed"));
		}
		catch (ConfigurationException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Initiates the facebook feed.
	 */
	@Before
	public	void	SetUp()
	{
		_feed	=	new	FacebookCapitalWordSearchableFeed();
	}
	
	/**
	 * Cleans up the facebook feed.
	 */
	@After
	public	void	TearDown()
	{
		_feed	=	null;
	}
	
	/**
	 * Tests capital word finding when feed has capital words only into content.
	 */
	@Test
	public	void	GetCapitalWords_ContentHasCapitalWords_Test()
	{
		( ( FacebookCapitalWordSearchableFeed )_feed ).SetContent(GetContentWithCapitals());
		( ( FacebookCapitalWordSearchableFeed )_feed ).SetComments(GetCommentsWithoutCapitals());
		
		assertTrue( _feed.GetCapitalWords().GetSize() > 0 );
	}
	
	/**
	 * Tests capital word finding when Facebook feed has capital words only into comments.
	 */
	@Test
	public	void	GetCapitalWords_CommentsHasCapitalWords_Test()
	{
		( ( FacebookCapitalWordSearchableFeed )_feed ).SetContent(GetContentWithoutCapitals());
		( ( FacebookCapitalWordSearchableFeed )_feed ).SetComments(GetCommentsWithCapitals());
		
		assertTrue( _feed.GetCapitalWords().GetSize() > 0 );
	}
	
	/**
	 * Tests capital word finding for a Facebook feed that has capital words into its content and comments.
	 */
	@Test
	public	void	GetCapitalWords_CommentsAndContentHaveCapitalWords_Test()
	{
		( ( FacebookCapitalWordSearchableFeed )_feed ).SetContent(GetContentWithCapitals());
		( ( FacebookCapitalWordSearchableFeed )_feed ).SetComments(GetCommentsWithCapitals());
		
		assertTrue( _feed.GetCapitalWords().GetSize() > 0 );
	}
	
	/**
	 * Tests capital word finding for a Facebook feed that has not capital words into its content and comments.
	 */
	@Test
	public	void	GetCapitalWords_CommentsAndContentHaveNotCapitalWords_Test()
	{
		( ( FacebookCapitalWordSearchableFeed )_feed ).SetContent(GetContentWithoutCapitals());
		( ( FacebookCapitalWordSearchableFeed )_feed ).SetComments(GetCommentsWithoutCapitals());
		
		assertTrue( _feed.GetCapitalWords().GetSize() == 0 );
	}
	
	private	ICapitalWordsSearchableComments	GetCommentsWithCapitals()
	{
		ICapitalWordsSearchableComment	comment	=	new	CapitalizedFacebookComment_old();
		String	message	=	"A Message With Capital Letters.";
		((CapitalizedFacebookComment_old)comment).SetMessage(message);
		
		ICapitalWordsSearchableComments	comments	=	new	CapitalizedFacebookComments_old();
		comments.AddComment(comment);
		
		return comments;
	}
	
	private	ICapitalWordsSearchableContent	GetContentWithCapitals()
	{
		CapitalizedFacebookStatusMessage	status	=	new	CapitalizedFacebookStatusMessage();
		status .SetMessage("A Message With Capital Letters.");
		return	status;
	}
	
	private	ICapitalWordsSearchableComments	GetCommentsWithoutCapitals()
	{
		ICapitalWordsSearchableComment	comment	=	new	CapitalizedFacebookComment_old();
		String	message	=	"a message without capital letters.";
		comment.SetMessage(message);
		CapitalizedFacebookComments_old	comments	=	new	CapitalizedFacebookComments_old();
		comments.AddComment(comment);
		
		return comments;
	}
	
	private	ICapitalWordsSearchableContent	GetContentWithoutCapitals()
	{
		CapitalizedFacebookStatusMessage	status	=	new	CapitalizedFacebookStatusMessage();
		status .SetMessage("a message without capital letters.");
		return	status;
	}
}
