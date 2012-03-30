package com.sones.facebook.graphApi.FacebookRestHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FacebookRestHandlerTester 
{
	private IFacebookRestHandler handler;
	private	String sourceId = "100000866964787";
	private	String token = "access_token=AAAAAAITEghMBACz1DuIOk2aTEbNlLO63snOV3J4NEe9nP2xPSj2U3LQK0GHq7OBCJTciDSTIkseUagRSUnCFaGXhK9fJOKRL9BieOFUubkRd9UUA";
	
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
}
