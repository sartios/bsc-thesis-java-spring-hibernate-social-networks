package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import	static	org.junit.Assert.*;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.CapitalizedFacebookLink;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.ICapitalWordsSearchableContent;
import com.sones.businessLogic.Facebook.FeedManager.ILinkContent;

public class CapitalizedFacebookLinkTest 
{
	private	ICapitalWordsSearchableContent _link;
	private	PropertiesConfiguration	_config;
	private	Logger	_logger;
	
	public CapitalizedFacebookLinkTest()
	{
		_logger	=	Logger.getLogger( CapitalizedFacebookLinkTest.class );
		try 
		{
			_config	=	new	PropertiesConfiguration("com/sones/businessLogic/LocationSpoter/CapitalizedFeeds/Searchable/Contents/CapitalizedFacebookContentTest.properties");
		} 
		catch (ConfigurationException e) 
		{
			_logger.error( e.getMessage() );
		}
	}
	
	@Before
	public	void	SetUp()
	{
		_link	=	new	CapitalizedFacebookLink();
		
		((ILinkContent)_link).SetCaption( _config.getString("caption") );
		((ILinkContent)_link).SetDescription( _config.getString("description") );
		((ILinkContent)_link).SetLinkURL( _config.getString("linkURL") );
		((ILinkContent)_link).SetMessage( _config.getString("message") );
		((ILinkContent)_link).SetName( _config.getString("name") );
		((ILinkContent)_link).SetPhotoURL( _config.getString("photoURL") );
	}
	
	@After
	public	void	TearDown()
	{
		_link	=	null;
	}
	
	@Test
	public	void	GetCapitalWords_CheckTheNumberOfCapitaWords_Test()
	{
		assertEquals(_link.GetCapitalWords().size(), 5);
	}
	
}
