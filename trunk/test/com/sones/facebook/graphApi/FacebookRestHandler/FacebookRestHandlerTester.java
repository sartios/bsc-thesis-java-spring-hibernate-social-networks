package com.sones.facebook.graphApi.FacebookRestHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FacebookRestHandlerTester 
{
	private IFacebookRestHandler handler;
	private	String sourceId = "100000866964787";
	private	String token = "access_token=AAACV6ZAIZClUQBAIUWj1gLZCZCFmkFugI4MLsBIv5MbBybzYTVTmFeWzd4LVQflYG9s50RA3GcOE771VVGe8HrMT7jHZBuNqCC0F4Ppc4CQZDZD";
	
	@Before
	public	void	SetUp()
	{
		handler = new FacebookRestHandler();
	}
	
	@After
	public	void	TearDown()
	{
		handler = null;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	TestGetWallNullSourceId()
	{
		handler.GetWall(null, "a token");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	TestGetWallEmptySourceId()
	{
		handler.GetWall("", "a token");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	TestGetWallNullToken()
	{
		handler.GetWall("a user", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	TestGetWallEmptyToken()
	{
		handler.GetWall("a user", "");
	}
	
	@Test
	public	void	TestGetWall()
	{
		assertFalse("Returned string is empty", handler.GetWall(sourceId, token).isEmpty());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	TestGetWallWithDateNullDate()
	{
		handler.GetWall("1", "a token",null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	TestGetWallWithDateEmptyDate()
	{
		handler.GetWall("1", "a token","");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	TestGetWallWithDateNullSourceId()
	{
		handler.GetWall(null, "a token","0");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	TestGetWallWithDateEmptySourceId()
	{
		handler.GetWall("", "a token","0");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	TestGetWallWithDateNullToken()
	{
		handler.GetWall("a user", null,"0");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	TestGetWallWithDateEmptyToken()
	{
		handler.GetWall("a user", "","0");
	}
	
	@Test
	public	void	TestGetWallWithDate()
	{
		assertFalse("Returned string is empty", handler.GetWall(sourceId, token,"0").isEmpty());
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void TestGetPublicPlacesEmptyCriteria()
	{
		handler.GetPublicPlaces("", "token");
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void TestGetPublicPlacesEmptyToke()
	{
		handler.GetPublicPlaces("Criteria", "");
	}
	
	@Test
	public void TestGetPublicPlaces()
	{
		String jsonString = handler.GetPublicPlaces("coffee", "access_token=AAAAAAITEghMBAKQyyM0xgEoLpy2WjA4v6Cd5OHwLf4OhV6o2ZBw2BN4ZCZC0KU9cyQEwIbWJIhiPyZAJkp02n1Q543ZCZBuUE0HZByp0eK6v7vJFH6QOve4");
		assertFalse( jsonString.isEmpty() );
	}
	
	@Test
	public void TestGetFacebookAccountCorrectly()
	{
		String jsonString = handler.GetFacebookAccount(token);
		assertFalse( jsonString.isEmpty() );
	}
}
