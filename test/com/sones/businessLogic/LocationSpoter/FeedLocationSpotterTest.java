package com.sones.businessLogic.LocationSpoter;

import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.FacebookCapitalWordSearchableFeed;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeed;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeeds;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.CapitalizedFacebookComment_old;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.CapitalizedFacebookComments_old;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComment;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComments;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.CapitalizedFacebookStatusMessage;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.ICapitalWordsSearchableContent;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComment;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.ILocatedFeed;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.ILocatedFeeds;
import com.sones.businessLogic.LocationSpoter.Locations.ILocation;

import	static	org.junit.Assert.*;
/**
 * Provides methods for testing {@link #FeedLocationSpotter}.
 * Each feed returns a collection of words that start with upper case letter.
 * Then it should search if these words are locations and relates the locations with feeds.
 * 
 * @author Savramis	Sartios
 *
 */
public class FeedLocationSpotterTest 
{
	/**
	 * Spots locations into feeds
	 */
	private	FeedLocationSpotter	_locationSpotter;
	
	/**
	 * Initiates the objects.
	 */
	@Before
	public	void	SetUp()
	{
		_locationSpotter	=	new	FeedLocationSpotter();
	}
	
	/**
	 * Clears up the objects.
	 */
	@After
	public	void	TearDown()
	{
		_locationSpotter	=	null;
	}
	
	/**
	 * Tests if can find the locations that exist into feeds.
	 * Each feed contains one location.
	 */
	@Test
	public	void	GetFeedsWithLocations_FeedContainsALocation_Test()
	{
		ICapitalWordsSearchableFeed	feed	=	GetFeedWithLocations("Sarti","Sarti");
		ILocatedFeed	locatedFeed	=	_locationSpotter.GetFeedWithLocations(feed);
		Iterator< ILocation >	iterator	=	locatedFeed.GetLocationsIterator();
		Assert.assertTrue( iterator.hasNext() );
	}
	
	/**
	 * Tests if can find the locations that exist into feeds.
	 * Each feed contains more than one location.
	 */
	@Test
	public	void	GetFeedsWithLocations_FeedContainsLocations_Test()
	{
		ICapitalWordsSearchableFeed	feed	=	GetFeedWithLocations("Sarti","Thessaloniki");
		ILocatedFeed	locatedFeed	=	_locationSpotter.GetFeedWithLocations(feed);
		Iterator< ILocation >	iterator	=	locatedFeed.GetLocationsIterator();
		Assert.assertTrue( iterator.hasNext() );
	}
	
	/**
	 * Tests if can find the locations that exist into feeds.
	 * Each feed contains one location, but exist more locations with same name.
	 */
	@Test
	public	void	GetFeedsWithLocations_FeedDoesNotContainLocations_Test()
	{
		ICapitalWordsSearchableFeed	feed	=	GetFeedWithLocations("blabla","something");
		ILocatedFeed	locatedFeed	=	_locationSpotter.GetFeedWithLocations(feed);
		Iterator< ILocation >	iterator	=	locatedFeed.GetLocationsIterator();
		Assert.assertFalse( iterator.hasNext() );
	}
	
	/**
	 * Tests if the location that is returned is the same with the input.
	 */
	@Test
	public	void	GetFeedsWithLocations_CheckFeedLocation_Test()
	{
		ICapitalWordsSearchableFeed	feed	=	GetFeedWithLocations("Sarti","Sarti");
		ILocatedFeed	locatedFeed	=	_locationSpotter.GetFeedWithLocations(feed);
		Iterator< ILocation >	iterator	=	locatedFeed.GetLocationsIterator();
		ILocation	location	=	iterator.next();
		Assert.assertEquals(location.GetLocationName(), "Sarti");
	}
	
	@Test
	public	void	GetFeedsWithLocations_Test()
	{
		ILocatedFeeds	feeds	=	_locationSpotter.GetFeedsWithLocations("Mon Nov 14 22:10:20 EET 2011", "Mon Nov 14 22:47:00 EET 2011");
		assertNotNull(feeds);
	}
	
	
	private	ICapitalWordsSearchableFeed	GetFeedWithLocations( String content, String comment )
	{
		ICapitalWordsSearchableFeed	feed	=	new	FacebookCapitalWordSearchableFeed();
		((FacebookCapitalWordSearchableFeed)feed).SetComments(GetComments( comment ));
		((FacebookCapitalWordSearchableFeed)feed).SetContent(GetContent(content));
		return	feed;
	}
	
	private	ICapitalWordsSearchableContent	GetContent( String content )
	{
		ICapitalWordsSearchableContent	_content	=	new	CapitalizedFacebookStatusMessage();
		((CapitalizedFacebookStatusMessage)_content).SetMessage(content);
		return	_content;
	}
	
	private	ICapitalWordsSearchableComments	GetComments( String content )
	{
		ICapitalWordsSearchableComment	comment	=	new	CapitalizedFacebookComment_old();
		((CapitalizedFacebookComment_old)comment).SetMessage( content );
		ICapitalWordsSearchableComments	comments	=	new	CapitalizedFacebookComments_old();
		((CapitalizedFacebookComments_old)comments).AddComment( (IFacebookComment) comment );
		return	comments;
	}
	
	private	void	fail()
	{
		assertTrue(false);
	}
}
